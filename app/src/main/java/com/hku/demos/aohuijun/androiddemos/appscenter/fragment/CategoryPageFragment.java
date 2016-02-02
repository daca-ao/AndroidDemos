package com.hku.demos.aohuijun.androiddemos.appscenter.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hku.demos.aohuijun.androiddemos.R;

/**
 * The second tab of the Apps Center Demo.
 */
public class CategoryPageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().setTitle(R.string.title_fragment_category);
        }
        View categoryView = inflater.inflate(R.layout.fragment_category_page, container, false);
        TextView categoryTitleView = (TextView) categoryView.findViewById(R.id.apps_center_category_content);
        categoryTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),
                        getResources().getString(R.string.title_fragment_category),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return categoryView;
    }

}
