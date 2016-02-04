package com.hku.demos.aohuijun.androiddemos.loadablelistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hku.demos.aohuijun.androiddemos.R;

/**
 * Created by aohuijun on 16/2/3.
 */
public class LoadableFooterView extends LinearLayout {

    public static final int STATE_IDLE = 0;
    public static final int STATE_READY = 1;
    public static final int STATE_LOADING = 2;
    private int mFooterState;

    private RotateAnimation mRotateAnimation, mReverseAnimation;

    private Context mContext;
    private RelativeLayout mFooterContentLayout;
    private TextView mStateTextView;
    private ImageView mArrowView;
    private ImageView mProgressBarView;

    public LoadableFooterView(Context context) {
        super(context);
        initView(context);
    }

    public LoadableFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        LinearLayout mLayout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.item_footer_view, null);
        mLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        addView(mLayout);

        mFooterContentLayout = (RelativeLayout) mLayout.findViewById(R.id.footer_view_content);
        mStateTextView = (TextView) mLayout.findViewById(R.id.footer_view_text);
        mArrowView = (ImageView) mLayout.findViewById(R.id.footer_view_arrow);
        mProgressBarView = (ImageView) mLayout.findViewById(R.id.footer_view_progress_bar);

        mReverseAnimation = (RotateAnimation) AnimationUtils.loadAnimation(mContext, R.anim.reverse);
        mRotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(mContext, R.anim.rotating);

        setBottomMargin(LayoutParams.WRAP_CONTENT);
    }

    public void setState(int state) {
        this.mFooterState = state;
        switch (state) {
            case STATE_IDLE:
                mStateTextView.setText(getResources().getString(R.string.status_pull_up_to_load));
                mArrowView.setVisibility(View.VISIBLE);
                mProgressBarView.clearAnimation();
                mProgressBarView.setVisibility(View.GONE);
                break;
            case STATE_READY:
                mStateTextView.setText(getResources().getString(R.string.status_release_to_load));
                mArrowView.startAnimation(mReverseAnimation);
                break;
            case STATE_LOADING:
                mStateTextView.setText(getResources().getString(R.string.status_loading));
                mArrowView.clearAnimation();
                mArrowView.setVisibility(View.INVISIBLE);
                mProgressBarView.startAnimation(mRotateAnimation);
                mProgressBarView.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    public int getState() {
        return mFooterState;
    }

    public void setBottomMargin(int height) {
        if (height < 0)
            return;
        LinearLayout.LayoutParams mParams = (LayoutParams) mFooterContentLayout.getLayoutParams();
        mParams.bottomMargin = height;
        mFooterContentLayout.setLayoutParams(mParams);
    }

    public int getBottomMargin() {
        LinearLayout.LayoutParams mParams = (LayoutParams) mFooterContentLayout.getLayoutParams();
        return mParams.bottomMargin;
    }

    public void hideFooter() {
        LinearLayout.LayoutParams mParams = (LayoutParams) mFooterContentLayout.getLayoutParams();
        mParams.height = 0;
        mFooterContentLayout.setLayoutParams(mParams);
    }

    public void showFooter() {
        LinearLayout.LayoutParams mParams = (LayoutParams) mFooterContentLayout.getLayoutParams();
        mParams.height = LayoutParams.WRAP_CONTENT;
        mFooterContentLayout.setLayoutParams(mParams);
    }
}
