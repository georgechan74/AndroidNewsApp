package com.example.rkjc.news_app_2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class JsonUtils {

    public static ArrayList<NewsItem> parseNews(String jsonObject) {
        ArrayList<NewsItem> newsList = new ArrayList<>();

        try {
            JSONObject mainJSONObject = new JSONObject(jsonObject);
            JSONArray articles = mainJSONObject.getJSONArray("articles");

            for (int i = 0; i < articles.length(); i++) {
                JSONObject article = articles.getJSONObject(i);
                newsList.add(new NewsItem(
                        article.getString("author"),
                        article.getString("title"),
                        article.getString("description"),
                        article.getString("url"),
                        article.getString("publishedAt")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return newsList;
    }
}


