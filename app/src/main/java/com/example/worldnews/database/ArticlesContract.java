package com.example.worldnews.database;

import android.net.Uri;
import android.provider.BaseColumns;

public class ArticlesContract {

    static final String AUTHORITY = "com.example.worldnews";

    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);


    static final String PATH_ARTICLES = "articles";

    static final String DATABASE_NAME = "articles.db";

    static final int DATABASE_VERSION = 1;

    public static final class ArticlesEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_ARTICLES).build();

        static final String TABLE_NAME = "articles";

        public static final String COLUMN_ARTICLE_TITLE = "articleTitle";
        public static final String COLUMN_ARTICLE_SOURCE_ID = "articleSourceId";
        public static final String COLUMN_ARTICLE_SOURCE_NAME = "articleSourceName";
        public static final String COLUMN_ARTICLE_SOURCE_AUTHOR = "articleAuthor";
        public static final String COLUMN_ARTICLE_SOURCE_DESCRIPTION = "articleDescription";
        public static final String COLUMN_ARTICLE_URL = "url";
        public static final String COLUMN_ARTICLE_IMAGE_URL = "imageUrl";
        public static final String COLUMN_ARTICLE_PUBLISHED = "published";

        static final String CREATE_TABLE_ARTICLES =
                "CREATE TABLE " + ArticlesEntry.TABLE_NAME + "(" +
                        ArticlesEntry.COLUMN_ARTICLE_TITLE + " TEXT," +
                        ArticlesEntry.COLUMN_ARTICLE_SOURCE_ID + " TEXT," +
                        ArticlesEntry.COLUMN_ARTICLE_SOURCE_NAME + " TEXT," +
                        ArticlesEntry.COLUMN_ARTICLE_SOURCE_AUTHOR + " TEXT," +
                        ArticlesEntry.COLUMN_ARTICLE_SOURCE_DESCRIPTION + " TEXT," +
                        ArticlesEntry.COLUMN_ARTICLE_URL + " TEXT," +
                        ArticlesEntry.COLUMN_ARTICLE_IMAGE_URL + " TEXT," +
                        ArticlesEntry.COLUMN_ARTICLE_PUBLISHED + " TEXT);";

        static final String DELETE_TABLE_ARTICLES =
                "DROP TABLE IF EXISTS " + ArticlesEntry.TABLE_NAME;
    }
}
