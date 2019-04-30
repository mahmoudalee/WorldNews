package com.example.worldnews;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.worldnews.Model.Article;
import com.example.worldnews.articlesData.DownloadArticlesTopHeadlines;

import java.util.ArrayList;

public class WidgetGetArticleData extends IntentService {

    public static final String UPDATE_ARTICLES_LIST = "com.robertkiszelirk.newsselector.ui.widget";

    public WidgetGetArticleData() {
        super("WidgetGetArticleData");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        ArrayList<Article> articleArrayList = new DownloadArticlesTopHeadlines(getApplicationContext(),"us","general").loadInBackground();

        passArticleArray(articleArrayList);
    }

    private void passArticleArray(ArrayList<Article> articleArrayList) {

        Intent intent = new Intent(UPDATE_ARTICLES_LIST);
        intent.putParcelableArrayListExtra(Constants.ARTICLE_LIST,articleArrayList);
        intent.setAction(UPDATE_ARTICLES_LIST);
        sendBroadcast(intent);
    }

}
