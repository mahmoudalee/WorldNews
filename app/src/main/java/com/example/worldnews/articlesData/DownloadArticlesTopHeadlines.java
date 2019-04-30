package com.example.worldnews.articlesData;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.worldnews.HandleUrls;
import com.example.worldnews.Model.Article;
import com.example.worldnews.Model.GetJsonData;
import com.example.worldnews.ParseJsonData;

import org.json.JSONException;

import java.util.ArrayList;

public class DownloadArticlesTopHeadlines extends AsyncTaskLoader<ArrayList<Article>> {

    private String country;
    private String category;

    public DownloadArticlesTopHeadlines(Context context, String country, String category) {
        super(context);
        this.country = country;
        this.category = category;
    }

    @Override
    public ArrayList<Article> loadInBackground() {

        ArrayList<Article> articleList = new ArrayList<>();

        String jsonData = GetJsonData.getJson(HandleUrls.createTopHeadlinesListUrl(country,category));

        try {
            if(jsonData != null) {
                articleList = ParseJsonData.jsonParseForArticlesList(jsonData);
            }else{
                articleList = null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return articleList;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


}
