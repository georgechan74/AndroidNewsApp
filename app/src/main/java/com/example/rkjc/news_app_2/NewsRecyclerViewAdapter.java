package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class NewsRecyclerViewAdapter  extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {

    Context mContext;
    ArrayList<NewsItem> mArticles;
    private static final String TAG = "TheAdapter";

    public NewsRecyclerViewAdapter (Context context, ArrayList<NewsItem> articles) {
        this.mArticles = articles;
        this.mContext = context;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.news_item, parent, shouldAttachToParentImmediately);
        NewsViewHolder viewHolder = new NewsViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView description;
        TextView date;

        public NewsViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            date = (TextView) itemView.findViewById(R.id.date);
        }

        void bind(final int position) {
            title.setText("Title: " + mArticles.get(position).getTitle());
            Log.d(TAG, "Title: " + mArticles.get(position).getTitle());
            description.setText("Description: " + mArticles.get(position).getDescription());
            Log.d(TAG, "Description: " + mArticles.get(position).getDescription());
            date.setText("Date: " + mArticles.get(position).getPublishedAt());
            Log.d(TAG, "Date: " + mArticles.get(position).getPublishedAt());
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String urlString = mArticles.get(getAdapterPosition()).getUrl();
            openWebPage(urlString);
        }

        private void openWebPage(String url) {
            Uri webpage = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            mContext.startActivity(intent);
        }
    }

}
