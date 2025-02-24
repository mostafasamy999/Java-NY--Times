package com.samy.j_nytimes.presentation.news_screen;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.samy.j_nytimes.R;
import com.samy.j_nytimes.databinding.FragmentNewsBinding;
import com.samy.j_nytimes.domain.entities.NewsArticle;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NewsFragment extends Fragment {
    private FragmentNewsBinding binding;
    private NewsAdapter newsAdapter;
    private NewsViewModel viewModel;
    private List<NewsArticle> allArticles = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        setupRecyclerView();
        setupSwipeRefresh();
        setupErrorHandling();
        observeNews();
    }

    private void setupSwipeRefresh() {
        binding.swipeRefresh.setColorSchemeResources(R.color.ny_times_green);
        binding.swipeRefresh.setOnRefreshListener(() -> {
            viewModel.refreshNews();
        });
    }

    private void setupErrorHandling() {
        binding.retryButton.setOnClickListener(v -> {
            viewModel.refreshNews();
        });
    }

    private void observeNews() {
        viewModel.newsArticles.observe(getViewLifecycleOwner(), resource -> {
            binding.swipeRefresh.setRefreshing(false);
            switch (resource.status) {
                case LOADING:
                    if (allArticles.isEmpty()) {
                        binding.shimmerLayout.setVisibility(View.VISIBLE);
                        binding.shimmerLayout.startShimmer();
                        binding.recyclerView.setVisibility(View.GONE);
                        binding.errorLayout.setVisibility(View.GONE);
                    }
                    break;

                case SUCCESS:
                    binding.shimmerLayout.stopShimmer();
                    binding.shimmerLayout.setVisibility(View.GONE);
                    binding.swipeRefresh.setVisibility(View.VISIBLE);
                    binding.recyclerView.setVisibility(View.VISIBLE);
                    binding.errorLayout.setVisibility(View.GONE);
                    allArticles = resource.data;
                    newsAdapter.submitList(resource.data);
                    break;

                case ERROR:
                    binding.shimmerLayout.stopShimmer();
                    if (allArticles.isEmpty()) {
                        binding.shimmerLayout.setVisibility(View.GONE);
                        binding.swipeRefresh.setVisibility(View.GONE);
                        binding.recyclerView.setVisibility(View.GONE);
                        binding.errorLayout.setVisibility(View.VISIBLE);
                        binding.errorMessage.setText(resource.message);
                    } else {
                        Snackbar.make(binding.getRoot(),
                                        resource.message,
                                        Snackbar.LENGTH_LONG)
                                .setAction("Retry", v -> viewModel.refreshNews())
                                .show();
                    }
                    break;
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.news_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        // Customize SearchView
        searchView.setQueryHint("Search articles...");
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // Find the search text view directly
        EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        if (searchEditText != null) {
            searchEditText.setTextColor(Color.WHITE);
            searchEditText.setHintTextColor(Color.WHITE);
        }

        // Change the search icon color to white
        ImageView searchIcon = searchView.findViewById(androidx.appcompat.R.id.search_mag_icon);
        if (searchIcon != null) {
            searchIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        }

        // Change the close icon color to white
        ImageView closeIcon = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        if (closeIcon != null) {
            closeIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        }

        // Setup search listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterArticles(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterArticles(newText);
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void filterArticles(String query) {
        if (query == null || query.isEmpty()) {
            newsAdapter.submitList(allArticles);
            return;
        }

        List<NewsArticle> filteredList = new ArrayList<>();
        String lowercaseQuery = query.toLowerCase();

        for (NewsArticle article : allArticles) {
            String title = article.getTitle();
            String author = article.getAuthor();
            String summary = article.getSummary();

            if ((title != null && title.toLowerCase().contains(lowercaseQuery)) ||
                    (author != null && author.toLowerCase().contains(lowercaseQuery)) ||
                    (summary != null && summary.toLowerCase().contains(lowercaseQuery))) {
                filteredList.add(article);
            }
        }

        newsAdapter.submitList(filteredList);
    }

    private void setupRecyclerView() {
        newsAdapter = new NewsAdapter();
        newsAdapter.setOnClickListener(article -> {
            Bundle args = new Bundle();
            args.putParcelable("article", article);
            Navigation.findNavController(requireView())
                    .navigate(R.id.action_news_to_detail, args);
        });

        binding.recyclerView.setAdapter(newsAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.addItemDecoration(
                new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}