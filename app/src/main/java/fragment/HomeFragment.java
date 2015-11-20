package fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.job.activity.AllClassActivity;
import com.job.activity.R;
import com.job.adapter.HomehotAdpter;
import com.job.adapter.HomejiajiAdpter;
import com.job.bean.Home_hot_item;
import com.job.bean.Home_jiaji_item;
import com.job.utils.GridViewForScrollView;
import com.job.utils.ListViewForScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页框架
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    List<Home_hot_item> mhot_list = new ArrayList<>();
    List<Home_jiaji_item> mjiaji_list = new ArrayList<>();
    ListView mhot_listview,mjiaji_listview;
    Button mmore;
    private GridView mgv;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this com.job.fragment
        View v = inflater.inflate(R.layout.fragment_home,container,false);
        mgv=(GridViewForScrollView)v.findViewById(R.id.home_gridview);
        mgv.setAdapter(new GridViewAdpter(getActivity()));
        mhot_listview=(ListViewForScrollView)v.findViewById(R.id.home_hot_list);
        mjiaji_listview=(ListViewForScrollView)v.findViewById(R.id.home_jiaji_list);
        mjiaji_list.add(new Home_jiaji_item("急招一名JS人员","地址：临沂市兰山区警察局","薪资可面议"));
        mjiaji_list.add(new Home_jiaji_item("急招一名PS人员","地址：临沂市兰山区民政局","薪资可面议"));
        mhot_list.add(new Home_hot_item("烫头搓澡需100人","临沂市烫头镇马云家","70元/天"));
        mhot_list.add(new Home_hot_item("烫头搓澡需100人","临沂市烫头镇马云家","70元/天"));
        mhot_list.add(new Home_hot_item("烫头搓澡需100人","临沂市烫头镇马云家","70元/天"));
        mhot_list.add(new Home_hot_item("烫头搓澡需100人","临沂市烫头镇马云家","70元/天"));
        mhot_list.add(new Home_hot_item("烫头搓澡需100人","临沂市烫头镇马云家","70元/天"));
        mhot_list.add(new Home_hot_item("烫头搓澡需100人","临沂市烫头镇马云家","70元/天"));
        mhot_list.add(new Home_hot_item("烫头搓澡需100人","临沂市烫头镇马云家","70元/天"));
        mhot_list.add(new Home_hot_item("烫头搓澡需100人","临沂市烫头镇马云家","70元/天"));
        mgv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==7)
                {
                    Intent intent =new Intent(getActivity(), AllClassActivity.class);
                    startActivity(intent);
                }
            }
        });
        mjiaji_listview.setAdapter(new HomejiajiAdpter(getActivity(),mjiaji_list));
        mhot_listview.setAdapter(new HomehotAdpter(getActivity(),mhot_list));
        return v;
    }

    class GridViewAdpter extends BaseAdapter
    {
        private int[] images={R.drawable.anbao_img,R.drawable.xiaoshou_img,R.drawable.jiajiao_img,R.drawable.zhuchi_img,
        R.drawable.cuxiao_img,R.drawable.paidan_img,R.drawable.jiazheng_img,R.drawable.more_img};
        private String[] texts={"安保","销售","家教","主持人","促销","派单","家政","更多"};

        LayoutInflater inflater;
        public GridViewAdpter(Context context)
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
