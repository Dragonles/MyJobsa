package com.job.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.baidu.android.pushservice.PushMessageReceiver;
import com.job.activity.TuisongActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2015/11/30.
 */
public class PushTestReceiver extends PushMessageReceiver {
    public  static String channe_id;
    @Override
    public void onBind(Context context, int errorCode, String appid, String userId, String channelId, String requestId) {
    //    Log.i("chinleid",s2);
        String responseString = "onBind errorCode=" + errorCode + " appid="
                + appid + " userId=" + userId + " channelId=" + channelId
                + " requestId=" + requestId;
        Log.i("fengfeng", "fengfegn" + responseString+"chid"+channelId);
        channe_id=channelId;
//        Intent intent = new Intent();
//        intent.setClass(context.getApplicationContext(),ShanActivity.class);
//        intent.putExtra("chanelid", channelId);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.getApplicationContext().startActivity(intent);
    }

    @Override
    public void onUnbind(Context context, int i, String s) {

    }

    @Override
    public void onSetTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onListTags(Context context, int i, List<String> list, String s) {

    }

    @Override
    public void onMessage(Context context, String s, String s1) {

    }

    @Override
    public void onNotificationClicked(Context context, String s, String s1, String s2) {
        Log.i("fengfeng",s+"clicked"+s1+ "  "+  s2);
        Intent intent = new Intent(context.getApplicationContext(),TuisongActivity.class);
        try {
            intent.putExtra("qid", new JSONObject(s2).getString("qid"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(intent);
    }
    @Override
    public void onNotificationArrived(Context context, String s, String s1, String s2) {

    }
}
