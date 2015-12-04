package fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.job.activity.AllClassActivity;
import com.job.activity.FabuDetailsActivity;
import com.job.activity.FabuJobDetailsActivity;
import com.job.activity.LocationActivity;
import com.job.activity.R;
import com.job.utils.GridViewForScrollView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * 2015-1-18
 * yanhao
 */
public class FabuFragment extends Fragment {

    /**
     * 根据个人中心设置，是商家模式还是求职者模式
     * */

    TextView myfabu,fabutitle;    //我的发布
    GridView gridView;  //GridView
    FabuFragmentAdapter fabuFragmentAdapter;    //adapter  发布页面adapter
    List<Integer> list = new ArrayList<>();     //list  放置图片
    boolean type;
    SharedPreferences sp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //设置模式开关传递，，接收  默认为true
        sp = getActivity().getSharedPreferences("user_type", Context.MODE_PRIVATE);
        type = sp.getBoolean("type",true);

        View v = inflater.inflate(R.layout.fragment_fabu, container, false);
        fabutitle = (TextView) v.findViewById(R.id.fragment_fabu_title);
        gridView = (GridViewForScrollView) v.findViewById(R.id.fragment_fa_gridview);

        Log.i("FabuFragmentflag",type+"");
        //判断是招聘 还是 求职
        if (type){
            //true  求职者模式
            fabutitle.setText("选择求职类型");
         //   myfabu.setText("兼职历史");
            Log.i("FabuFragmentflag", "**********" + type);
        }else{
            //false  招聘者模式
            fabutitle.setText("选择发布类型");
//            myfabu.setText("我的发布");
            Log.i("FabuFragmentflag","////////"+type);
        }

        //添加类型图片
       gridView.setAdapter(new FabuFragmentAdapter(getActivity()));

        //TextView点击事件
//        myfabu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity().getApplicationContext(), "点击了我的发布", Toast.LENGTH_LONG).show();
//            }
//        });

        //GridView点击事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getActivity().getApplicationContext(), "点击了：" + position, Toast.LENGTH_SHORT).show();
                if (position == 7) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), AllClassActivity.class);
                    startActivity(intent);
                } else {
                    //跳转到详情页
                    if (type) {
                        //true  求职者模式  跳转到求职界面
                        Intent intent = new Intent(getActivity().getApplicationContext(), LocationActivity.class);
                        intent.putExtra("poskey", position);
                        startActivity(intent);
                    } else {
                        //false  招聘者模式  跳转到招聘页面
                        Intent intent = new Intent(getActivity().getApplicationContext(), FabuDetailsActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });




        return v;
        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_fabu, container, false);

    }



    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        type = sp.getBoolean("type",true);
        Log.i("FabuFragment","hiddenchanfed");
        //判断是招聘 还是 求职
        if (type){
            //true  求职者模式
            fabutitle.setText("选择求职类型");
        //    myfabu.setText("兼职历史");
            Log.i("FabuFragmentflag", "**********" + type+fabutitle.getText());
        }else{
            //false  招聘者模式
            fabutitle.setText("选择发布类型");
         //   myfabu.setText("我的发布");
            Log.i("FabuFragmentflag","////////"+type+fabutitle.getText());
        }
    }

    class FabuFragmentAdapter extends BaseAdapter
    {
        private int[] images={R.drawable.anbao_img,R.drawable.xiaoshou_img,R.drawable.jiajiao_img,R.drawable.zhuchi_img,
                R.drawable.cuxiao_img,R.drawable.paidan_img,R.drawable.jiazheng_img,R.drawable.more_img};
        private String[] texts={"安保","销售","家教","主持人","促销","派单","家政","更多"};

        LayoutInflater inflater;
        public FabuFragmentAdapter(Context context)
        {
            inflater=LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return images[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v =inflater.inflate(R.layout.gridview_item,null);
            ImageView iv=(ImageView)v.findViewById(R.id.gridview_img);
            TextView tv=(TextView)v.findViewById(R.id.gridview_text);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setPadding(8, 8, 8, 8);
            iv.setImageResource(images[position]);
            tv.setText(texts[position]);
            return v;
        }
    }

}
