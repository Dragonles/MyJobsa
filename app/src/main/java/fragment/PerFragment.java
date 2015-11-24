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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.job.activity.AboutWeActivity;
import com.job.activity.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerFragment extends Fragment {


    public PerFragment() {
        // Required empty public constructor
    }

    private CheckBox checkBox;      //模式切换按钮
    Button btn_renzheng;            //认证按钮
    TextView txt_jianzhilishi,txt_yaoqing,txt_zhaopin;      //兼职历史    邀请好友    招聘信用
    TextView tv_about;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_per,container,false);
        btn_renzheng = (Button) v.findViewById(R.id.btn_renzheng);
        txt_jianzhilishi = (TextView) v.findViewById(R.id.txt_jianzhilishi);
        txt_yaoqing = (TextView) v.findViewById(R.id.txt_yaoqing);
        txt_zhaopin = (TextView) v.findViewById(R.id.txt_zhaopin);
        checkBox = (CheckBox) v.findViewById(R.id.mode);
        tv_about = (TextView) v.findViewById(R.id.tv_about);

        //传递数据
        final SharedPreferences sp = getActivity().getSharedPreferences("user_type", Context.MODE_PRIVATE);

        /**
         * 模式点击切换点击事件
         * 改变个人中心
         */
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    /** false 变为招聘
                     *  改变为招聘者模式
                     */
                    txt_jianzhilishi.setText("发布岗位");
                    txt_yaoqing.setText("招聘历史");
                    txt_zhaopin.setText("招聘信用");
                    btn_renzheng.setVisibility(View.VISIBLE);
                    //传递数据
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("type",false);
                    editor.commit();
                    Log.i("CheckBoxPer", sp.getBoolean("type",false)+"");
                }else{
                    /** true 变为求职
                     *  改变为求职者模式
                     */
                    txt_jianzhilishi.setText("兼职历史");
                    txt_yaoqing.setText("邀请好友兼职");
                    txt_zhaopin.setText("兼职收入");
                    btn_renzheng.setVisibility(View.GONE);

                    //传递数据
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("type",true);
                    editor.commit();
                    Log.i("CheckBoxPer", sp.getBoolean("type",true)+"");
                }
            }
        });

        tv_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutWeActivity.class);
                startActivity(intent);
            }
        });

        return v;
        // Inflate the layout for this com.job.fragment
        // return inflater.inflate(R.layout.fragment_per, container, false);
    }


}
