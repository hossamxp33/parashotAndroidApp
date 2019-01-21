package com.codesroots.hossam.parashotApp.helper;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import com.codesroots.hossam.parashotApp.entities.OrderDetails;
import com.codesroots.hossam.parashotApp.entities.UserInfo;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URISyntaxException;


public class MyService extends Service
{
    MyReceiver mReceiver;
    boolean isRegistered = false;
    String userKey1 = null;
    public static com.github.nkzawa.socketio.client.Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://parashotescoket.codesroots.com:2800");
            //    mSocket = IO.socket("http://192.168.1.25:2400");
        }
        catch (URISyntaxException e) {
        }
    }

    @Override
    public void onCreate() {
        if (mReceiver == null) {
            mReceiver = new MyReceiver();
            IntentFilter filter = new IntentFilter(BroadcastHelper.ACTION_NAME);
            registerReceiver(mReceiver, filter);
            isRegistered = true;
        }
        mSocket.connect();

        mSocket.on("user_connection", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d("dfsd", args[0].toString());
                if (userKey1 == null) {
                    userKey1 = args[0].toString();
                    String cc = PreferenceHelper.getCURRENTLAT();
                    String cc2 = PreferenceHelper.getCURRENTLONG();
                    makeUserConnection();
                    Intent intent = new Intent("connected");
                    BroadcastHelper.sendInform(getApplicationContext(),"action",intent);
                }
            }
        });
    }

    public void makeUserConnection() {
        UserInfo userInfo = new UserInfo(PreferenceHelper.getUserId(), "osama", userKey1
                , PreferenceHelper.getCURRENTLAT(),PreferenceHelper.getCURRENTLONG());
        Gson gson = new Gson();
        try {
            JSONObject obj = new JSONObject(gson.toJson(userInfo));
            mSocket.emit("create_user", obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // do something
            Log.v("r", "receive " + intent.getStringExtra(BroadcastHelper.BROADCAST_EXTRA_METHOD_NAME));
            String methodName = intent.getStringExtra(BroadcastHelper.BROADCAST_EXTRA_METHOD_NAME);
            switch (methodName)
            {
                case "new_order":
                    OrderDetails orderDetails = new OrderDetails();
                    orderDetails.setStorename(intent.getStringExtra("sname"));
                    orderDetails.setStorelat(intent.getStringExtra("slat"));
                    orderDetails.setStorelang(intent.getStringExtra("slong"));
                    orderDetails.setClientaddress(intent.getStringExtra("uaddress"));
                    orderDetails.setClientname("midi");
                    orderDetails.setUserlat(intent.getStringExtra("ulate"));
                    orderDetails.setUserlang(intent.getStringExtra("ulong"));
                    orderDetails.setProductPrice(intent.getStringExtra("price"));
                    orderDetails.setPaymentWay(intent.getIntExtra("payment",0));
                    orderDetails.setProductname(intent.getStringExtra("product_name"));
                    orderDetails.setStoreaddress(intent.getStringExtra("saddress"));
                    orderDetails.setId(intent.getIntExtra("order_id",0));
                    orderDetails.setUserId(PreferenceHelper.getUserId());
                    Gson gson = new Gson();
                    try {
                        JSONObject obj = new JSONObject(gson.toJson(orderDetails));
                        mSocket.emit("invite_room",userKey1,obj);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;


            }

        }

        // constructor
        public MyReceiver(){

        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mReceiver != null)
            if (isRegistered) {
                unregisterReceiver(mReceiver);
            }
            mSocket.disconnect();

    }



}