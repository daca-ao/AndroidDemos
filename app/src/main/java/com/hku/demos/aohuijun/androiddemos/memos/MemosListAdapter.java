package com.hku.demos.aohuijun.androiddemos.memos;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.hku.demos.aohuijun.androiddemos.R;

/**
 * Created by aohuijun on 16/2/8.
 */
public class MemosListAdapter extends CursorAdapter {

    public MemosListAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_memo, null);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView memoTitleView = (TextView) view.findViewById(R.id.memos_text_view_title);
        TextView memoContentView = (TextView) view.findViewById(R.id.memos_text_view_content);
        memoTitleView.setText(cursor.getString(cursor.getColumnIndex(Memos._MEMO_TITLE)));
        String memoContent = cursor.getString(cursor.getColumnIndex(Memos._MEMO_CONTENT));
        if (!memoContent.equals("")) {
            memoContentView.setText(memoContent);
        }
    }
}
