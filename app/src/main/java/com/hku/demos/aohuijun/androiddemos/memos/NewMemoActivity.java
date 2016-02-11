package com.hku.demos.aohuijun.androiddemos.memos;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.hku.demos.aohuijun.androiddemos.R;

public class NewMemoActivity extends Activity {

    private EditText mMemoTitleText, mMemoContentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_memo);
        mMemoTitleText = (EditText) findViewById(R.id.memos_title_input_edit_text);
        mMemoContentText = (EditText) findViewById(R.id.memos_content_input_edit_text);
        setMemoContent();
    }

    private void setMemoContent() {
        Intent memoIntent = getIntent();
        if (memoIntent.getAction().equals(Memos.ACTION_VIEW_MEMO)) {
            String memoTitle = memoIntent.getStringExtra(Memos._MEMO_TITLE);
            if (memoTitle != null && !memoTitle.equals("")) {
                mMemoTitleText.setText(memoTitle);
            }
            String memoContent = memoIntent.getStringExtra(Memos._MEMO_CONTENT);
            if (memoContent != null && !memoContent.equals("")) {
                mMemoContentText.setText(memoContent);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_memo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_memo_cancel) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = getIntent();
        String memoTitle = mMemoTitleText.getText().toString();
        String memoContent = mMemoContentText.getText().toString();
        if (memoTitle.equals("") && !memoContent.equals("")) {
            memoTitle = "*Empty Title";
            Toast.makeText(getApplicationContext(), "Empty Title", Toast.LENGTH_SHORT).show();
        }
        ContentValues contentValues = setContentValues(memoTitle, memoContent);
        if (intent.getAction().equals(Memos.ACTION_VIEW_MEMO)) {
            if (memoContent.equals("") && memoTitle.equals("")) {
                //  EMPTY MEMO: DELETE
                getContentResolver().delete(intent.getData(), null, null);
                Toast.makeText(getApplicationContext(), "Memo deleted. ", Toast.LENGTH_SHORT).show();
            } else if (!memoTitle.equals(intent.getStringExtra(Memos._MEMO_TITLE)) ||
                    !memoContent.equals(intent.getStringExtra(Memos._MEMO_CONTENT))) {
                //  DIFFERENT CONTENTS: UPDATE
                getContentResolver().update(intent.getData(), contentValues, null, null);
                Toast.makeText(getApplicationContext(), "Memo updated. ", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if (memoContent.equals("") && memoTitle.equals("")) {
            //  CREATE EMPTY MEMO: NO OPERATION
            return;
        }
        //  ELSE: INSERT NEW MEMO
        getContentResolver().insert(intent.getData(), contentValues);
        Toast.makeText(getApplicationContext(), "Memo created. ", Toast.LENGTH_SHORT).show();
    }

    private ContentValues setContentValues(String titleString, String contentString) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Memos._MEMO_TITLE, titleString);
        contentValues.put(Memos._MEMO_CONTENT, contentString);
        return contentValues;
    }
}
