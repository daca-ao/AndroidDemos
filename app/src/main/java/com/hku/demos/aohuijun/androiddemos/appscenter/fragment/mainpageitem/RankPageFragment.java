package com.hku.demos.aohuijun.androiddemos.appscenter.fragment.mainpageitem;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hku.demos.aohuijun.androiddemos.R;
import com.hku.demos.aohuijun.androiddemos.appscenter.Item;

import java.util.ArrayList;
import java.util.List;

/**
 *  The second tab of main tab in Apps Center Demo.
 */
public class RankPageFragment extends Fragment {

    private static final int ITEM_COUNT = 45;
    private ListView mListView;
    private RankPageAdapter mAdapter;
    private List<Item> rankItemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rankView = inflater.inflate(R.layout.fragment_rank_page, container, false);
        rankItemList = new ArrayList<>();
        for (int i = 0; i < ITEM_COUNT; i++) {
            Item item = new Item();
            item.setData(i);
            item.setGroup(1);
            rankItemList.add(item);
        }
        mAdapter = new RankPageAdapter();
        mListView = (ListView) rankView.findViewById(R.id.apps_center_rank_list_view);
        mListView.setAdapter(mAdapter);
        return rankView;
    }

    class ViewHolder {
        public ImageView appImg;
        public TextView appTitle;
        public TextView appCategory;
        public Button confirmButton;
    }

    private class RankPageAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public RankPageAdapter() {
            mInflater = (LayoutInflater) getParentFragment().getActivity().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return rankItemList.size();
        }

        @Override
        public Object getItem(int position) {
            return rankItemList.get(position).getData();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = new ViewHolder();
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_apps_center_app, null);
                holder.appImg = (ImageView) convertView.findViewById(R.id.item_apps_center_app_image);
                holder.appTitle = (TextView) convertView.findViewById(R.id.item_apps_center_app_title);
                holder.appCategory = (TextView) convertView.findViewById(R.id.item_apps_center_app_category);
                holder.confirmButton = (Button) convertView.findViewById(R.id.item_apps_center_app_confirm);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.appTitle.setText("App Title " + getItem(position));
            holder.appCategory.setText("Category " + rankItemList.get(position).getGroup());
            holder.confirmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getParentFragment().getActivity().getApplicationContext(),
                            "Wait for downloading... (" + position + ")",
                            Toast.LENGTH_SHORT).show();
                }
            });
            return convertView;
        }
    }
}
