package com.job.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.job.activity.R;
import com.job.bean.All_class_item;
import com.job.bean.Home_hot_item;

import java.util.List;

/**
 * Created by Administrator on 2015/11/20.
 */
public class AllclassAdpter  extends BaseAdapter{

    private List<All_class_item> alllist;
    Context context;
    public AllclassAdpter(Context context,List<All_class_item> alllist)
    {
        this.context=context;
        this.alllist=alllist;
    }
    @Override
    public int getCount() {
        return alllist.size();
    }

    @Override
    public Object getItem(int position) {
        return alllist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null)
        {
            holder= new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.all_class_item,null);
            holder.title=(TextView)convertView.findViewById(R.id.all_calss_text);
            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder)convertView.getTag();
        }
        holder.title.setText(alllist.get(position).getCalsstext());
        return convertView;
    }
    class ViewHolder
    {

        public TextView title;

    }
}
