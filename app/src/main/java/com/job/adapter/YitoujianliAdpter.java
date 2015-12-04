package com.job.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.job.activity.R;
import com.job.bean.Classify;
import com.job.bean.CompanyRelease;

import java.util.List;

/**
 * Created by Administrator on 2015/12/4.
 */
public class YitoujianliAdpter extends BaseAdapter {
    private List<CompanyRelease> alllist;
    Context context;
    public YitoujianliAdpter(Context context,List<CompanyRelease> alllist)
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
        if(convertView == null) {
            holder= new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.yitou_item,null);
            holder.title=(TextView)convertView.findViewById(R.id.hot_item_title);
            holder.peron=(TextView)convertView.findViewById(R.id.hot_item_money);
            holder.adress=(TextView)convertView.findViewById(R.id.hot_item_address);
            convertView.setTag(holder);
        } else {
            holder=(ViewHolder)convertView.getTag();
        }
        holder.title.setText(alllist.get(position).getCr_position());
        holder.peron.setText(alllist.get(position).getCr_position());
        holder.adress.setText(alllist.get(position).getCr_address());
        return convertView;
    }
    class ViewHolder {

        public TextView title,peron,adress;
    }
}
