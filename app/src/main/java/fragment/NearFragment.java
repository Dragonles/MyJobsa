package fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Text;
import com.amap.api.maps.model.TextOptions;
import com.job.activity.FabuJobDetailsActivity;
import com.job.activity.R;
import com.job.adapter.NearFragmentAdapter;
import com.job.bean.CompanyRelease;
import com.job.bean.UserRelease;
import com.job.utils.ListViewForScrollView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class NearFragment extends Fragment {

    SwipeRefreshLayout swipeRefreshLayout;
    ListView listView;
    LinearLayout linear;
    NearFragmentAdapter nfadapter;
    List<CompanyRelease> list_n =new ArrayList<>();
    //声明变量
    private MapView mapView;
    private AMap aMap;

    LatLng latLng;
    SharedPreferences sp;
    double x;
    double y;    //接收经纬度
    String city;
    float f = (float) 16.0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_near,container,false);
        listView = (ListViewForScrollView) v.findViewById(R.id.lv);
        linear=(LinearLayout)v.findViewById(R.id.linner1);
        swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.sr);

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,android.R.color.holo_red_light,android.R.color.holo_green_light,android.R.color.holo_orange_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                BmobQuery<CompanyRelease> query = new BmobQuery<CompanyRelease>();
                query.findObjects(getActivity(), new FindListener<CompanyRelease>() {
                    @Override
                    public void onSuccess(List<CompanyRelease> object) {
                        list_n.clear();
                        for (CompanyRelease gameScore : object) {
                            list_n.add(new CompanyRelease(gameScore.getCr_address(), gameScore.getCr_require(), gameScore.getCr_count()));
                            Log.i("adddd",gameScore.getCr_address() );
                        }
                        nfadapter = new NearFragmentAdapter(getActivity().getApplicationContext(), list_n);
                        listView.setAdapter(nfadapter);

                    }

                    @Override
                    public void onError(int code, String msg) {
                    }
                });
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setPadding(0, 0, 0, 0);
//                        tv.setText("刷新成功");
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 5000);
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(), FabuJobDetailsActivity.class);
                startActivity(intent);
            }
        });
        //得到自身位置经纬度
        sp = getActivity().getSharedPreferences("user_type", Context.MODE_PRIVATE);
        x = Double.valueOf(sp.getString("x", "11"));
        y = Double.valueOf(sp.getString("y", "11"));
        city = sp.getString("city", "临沂");
        Log.i("logss", city + x + y + "   " + f);

        //在onCreat方法中给aMap对象赋值
        mapView = (MapView) v.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 必须要写
        aMap = mapView.getMap();
        Log.i("logss", "9999999");

        //改变地图视图为自己的位置
        latLng = new LatLng(x, y);
        LatLng mTarget = aMap.getCameraPosition().target;
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, f));
        Log.i("logss",""+mTarget);

        // 系统定位小蓝点
//
//        aMap.setOnMarkerDragListener((AMap.OnMarkerDragListener) this);// 设置marker可拖拽事件监听器
//        aMap.setOnMapLoadedListener((AMap.OnMapLoadedListener) this);// 设置amap加载成功事件监听器
//        aMap.setOnMarkerClickListener((AMap.OnMarkerClickListener) this);// 设置点击marker事件监听器
//        aMap.setOnInfoWindowClickListener((AMap.OnInfoWindowClickListener) this);// 设置点击infoWindow事件监听器
//        aMap.setInfoWindowAdapter((AMap.InfoWindowAdapter) this);// 设置自定义InfoWindow样式
        addMarkersToMap();// 往地图上添加marker

//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        markerOptions.visible(true);
//        markerOptions.anchor((float) x,(float) y);
//        aMap.addMarker(markerOptions);
        
//        MyLocationStyle myLocationStyle = new MyLocationStyle();
//        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker));
//        myLocationStyle.strokeColor(Color.BLACK);
//        myLocationStyle.strokeWidth(5);
//        aMap.setMyLocationStyle(myLocationStyle);

        // 获取当前地图中心点的坐标
        float mZoom = aMap.getCameraPosition().zoom;
        Toast.makeText(getActivity().getApplicationContext(),mZoom+"**"+mTarget,Toast.LENGTH_LONG).show();
        Log.i("suofanfang", mZoom + "");

        //listview适配器


        return v;
    }


    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap() {
        //文字显示标注，可以设置显示内容，位置，字体大小颜色，背景色旋转角度
            TextOptions textOptions = new TextOptions().position(latLng)
                .text("Text").fontColor(Color.BLACK)
                .backgroundColor(Color.BLUE).fontSize(30).rotate(20).align(Text.ALIGN_CENTER_HORIZONTAL, Text.ALIGN_CENTER_VERTICAL)
                .zIndex(1.f).typeface(Typeface.DEFAULT_BOLD)
                ;

//        ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
//        giflist.add(BitmapDescriptorFactory.fromResource(R.drawable.location_marker));
//        giflist.add(BitmapDescriptorFactory);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng)
                .visible(true)
                .title("123123")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker));
        aMap.addMarker(markerOptions);

//        Marker marker = aMap.addMarker(new MarkerOptions()
//
//                .title("好好学习")
//                .icon(BitmapDescriptorFactory
//                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
//                .draggable(true));
//        marker.setRotateAngle(90);// 设置marker旋转90度
//        marker.setPositionByPixels(400, 400);
//        marker.showInfoWindow();// 设置默认显示一个infowinfow
//
//        markerOption = new MarkerOptions();
//        markerOption.position(SyncStateContract.Constants.XIAN);
//        markerOption.title("西安市").snippet("西安市：34.341568, 108.940174");
//
//        markerOption.draggable(true);
//        markerOption.icon(
//                // BitmapDescriptorFactory
//                // .fromResource(R.drawable.location_marker)
//                BitmapDescriptorFactory.fromBitmap(BitmapFactory
//                        .decodeResource(getResources(),
//                                R.drawable.location_marker)));
//        // 将Marker设置为贴地显示，可以双指下拉看效果
//        markerOption.setFlat(true);
//
//        ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
//        giflist.add(BitmapDescriptorFactory
//                .defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//        giflist.add(BitmapDescriptorFactory
//                .defaultMarker(BitmapDescriptorFactory.HUE_RED));
//        giflist.add(BitmapDescriptorFactory
//                .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
//
//        MarkerOptions markerOption1 = new MarkerOptions().anchor(0.5f, 0.5f)
//                .position(SyncStateContract.Constants.CHENGDU).title("成都市")
//                .snippet("成都市:30.679879, 104.064855").icons(giflist)
//                .draggable(true).period(50);
//        ArrayList<MarkerOptions> markerOptionlst = new ArrayList<MarkerOptions>();
//        markerOptionlst.add(markerOption);
//        markerOptionlst.add(markerOption1);
//        List<Marker> markerlst = aMap.addMarkers(markerOptionlst, true);
//        marker2 = markerlst.get(0);
    }


}
