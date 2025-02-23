package com.samy.j_nytimes.domain.usecases;

import com.samy.j_nytimes.domain.entities.NewsArticle;
import com.samy.j_nytimes.domain.repository.NewsRepository;
import com.samy.j_nytimes.utils.AppConstants;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetMostPopularNewsUseCase {
    private final NewsRepository repository;

    @Inject
    public GetMostPopularNewsUseCase(NewsRepository repository) {
        this.repository = repository;
    }

    public Observable<List<NewsArticle>> execute(String apiKey) {
        return repository.getMostPopularNews(apiKey)
                .subscribeOn(Schedulers.io());
    }
}