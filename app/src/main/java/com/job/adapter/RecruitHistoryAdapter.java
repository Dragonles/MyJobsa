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
 * Created by zyh on 2015/11/25.
 */
public class RecruitHistoryAdapter extends BaseAdapter {

    Context context;
    List<CompanyRelease> list_rh;
    public RecruitHistoryAdapter(Context context, List<CompanyRelease> list_rh) {
        this.context = context;
        this.list_rh = list_rh;
    }

    @Override
    public int getCount() {
        return list_rh.size();
    }

    @Override
    public Object getItem(int position) {
        return list_rh.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if(convertView == null) {
            vh= new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.list_myrecruit_layout,null);
            vh.text_jobincome_type = (TextView) convertView.findViewById(R.id.text_jobincome_type);
            vh.text_jobincome_district = (TextView) convertView.findViewById(R.id.text_jobincome_district);
            vh.text_job_count = (TextView) convertView.findViewById(R.id.text_job_count);
            vh.text_jobincome_zhuangtai = (TextView) convertView.findViewById(R.id.text_jobincome_zhuangtai);
            convertView.setTag(vh);
        }
        vh=(ViewHolder)convertView.getTag();
        vh.text_jobincome_type.setText(list_rh.get(position).getCr_position().toString());
        vh.text_jobincome_district.setText(list_rh.get(position).getCr_address().toString());
        vh.text_job_count.setText(list_rh.get(position).getCr_count().toString());
        vh.text_jobincome_zhuangtai.setText(list_rh.get(position).getCr_state().toString()
        );

        return convertView;
    }
    class ViewHolder {
        public TextView text_jobincome_type,text_jobincome_district,text_job_count,text_jobincome_zhuangtai;
    }
}
