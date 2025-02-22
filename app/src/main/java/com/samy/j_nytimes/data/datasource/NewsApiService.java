package com.samy.j_nytimes.data.datasource;

import com.samy.j_nytimes.data.response.NewsResponse;
import com.samy.j_nytimes.domain.entities.NewsArticle;

import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {
    @GET("search/v2/articlesearch.json/")
    Observable<NewsResponse> getMostPopularArticles(
            @Query("api-key") String apiKey
    );
}