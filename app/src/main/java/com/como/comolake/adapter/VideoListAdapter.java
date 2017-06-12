package com.como.comolake.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.como.comolake.R;

import java.util.ArrayList;

/**
 * Created by prolance on 6/4/16.
 */
public class VideoListAdapter extends ArrayAdapter<String>{

    Context context;
    int layout;
    int nSelectedItem = 0;

    ArrayList<String> videoNames;

    public VideoListAdapter(Context context, int layout, ArrayList<String> videoNames)
    {
        super(context, layout, videoNames);
        this.context = context;
        this.layout = layout;
        this.videoNames = videoNames;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(layout, null);
        ((TextView) convertView.findViewById(R.id.txt_videoname)).setText(videoNames.get(position));

        if (nSelectedItem == position)
        {
            convertView.findViewById(R.id.txt_videoname).setBackgroundColor(context.getResources().getColor(R.color.colorHighlightSelected));
        }
        else
        {
            convertView.findViewById(R.id.txt_videoname).setBackgroundColor(context.getResources().getColor(R.color.colorHighlightUnselected));
        }

        return convertView;
    }

    public ArrayList<String> getVideoNames(){
        return videoNames;
    }

    public void setChatContents(ArrayList<String> videoNames){
        this.videoNames = videoNames;
    }

    public void setSelectedItem(int nSelectedItem) {
        this.nSelectedItem = nSelectedItem;
    }
}

