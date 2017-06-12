package com.como.comolake.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.como.comolake.R;
import com.como.comolake.activity.MainActivity;
import com.como.comolake.activity.PlayerActivity;
import com.como.comolake.adapter.VideoListAdapter;
import com.como.comolake.dto.FragmentStack;
import com.como.comolake.util.Constants;
import com.como.comolake.util.Descriptions;
import com.como.comolake.util.Variables;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    public ListFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View convertView = inflater.inflate(R.layout.fragment_list, container, false);
        final ListView lstVideo = (ListView) convertView.findViewById(R.id.lst_video);
        ArrayList<String> lstVideoNames = new ArrayList<String>();

        for (int i = 0; i < Constants.CAT_VIDEO_TITLES[Variables.nCategoryIndex].length; i++) {
            lstVideoNames.add(Constants.CAT_VIDEO_TITLES[Variables.nCategoryIndex][i][Variables.nLanguageIndex]);
        }
        final VideoListAdapter videoListAdapter = new VideoListAdapter(getActivity(), R.layout.adapter_videolist, lstVideoNames);
        lstVideo.setAdapter(videoListAdapter);

        ((TextView) convertView.findViewById(R.id.txt_category)).setText(Constants.MAIN_CATEGORIES[Variables.nCategoryIndex][Variables.nLanguageIndex]);
        ((TextView) convertView.findViewById(R.id.txt_title)).setText(Constants.CAT_VIDEO_TITLES[Variables.nCategoryIndex][Variables.nVideoIndex][Variables.nLanguageIndex]);
        ((TextView) convertView.findViewById(R.id.txt_description)).setText(Descriptions.descriptions[Variables.nCategoryIndex][Variables.nVideoIndex][Variables.nLanguageIndex]);
        ((ImageView) convertView.findViewById(R.id.img_thumbnail)).setImageResource(Constants.LIST_COVER_IMAGES[Variables.nCategoryIndex][Variables.nVideoIndex]);

        videoListAdapter.setSelectedItem(Variables.nVideoIndex);
        videoListAdapter.notifyDataSetChanged();

        lstVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Variables.nVideoIndex = position;

                ((TextView) convertView.findViewById(R.id.txt_title)).setText(Constants.CAT_VIDEO_TITLES[Variables.nCategoryIndex][position][Variables.nLanguageIndex]);
                ((TextView) convertView.findViewById(R.id.txt_description)).setText(Descriptions.descriptions[Variables.nCategoryIndex][position][Variables.nLanguageIndex]);
                ((ImageView) convertView.findViewById(R.id.img_thumbnail)).setImageResource(Constants.LIST_COVER_IMAGES[Variables.nCategoryIndex][position]);

                videoListAdapter.setSelectedItem(position);
                videoListAdapter.notifyDataSetChanged();
            }
        });

        lstVideo.setDivider(null);

        convertView.findViewById(R.id.img_thumbnail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlayerActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return convertView;
    }
}
