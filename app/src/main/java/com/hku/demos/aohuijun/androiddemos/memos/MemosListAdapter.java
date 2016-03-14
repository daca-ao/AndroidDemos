package com.hku.demos.aohuijun.androiddemos.memos;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.hku.demos.aohuijun.androiddemos.R;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        TextView memoTimeView = (TextView) view.findViewById(R.id.memos_text_view_time);
        TextView memoContentView = (TextView) view.findViewById(R.id.memos_text_view_content);

        memoTitleView.setText(cursor.getString(cursor.getColumnIndex(Memos._MEMO_TITLE)));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        memoTimeView.setText(dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Memos._UPDATE_TIME)))));
        String memoContent = cursor.getString(cursor.getColumnIndex(Memos._MEMO_CONTENT));
        if (!memoContent.equals("")) {
            if (memoContent.length() > Memos.MEMO_CONTENT_LENGTH) {
                memoContent = memoContent.substring(0, Memos.MEMO_CONTENT_LENGTH);
                memoContent += "...";
            }
            memoContentView.setText(memoContent);
        }
    }
}
