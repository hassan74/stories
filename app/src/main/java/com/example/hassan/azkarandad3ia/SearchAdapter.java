package com.example.hassan.azkarandad3ia;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hassan on 11/04/2017.
 */
public class SearchAdapter extends BaseAdapter {
    private Context context;
    List<Story>stories=new ArrayList<Story>();
    public SearchAdapter(Context c ,List<Story>stories)
    {
        context=c ;
        this.stories=stories;
    }
    @Override
    public int getCount() {
        return stories.size();
    }

    @Override
    public Object getItem(int position) {
        return stories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView ;
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView =mInflater.inflate(R.layout.story_grid_item,null);
        textView = (TextView)convertView.findViewById(R.id.storyName);
        Story story=stories.get(position);
        String storyName =story.getTitle();
        textView.setText(storyName);
        return convertView;
    }
}
