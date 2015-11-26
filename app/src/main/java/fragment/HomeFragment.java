package fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.gugalor.citylist.CityList;
import com.job.activity.AllClassActivity;
import com.job.activity.R;
import com.job.adapter.HomehotAdpter;
import com.job.adapter.HomejiajiAdpter;
import com.job.bean.CompanyRelease;
import com.job.bean.Home_jiaji_item;
import com.job.utils.GridViewForScrollView;
import com.job.utils.ListViewForScrollView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * 主页框架
 * A simple {@link } subclass.
 */
public class HomeFragment extends Fragment {

    List<CompanyRelease> mhot_list = new ArrayList<>();
    List<Home_jiaji_item> mjiaji_list = new ArrayList<>();
    ListView mhot_listview,mjiaji_listview;
    TextView text_city,qiandao;
    ImageView qiandaoimg;
    private GridView mgv;
    private String MY_RMBCost ="MY_RMBCost";
    private String TodayTime ="TodayTime";
    double x,y;     //经纬度
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    //设置是否返回地址信息（默认返回地址信息）
    public String city;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this com.job.fragment
        View v = inflater.inflate(R.layout.fragment_home,container,false);
        //传递数据
        final SharedPreferences sp = getActivity().getSharedPreferences("user_type", Context.MODE_PRIVATE);
        text_city = (TextView) v.findViewById(R.id.text_city);

        /**
         * 跳转城市列表页  返回定位城市或者选择城市
         *
         * */
        text_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< HEAD
//                Intent intent = new Intent(getActivity(), CityList.class);
//                startActivityForResult(intent, 1);
=======
                Intent intent = new Intent(getActivity(), CityList.class);
                startActivityForResult(intent, 1);
>>>>>>> d25eeba0516b750c0700752d203edecf295bc3d3
            }
        });



        mgv=(GridViewForScrollView)v.findViewById(R.id.home_gridview);
        mgv.setAdapter(new GridViewAdpter(getActivity()));
        mhot_listview=(ListViewForScrollView)v.findViewById(R.id.home_hot_list);
        mjiaji_listview=(ListViewForScrollView)v.findViewById(R.id.home_jiaji_list);
        qiandao=(TextView)v.findViewById(R.id.home_font_qiandao);
        qiandaoimg=(ImageView)v.findViewById(R.id.qiandaoimg);
        SharedPreferences my_rmb_data = getActivity().getSharedPreferences(MY_RMBCost, 0);
        Time t = new Time();
        t.setToNow();
        int lastmonth = t.month + 1 ;
        final String str =  t.year + "年" + lastmonth + "月" + t.monthDay + "日";
        final String nowtime =my_rmb_data.getString(TodayTime, "").toString();
        if(nowtime.equals(str)==true)
        {
            qiandao.setText("已签到！");
            qiandaoimg.setBackgroundResource(R.drawable.conversation_needhandle_icon_normal2);
        }
        else
        {
          //  qiandao.setText("日期："+ str);
            qiandaoimg.setBackgroundResource(R.drawable.conversation_needhandle_icon_normal2);
        }

        //签到功能
        qiandao.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                qiandao.setText("已签到！");
                qiandao.setTextSize(12);
               // qiandaoimg.setImageResource(R.drawable.conversation_needhandle_icon_normal);
                SharedPreferences my_rmb_data = getActivity().getSharedPreferences(MY_RMBCost, 0);
                if (my_rmb_data.getString(TodayTime, "").toString().equals(str) == true) {
                    Toast.makeText(getActivity(), "今日已签到！", Toast.LENGTH_SHORT).show();
                } else {
                    my_rmb_data.edit()
                            .putString(TodayTime, str)
                            .commit();
                    // qiandao.setText("日期："+ str +"已签到！");
                    qiandaoimg.setBackgroundResource(R.drawable.conversation_needhandle_icon_normal2);
                    Toast.makeText(getActivity(), "签到成功！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        BmobQuery<CompanyRelease> query = new BmobQuery<CompanyRelease>();
        query.findObjects(getActivity(), new FindListener<CompanyRelease>() {
            @Override
            public void onSuccess(List<CompanyRelease> object) {
                // TODO Auto-generated method stub
              //  toast("查询成功：共" + object.size() + "条数据。");
                for (CompanyRelease gameScore : object) {
                    mhot_list.add(new CompanyRelease(gameScore.getTitle(),gameScore.getCr_salary(),gameScore.getCr_address()));
                }
                mhot_listview.setAdapter(new HomehotAdpter(getActivity(),mhot_list));
            }

            @Override
            public void onError(int code, String msg) {
                // TODO Auto-generated method stub
              //  toast("查询失败：" + msg);
            }
        });
        mgv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 7) {
                    Intent intent = new Intent(getActivity(), AllClassActivity.class);
                    startActivity(intent);
                    Log.i("ttt",position+"ppppppppppp");
                }
            }
        });
        mjiaji_listview.setAdapter(new HomejiajiAdpter(getActivity(), mjiaji_list));
        mhot_listview.setAdapter(new HomehotAdpter(getActivity(), mhot_list));

        text_city.setText("定位");
        //定位
        //初始化定位
        mLocationClient = new AMapLocationClient(getActivity().getApplicationContext());
        //设置定位回调监听

        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {

                    if (amapLocation.getErrorCode() == 0) {
                        //定位成功回调信息，设置相关消息
                        amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        Log.i("vv", amapLocation.getLatitude() + "");
                        amapLocation.getLatitude();//获取经度
                        x = amapLocation.getLatitude();
                        Log.i("vv", amapLocation.getLongitude() + "");
                        amapLocation.getLongitude();//获取纬度
                        y = amapLocation.getLongitude();
                        amapLocation.getAccuracy();//获取精度信息
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(amapLocation.getTime());
                        df.format(date);//定位时间
                        //  mLocationOption.setNeedAddress(true);
                        amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果
                        amapLocation.getCountry();//国家信息
                        amapLocation.getProvince();//省信息
                        amapLocation.getCity();//城市信息

                        amapLocation.getDistrict();//城区信息
                        amapLocation.getRoad();//街道信息
                        amapLocation.getCityCode();//城市编码
                        amapLocation.getAdCode();//地区编码
                        city = amapLocation.getCity();

                        //传递经纬度  城市
                        Log.i("jinweidu",x+"        "+y);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("x", x+"");
                        editor.putString("y", y + "");
                        editor.putString("city", city);
                        editor.commit();
                        text_city.setText(city);
//                        Toast.makeText(getActivity().getApplicationContext(),city,Toast.LENGTH_LONG).show();
                        Log.i("AmapError", amapLocation.getDistrict() + df.format(date) + amapLocation.getCity());
                        String s = amapLocation.getCity();
                        //Ttv.setText(s);
                    } else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        Log.i("AmapError", "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
            }
        });
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(20000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
//        Toast.makeText(getActivity().getApplicationContext(), city, Toast.LENGTH_LONG);
//        text_city.setText(dw.city);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null)
            switch (resultCode) {
                case 2:
                    text_city.setText(data.getStringExtra("city"));
                    break;

                default:
                    break;
            }
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
