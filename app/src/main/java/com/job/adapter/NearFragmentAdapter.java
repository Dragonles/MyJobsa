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

import java.util.List;

/**
 * Created by zyh on 2015/11/22.
 */
public class NearFragmentAdapter extends BaseAdapter {
    Context context;
    List<CompanyRelease> list_nf;
    public NearFragmentAdapter(Context context, List<CompanyRelease> list_nf) {
        this.context = context;
        this.list_nf=list_nf;
    }

    public NearFragmentAdapter(String cr_address, Integer cr_count, String cr_require) {

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
            vh.text_near_distance=(TextView)convertView.findViewById(R.id.text_near_distance);
            vh.text_near_placename=(TextView)convertView.findViewById(R.id.text_near_placename);
            vh.text_near_jobnead=(TextView)convertView.findViewById(R.id.text_near_jobnead);
            vh.text_near_money=(TextView)convertView.findViewById(R.id.text_near_money);
            convertView.setTag(vh);
        }
        else {
            vh=(ViewHolder)convertView.getTag();
        }

        return convertView;
    }
    class ViewHolder {
        public TextView text_near_distance,text_near_placename,text_near_jobnead,text_near_money;
    }

}
