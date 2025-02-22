package com.samy.j_nytimes.presentation.news_screen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

//import com.bumptech.glide.load.engine.Resource;
//import com.bumptech.glide.load.engine.Resource;
import com.samy.j_nytimes.domain.entities.NewsArticle;
import com.samy.j_nytimes.domain.repository.NewsRepository;
import com.samy.j_nytimes.utils.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class NewsViewModel extends ViewModel {
    private final NewsRepository repository;
    private final MutableLiveData<Resource<List<NewsArticle>>> _newsArticles = new MutableLiveData<>();
    public LiveData<Resource<List<NewsArticle>>> newsArticles = _newsArticles;

    @Inject
    public NewsViewModel(NewsRepository repository) {
        this.repository = repository;
        loadNews();
    }

    private void loadNews() {
        repository.getMostPopularNews("seK0AFEPwKbuArKaL0vYwuqAJFgRsAai")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        articles -> _newsArticles.setValue(Resource.success( articles)),
                        error -> _newsArticles.setValue(Resource.error(error.getMessage()))
                );
    }
}