package fragment;


import android.app.ProgressDialog;
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
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.job.activity.AllClassActivity;
import com.job.activity.FabuJobDetailsActivity;
import com.job.activity.LocationActivity;
import com.job.activity.R;
import com.job.activity.UserJianliDetailsActivity;
import com.job.adapter.HomeJianliAdapter;
import com.job.adapter.HomehotAdpter;
import com.job.adapter.HomejiajiAdpter;
import com.job.bean.CompanyProve;
import com.job.bean.CompanyRelease;
import com.job.bean.Home_jiaji_item;
import com.job.bean.Qiandao;
import com.job.bean.UserRelease;
import com.job.citylist.CityList;
import com.job.utils.GridViewForScrollView;
import com.job.utils.ListViewForScrollView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 主页框架
 * A simple {@link } subclass.
 */
public class HomeFragment extends Fragment {

    List<CompanyRelease> mhot_list = new ArrayList<>();      //热门list
    List<CompanyRelease> mjiaji_list = new ArrayList<>();  //加急list
    List<UserRelease> mjianli_list = new ArrayList<>();     //简历list
    HomeJianliAdapter homeJianliAdapter;    //简历adapter
    HomejiajiAdpter homejiajiAdpter;
    HomehotAdpter homehotAdpter;
    ListView mhot_listview,mjiaji_listview;   //热门listview   加急listview
    TextView text_city,qiandao;
     int Jifen=0;
    ImageView qiandaoimg;
    EditText edit_sousuo;   //顶部搜索框

    TextView txt_jiaji;     //加急TextView
    LinearLayout linear_remen;
    SharedPreferences spf;
    boolean type = true;


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
        edit_sousuo = (EditText) v.findViewById(R.id.edit_sousuo);
        linear_remen = (LinearLayout) v.findViewById(R.id.linear_remen);
        txt_jiaji = (TextView) v.findViewById(R.id.txt_jiaji);

        //设置模式开关传递，，接收  默认为true
        spf = getActivity().getSharedPreferences("user_type", Context.MODE_PRIVATE);
        type = spf.getBoolean("type", true);
        Log.i("rtpess", ""+type);


        final ProgressDialog pd = ProgressDialog.show(getActivity(), "", "正在加载数据.....");
        //传递数据
        final SharedPreferences sp = getActivity().getSharedPreferences("user_type", Context.MODE_PRIVATE);
        edit_sousuo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Intent intent = new Intent(getActivity().getApplicationContext(), LocationActivity.class);
                startActivity(intent);
            }
        });
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
        Bmob.initialize(getActivity(), "e98c629c488e891e6d090798dd2ced7f");
        final BmobUser bmobUser = BmobUser.getCurrentUser(getActivity());
        if(bmobUser != null){
            qiandao.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    qiandao.setText("已签到！");
                    qiandao.setTextSize(12);
                    Jifen++;
                    Bmob.initialize(getActivity(), "e98c629c488e891e6d090798dd2ced7f");
                    BmobQuery<Qiandao> cpbq = new BmobQuery<Qiandao>();
                    cpbq.addWhereEqualTo("user_id",bmobUser.getObjectId());
                    cpbq.findObjects(getActivity(), new FindListener<Qiandao>() {
                        @Override
                        public void onSuccess(List<Qiandao> list) {
                            if (list.size()!=0)
                            {
                                Qiandao gameScore = new Qiandao();
                                gameScore.setJifen(Jifen+"");
                                gameScore.update(getActivity(),list.get(0).getObjectId(), new UpdateListener() {

                                    @Override
                                    public void onSuccess() {
                                        // TODO Auto-generated method stub
                                        Log.i("bmob","更新成功：");
                                    }

                                    @Override
                                    public void onFailure(int code, String msg) {
                                        // TODO Auto-generated method stub
                                        Log.i("bmob","更新失败："+msg);
                                    }
                                });
                            }
                            else {
                                Qiandao gameScore = new Qiandao();
                                gameScore.setJifen(Jifen+"");
                                gameScore.setUser_id(bmobUser.getObjectId());
                                gameScore.save(getActivity(), new SaveListener() {

                                    @Override
                                    public void onSuccess() {
                                        // TODO Auto-generated method stub
                                        //  toast("添加数据成功，返回objectId为："+gameScore.getObjectId() + ”,数据在服务端的创建时间为：“ + gameScore.getCreatedAt());
                                    }

                                    @Override
                                    public void onFailure(int code, String arg0) {
                                        // TODO Auto-generated method stub
                                        // 添加失败
                                    }
                                });
                            }
                        }

                        @Override
                        public void onError(int i, String s) {

                        }
                    });
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
        }else{
            //缓存用户对象为空时， 可打开用户注册界面…
        }

//        BmobQuery<CompanyRelease> query = new BmobQuery<CompanyRelease>();
//        query.findObjects(getActivity(), new FindListener<CompanyRelease>() {
//            @Override
//            public void onSuccess(List<CompanyRelease> object) {
//                // TODO Auto-generated method stub
//              //  toast("查询成功：共" + object.size() + "条数据。");
//                for (CompanyRelease gameScore : object) {
//                    mhot_list.add(new CompanyRelease(gameScore.getTitle(),gameScore.getCr_salary(),gameScore.getCr_address()));
//                }
//                mhot_listview.setAdapter(new HomehotAdpter(getActivity(),mhot_list));
//            }
//
//            @Override
//            public void onError(int code, String msg) {
//                // TODO Auto-generated method stub
//              //  toast("查询失败：" + msg);
//            }
//        });
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
        Bmob.initialize(getActivity(), "e98c629c488e891e6d090798dd2ced7f");

        Bmob.initialize(getActivity(), "e98c629c488e891e6d090798dd2ced7f");
        //判断是招聘 还是 求职
        Log.i("rtpess", "**" + type);
        if (type){
            //true  求职者模式
            //获得加急数据
            Log.i("rtpess", "求职者模式");
            txt_jiaji.setText("加急：");
            linear_remen.setVisibility(View.VISIBLE);

            BmobQuery<CompanyRelease> crbq = new BmobQuery<>();
//            BmobQuery<CompanyRelease> crbq = new BmobQuery<>();
            crbq.addWhereEqualTo("cr_urgency", "true");
            crbq.findObjects(getActivity(), new FindListener<CompanyRelease>() {
                @Override
                public void onSuccess(List<CompanyRelease> list) {
                    mjiaji_list = list;
                    Log.i("sdsdsdsd", mjiaji_list.size() + "加急");
                    homejiajiAdpter = new HomejiajiAdpter(getActivity().getApplicationContext(), mjiaji_list);
                    mjiaji_listview.setAdapter(homejiajiAdpter);
                    Log.i("rtpess", "求职*/*/者模式");

                }

                @Override
                public void onError(int i, String s) {
                    Toast.makeText(getActivity().getApplicationContext(), "加载失败，请检查网络", Toast.LENGTH_LONG).show();
                }
            });

            //获得热门数据
            BmobQuery<CompanyRelease> crbqj = new BmobQuery<>();
            crbqj.findObjects(getActivity(), new FindListener<CompanyRelease>() {
                @Override
                public void onSuccess(List<CompanyRelease> list) {
                    mhot_list = list;
                    Log.i("sdsdsdsd", mhot_list.size() + "热门");
                    pd.dismiss();
                    homehotAdpter = new HomehotAdpter(getActivity().getApplicationContext(), mhot_list);
                    mhot_listview.setAdapter(homehotAdpter);
                    Log.i("rtpess", "求职*/热门*/者模式");
                }

                @Override
                public void onError(int i, String s) {
                    pd.dismiss();
                    Toast.makeText(getActivity().getApplicationContext(), "加载失败，请检查网络", Toast.LENGTH_LONG).show();
                }
            });


            Log.i("FabuFragmentflag", "**********" + type);
        }else{
            //false  招聘者模式
            txt_jiaji.setText("推荐简历：");
            //隐藏热门listview
            linear_remen.setVisibility(View.GONE);
            //获得简历数据
            Log.i("rtpess", "招聘者模式"+type);

            BmobQuery<UserRelease> urbq = new BmobQuery<>();
            urbq.findObjects(getActivity(), new FindListener<UserRelease>() {
                @Override
                public void onSuccess(List<UserRelease> list) {
                    mjianli_list = list;
                    Log.i("rtpess", mjianli_list.size() + "简历");
                    homeJianliAdapter = new HomeJianliAdapter(getActivity().getApplicationContext(), mjianli_list);
                    mjiaji_listview.setAdapter(homeJianliAdapter);
                    pd.dismiss();
                    Log.i("rtpess", "招聘者/*/*/模式");
                }

                @Override
                public void onError(int i, String s) {
                    Toast.makeText(getActivity().getApplicationContext(), "加载失败，请检查网络", Toast.LENGTH_LONG).show();
                }
            });
            Log.i("FabuFragmentflag","////////"+type);
        }

        //加急/简历   listview  点击事件
        mjiaji_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //判断是招聘 还是 求职
                if (type) {
                    //true  求职者模式
                    //跳转到招聘信息详细页
                    Intent jintent = new Intent(getActivity(), FabuJobDetailsActivity.class);
                    jintent.putExtra("keyposj", position);
                    startActivity(jintent);
                }else {
                    //false  招聘者模式
                    //跳转到个人简历详细页
                    Intent hotintent = new Intent(getActivity(), UserJianliDetailsActivity.class);
                    hotintent.putExtra("keypos", position);
                    startActivity(hotintent);
                }
            }
        });

        //热门listview 点击事件
        mhot_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent hotintent = new Intent(getActivity(), FabuJobDetailsActivity.class);
                hotintent.putExtra("keypos",position);
                startActivity(hotintent);
            }
        });

        //gridview点击事件
        mgv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //更多按钮
                if (position == 7) {
                    Intent intent = new Intent(getActivity(), AllClassActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), LocationActivity.class);
                    intent.putExtra("poskey", position);
                    startActivity(intent);
                }
            }
        });


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
        mLocationOption.setOnceLocation(true);
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
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i("rtpess", "4564646");
        //显示正在加载的框
        final ProgressDialog pds = ProgressDialog.show(getActivity(), "", "正在加载数据.....");
        type = spf.getBoolean("type",true);
        //判断是招聘 还是 求职
        if (type){
            //true  求职者模式
            txt_jiaji.setText("加急：");

            //隐藏热门listview
            linear_remen.setVisibility(View.VISIBLE);
            //获得加急数据
            Log.i("rtpess","qiuzhi   1111111111");
            BmobQuery<CompanyRelease> crbq = new BmobQuery<>();
            crbq.addWhereEqualTo("cr_urgency", "true");
            crbq.findObjects(getActivity(), new FindListener<CompanyRelease>() {
                @Override
                public void onSuccess(List<CompanyRelease> list) {
                    mjiaji_list = list;
                    Log.i("sdsdsdsd", mjiaji_list.size() + "加急");
                    homejiajiAdpter = new HomejiajiAdpter(getActivity().getApplicationContext(), mjiaji_list);
                    mjiaji_listview.setAdapter(homejiajiAdpter);
                }

                @Override
                public void onError(int i, String s) {
                    Toast.makeText(getActivity().getApplicationContext(), "加载失败，请检查网络", Toast.LENGTH_LONG).show();
                }
            });

            //获得热门数据
            BmobQuery<CompanyRelease> crbqj = new BmobQuery<>();
            crbqj.findObjects(getActivity(), new FindListener<CompanyRelease>() {
                @Override
                public void onSuccess(List<CompanyRelease> list) {
                    mhot_list = list;
                    Log.i("sdsdsdsd", mhot_list.size() + "热门");
                    pds.dismiss();
                    homehotAdpter = new HomehotAdpter(getActivity().getApplicationContext(), mhot_list);
                    mhot_listview.setAdapter(homehotAdpter);
                }

                @Override
                public void onError(int i, String s) {
                    pds.dismiss();
                    Toast.makeText(getActivity().getApplicationContext(), "加载失败，请检查网络", Toast.LENGTH_LONG).show();
                }
            });


            Log.i("FabuFragmentflag", "**********" + type);
        }else{
            //false  招聘者模式
            txt_jiaji.setText("推荐简历：");

            //隐藏热门listview
            linear_remen.setVisibility(View.GONE);
            //获得简历数据
            BmobQuery<UserRelease> crbq = new BmobQuery<>();
            crbq.findObjects(getActivity(), new FindListener<UserRelease>() {
                @Override
                public void onSuccess(List<UserRelease> list) {
                    mjianli_list = list;
                    Log.i("sdsdsdsd", mjiaji_list.size() + "加急");
                    pds.dismiss();
                    homeJianliAdapter = new HomeJianliAdapter(getActivity().getApplicationContext(), mjianli_list);
                    mjiaji_listview.setAdapter(homeJianliAdapter);
                    Log.i("rtpess","jianli 444444");
                }

                @Override
                public void onError(int i, String s) {
                    Toast.makeText(getActivity().getApplicationContext(), "加载失败，请检查网络", Toast.LENGTH_LONG).show();
                }
            });
            Log.i("FabuFragmentflag","////////"+type);
        }
    }

}
