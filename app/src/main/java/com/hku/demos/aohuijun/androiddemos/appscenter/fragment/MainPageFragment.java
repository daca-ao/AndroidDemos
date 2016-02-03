package com.hku.demos.aohuijun.androiddemos.appscenter.fragment;


import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.hku.demos.aohuijun.androiddemos.R;
import com.hku.demos.aohuijun.androiddemos.appscenter.fragment.mainpageitem.ChoicePageFragment;
import com.hku.demos.aohuijun.androiddemos.appscenter.fragment.mainpageitem.NewPageFragment;
import com.hku.demos.aohuijun.androiddemos.appscenter.fragment.mainpageitem.RankPageFragment;
import com.hku.demos.aohuijun.androiddemos.appscenter.fragment.mainpageitem.TopicsPageFragment;

import java.util.ArrayList;

/**
 * The first tab of the Apps Center Demo.
 */
public class MainPageFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private static final int COUNT_TABS = 4;
    private static final int CHOICE = 0;
    private static final int RANKING = 1;
    private static final int NEW = 2;
    private static final int TOPICS = 3;

    private ArrayList<Fragment> fragmentArrayList;
    private ChoicePageFragment choicePageFragment;
    private RankPageFragment rankPageFragment;
    private NewPageFragment newPageFragment;
    private TopicsPageFragment topicsPageFragment;

    private TextView choiceTab, rankingTab, newTab, topicsTab;
    private int mCurrentIndex = 0;

    private View mActionBarView;
    private ImageView mScrollBar;
    private int mActionBarOptions;
    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        choicePageFragment = new ChoicePageFragment();
        rankPageFragment = new RankPageFragment();
        newPageFragment = new NewPageFragment();
        topicsPageFragment = new TopicsPageFragment();

        fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(choicePageFragment);
        fragmentArrayList.add(rankPageFragment);
        fragmentArrayList.add(newPageFragment);
        fragmentArrayList.add(topicsPageFragment);

        mActionBarView = LayoutInflater.from(getActivity()).inflate(R.layout.action_bar_apps_center_main, null);
        getActivity().getActionBar().setCustomView(mActionBarView);
        mScrollBar = (ImageView) mActionBarView.findViewById(R.id.apps_center_scroll_bar);
        choiceTab = (TextView) mActionBarView.findViewById(R.id.tab_choice);
        rankingTab = (TextView) mActionBarView.findViewById(R.id.tab_ranking);
        newTab = (TextView) mActionBarView.findViewById(R.id.tab_new);
        topicsTab = (TextView) mActionBarView.findViewById(R.id.tab_topics);
        choiceTab.setOnClickListener(new OnTabClickListener(CHOICE));
        rankingTab.setOnClickListener(new OnTabClickListener(RANKING));
        newTab.setOnClickListener(new OnTabClickListener(NEW));
        topicsTab.setOnClickListener(new OnTabClickListener(TOPICS));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_main_page, container, false);
        mViewPager = (ViewPager) mainView.findViewById(R.id.apps_center_pager);
        mViewPager.setAdapter(new MainPagerAdapter(getChildFragmentManager(), fragmentArrayList));
        mViewPager.addOnPageChangeListener(this);
        return mainView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBar actionBar = getActivity().getActionBar();
        mActionBarOptions = actionBar.getDisplayOptions();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM,
                ActionBar.DISPLAY_SHOW_CUSTOM | mActionBarOptions);
        //  Set the tab being selected.
        this.onPageSelected(mViewPager.getCurrentItem());
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().getActionBar().setDisplayOptions(mActionBarOptions,
                ActionBar.DISPLAY_SHOW_CUSTOM | mActionBarOptions);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int width = mScrollBar.getMeasuredWidth();
        Animation mAnimation = new TranslateAnimation(width*mCurrentIndex, width*position, 0, 0);
        mCurrentIndex = position;
        mAnimation.setFillAfter(true);
        mAnimation.setDuration(300);
        mScrollBar.startAnimation(mAnimation);
        for (int i = 0; i < COUNT_TABS; i++) {
            refreshTabColor(i);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class MainPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragmentArrayList;

        public MainPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.fragmentArrayList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }
    }

    private class OnTabClickListener implements View.OnClickListener {
        private int tabIndex;

        public OnTabClickListener(int index) {
            this.tabIndex = index;
        }

        @Override
        public void onClick(View v) {
            mViewPager.setCurrentItem(tabIndex);
        }
    }

    private void refreshTabColor(int index) {
        switch (index) {
            case CHOICE:
                choiceTab.setTextColor(mCurrentIndex == index ? Color.parseColor("#2db5e5") : Color.BLACK);
                break;
            case RANKING:
                rankingTab.setTextColor(mCurrentIndex == index ? Color.parseColor("#2db5e5") : Color.BLACK);
                break;
            case NEW:
                newTab.setTextColor(mCurrentIndex == index ? Color.parseColor("#2db5e5") : Color.BLACK);
                break;
            case TOPICS:
                topicsTab.setTextColor(mCurrentIndex == index ? Color.parseColor("#2db5e5") : Color.BLACK);
                break;
            default:
                break;
        }
    }
}
