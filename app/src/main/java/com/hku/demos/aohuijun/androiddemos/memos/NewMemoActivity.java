package com.hku.demos.aohuijun.androiddemos.memos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.hku.demos.aohuijun.androiddemos.R;

public class NewMemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_memo);
        setMemoContent();
    }

    private void setMemoContent() {
        Intent memoIntent = getIntent();
        if (memoIntent.getAction().equals(Memos.ACTION_VIEW_MEMO)) {
            String memoTitle = memoIntent.getStringExtra(Memos._MEMO_TITLE);
            String memoContent = memoIntent.getStringExtra(Memos._MEMO_CONTENT);
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
    }
}
