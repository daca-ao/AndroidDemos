package com.hku.demos.aohuijun.androiddemos.memos;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

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
            default:
                throw new IllegalArgumentException("Error Uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //  Handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        //  Initialize content provider on startup.
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        //  Handle query requests from clients.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        //  Handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
