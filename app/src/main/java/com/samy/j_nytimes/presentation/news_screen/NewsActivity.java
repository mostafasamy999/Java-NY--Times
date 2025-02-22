package com.samy.j_nytimes.presentation.news_screen;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.samy.j_nytimes.databinding.ActivityNewsBinding;
import com.samy.j_nytimes.utils.Utils;

import javax.inject.Inject;

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
        Utils.statusBarColor(this);
       setupRecyclerView();
        observeNews();
    }
    private void setupRecyclerView() {
        newsAdapter = new NewsAdapter();
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
