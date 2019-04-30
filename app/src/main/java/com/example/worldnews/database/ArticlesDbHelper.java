package com.example.worldnews.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ArticlesDbHelper extends SQLiteOpenHelper {

    ArticlesDbHelper(Context context) {
        super(context, ArticlesContract.DATABASE_NAME, null, ArticlesContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ArticlesContract.ArticlesEntry.CREATE_TABLE_ARTICLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ArticlesContract.ArticlesEntry.DELETE_TABLE_ARTICLES);
        onCreate(db);
    }
}