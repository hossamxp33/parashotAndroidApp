package com.example.hossam.parashotApp.helper;

import android.app.Activity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;


public class Json_Controller {
    //PreferenceHelper helper;
    private Response.ErrorListener mErrorListener;


    public void GetDataFromServer(final VolleyCallback callback, final Activity context, String url, final boolean Authorization) {
        final String tag_string_req = "string_req";
        //helper = new PreferenceHelper(context);

        final StringRequest strReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //finalDialog.dismiss();
                    String encodingString = URLEncoder.encode(response, "ISO-8859-1");
                    response = URLDecoder.decode(encodingString, "UTF-8");

                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
                Log.d(TAG, response.toString());
                try {
                    callback.onSuccess(response.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                callback.onError(error);
                //finalDialog.dismiss();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Accept", "application/json");
                params.put("Content-Type", "application/json");
                if (Authorization){

                }

                return params;
            }
        };
        strReq.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyApplication.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

}
