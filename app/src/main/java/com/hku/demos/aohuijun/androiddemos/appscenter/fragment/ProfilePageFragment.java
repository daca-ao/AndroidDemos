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
 * The fourth tab of the Apps Center Demo.
 */
public class ProfilePageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().setTitle(R.string.title_fragment_profile);
        }
        View profileView = inflater.inflate(R.layout.fragment_profile_page, container, false);
        TextView profileTitleView = (TextView) profileView.findViewById(R.id.apps_center_profile_content);
        profileTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),
                        getResources().getString(R.string.title_fragment_profile),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return profileView;
    }

}
