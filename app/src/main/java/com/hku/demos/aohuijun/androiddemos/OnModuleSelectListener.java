package com.hku.demos.aohuijun.androiddemos;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.hku.demos.aohuijun.androiddemos.activitythemes.ThemesActivity;
import com.hku.demos.aohuijun.androiddemos.appscenter.AppsCenterActivity;
import com.hku.demos.aohuijun.androiddemos.floatwindow.FloatWindowActivity;
import com.hku.demos.aohuijun.androiddemos.launchmodes.SingleInstanceActivity;
import com.hku.demos.aohuijun.androiddemos.launchmodes.SingleTaskActivity;
import com.hku.demos.aohuijun.androiddemos.launchmodes.SingleTopActivity;
import com.hku.demos.aohuijun.androiddemos.launchmodes.StandardActivity;
import com.hku.demos.aohuijun.androiddemos.loadablelistview.ListViewActivity;
import com.hku.demos.aohuijun.androiddemos.memos.MemosListActivity;
import com.hku.demos.aohuijun.androiddemos.multidrawables.MultiDrawablesActivity;
import com.hku.demos.aohuijun.androiddemos.selectablegridview.GridViewActivity;
import com.hku.demos.aohuijun.androiddemos.textsharing.TextSharingActivity;

/**
 * Created by aohuijun on 16/1/11.
 */
public class OnModuleSelectListener implements View.OnClickListener {
    private Context mContext;
    private int mModule;

    public OnModuleSelectListener(Context ctx, int module) {
        this.mContext = ctx;
        this.mModule = module;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Class<?> cls = null;
        switch (mModule) {
            case MyApp.ENTRY_BASICS:
                cls = BasicsEntryActivity.class;
                break;
            case MyApp.ENTRY_DEMOS:
                cls = DemosEntryActivity.class;
                break;
            case MyApp.LAUNCH_MODES:
                cls = StandardActivity.class;
                break;
            case MyApp.STANDARD:
                cls = StandardActivity.class;
                break;
            case MyApp.SINGLE_TOP:
                cls = SingleTopActivity.class;
                break;
            case MyApp.SINGLE_TASK:
                cls = SingleTaskActivity.class;
                break;
            case MyApp.SINGLE_INSTANCE:
                cls = SingleInstanceActivity.class;
                break;
            case MyApp.DIFF_THEMES:
                cls = ThemesActivity.class;
                break;
            case MyApp.MULTI_DRAWABLES:
                cls = MultiDrawablesActivity.class;
                break;
            case MyApp.TEXT_SHARE:
                cls = TextSharingActivity.class;
                break;
            case MyApp.APPS_CENTER:
                cls = AppsCenterActivity.class;
                break;
            case MyApp.SELECTABLE_GRID_VIEW:
                cls = GridViewActivity.class;
                break;
            case MyApp.LOADABLE_LIST_VIEW:
                cls = ListViewActivity.class;
                break;
            case MyApp.FLOAT_WINDOW:
                cls = FloatWindowActivity.class;
                break;
            case MyApp.MEMOS:
                cls = MemosListActivity.class;
                break;
            default:
                break;
        }
        intent.setClass(mContext, cls);
        mContext.startActivity(intent);
    }
}
