package com.job.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.job.activity.R;
import com.job.bean.Home_jiaji_item;

import java.util.List;

/**
 * Created by Administrator on 2015/11/20.
 */
public class HomejiajiAdpter  extends BaseAdapter{

    private List<Home_jiaji_item> mjiaji_list;
    Context context;
    public HomejiajiAdpter(Context context,List<Home_jiaji_item> mjiaji_list)
    {
        this.context=context;
        this.mjiaji_list=mjiaji_list;
    }
    @Override
    public int getCount() {
        return mjiaji_list.size();
    }

    @Override
    public Object getItem(int position) {
        return mjiaji_list.get(position);
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
            convertView= LayoutInflater.from(context).inflate(R.layout.home_jiaji_item,null);
            holder.hot_item_title=(TextView)convertView.findViewById(R.id.hot_item_title);
            holder.hot_item_money=(TextView)convertView.findViewById(R.id.hot_item_money);
            holder.hot_item_address=(TextView)convertView.findViewById(R.id.hot_item_address);
            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder)convertView.getTag();
        }
        holder.hot_item_title.setText(mjiaji_list.get(position).getHot_title());
        holder.hot_item_money.setText(mjiaji_list.get(position).getHot_moneny());
        holder.hot_item_address.setText(mjiaji_list.get(position).getHot_address());
        return convertView;
    }
    class ViewHolder
    {

        public TextView hot_item_title;
        public TextView hot_item_money;
        public TextView hot_item_address;

    }

}
