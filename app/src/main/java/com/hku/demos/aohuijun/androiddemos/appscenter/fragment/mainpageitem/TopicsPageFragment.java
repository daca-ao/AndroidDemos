package com.hku.demos.aohuijun.androiddemos.appscenter.fragment.mainpageitem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hku.demos.aohuijun.androiddemos.R;

/**
 * The fourth tab of main tab in Apps Center Demo.
 */
public class TopicsPageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View topicsView = inflater.inflate(R.layout.fragment_topics_page, container, false);
        TextView topicsTitleView = (TextView) topicsView.findViewById(R.id.apps_center_topics_content);
        topicsTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),
                        getResources().getString(R.string.title_fragment_topics),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return topicsView;
    }

}
