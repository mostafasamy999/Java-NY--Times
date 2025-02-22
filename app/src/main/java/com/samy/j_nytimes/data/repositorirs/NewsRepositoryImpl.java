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
                    if (newsResponse.getResponse() == null || newsResponse.getResponse().getDocs() == null) {
                        return Collections.<NewsArticle>emptyList();
                    }
                    return newsResponse.getResponse().getDocs().stream()
                            .map(doc -> new NewsArticle(
                                    doc.getHeadline().getMain(),
                                    doc.getByline().getOriginal(),
                                    doc.getLeadParagraph(),
                                    doc.getPubDate(),
                                    doc.getMultimedia().isEmpty() ? "" : doc.getMultimedia().get(0).getUrl()
                            ))
                            .collect(Collectors.toList());
                })
                .onErrorReturnItem(Collections.emptyList());
    }

}