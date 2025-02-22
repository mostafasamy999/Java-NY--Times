package com.samy.j_nytimes.data.repositorirs;

import com.samy.j_nytimes.data.datasource.NewsApiService;
import com.samy.j_nytimes.domain.entities.NewsArticle;
import com.samy.j_nytimes.domain.repository.NewsRepository;
import com.samy.j_nytimes.utils.Resource;
import com.samy.j_nytimes.data.response.NewsResponse;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Observable;

@Singleton
public class NewsRepositoryImpl implements NewsRepository {
    private final NewsApiService apiService;


    @Inject
    public NewsRepositoryImpl(NewsApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<List<NewsArticle>> getMostPopularNews(String apiKey) {
        return apiService.getMostPopularArticles(apiKey)
                .map(NewsResponse::getArticles)
                .onErrorReturnItem(Collections.emptyList()); // Returns an empty list on error
    }
}