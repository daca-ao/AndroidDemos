package com.hku.demos.aohuijun.androiddemos.loadablelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hku.demos.aohuijun.androiddemos.appscenter.Item;

import java.util.List;

/**
 * Created by aohuijun on 16/2/4.
 */
public class LoadableListViewAdapter extends BaseAdapter implements View.OnClickListener {
    private static final int ITEM_LEFT = 0;
    private static final int ITEM_MIDDLE = 1;
    private static final int ITEM_RIGHT = 2;

    private Context mContext;
    private List<Item> mItemList;
    private LayoutInflater mInflater;
    private int mItemNum;

    public LoadableListViewAdapter(Context context, int num) {
        this.mContext = context;
        this.mItemNum = num;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        int count = mItemList.size() / mItemNum;
        return mItemList.size() % mItemNum == 0 ? count : count + 1;
    }

    @Override
    public Object getItem(int position) {
        return mItemList.get(position * mItemNum).getData();
    }

    @Override
    public long getItemId(int position) {
        return position * mItemNum;
    }

    class ViewHolder {
        ImageView leftView, middleView, rightView;
        TextView leftTextView, middleTextView, rightTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(mContext.getApplicationContext(),
                        v.getTag().toString(),
                        Toast.LENGTH_SHORT).show();
    }
}
