package com.hku.demos.aohuijun.androiddemos.appscenter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.hku.demos.aohuijun.androiddemos.R;
import com.hku.demos.aohuijun.androiddemos.appscenter.fragment.CategoryPageFragment;
import com.hku.demos.aohuijun.androiddemos.appscenter.fragment.MainPageFragment;
import com.hku.demos.aohuijun.androiddemos.appscenter.fragment.ProfilePageFragment;
import com.hku.demos.aohuijun.androiddemos.appscenter.fragment.SearchPageFragment;

public class AppsCenterActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_center);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.addTab(mTabHost.newTabSpec("main").setIndicator("main"), MainPageFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("category").setIndicator("category"), CategoryPageFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("search").setIndicator("search"), SearchPageFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("profile").setIndicator("profile"), ProfilePageFragment.class, null);
        mTabHost.getTabWidget().setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //  Adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_apps_center, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        invalidateOptionsMenu();
        return super.onOptionsItemSelected(item);
    }
}
