package com.samy.j_nytimes.presentation.news_screen;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.samy.j_nytimes.databinding.ItemNewsBinding;
import com.samy.j_nytimes.domain.entities.NewsArticle;

public class NewsAdapter extends ListAdapter<NewsArticle, NewsAdapter.NewsViewHolder> {
    protected NewsAdapter() {
        super(DIFF_CALLBACK);
    }
    private OnItemClickListener onClickListener;
    public interface OnItemClickListener {
        void onItemClick(NewsArticle article);
    }
    private static final DiffUtil.ItemCallback<NewsArticle> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<NewsArticle>() {
                @Override
                public boolean areItemsTheSame(NewsArticle oldItem, NewsArticle newItem) {
                    return oldItem.getTitle().equals(newItem.getTitle());
                }

                @Override
                public boolean areContentsTheSame(NewsArticle oldItem, NewsArticle newItem) {
                    return oldItem.equals(newItem);
                }
            };

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemNewsBinding binding = ItemNewsBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.bind(getItem(position),onClickListener);

    }
    public void setOnClickListener(OnItemClickListener listener) {
        this.onClickListener = listener;
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        private final ItemNewsBinding binding;

        NewsViewHolder(ItemNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(NewsArticle article, OnItemClickListener onClickListener) {
            binding.titleText.setText(article.getTitle());
            binding.authorText.setText(article.getAuthor());
            binding.dateText.setText(article.getDate().substring(0,10));
            // Load image using Glide
            Glide.with(binding.getRoot())
                    .load("https://static01.nyt.com/"+article.getImageUrl())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.newsImage);
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onItemClick(article);
                }
            });
        }
    }
}