package com.samy.j_nytimes.domain.entities;

import java.util.Objects;

public class NewsArticle {
    private String title;
    private String author;
    private String summary;
    private String date;
    private String imageUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsArticle that = (NewsArticle) o;
        return Objects.equals(title, that.title) && Objects.equals(author, that.author) && Objects.equals(summary, that.summary) && Objects.equals(date, that.date) && Objects.equals(imageUrl, that.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, summary, date, imageUrl);
    }

    public NewsArticle(String title, String author, String summary, String date, String imageUrl) {
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.date = date;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}