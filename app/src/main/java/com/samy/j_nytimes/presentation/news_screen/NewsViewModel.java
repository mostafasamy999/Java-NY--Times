package com.samy.j_nytimes.presentation.news_screen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

//import com.bumptech.glide.load.engine.Resource;
//import com.bumptech.glide.load.engine.Resource;
import com.samy.j_nytimes.domain.entities.NewsArticle;
import com.samy.j_nytimes.domain.repository.NewsRepository;
import com.samy.j_nytimes.domain.usecases.GetMostPopularNewsUseCase;
import com.samy.j_nytimes.utils.AppConstants;
import com.samy.j_nytimes.utils.Resource;

import java.net.UnknownHostException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.HttpException;

@HiltViewModel
public class NewsViewModel extends ViewModel {
    private final GetMostPopularNewsUseCase getMostPopularNewsUseCase;
    private final MutableLiveData<Resource<List<NewsArticle>>> _newsArticles = new MutableLiveData<>();
    public LiveData<Resource<List<NewsArticle>>> newsArticles = _newsArticles;
    private final CompositeDisposable disposables = new CompositeDisposable();
    @Inject
    public NewsViewModel(GetMostPopularNewsUseCase getMostPopularNewsUseCase) {
        this.getMostPopularNewsUseCase = getMostPopularNewsUseCase;
        loadNews();
    }
    private void loadNews() {
        _newsArticles.setValue(Resource.loading());

        disposables.add(getMostPopularNewsUseCase.execute(AppConstants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        articles -> {
                            if (articles.isEmpty()) {
                                _newsArticles.setValue(Resource.error("No articles found"));
                            } else {
                                _newsArticles.setValue(Resource.success(articles));
                            }
                        },
                        error -> {
                            String errorMessage = getErrorMessage(error);
                            _newsArticles.setValue(Resource.error(errorMessage));
                        }
                ));
    }

    private String getErrorMessage(Throwable error) {
        if (error instanceof UnknownHostException) {
            return "No internet connection. Please check your network.";
        } else if (error instanceof HttpException) {
            HttpException httpError = (HttpException) error;
            switch (httpError.code()) {
                case 401:
                    return "API key is invalid or expired";
                case 429:
                    return "Too many requests. Please try again later";
                case 500:
                case 502:
                case 503:
                case 504:
                    return "Server error. Please try again later";
                default:
                    return "Network error: " + httpError.code();
            }
        } else {
            return "An unexpected error occurred: " + error.getMessage();
        }
    }

    public void refreshNews() {
        loadNews();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}