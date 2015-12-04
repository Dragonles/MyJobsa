package com.job.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
public class MyRecruitAdapter extends BaseAdapter {
    Context context;
    List<CompanyRelease> list_cr;
    String zt;
    public MyRecruitAdapter(Context context, List<CompanyRelease> list_cr) {
        this.context = context;
        this.list_cr = list_cr;
    }

    @Override
    public int getCount() {
        return list_cr.size();
    }

    @Override
    public Object getItem(int position) {
        return list_cr.get(position);
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
        vh.text_jobincome_type.setText(list_cr.get(position).getCr_position().toString());
        vh.text_jobincome_district.setText(list_cr.get(position).getCr_address().toString());
        vh.text_job_count.setText(list_cr.get(position).getCr_count().toString());
//        vh.text_jobincome_zhuangtai.setText(list_cr.get(position).getCr_state().toString());
        zt = list_cr.get(position).getCr_state();
        vh.text_jobincome_zhuangtai.setText(zt);
//        final ViewHolder finalVh = vh;
        final ViewHolder finalVh = vh;
        vh.text_jobincome_zhuangtai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("已完成".equals(zt)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("完成任务");
                    builder.setIcon(android.R.drawable.ic_dialog_info);
                    builder.setCancelable(true);
                    builder.setMessage("已完成，不可修改");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
//                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("未完成");
                    builder.setIcon(android.R.drawable.ic_dialog_info);
                    builder.setCancelable(true);
                    builder.setMessage("是否确定完成任务");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            zt = "已完成";
                            finalVh.text_jobincome_zhuangtai.setText(zt);
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });

        return convertView;
    }
    class ViewHolder {
        public TextView text_jobincome_type,text_jobincome_district,text_job_count,text_jobincome_zhuangtai;
    }
}
