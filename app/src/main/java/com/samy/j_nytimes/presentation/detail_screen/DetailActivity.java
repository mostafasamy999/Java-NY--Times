package com.samy.j_nytimes.presentation.detail_screen;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.samy.j_nytimes.R;
import com.samy.j_nytimes.databinding.ActivityDetailBinding;
import com.samy.j_nytimes.domain.entities.NewsArticle;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private NewsArticle article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        String title = getIntent().getStringExtra("title");
        String date = getIntent().getStringExtra("date");
        String author = getIntent().getStringExtra("auth");
        String imgUrl = getIntent().getStringExtra("imgUrl");
        String description = getIntent().getStringExtra("description");
        article = new NewsArticle(title, author, description, date, imgUrl);

        setupStatusBarAndToolbar();
        setupUI();
    }
    private void setupStatusBarAndToolbar() {
        // Set status bar color
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.ny_times_green));

        // Setup toolbar
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("NY Times Most Popular");

            // Change back arrow color to white
            Drawable backArrow = getResources().getDrawable(R.drawable.baseline_arrow_back_24);
            backArrow.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(backArrow);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }

        binding.toolbar.setBackgroundColor(getResources().getColor(R.color.ny_times_green));
        binding.toolbar.setTitleTextColor(Color.WHITE);
    }

    private void setupUI() {
        binding.titleText.setText(article.getTitle());
        binding.abstractText.setText(article.getSummary());
        binding.authorText.setText(article.getAuthor());
        binding.dateText.setText(article.getDate().substring(1,10));

        // Load image if available
        if (article.getImageUrl() != null && !article.getImageUrl().isEmpty()) {
            Glide.with(this)
                    .load("https://static01.nyt.com/"+article.getImageUrl())
                    .centerCrop()
                    .into(binding.articleImage);
        }

        binding.bodyText.setText(article.getSummary() + article.getSummary() + article.getSummary());

        // Setup author initial circle
        TextView initialView = binding.authorInitial;
        String initial = article.getAuthor().substring(0, 1).toUpperCase();
        initialView.setText(initial);
        initialView.setBackgroundResource(R.drawable.circle_background);
    }

}