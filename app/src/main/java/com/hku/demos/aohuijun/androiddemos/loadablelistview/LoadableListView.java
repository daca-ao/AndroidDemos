package com.hku.demos.aohuijun.androiddemos.loadablelistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by aohuijun on 16/2/3.
 */
public class LoadableListView extends ListView implements AbsListView.OnScrollListener {

    private static final int DELTA_LOAD_MORE = 100;

    private LoadableFooterView mFooterView;
    private OnLoadCallback mOnLoadCaller;
    private int mTotalItemCount;
    private float mCurrentY = -1;
    private float deltaY;
    private boolean isFooterReady = false;
    private boolean isScrolledToBottom = false;
    private boolean isLoading = false;

    public LoadableListView(Context context) {
        super(context);
        initFooterView(context);
    }

    public LoadableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFooterView(context);
    }

    private void initFooterView(Context context) {
        super.setOnScrollListener(this);
        mFooterView = new LoadableFooterView(context);
        mFooterView.setState(LoadableFooterView.STATE_IDLE);
        mFooterView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLoading)
                    startLoadMore();
            }
        });
        if (!isFooterReady) {
            addFooterView(mFooterView);
            isFooterReady = true;
        }
    }

    private void startLoadMore() {
        isLoading = true;
        mFooterView.setState(LoadableFooterView.STATE_LOADING);
        if (mOnLoadCaller != null)
            mOnLoadCaller.onLoadMore();
    }

    public void stopLoadMore() {
        if (isLoading) {
            isLoading = false;
            mFooterView.setState(LoadableFooterView.STATE_IDLE);
        }
    }

    public int getTotalItemCount() {
        return mTotalItemCount;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mCurrentY == -1)
            mCurrentY = ev.getY();
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mCurrentY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                deltaY = ev.getY() - mCurrentY;
                if (getLastVisiblePosition() == mTotalItemCount - 1 &&
                        (mFooterView.getBottomMargin() > 0 || deltaY < 0)) {
                    if (mFooterView.getState() == LoadableFooterView.STATE_IDLE)
                        updateFooterStatus(-deltaY);
                }
                break;
            case MotionEvent.ACTION_UP:
            default:
                mCurrentY = -1;
                break;
        }
        super.onTouchEvent(ev);
        return true;
    }

    private void updateFooterStatus(float delta) {
        int margin = mFooterView.getBottomMargin();
        int height = margin + (int) delta;
        if (!isLoading) {
            if (height >= DELTA_LOAD_MORE)
                mFooterView.setState(LoadableFooterView.STATE_READY);
            else
                mFooterView.setState(LoadableFooterView.STATE_IDLE);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE) {
            if (isScrolledToBottom && !isLoading && deltaY < 0)
                startLoadMore();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mTotalItemCount = totalItemCount;
        if (getLastVisiblePosition() == (totalItemCount - 1))
            isScrolledToBottom = true;
        else
            isScrolledToBottom = false;
    }

    public void setOnLoadCallback(OnLoadCallback caller) {
        mOnLoadCaller = caller;
    }

    public interface OnLoadCallback {
        void onLoadMore();
    }
}
