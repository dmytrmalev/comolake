package com.como.comolake.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.como.comolake.R;
import com.como.comolake.activity.MainActivity;
import com.como.comolake.dto.FragmentStack;
import com.como.comolake.util.Constants;
import com.como.comolake.util.Variables;

import org.w3c.dom.Text;

public class CategoryFragment extends Fragment {

    TextView txtParticularity;
    TextView txtComocity;
    TextView txtTown;
    TextView txtVilla;

    public interface OnShowVideoListListener {
        void onShowVideoList();
    };

    OnShowVideoListListener onShowVideoListListener;

    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
        return fragment;
    }

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View convertView = inflater.inflate(R.layout.fragment_category, container, false);

        txtParticularity = (TextView) convertView.findViewById(R.id.txt_particular);
        txtComocity = (TextView) convertView.findViewById(R.id.txt_comocity);
        txtTown = (TextView) convertView.findViewById(R.id.txt_town);
        txtVilla = (TextView) convertView.findViewById(R.id.txt_villa);

        txtParticularity.setText(Constants.MAIN_CATEGORIES[0][Variables.nLanguageIndex]);
        txtComocity.setText(Constants.MAIN_CATEGORIES[1][Variables.nLanguageIndex]);
        txtTown.setText(Constants.MAIN_CATEGORIES[2][Variables.nLanguageIndex]);
        txtVilla.setText(Constants.MAIN_CATEGORIES[3][Variables.nLanguageIndex]);

        txtParticularity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenListFragment(Constants.PARTICULARITY);
            }
        });

        txtComocity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenListFragment(Constants.COMOCITY);
            }
        });

        txtTown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenListFragment(Constants.TOWN);
            }
        });

        txtVilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenListFragment(Constants.VILLA);
            }
        });

        return convertView;
    }

    public void OpenListFragment(int index) {
        Variables.nCategoryIndex = index;
        Variables.nVideoIndex = 0;
        onShowVideoListListener.onShowVideoList();
        //((MainActivity) getActivity()).openView(ListFragment.newInstance(), new FragmentStack());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onShowVideoListListener = (OnShowVideoListListener) activity;
    }

}
