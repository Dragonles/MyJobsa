package fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.job.activity.AboutWeActivity;
import com.job.activity.CircleImageView;
import com.job.activity.CompanyProveActivity;
import com.job.activity.CreatJianliActivity;
import com.job.activity.FabuDetailsActivity;
import com.job.activity.FankuiActivity;
import com.job.activity.JifenActivity;
import com.job.activity.JobShouruActivity;
import com.job.activity.LoginActivity;
import com.job.activity.MessageActivity;
import com.job.activity.MyRecruitActivity;
import com.job.activity.R;
import com.job.activity.RecruitHistoryActivity;
import com.job.activity.UpdateInformationActivity;
import com.job.bean.CompanyProve;
import com.job.bean.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerFragment extends Fragment {


    public PerFragment() {
        // Required empty public constructor
    }

    private CheckBox checkBox;      //模式切换按钮
    public static  Button btn_renzheng;            //认证按钮
    TextView txt_jianzhilishi,txt_yaoqing,txt_zhaopin,usrname;      //兼职历史    邀请好友    招聘信用
    TextView tv_about;
    TextView xiaoxi;  //消息中心
    Button login_out,jianli;
    LinearLayout history,idea;
    ImageView iv_job_history_img,iv_job_friends_img,iv_job_xinyong_img;
    CircleImageView usericon;
    LinearLayout user_message,jifen;
    boolean zq = true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_per,container,false);
        xiaoxi = (TextView) v.findViewById(R.id.xiaoxi);
        btn_renzheng = (Button) v.findViewById(R.id.btn_renzheng);
        txt_jianzhilishi = (TextView) v.findViewById(R.id.txt_jianzhilishi);
        txt_yaoqing = (TextView) v.findViewById(R.id.txt_yaoqing);
        history=(LinearLayout)v.findViewById(R.id.history);
        txt_zhaopin = (TextView) v.findViewById(R.id.txt_zhaopin);
        checkBox = (CheckBox) v.findViewById(R.id.mode);
        user_message=(LinearLayout)v.findViewById(R.id.user_message);
        tv_about = (TextView) v.findViewById(R.id.tv_about);
        usericon=(CircleImageView)v.findViewById(R.id.user_img);
        usrname=(TextView)v.findViewById(R.id.username);
        login_out=(Button)v.findViewById(R.id.login_outs);
        jifen=(LinearLayout)v.findViewById(R.id.jifen);
        idea=(LinearLayout)v.findViewById(R.id.idea);
        jianli=(Button)v.findViewById(R.id.jianli);
        iv_job_history_img = (ImageView) v.findViewById(R.id.job_history_img);
        iv_job_friends_img = (ImageView) v.findViewById(R.id.job_friends_img);
        iv_job_xinyong_img = (ImageView) v.findViewById(R.id.job_xinyong_img);
        idea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), FankuiActivity.class);
                startActivity(intent);
            }
        });

        user_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), UpdateInformationActivity.class);
                startActivity(intent);
            }
        });
        jianli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), CreatJianliActivity.class);
                startActivity(intent);
            }
        });

        //消息中心
        xiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zq) {
                    //求职者模式
                    Intent intent = new Intent(getActivity().getApplicationContext(), MessageActivity.class);
                    startActivity(intent);
                }else {
                    //招聘者模式

                }
            }
        });

        Bmob.initialize(getActivity(), "e98c629c488e891e6d090798dd2ced7f");
        final BmobUser bmobUser = BmobUser.getCurrentUser(getActivity());
        if(bmobUser != null){
            usrname.setText(bmobUser.getUsername());
            BmobQuery<User> query = new BmobQuery<User>();
            query.getObject(getActivity(), bmobUser.getObjectId(), new GetListener<User>() {
                @Override
                public void onSuccess(User object) {
                    Picasso.with(getActivity()).load(object.getUser_icon()).into(usericon);
                    Log.i("coe", object.getUser_icon().toString());
                }

                @Override
                public void onFailure(int code, String arg0) {
                    // TODO Auto-generated method stub
                    Log.i("ss", code + arg0);
                }

            });
            btn_renzheng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(getActivity(), CompanyProveActivity.class);
                    startActivity(intent);
                }
            });
            login_out.setText("退出登录");
        }else{
            login_out.setText("请登录");
        }



        login_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser bmobUsers = BmobUser.getCurrentUser(getActivity());
                if(bmobUsers != null) {
                    Log.i("bilibili", "已经登录");
//                    login_out.setText("退出登录");

                    BmobUser.logOut(getActivity());   //清除缓存用户对象
                    BmobUser currentUser = BmobUser.getCurrentUser(getActivity()); // 现在的currentUser是null了
                    login_out.setText("请登录");
                    usrname.setText("username");
                    btn_renzheng.setText("认证");

                    btn_renzheng.setBackgroundResource(R.drawable.per_button_style);
                    usericon.setImageResource(R.drawable.user_head);

                }else{
                    Log.i("bilibili","未登录");
                    login_out.setText("请登录");
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    getActivity().finish();
                    startActivity(intent);
                }
            }
        });
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
                    zq = false;
                    /** false 变为招聘
                     *  改变为招聘者模式
                     */
                    txt_jianzhilishi.setText("发布岗位");
                    txt_yaoqing.setText("我的发布");
                    txt_zhaopin.setText("招聘信用");
                    btn_renzheng.setVisibility(View.VISIBLE);
                    iv_job_xinyong_img.setBackgroundResource(R.drawable.button_credit);  //招聘信用
                    iv_job_friends_img.setBackgroundResource(R.drawable.button_jobhistory);  //招聘历史
                    iv_job_history_img.setBackgroundResource(R.drawable.button_releasehistory);  //发布岗位
                    //传递数据
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("type", false);
                    editor.commit();
                    BmobUser bu = BmobUser.getCurrentUser(getActivity());
                    BmobQuery<CompanyProve> cpbq = new BmobQuery<CompanyProve>();
//                    Log.i("ddfdfdfdf", "" + bu.getObjectId());
                    if (bu != null) {
                        cpbq.addWhereEqualTo("user_id", bu.getObjectId());
                        cpbq.findObjects(getActivity(), new FindListener<CompanyProve>() {
                            @Override
                            public void onSuccess(List<CompanyProve> list) {
                                Log.i("list",list.get(0)+"");
                                if (list.size() != 0) {
                                    Log.i("log", "true" + list.size());
                                    if (list.get(0).getProve_flag().equals("ok")) {
                                        btn_renzheng.setText("认证V");
                                        Log.i("log", "走过");
                                        btn_renzheng.setBackgroundResource(R.drawable.yeellow_button_style);
                                    } else {
                                        btn_renzheng.setText("认证");
                                        btn_renzheng.setBackgroundResource(R.drawable.per_button_style);
                                    }
                                } else {
                                    btn_renzheng.setText("认证V");
                                    btn_renzheng.setBackgroundResource(R.drawable.per_button_style);
                                }

                            }
                            @Override
                            public void onError(int i, String s) {
                                Log.i("ss",i+s);
                            }
                        });
                    }
                }else{
                    zq = true;
                    /** true 变为求职
                     *  改变为求职者模式
                     */
                    txt_jianzhilishi.setText("兼职历史");
                    txt_yaoqing.setText("我的积分");
                    txt_zhaopin.setText("兼职收入");
                    btn_renzheng.setVisibility(View.GONE);
                    iv_job_xinyong_img.setBackgroundResource(R.drawable.button_money);  //兼职收入
                    iv_job_friends_img.setBackgroundResource(R.drawable.button_friends);  //邀请好友兼职
                    iv_job_history_img.setBackgroundResource(R.drawable.button_jobhistory);  //兼职历史
                    //传递数据
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("type",true);
                    editor.commit();
                    Log.i("CheckBoxPer", sp.getBoolean("type",true)+"");
                }
            }
        });
        //兼职收入
        txt_zhaopin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zq){
                    //求职者
                    Log.i("perfragmentss","求职者");
                    Intent intent = new Intent(getActivity(), JobShouruActivity.class);
                    startActivity(intent);
                    Toast.makeText(getActivity().getApplicationContext(),"求职者",Toast.LENGTH_LONG).show();

                }else{
                    //招聘者
                    Log.i("perfragmentss", "招聘者");
                    Toast.makeText(getActivity().getApplicationContext(),"招聘者",Toast.LENGTH_LONG).show();

//                            Intent intent = new Intent(getActivity(), MyRecruitActivity.class);
//                            startActivity(intent);
                }
            }
        });
        jifen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zq) {
                    //求职者
                    Intent intent =new Intent(getActivity(),JifenActivity.class);
                    startActivity(intent);
                    Log.i("perfragmentss", "求职者   我的积分");
                    Toast.makeText(getActivity().getApplicationContext(), "求职者", Toast.LENGTH_LONG).show();
                } else {
                    //招聘者
                    Log.i("perfragmentss", "招聘者");
                    Intent intent = new Intent(getActivity(), MyRecruitActivity.class);
                    startActivity(intent);
                }
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zq){
                    //求职者
                    Log.i("perfragmentss", "求职者");
                    Intent intent = new Intent(getActivity(), RecruitHistoryActivity.class);
                    startActivity(intent);
                    Toast.makeText(getActivity().getApplicationContext(),"求职者",Toast.LENGTH_LONG).show();

                }else{
                    //招聘者
                    Log.i("perfragmentss", "招聘者");
                    Toast.makeText(getActivity().getApplicationContext(),"招聘者",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getActivity(), FabuDetailsActivity.class);
                    startActivity(intent);
                }
            }
        });


        txt_jianzhilishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zq){
                    //求职者
                    Log.i("perfragmentss","求职者");
                    Intent intent = new Intent(getActivity(), RecruitHistoryActivity.class);
                    startActivity(intent);
                    Toast.makeText(getActivity().getApplicationContext(),"求职者",Toast.LENGTH_LONG).show();

                }else{
                    //招聘者
                    Log.i("perfragmentss","招聘者");
                    Toast.makeText(getActivity().getApplicationContext(),"招聘者",Toast.LENGTH_LONG).show();

//                            Intent intent = new Intent(getActivity(), MyRecruitActivity.class);
//                            startActivity(intent);
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
        jifen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zq) {
                    //求职者
                    Intent intent =new Intent(getActivity(),JifenActivity.class);
                    startActivity(intent);
                    Log.i("perfragmentss", "求职者   我的积分");
                    Toast.makeText(getActivity().getApplicationContext(), "求职者", Toast.LENGTH_LONG).show();
                } else {
                    //招聘者
                    Log.i("perfragmentss", "招聘者");
                    Intent intent = new Intent(getActivity(), MyRecruitActivity.class);
                    startActivity(intent);
                }
            }
        });
        return v;
        // Inflate the layout for this com.job.fragment
        // return inflater.inflate(R.layout.fragment_per, container, false);
    }



}
