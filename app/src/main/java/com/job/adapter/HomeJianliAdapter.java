package com.job.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.job.activity.R;
import com.job.bean.UserRelease;

import java.util.List;

/**
 * Created by zyh on 2015/12/3.
 */
public class HomeJianliAdapter extends BaseAdapter {
    Context context;
     List<UserRelease> mjianli_list;
    public HomeJianliAdapter(Context context, List<UserRelease> mjianli_list) {
        this.context = context;
        this.mjianli_list = mjianli_list;
    }

    @Override
    public int getCount() {
        return mjianli_list.size();
    }

    @Override
    public Object getItem(int position) {
        return mjianli_list.get(position);
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
            convertView= LayoutInflater.from(context).inflate(R.layout.home_jianli_item,null);
            holder.txt_home_jianli_name = (TextView)convertView.findViewById(R.id.txt_home_jianli_name);
            holder.txt_home_jianli_xinzi = (TextView)convertView.findViewById(R.id.txt_home_jianli_xinzi);
            holder.txt_home_jianli_address = (TextView)convertView.findViewById(R.id.txt_home_jianli_address);
            holder.txt_home_jianli_xueli = (TextView) convertView.findViewById(R.id.txt_home_jianli_xueli);
            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder)convertView.getTag();
        }
//        String name = mjianli_list.get(position).getUr_name();
        holder.txt_home_jianli_name.setText(mjianli_list.get(position).getUr_name() );
        holder.txt_home_jianli_xinzi.setText(mjianli_list.get(position).getUr_money());
        holder.txt_home_jianli_address.setText(mjianli_list.get(position).getUr_address());
        holder.txt_home_jianli_xueli.setText(mjianli_list.get(position).getUr_study());
        Log.i("rtpess","简历adapter");
        return convertView;
    }
    class ViewHolder {
        private TextView txt_home_jianli_name, txt_home_jianli_xinzi, txt_home_jianli_address, txt_home_jianli_xueli;
    }
}
