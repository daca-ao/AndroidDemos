package com.hku.demos.aohuijun.androiddemos.appscenter.fragment.mainpageitem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.hku.demos.aohuijun.androiddemos.R;

/**
 * The third tab of main tab in Apps Center Demo.
 */
public class NewPageFragment extends Fragment {

    private static final int ITEM_COUNT = 45;
    private ListView mListView;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View newView = inflater.inflate(R.layout.fragment_new_page, container, false);
        mListView = (ListView) newView.findViewById(R.id.apps_center_new_list_view);
        return newView;
    }

    private class NewPageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
