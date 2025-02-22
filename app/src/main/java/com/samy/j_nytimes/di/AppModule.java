package com.samy.j_nytimes.di;

import com.samy.j_nytimes.data.datasource.NewsApiService;
import com.samy.j_nytimes.data.repositorirs.NewsRepositoryImpl;
import com.samy.j_nytimes.domain.repository.NewsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    private static final String BASE_URL = "https://api.nytimes.com/svc/";
    private static final String API_KEY = "seK0AFEPwKbuArKaL0vYwuqAJFgRsAai";

    @Provides
    @Singleton
    public NewsApiService provideNewsApiService() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(NewsApiService.class);
    }


    @Provides
    @Singleton
    public NewsRepository provideNewsRepository(NewsApiService apiService) {
        return new NewsRepositoryImpl(apiService);
    }
}
