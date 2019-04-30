package com.example.worldnews.Controller;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.worldnews.Model.Article;
import com.example.worldnews.R;

import java.util.ArrayList;

public class ArticlesToRecyclerView {

    public static void LoadArticlesListToRecyclerView(
            Boolean inSavedArticleState,
            ArrayList<Article> articleArrayList,
            RecyclerView recyclerView,
            Context context){

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,context.getResources().getInteger(R.integer.list_column_count));

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setFocusable(true);

        ArticlesToRecyclerViewAdapter recyclerViewAdapter = new ArticlesToRecyclerViewAdapter(inSavedArticleState,articleArrayList,context);

        recyclerView.setAdapter(recyclerViewAdapter);

    }

}
