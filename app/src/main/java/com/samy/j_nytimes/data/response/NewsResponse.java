package com.samy.j_nytimes.data.response;

import com.google.gson.annotations.SerializedName;
import com.samy.j_nytimes.domain.entities.NewsArticle;

import java.util.List;

public class NewsResponse {
    @SerializedName("results")
    private List<NewsArticle> articles;

    public List<NewsArticle> getArticles() {
        return articles;
    }
}