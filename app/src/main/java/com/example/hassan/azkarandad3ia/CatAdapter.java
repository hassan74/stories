package com.example.hassan.azkarandad3ia;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hassan on 12/04/2017.
 */

public class CatAdapter extends BaseAdapter {
    Context context;
    List<Cat>cats=new ArrayList<Cat>();

    public CatAdapter(Context context, List<Cat> cats) {
        this.context = context;
        this.cats = cats;
    }

    @Override
    public int getCount() {
        return cats.size();
    }

    @Override
    public Object getItem(int position) {
        return cats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView=mInflater.inflate(R.layout.cat_grid_item,null);
        TextView textView=(TextView)convertView.findViewById(R.id.catname);
        Cat cat = (Cat) getItem(position);
        textView.setText(cat.getName());
        return convertView;
    }
}
