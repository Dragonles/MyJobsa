package com.job.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.job.activity.R;
import com.job.bean.CompanyRelease;

import java.util.List;

/**
 * Created by Alter on 2015/11/24.
 */
public class LocartionAdapter extends BaseAdapter {

    Context context;
    List<CompanyRelease> list;


    public LocartionAdapter(Context context, List<CompanyRelease> list) {

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

        ViewHolder holder = null;
        if(convertView == null) {
            holder= new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.list_location_item,null);
            holder.lc_leixing=(TextView)convertView.findViewById(R.id.text_jobincome_type);
            holder.lc_address=(TextView)convertView.findViewById(R.id.text_jobincome_district);
            holder.lc_money=(TextView)convertView.findViewById(R.id.text_jobincome_money);
            convertView.setTag(holder);
        } else {
            holder=(ViewHolder)convertView.getTag();
        }
        holder.lc_leixing.setText(list.get(position).getCr_position());
        holder.lc_address.setText(list.get(position).getCr_address());
        holder.lc_money.setText(list.get(position).getCr_salary().toString());
        return convertView;
    }
    class ViewHolder {

        public TextView lc_leixing;
        public TextView lc_address;
        public TextView lc_money;

    }
}
