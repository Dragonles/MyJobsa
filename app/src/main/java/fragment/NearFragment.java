package fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.job.activity.FabuJobDetailsActivity;
import com.job.activity.R;
import com.job.adapter.NearFragmentAdapter;
import com.job.utils.ListViewForScrollView;

import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class NearFragment extends Fragment {

    ListView listView;
    NearFragmentAdapter nfadapter;
    List<String> list_nf;
    //声明变量
    private MapView mapView;
    private AMap aMap;

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
        LatLng latLng = new LatLng(x, y);
        LatLng mTarget = aMap.getCameraPosition().target;
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, f));
        Log.i("logss",""+mTarget);

        // 系统定位小蓝点
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        markerOptions.title("我");
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
        nfadapter = new NearFragmentAdapter(getActivity().getApplicationContext(),list_nf);
        listView.setAdapter(nfadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(), FabuJobDetailsActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

}
