package com.example.worldnews.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ArticlesContentProvider extends ContentProvider {

    private final static int ARTICLES = 100;
    private final static int ARTICLE_WITH_ID = 101;
    private final static UriMatcher uriMatcher = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {

        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(ArticlesContract.AUTHORITY, ArticlesContract.PATH_ARTICLES, ARTICLES);
        uriMatcher.addURI(ArticlesContract.AUTHORITY, ArticlesContract.PATH_ARTICLES + "/#", ARTICLE_WITH_ID);

        return uriMatcher;
    }

    private ArticlesDbHelper dbHelper;

    @Override
    public boolean onCreate() {

        dbHelper = new ArticlesDbHelper(getContext());

        return true;
    }

    @Nullable
    @Override
    public Cursor query(
            @NonNull Uri uri,
            @Nullable String[] projection,
            @Nullable String selection,
            @Nullable String[] selectionArgs,
            @Nullable String sortOrder) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        int match = uriMatcher.match(uri);
        Cursor retCursor;

        switch (match) {
            case ARTICLES:
                retCursor = db.query(
                        ArticlesContract.ArticlesEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                db.close();
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        retCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        int match = uriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case ARTICLES:
                long id = db.insert(ArticlesContract.ArticlesEntry.TABLE_NAME, null, values);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(ArticlesContract.ArticlesEntry.CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                db.close();
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        db.close();
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        int match = uriMatcher.match(uri);
        int articleDeleted;

        switch (match) {
            case ARTICLES:
                articleDeleted = db.delete(ArticlesContract.ArticlesEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                db.close();
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (articleDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        db.close();
        return articleDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
