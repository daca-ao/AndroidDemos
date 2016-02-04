package com.hku.demos.aohuijun.androiddemos.loadablelistview;

import android.app.Activity;
import android.os.Bundle;

import com.hku.demos.aohuijun.androiddemos.R;
import com.hku.demos.aohuijun.androiddemos.appscenter.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Handler;

public class ListViewActivity extends Activity implements LoadableListView.OnLoadCallback {

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
        mLoadableListView = (LoadableListView) findViewById(R.id.loadable_list_view);
        mLoadableListView.setOnLoadCallback(this);
    }

    private void initDataSet() {
        mColorArray = getResources().getStringArray(R.array.color_array);
        mItemList = new ArrayList<>();
        insertData();
    }

    private void insertData() {
        Random random = new Random();
    }

    @Override
    public void onLoadMore() {

    }
}
