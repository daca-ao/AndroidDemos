package com.hku.demos.aohuijun.androiddemos.appscenter.fragment.mainpageitem;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
 * The first tab of main tab in Apps Center Demo.
 */
public class ChoicePageFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final int ITEM_TYPE_COUNT = 3;
    private static final int ITEM_GROUP = 6;
    private static final int ITEM_GROUP_ITEM = 6;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_TITLE = 1;
    private static final int TYPE_RECOMMEND = 2;

    private ListView mListView;
    private ChoicePageAdapter mAdapter;
    private List<Item> choiceItemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View choiceView = inflater.inflate(R.layout.fragment_choice_page, container, false);

        choiceItemList = new ArrayList<>();
        for (int i = 0; i < ITEM_GROUP; i++) {
            Item titleItem = new Item();
            titleItem.setType(TYPE_TITLE);
            titleItem.setData(i);
            choiceItemList.add(titleItem);
            for (int j = 0; j < ITEM_GROUP_ITEM; j++) {
                Item appItem = new Item();
                appItem.setType(TYPE_ITEM);
                appItem.setData(j);
                appItem.setGroup(i);
                choiceItemList.add(appItem);
            }
            Item recommendItem = new Item();
            recommendItem.setType(TYPE_RECOMMEND);
            recommendItem.setGroup(i);
        }
        mAdapter = new ChoicePageAdapter();
        mListView = (ListView) choiceView.findViewById(R.id.apps_center_choice_list_view);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        return choiceView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Item item = choiceItemList.get(position);
        if (item.getType() == TYPE_ITEM) {
            Toast.makeText(getParentFragment().getActivity().getApplicationContext(),
                    "Item" + position, Toast.LENGTH_SHORT).show();
        } else if (item.getType() == TYPE_TITLE) {
            Toast.makeText(getParentFragment().getActivity().getApplicationContext(),
                    "More Apps (Position " + position + ")", Toast.LENGTH_SHORT).show();
        }
    }

    public static class ViewHolder {
        public ImageView appImg;
        public TextView appTitle;
        public TextView appCategory;
        public Button confirmButton;

        public TextView recommendTitle;
        public TextView recommendMore;

        public ImageView recommendImg1;
        public ImageView recommendImg2;
    }

    private class ChoicePageAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public ChoicePageAdapter() {
            mInflater = (LayoutInflater) getParentFragment().getActivity().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return choiceItemList.size();
        }

        @Override
        public Object getItem(int position) {
            return choiceItemList.get(position).getData();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public int getGroup(int position) {
            return choiceItemList.get(position).getGroup();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = new ViewHolder();
            int type = getItemViewType(position);
            if (convertView == null) {
                switch (type) {
                    case TYPE_ITEM:
                        convertView = mInflater.inflate(R.layout.item_apps_center_app, null);
                        holder.appImg = (ImageView) convertView.findViewById(R.id.item_apps_center_app_image);
                        holder.appTitle = (TextView) convertView.findViewById(R.id.item_apps_center_app_title);
                        holder.appCategory = (TextView) convertView.findViewById(R.id.item_apps_center_app_category);
                        holder.confirmButton = (Button) convertView.findViewById(R.id.item_apps_center_app_confirm);
                        break;
                    case TYPE_TITLE:
                        convertView = mInflater.inflate(R.layout.item_apps_center_title, null);
                        holder.recommendTitle = (TextView) convertView.findViewById(R.id.item_apps_center_title_recommend);
                        holder.recommendMore = (TextView) convertView.findViewById(R.id.item_apps_center_title_more);
                        break;
                    case TYPE_RECOMMEND:
                        convertView = mInflater.inflate(R.layout.item_apps_center_recommend, null);
                        holder.recommendImg1 = (ImageView) convertView.findViewById(R.id.item_apps_center_recommend_img1);
                        holder.recommendImg2 = (ImageView) convertView.findViewById(R.id.item_apps_center_recommend_img2);
                        break;
                    default:
                        break;
                }
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            switch (type) {
                case TYPE_ITEM:
                    holder.appTitle.setText("App Title " + getItem(position)
                            + ", Group " + getGroup(position));
                    holder.appCategory.setText("Category " + getGroup(position));
                    holder.confirmButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getParentFragment().getActivity().getApplicationContext(),
                                    "Wait for downloading... (" + position + ")",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                case TYPE_TITLE:
                    holder.recommendTitle.setText("Recommend App " + getItem(position));
                    break;
                case TYPE_RECOMMEND:
                    holder.recommendImg1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getParentFragment().getActivity().getApplicationContext(),
                                    "Group " + getGroup(position) + ", Recommend 1. ",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                    holder.recommendImg2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getParentFragment().getActivity().getApplicationContext(),
                                    "Group " + getGroup(position) + ", Recommend 2. ",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                default:
                    break;
            }
            return convertView;
        }

        @Override
        public int getItemViewType(int position) {
            return choiceItemList.get(position).getType();
        }

        @Override
        public int getViewTypeCount() {
            return ITEM_TYPE_COUNT;
        }
    }
}
