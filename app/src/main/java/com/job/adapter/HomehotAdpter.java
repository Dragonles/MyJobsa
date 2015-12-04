package com.job.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.job.activity.R;
import com.job.bean.CompanyRelease;
import com.job.bean.Home_hot_item;

import java.util.List;

/**
 * Created by Administrator on 2015/11/20.
 */
public class HomehotAdpter extends BaseAdapter{
    private List<CompanyRelease> mhot_list;
    Context context;
    public HomehotAdpter(Context context,List<CompanyRelease> mhot_list)
    {
        this.context=context;
        this.mhot_list=mhot_list;
    }
    @Override
    public int getCount() {
        return mhot_list.size();
    }

    @Override
    public Object getItem(int position) {
        return mhot_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            holder= new ViewHolder();
            convertView=LayoutInflater.from(context).inflate(R.layout.home_hot_item,null);
            holder.hot_item_title=(TextView)convertView.findViewById(R.id.hot_item_title);
            holder.hot_item_money=(TextView)convertView.findViewById(R.id.hot_item_money);
            holder.hot_item_address=(TextView)convertView.findViewById(R.id.hot_item_address);
            holder.mannumber=(TextView)convertView.findViewById(R.id.person_number);
            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder)convertView.getTag();
        }
        holder.hot_item_title.setText(mhot_list.get(position).getCr_position()+"");
        holder.hot_item_money.setText(mhot_list.get(position).getCr_salary()+mhot_list.get(position).getCr_payments());
        holder.hot_item_address.setText(mhot_list.get(position).getCr_address());
        holder.mannumber.setText(mhot_list.get(position).getCr_count()+"人");
        return convertView;
    }
    class ViewHolder {

        public TextView hot_item_title;
        public TextView hot_item_money;
        public TextView hot_item_address;
        public TextView mannumber;
    }
}
