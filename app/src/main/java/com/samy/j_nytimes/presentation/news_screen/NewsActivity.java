package com.samy.j_nytimes.presentation.news_screen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.samy.j_nytimes.R;
import com.samy.j_nytimes.databinding.ActivityNewsBinding;
import com.samy.j_nytimes.domain.entities.NewsArticle;
import com.samy.j_nytimes.presentation.detail_screen.DetailActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NewsActivity extends AppCompatActivity {
    private ActivityNewsBinding binding;
    private NewsAdapter newsAdapter;

    NewsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewsBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        setContentView(binding.getRoot());
        setupStatusBarAndToolbar();
        setupRecyclerView();
        observeNews();
    }
    private void setupStatusBarAndToolbar() {
        // Set status bar color
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.ny_times_green));

        // Setup toolbar
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("NY Times Most Popular");


        }

        binding.toolbar.setBackgroundColor(getResources().getColor(R.color.ny_times_green));
        binding.toolbar.setTitleTextColor(Color.WHITE);
    }

    private void setupRecyclerView() {
        newsAdapter = new NewsAdapter();
        newsAdapter.setOnClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(NewsArticle article) {
                Intent intent = new Intent(NewsActivity.this, DetailActivity.class);
                intent.putExtra("title", article.getTitle());
                intent.putExtra("date", article.getDate());
                intent.putExtra("auth", article.getAuthor());
                intent.putExtra("imgUrl", article.getImageUrl());
                intent.putExtra("description", article.getSummary());
                startActivity(intent);

            }
        });
        binding.recyclerView.setAdapter(newsAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        );

    }

    private void observeNews() {
        viewModel.newsArticles.observe(this, resource -> {
            switch (resource.status) {
                case LOADING:
                    binding.shimmerLayout.setVisibility(View.VISIBLE);
                    binding.recyclerView.setVisibility(View.GONE);
                    binding.errorLayout.setVisibility(View.GONE);
                    break;
                case SUCCESS:
                    binding.shimmerLayout.setVisibility(View.GONE);
                    binding.recyclerView.setVisibility(View.VISIBLE);
                    binding.errorLayout.setVisibility(View.GONE);
                    newsAdapter.submitList(resource.data);
                    break;
                case ERROR:
                    binding.shimmerLayout.setVisibility(View.GONE);
                    binding.recyclerView.setVisibility(View.GONE);
                    binding.errorLayout.setVisibility(View.VISIBLE);
                    binding.errorMessage.setText(resource.message);
                    break;
            }
        });
    }
}
