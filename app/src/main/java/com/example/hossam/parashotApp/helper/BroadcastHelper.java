package com.example.hossam.parashotApp.helper;


import android.content.Context;
import android.content.Intent;

public class BroadcastHelper {
     public static final String BROADCAST_EXTRA_METHOD_NAME = "INPUT_METHOD_CHANGED";
    public static final String ACTION_NAME = "hassan.hossam";
    private static final String UPDATE_LOCATION_METHOD = "updateLocation";


    public static void sendInform(Context context, String method) {
        Intent intent = new Intent();
        intent.setAction(ACTION_NAME);
        intent.putExtra(BROADCAST_EXTRA_METHOD_NAME, method);
        try {
            context.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendInform(Context context, String method, Intent intent) {
        intent.setAction(ACTION_NAME);
        intent.putExtra(BROADCAST_EXTRA_METHOD_NAME, method);
        try {
            context.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
