package com.samy.j_nytimes.domain.repository;

//import com.bumptech.glide.load.engine.Resource;
import com.samy.j_nytimes.domain.entities.NewsArticle;
import com.samy.j_nytimes.utils.Resource;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface NewsRepository {
    Observable<List<NewsArticle>> getMostPopularNews(String apiKey);
}