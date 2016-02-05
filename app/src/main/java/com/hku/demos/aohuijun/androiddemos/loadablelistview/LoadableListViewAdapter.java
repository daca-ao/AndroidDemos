package com.hku.demos.aohuijun.androiddemos.loadablelistview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hku.demos.aohuijun.androiddemos.R;
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
        return (position * mItemNum);
    }

    public void setData(List<Item> list) {
        this.mItemList = list;
    }

    class ViewHolder {
        ImageView leftView, middleView, rightView;
        TextView leftTextView, middleTextView, rightTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        int dataInitPos = position * mItemNum;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_list_view, null);
            holder.leftView = (ImageView) convertView.findViewById(R.id.item_list_view_image_left);
            holder.middleView = (ImageView) convertView.findViewById(R.id.item_list_view_image_middle);
            holder.rightView = (ImageView) convertView.findViewById(R.id.item_list_view_image_right);
            holder.leftTextView = (TextView) convertView.findViewById(R.id.item_list_view_text_left);
            holder.middleTextView = (TextView) convertView.findViewById(R.id.item_list_view_text_middle);
            holder.rightTextView = (TextView) convertView.findViewById(R.id.item_list_view_text_right);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //  Set contents of each row.
        holder.leftView.setBackgroundColor(mItemList.get(dataInitPos + ITEM_LEFT).getData());
        holder.leftView.setTag(dataInitPos + ITEM_LEFT);
        holder.leftView.setOnClickListener(this);
        holder.leftTextView.setText(holder.leftView.getTag().toString());
        holder.leftTextView.setTextColor(mItemList.get(dataInitPos + ITEM_LEFT).getData() == Color.BLACK ? Color.WHITE : Color.BLACK);
        if (dataInitPos + 1 < mItemList.size()) {   //  Reach the middle block.
            holder.middleView.setVisibility(View.VISIBLE);
            holder.middleView.setBackgroundColor(mItemList.get(dataInitPos + ITEM_MIDDLE).getData());
            holder.middleView.setTag(dataInitPos + ITEM_MIDDLE);
            holder.middleView.setOnClickListener(this);
            holder.middleTextView.setVisibility(View.VISIBLE);
            holder.middleTextView.setText(holder.middleView.getTag().toString());
            holder.middleTextView.setTextColor(mItemList.get(dataInitPos + ITEM_MIDDLE).getData() == Color.BLACK ? Color.WHITE : Color.BLACK);
        } else {
            holder.middleView.setVisibility(View.GONE);
            holder.middleTextView.setVisibility(View.GONE);
        }
        if (dataInitPos + 2 < mItemList.size()) {   //  Reach the right block.
            holder.rightView.setVisibility(View.VISIBLE);
            holder.rightView.setBackgroundColor(mItemList.get(dataInitPos + ITEM_RIGHT).getData());
            holder.rightView.setTag(dataInitPos + ITEM_RIGHT);
            holder.rightView.setOnClickListener(this);
            holder.rightTextView.setVisibility(View.VISIBLE);
            holder.rightTextView.setText(holder.rightView.getTag().toString());
            holder.rightTextView.setTextColor(mItemList.get(dataInitPos + ITEM_RIGHT).getData() == Color.BLACK ? Color.WHITE : Color.BLACK);
        } else {
            holder.rightView.setVisibility(View.GONE);
            holder.rightTextView.setVisibility(View.GONE);
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
