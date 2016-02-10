package com.hku.demos.aohuijun.androiddemos.memos;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.util.Log;

public class MemosProvider extends ContentProvider {

    private static final String DB_NAME = "Memos.db";
    private static final int DB_VERSION = 1;
    private static UriMatcher mUriMatcher;
    private MemosDBHelper mMemosDBHelper;

    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(Memos.AUTHORITY, Memos.TABLE_NAME, Memos.MEMO_DIR);
        mUriMatcher.addURI(Memos.AUTHORITY, Memos.TABLE_NAME + "/#", Memos.MEMO_ITEM);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        //  Handle requests to delete one or more rows.
        SQLiteDatabase database = mMemosDBHelper.getWritableDatabase();
        int deleteCount;
        switch (mUriMatcher.match(uri)) {
            case Memos.MEMO_DIR:
                deleteCount = database.delete(Memos.TABLE_NAME, selection, selectionArgs);
                break;
            case Memos.MEMO_ITEM:
                String deleteID = uri.getPathSegments().get(1);
                deleteCount = database.delete(Memos.TABLE_NAME, Memos._ID + " = ?", new String[]{deleteID});
                break;
            default:
                throw new IllegalArgumentException("Error Uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return deleteCount;
    }

    @Override
    public String getType(Uri uri) {
        //  Handle requests for the MIME type of the data at the given URI.
        switch (mUriMatcher.match(uri)) {
            case Memos.MEMO_DIR:
                return Memos.CONTENT_TYPE_PREFIX_PATH + Memos.AUTHORITY;
            case Memos.MEMO_ITEM:
                return Memos.CONTENT_TYPE_PREFIX_ITEM + Memos.AUTHORITY;
            default:
                throw new IllegalArgumentException("Error Uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //  Handle requests to insert a new row.
        Uri newUri;
        SQLiteDatabase database = mMemosDBHelper.getWritableDatabase();
        long newMemoID = database.insert(Memos.TABLE_NAME, Memos._ID, values);
        if (newMemoID < 0)
            throw new SQLiteException("Unable to insert " + values + " for " + newMemoID);
        switch (mUriMatcher.match(uri)) {
            case Memos.MEMO_DIR:
                newUri = ContentUris.withAppendedId(uri, newMemoID);
                break;
            case Memos.MEMO_ITEM:
                String path = uri.toString();
                newUri = Uri.parse(path.substring(0, path.lastIndexOf("/")) + newMemoID);
                break;
            default:
                throw new IllegalArgumentException("Error Uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(newUri, null);
        return newUri;
    }

    @Override
    public boolean onCreate() {
        //  Initialize content provider on startup.
        mMemosDBHelper = new MemosDBHelper(getContext(), DB_NAME, null, DB_VERSION);
        Log.d("Memos", Memos.AUTHORITY + " created. ");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        //  Handle query requests from clients.
        SQLiteDatabase database = mMemosDBHelper.getReadableDatabase();
        Cursor cursor;
        switch (mUriMatcher.match(uri)) {
            case Memos.MEMO_DIR:
                cursor = database.query(Memos.TABLE_NAME,
                                        projection,
                                        selection,
                                        selectionArgs,
                                        null,
                                        null,
                                        sortOrder);
                break;
            case Memos.MEMO_ITEM:
                String queryID = uri.getPathSegments().get(1);
                cursor = database.query(Memos.TABLE_NAME,
                                        projection,
                                        Memos._ID + " = ? ",
                                        new String[]{queryID},
                                        null,
                                        null,
                                        sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Error Uri: " + uri);
        }
        if (cursor != null)
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        //  Handle requests to update one or more rows.
        SQLiteDatabase database = mMemosDBHelper.getWritableDatabase();
        int updateCount;
        switch (mUriMatcher.match(uri)) {
            case Memos.MEMO_DIR:
                updateCount = database.update(Memos.TABLE_NAME,
                                            values,
                                            selection,
                                            selectionArgs);
                break;
            case Memos.MEMO_ITEM:
                String updateID = uri.getPathSegments().get(1);
                updateCount = database.update(Memos.TABLE_NAME,
                                            values,
                                            Memos._ID + " = ? ",
                                            new String[]{updateID});
                break;
            default:
                throw new IllegalArgumentException("Error Uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return updateCount;
    }
}
