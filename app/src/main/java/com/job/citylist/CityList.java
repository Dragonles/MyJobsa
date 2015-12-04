package com.job.citylist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.*;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.job.activity.R;
import com.job.adapter.HotCityGridAdapter;
import com.job.helper.ContactsHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
/**
 * 城市选择
 *
 * @author gugalor
 */
public class CityList extends Activity {
    private BaseAdapter adapter;
    private ListView mCityLit;
    private TextView overlay, citysearch;
    private ImageButton backbutton;
    private MyLetterListView letterListView;
    private HashMap<String, Integer> alphaIndexer;
    private String[] sections;
    private Handler handler;
    private OverlayThread overlayThread;
    private SQLiteDatabase database;
    private ArrayList<CityModel> mCityNames;
    private View city_locating_state;
    private View city_locate_failed;
    private TextView city_locate_state;
    private ProgressBar city_locating_progress;
    private ImageView city_locate_success_img;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    //设置是否返回地址信息（默认返回地址信息）
    public String city;

    View hotcityall;

    String[] hotcity = new String[]{"北京", "上海", "广州", "深圳", "杭州", "南京", "天津", "武汉", "重庆"};
    WindowManager windowManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater localLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View city_layout = localLayoutInflater.inflate(R.layout.public_cityhot, null);
        setContentView(city_layout);

        citysearch = (TextView) city_layout.findViewById(R.id.city_search_edittext);
        backbutton = (ImageButton) city_layout.findViewById(R.id.title_left_btn);
        mCityLit = (ListView) city_layout.findViewById(R.id.public_allcity_list);
        letterListView = (MyLetterListView) city_layout.findViewById(R.id.cityLetterListView);

        View cityhot_header_blank = localLayoutInflater.inflate(R.layout.public_cityhot_header_padding_blank, mCityLit, false);
        mCityLit.addHeaderView(cityhot_header_blank, null, false);
        cityhot_header_blank = localLayoutInflater.inflate(R.layout.city_locate_layout, mCityLit, false);
        city_locating_state = cityhot_header_blank.findViewById(R.id.city_locating_state);
        city_locate_state = ((TextView) cityhot_header_blank.findViewById(R.id.city_locate_state));
        city_locating_progress = ((ProgressBar) cityhot_header_blank.findViewById(R.id.city_locating_progress));
        city_locate_success_img = ((ImageView) cityhot_header_blank.findViewById(R.id.city_locate_success_img));
        city_locate_failed = cityhot_header_blank.findViewById(R.id.city_locate_failed);
        mCityLit.addHeaderView(cityhot_header_blank);

        View hotheadview = localLayoutInflater.inflate(R.layout.public_cityhot_header_padding, mCityLit, false);
        mCityLit.addHeaderView(hotheadview, null, false);
        hotcityall = localLayoutInflater.inflate(R.layout.public_cityhot_allcity, mCityLit, false);
        final GridView localGridView = (GridView) hotcityall.findViewById(R.id.public_hotcity_list);

        mCityLit.addHeaderView(hotcityall);
        HotCityGridAdapter adapter = new HotCityGridAdapter(this, Arrays.asList(hotcity));
        localGridView.setAdapter(adapter);
        localGridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cityModel =  parent.getAdapter()
                        .getItem(position).toString();
                Setting.Save2SharedPreferences(CityList.this, "city",
                        cityModel);
                Intent intent =new Intent();
                intent.putExtra("city",cityModel);
                setResult(2,intent);
                finish();
            }
        });

        city_locating_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityModel=city_locate_state.getText().toString();
                Setting.Save2SharedPreferences(CityList.this, "city",
                        cityModel);
                Intent intent =new Intent();
                intent.putExtra("city",cityModel);
                setResult(2,intent);
                finish();
            }
        });
        loadLocation();


        DBManager dbManager = new DBManager(this);
        dbManager.openDateBase();
        dbManager.closeDatabase();
        database = SQLiteDatabase.openOrCreateDatabase(DBManager.DB_PATH + "/"
                + DBManager.DB_NAME, null);
        mCityNames = getCityNames();
        database.close();
        letterListView
                .setOnTouchingLetterChangedListener(new LetterListViewListener());
        alphaIndexer = new HashMap<String, Integer>();
        handler = new Handler();
        overlayThread = new OverlayThread();
        initOverlay();
        setAdapter(mCityNames);
        mCityLit.setOnItemClickListener(new CityListOnItemClick());
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        citysearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean startLoad = ContactsHelper.getInstance().startLoadContacts();

                Intent intent = new Intent(CityList.this, searchactivity.class);
                startActivityForResult(intent, 2);
                return false;
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                String city=data.getStringExtra("city");
                Intent intent =new Intent();
                intent.putExtra("city",city);
                setResult(2,intent);
                finish();
                break;

            default:
                break;
        }
    }

    /**
     * 获取位置
     */
    public void loadLocation() {

        city_locate_failed.setVisibility(View.GONE);
        city_locate_state.setVisibility(View.VISIBLE);
        city_locating_progress.setVisibility(View.VISIBLE);
        city_locate_success_img.setVisibility(View.GONE);
        city_locate_state.setText(getString(R.string.locating));
        //定位
        //初始化定位
        mLocationClient = new AMapLocationClient(CityList.this.getApplicationContext());
        //设置定位回调监听

        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {

                    if (amapLocation.getErrorCode() == 0) {
                        mLocationClient.stopLocation();
                        city_locate_failed.setVisibility(View.GONE);
                        city_locate_state.setVisibility(View.VISIBLE);
                        city_locating_progress.setVisibility(View.GONE);
                        city_locate_success_img.setVisibility(View.VISIBLE);
                        city_locate_state.setText(amapLocation.getCity());
                    } else {
                        // 定位失败
                        city_locating_state.setVisibility(View.GONE);
                        city_locate_failed.setVisibility(View.VISIBLE);
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

    }

    /**
     * @return
     */
    private ArrayList<CityModel> getCityNames() {
        ArrayList<CityModel> names = new ArrayList<CityModel>();
        Cursor cursor = database.rawQuery(
                "SELECT * FROM T_City ORDER BY NameSort", null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            CityModel cityModel = new CityModel();
            cityModel.setCityName(cursor.getString(cursor
                    .getColumnIndex("CityName")));
            cityModel.setNameSort(cursor.getString(cursor
                    .getColumnIndex("NameSort")));
            names.add(cityModel);
        }
        cursor.close();
        return names;
    }

    /**
     * б 1/4
     *
     * @author
     */
    class CityListOnItemClick implements OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                long arg3) {
            CityModel cityModel = (CityModel) mCityLit.getAdapter()
                    .getItem(pos);
            if(cityModel!=null) {
                Setting.Save2SharedPreferences(CityList.this, "city",
                        cityModel.getCityName());
                Intent intent =new Intent();
                intent.putExtra("city",cityModel.getCityName());
                setResult(2,intent);
                finish();
            }
        }

    }

    /**
     * ListView
     *
     * @param list
     */
    private void setAdapter(List<CityModel> list) {
        if (list != null) {
            adapter = new ListAdapter(this, list);
            mCityLit.setAdapter(adapter);
        }

    }

    /**
     * ListViewAdapter
     *
     * @author gugalor
     */
    private class ListAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private List<CityModel> list;

        public ListAdapter(Context context, List<CityModel> list) {

            this.inflater = LayoutInflater.from(context);
            this.list = list;
            alphaIndexer = new HashMap<String, Integer>();
            sections = new String[list.size()];

            for (int i = 0; i < list.size(); i++) {
                //
                // getAlpha(list.get(i));
                String currentStr = list.get(i).getNameSort();
                //
                String previewStr = (i - 1) >= 0 ? list.get(i - 1)
                        .getNameSort() : " ";
                if (!previewStr.equals(currentStr)) {
                    String name = list.get(i).getNameSort();
                    alphaIndexer.put(name, i);
                    sections[i] = name;
                }
            }

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
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.public_cityhot_item,
                        null);
                holder = new ViewHolder();
                holder.alpha = (TextView) convertView.findViewById(R.id.alpha);
                holder.name = (TextView) convertView
                        .findViewById(R.id.public_cityhot_item_textview);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.name.setText(list.get(position).getCityName());
            String currentStr = list.get(position).getNameSort();
            String previewStr = (position - 1) >= 0 ? list.get(position - 1)
                    .getNameSort() : " ";
            if (!previewStr.equals(currentStr)) {
                holder.alpha.setVisibility(View.VISIBLE);
                holder.alpha.setText(currentStr);
            } else {
                holder.alpha.setVisibility(View.GONE);
            }
            return convertView;
        }

        private class ViewHolder {
            TextView alpha;
            TextView name;
        }

    }

    // ’
    private void initOverlay() {
        LayoutInflater inflater = LayoutInflater.from(this);
        overlay = (TextView) inflater.inflate(R.layout.overlay, null);
        overlay.setVisibility(View.INVISIBLE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        windowManager = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(overlay, lp);
    }
    @Override
    protected void onDestroy() {
        windowManager.removeView(overlay);
        super.onDestroy();
    }
    private class LetterListViewListener implements
            MyLetterListView.OnTouchingLetterChangedListener {

        @Override
        public void onTouchingLetterChanged(final String s) {
            if (alphaIndexer.get(s) != null) {
                int position = alphaIndexer.get(s);
                mCityLit.setSelection(position);
                overlay.setText(sections[position]);
                overlay.setVisibility(View.VISIBLE);
                handler.removeCallbacks(overlayThread);
                // Уoverlay
                handler.postDelayed(overlayThread, 1500);
            }
        }

    }

    // overlay
    private class OverlayThread implements Runnable {

        @Override
        public void run() {
            overlay.setVisibility(View.GONE);
        }

    }

}