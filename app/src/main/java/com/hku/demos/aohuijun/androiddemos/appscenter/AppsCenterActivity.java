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

    private static final int MAIN = 0;
    private static final int CATEGORY = 1;
    private static final int SEARCH = 2;
    private static final int PROFILE = 3;

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
        for (int i = 0; i < menu.size(); i++) {
            refreshTabIcon(menu.getItem(i), mTabHost.getCurrentTab());
        }
        return super.onPrepareOptionsMenu(menu);
    }

    private void refreshTabIcon(MenuItem item, int currentTab) {
        switch (item.getItemId()) {
            case R.id.menu_tab_main:
                item.setIcon(currentTab == MAIN ?
                        R.drawable.ic_action_app_center_selected : R.drawable.ic_action_app_center);
                break;
            case R.id.menu_tab_category:
                item.setIcon(currentTab == CATEGORY ?
                        R.drawable.ic_action_category_selected : R.drawable.ic_action_category);
                break;
            case R.id.menu_tab_search:
                item.setIcon(currentTab == SEARCH ?
                        R.drawable.ic_action_search_selected : R.drawable.ic_action_search);
                break;
            case R.id.menu_tab_profile:
                item.setIcon(currentTab == PROFILE ?
                        R.drawable.ic_action_profile_selected : R.drawable.ic_action_profile);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /**
         * Handle action bar item clicks here. The action bar will
         * automatically handle clicks on the Home/Up button, so long
         * as you specify a parent activity in AndroidManifest.xml.
         */
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_tab_main:
                mTabHost.setCurrentTab(MAIN);
                break;
            case R.id.menu_tab_category:
                mTabHost.setCurrentTab(CATEGORY);
                break;
            case R.id.menu_tab_search:
                mTabHost.setCurrentTab(SEARCH);
                break;
            case R.id.menu_tab_profile:
                mTabHost.setCurrentTab(PROFILE);
                break;
            default:
                break;
        }
        invalidateOptionsMenu();    //  IMPORTANT
        return super.onOptionsItemSelected(item);
    }
}
