package com.samy.j_nytimes.data.repositorirs;

import com.samy.j_nytimes.data.datasource.NewsApiService;
import com.samy.j_nytimes.domain.entities.NewsArticle;
import com.samy.j_nytimes.domain.repository.NewsRepository;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.HttpException;

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
                .map(newsResponse -> {
                    if (newsResponse == null)
                        throw new IllegalStateException("News response is null");
                    if (newsResponse.getResponse() == null)
                        throw new IllegalStateException("News response body is null");
                    if (newsResponse.getResponse().getDocs() == null)
                        return Collections.<NewsArticle>emptyList();
                    List<NewsArticle> articles = newsResponse.getResponse().getDocs().stream()
                            .filter(doc -> doc != null && doc.getHeadline() != null)
                            .map(doc -> new NewsArticle(
                                    doc.getHeadline().getMain(),
                                    doc.getByline() != null ? doc.getByline().getOriginal() : "Unknown Author",
                                    doc.getLeadParagraph(),
                                    doc.getPubDate(),
                                    doc.getMultimedia() != null && !doc.getMultimedia().isEmpty()
                                            ? doc.getMultimedia().get(0).getUrl()
                                            : ""
                            ))
                            .collect(Collectors.toList());

                    return articles;
                })
                .onErrorResumeNext(error -> {
                    // Log the error here if you have a logging system
                    if (error instanceof HttpException) {
                        return Observable.error(error);
                    }
                    return Observable.error(new Exception("Failed to fetch news articles", error));
                });
    }

}