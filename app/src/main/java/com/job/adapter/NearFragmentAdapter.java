package com.job.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.job.activity.R;

import java.util.List;

/**
 * Created by zyh on 2015/11/22.
 */
public class NearFragmentAdapter extends BaseAdapter {
    Context context;
    List<String> list_nf;
    public NearFragmentAdapter(Context context, List<String> list_nf) {
        this.context = context;
        this.list_nf = list_nf;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return list_nf.get(position);
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
            convertView=LayoutInflater.from(context).inflate(R.layout.fragment_near_adapter_layout,null);
            vh.edit_near_job_zhiye=(TextView)convertView.findViewById(R.id.edit_near_job_zhiye);
            vh.edit_near_job_diqu=(TextView)convertView.findViewById(R.id.edit_near_job_diqu);
            vh.edit_near_job_xinzi=(TextView)convertView.findViewById(R.id.edit_near_job_xinzi);
            convertView.setTag(vh);
        }
        else {
            vh=(ViewHolder)convertView.getTag();
        }

        return convertView;
    }
    class ViewHolder {
        public TextView edit_near_job_zhiye,edit_near_job_diqu,edit_near_job_xinzi;
    }

}
