package com.hku.demos.aohuijun.androiddemos.loadablelistview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.hku.demos.aohuijun.androiddemos.R;
import com.hku.demos.aohuijun.androiddemos.appscenter.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListViewActivity extends Activity implements LoadableListView.OnLoadCallback {

    private static final int INIT_DATA_SIZE = 4;
    private static final int LOAD_INTERVAL = 19;
    private static final int ROW_ITEM_NUM = 3;

    private LoadableListView mLoadableListView;
    private LoadableListViewAdapter mAdapter;
    private List<Item> mItemList;
    private String[] mColorArray;
    private Handler mHandler;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        
        initDataSet();
        mAdapter = new LoadableListViewAdapter(this, ROW_ITEM_NUM);
        mAdapter.setData(mItemList);
        mLoadableListView = (LoadableListView) findViewById(R.id.loadable_list_view);
        mLoadableListView.setAdapter(mAdapter);
        mLoadableListView.setOnLoadCallback(this);
        mHandler = new Handler();
    }

    private void initDataSet() {
        mColorArray = getResources().getStringArray(R.array.color_array);
        mItemList = new ArrayList<>();
        insertData();
    }

    private void insertData() {
        Random random = new Random();
        for (int i = 0; i < INIT_DATA_SIZE; i++) {
            Item item = new Item();
            item.setData(Color.parseColor(mColorArray[random.nextInt(mColorArray.length)]));
            mItemList.add(item);
        }
    }

    @Override
    public void onLoadMore() {
        if (mLoadableListView.getTotalItemCount() < LOAD_INTERVAL * 3) {
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.status_loading),
                    Toast.LENGTH_SHORT).show();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    insertData();
                    mAdapter.setData(mItemList);
                    mAdapter.notifyDataSetChanged();
                    mLoadableListView.stopLoadMore();
                }
            }, 3000);
        } else
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.status_finish_loading),
                    Toast.LENGTH_SHORT).show();
    }
}
