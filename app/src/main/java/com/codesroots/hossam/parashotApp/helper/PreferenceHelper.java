package com.codesroots.hossam.parashotApp.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class PreferenceHelper {

    private static SharedPreferences app_prefs;
    private static String app_ref = "userdetails";
    private static String photo = "photo";
    private static String Token = "token";
    private static String UserId = "userid";
    private static String Language = "language";
    private final static String CURRENTLAT = "latitude";
    private final static String CURRENTLONG = "longtude";
    private final static String userSocketKey = "usersocketkey";
    private final String CURRENTCATEGRY = "CURRENTCATEGRY";
    private static final String USER_GROUP_ID = "USERGROUPID";
    private static final String STORE_NAME = "STORE_NAME";
    private static final String STORE_LATE = "STORE_LATE";
    private static final String STORE_LONG = "STORE_LONG";
    private final static String USER_NAME = "USERNAME";


    private Context context;

    public PreferenceHelper(Context context) {
        this.context = context;
        try {
            app_prefs = context.getSharedPreferences(app_ref,
                    Context.MODE_PRIVATE);
        } catch (NullPointerException e) {
        }
    }

    public static void setUSER_GROUP_ID(int group) {
        Editor edit = app_prefs.edit();
        edit.putInt(USER_GROUP_ID, group);
        edit.apply();
    }


    public static int getUSER_GROUP_ID() {
        return app_prefs.getInt(USER_GROUP_ID, 0);
    }

    public static void setCURRENTLAT(String currentlat) {
        Editor edit = app_prefs.edit();
        edit.putString(CURRENTLAT, currentlat);
        edit.apply();
    }

    public static void setCURRENTLONG(String currentlong) {
        Editor edit = app_prefs.edit();
        edit.putString(CURRENTLONG, currentlong);
        edit.apply();
    }

    public static void setStoreName(String storeName) {
        Editor edit = app_prefs.edit();
        edit.putString(STORE_NAME, storeName);
        edit.apply();
    }
    public static void setStoreLate(String storeLate) {
        Editor edit = app_prefs.edit();
        edit.putString(STORE_LATE, storeLate);
        edit.apply();
    }
    public static void setStoreLong(String storeLong) {
        Editor edit = app_prefs.edit();
        edit.putString(STORE_LONG, storeLong);
        edit.apply();
    }

    public static void setSocketKey(String userSocketKey) {
        Editor edit = app_prefs.edit();
        edit.putString(userSocketKey, userSocketKey);
        edit.apply();
    }

    public void setCURRENTCATEGRY(String currentcategry) {

        Editor edit = app_prefs.edit();
        edit.putString(CURRENTCATEGRY, currentcategry);
        edit.apply();
    }

    public static String getUserSocketKey() {
        return app_prefs.getString(userSocketKey, null);
    }

    public static void setUserName(String name) {
        Editor edit = app_prefs.edit();
        edit.putString(USER_NAME, name);
        edit.apply();
    }

    public static String getUserName() {
        return app_prefs.getString(USER_NAME,null);
    }


    public String getCURRENTCATEGRY() {
        return app_prefs.getString(CURRENTCATEGRY, null);
    }

    public static String getCURRENTLAT() {
        return app_prefs.getString(CURRENTLAT, null);
    }

    public static String getCURRENTLONG() {
        return app_prefs.getString(CURRENTLONG, "0");
    }

    public static String getToken() {
        return app_prefs.getString(Token, null);
    }

    public static String getStoreName() {
        return app_prefs.getString(STORE_NAME, null);
    }

    public static String getStoreLate() {
        return app_prefs.getString(STORE_LATE, "0");
    }

    public static String getStoreLong() {
        return app_prefs.getString(STORE_LONG, "0");
    }

    public static void setToken(String API_TOKEN) {
        Editor edit = app_prefs.edit();
        edit.putString(Token, API_TOKEN);
        edit.commit();
    }

    public static int getUserId() {
        return app_prefs.getInt(UserId, 0);
    }

    public void setLanguage(String language) {
        Editor edit = app_prefs.edit();
        edit.putString(Language, language);
        edit.commit();
    }

    public String getLanguage() {
        return app_prefs.getString(Language, null);
    }

    public String getphoto() {
        return app_prefs.getString(photo, null);
    }

    public void setUserId(int user_id) {
        Editor edit = app_prefs.edit();
        edit.putInt(UserId, user_id);
        edit.apply();
    }

    public void setphoto(String uri) {
        Editor edit = app_prefs.edit();
        edit.putString(photo, uri);
        edit.apply();
    }


    public void Logout() {
        setToken(null);
        setUserId(0);
        setphoto(null);
    }

    public void clearRequestData() {

    }

}
