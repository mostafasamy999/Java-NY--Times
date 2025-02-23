package com.samy.j_nytimes.data.datasource;

import com.samy.j_nytimes.data.response.NewsResponse;
import com.samy.j_nytimes.utils.AppConstants;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {
    @GET(AppConstants.ENDPOINT)
    Observable<NewsResponse> getMostPopularArticles(
            @Query("api-key") String apiKey
    );
}