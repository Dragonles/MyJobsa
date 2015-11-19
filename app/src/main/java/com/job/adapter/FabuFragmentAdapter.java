package com.job.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.job.activity.R;

import java.util.List;

import fragment.FabuFragment;

/**
 * Created by zyh on 2015/11/18.
 */
public class FabuFragmentAdapter extends BaseAdapter {
    Context context;
    List<Integer> list;
    public FabuFragmentAdapter(Context context, List<Integer> list) {
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
//        ImageView iv = null;
//        if (convertView == null){
//            iv = new ImageView(context);
//            //设置ImageView对象布局
//            iv.setLayoutParams(new GridView.LayoutParams(150,150));
//            //设置图片如何调整大小或移动来匹对ImageView的size
//            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            iv.setPadding(0,0,0,0);//设置间距
//        }else {
//            iv = (ImageView) convertView;
//        }
//        iv.setImageResource(list.get(position));
        convertView = LayoutInflater.from(context).inflate(R.layout.fragment_fabu_adapter_layout,null);
        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        image.setImageResource(list.get(position));
        return convertView;
    }
}
