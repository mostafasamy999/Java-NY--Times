package com.samy.j_nytimes.presentation.detail_screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.samy.j_nytimes.R;
import com.samy.j_nytimes.databinding.FragmentDetailBinding;
import com.samy.j_nytimes.domain.entities.NewsArticle;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailFragment extends Fragment {
    private FragmentDetailBinding binding;
    private NewsArticle article;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get arguments from navigation
        String title = requireArguments().getString("title");
        String date = requireArguments().getString("date");
        String author = requireArguments().getString("auth");
        String imgUrl = requireArguments().getString("imgUrl");
        String description = requireArguments().getString("description");

        article = new NewsArticle(title, author, description, date, imgUrl);
        setupUI();
    }

    private void setupUI() {
        binding.titleText.setText(article.getTitle());
        binding.abstractText.setText(article.getSummary());
        binding.authorText.setText(article.getAuthor());
        binding.dateText.setText(article.getDate().substring(1, 10));

        // Load image if available
        if (article.getImageUrl() != null && !article.getImageUrl().isEmpty()) {
            Glide.with(requireContext())
                    .load("https://static01.nyt.com/" + article.getImageUrl())
                    .error(R.drawable.error_image)
                    .centerCrop()
                    .into(binding.articleImage);
        }

        binding.bodyText.setText(article.getSummary() + article.getSummary() + article.getSummary());

        // Setup author initial circle
        binding.authorInitial.setText(article.getAuthor().substring(0, 1).toUpperCase());
        binding.authorInitial.setBackgroundResource(R.drawable.circle_background);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}