package com.job.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.job.activity.JobShouruActivity;

import java.util.List;

/**
 * Created by Alter on 2015/11/20.
 */
public class JobShouruAdapter extends BaseAdapter{

    Context context;
    List<String> list;


    public JobShouruAdapter(Context context, List<String> list) {

        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        return null;
    }
}
