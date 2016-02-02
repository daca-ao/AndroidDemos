package com.hku.demos.aohuijun.androiddemos.appscenter.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.hku.demos.aohuijun.androiddemos.R;

/**
 * The third tab of the Apps Center Demo.
 */
public class SearchPageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().setTitle(R.string.title_fragment_search);
        }
        View searchView =inflater.inflate(R.layout.fragment_search_page, container, false);
        EditText searchInputText = (EditText) searchView.findViewById(R.id.apps_center_search_content);
        return searchView;
    }

}
