package com.hku.demos.aohuijun.androiddemos.memos;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.hku.demos.aohuijun.androiddemos.R;

public class MemosListActivity extends Activity implements AdapterView.OnItemClickListener {

    private Uri mContentUri = Uri.parse(Memos.CONTENT_URI);
    private ListView mMemosListView;
    private MemosListAdapter mAdapter;
    private ContentResolver mResolver;
    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memos_list);
        mResolver = getContentResolver();
        mResolver.registerContentObserver(mContentUri, true, new MemoContentObserver(new Handler()));
        mCursor = mResolver.query(mContentUri, null, null, null, null);
        mAdapter = new MemosListAdapter(this, mCursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mMemosListView = (ListView) findViewById(R.id.memos_list_view);
        mMemosListView.setAdapter(mAdapter);
        mMemosListView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_memos_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_new_memo) {
            Intent newMemoIntent = setMemoIntent(null, Memos.ACTION_CREATE_MEMO, mContentUri);
            newMemoIntent.setClass(MemosListActivity.this, NewMemoActivity.class);
            startActivity(newMemoIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Intent setMemoIntent(Cursor cursor, String action, Uri uri) {
        if (cursor == null) {
            return new Intent(action, uri);
        }
        int memoID = mCursor.getInt(mCursor.getColumnIndex(Memos._ID));
        String memoTitle = mCursor.getString(mCursor.getColumnIndex(Memos._MEMO_TITLE));
        String memoContent = mCursor.getString(mCursor.getColumnIndex(Memos._MEMO_CONTENT));
        Intent intent = new Intent(action, ContentUris.withAppendedId(uri,memoID));
        intent.putExtra(Memos._MEMO_TITLE, memoTitle);
        intent.putExtra(Memos._MEMO_CONTENT, memoContent);
        return intent;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Cursor cursor = (Cursor) mAdapter.getItem(position);
        Intent memoIntent = setMemoIntent(cursor, Memos.ACTION_VIEW_MEMO, mContentUri);
        memoIntent.setClass(MemosListActivity.this, NewMemoActivity.class);
        startActivity(memoIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.getCursor().close();
    }

    private class MemoContentObserver extends ContentObserver {

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public MemoContentObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            mCursor.requery();
            mAdapter.swapCursor(mCursor);
        }
    }
}
