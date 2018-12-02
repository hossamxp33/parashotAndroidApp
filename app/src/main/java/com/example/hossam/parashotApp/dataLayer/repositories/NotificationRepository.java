package com.example.hossam.parashotApp.dataLayer.repositories;

import android.app.Notification;
import android.support.v4.util.Consumer;
import android.util.Log;

import com.example.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.example.hossam.parashotApp.entities.DeliveryComments;
import com.example.hossam.parashotApp.entities.Notifications;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationRepository {

    private ApiInterface apiService;
    private Consumer<Notifications> onSuccess;
    private Consumer<Throwable> onError;
    private int userid;

    public NotificationRepository(ApiInterface apiService1, int deliveryid1) {
        apiService = apiService1;
        userid = deliveryid1;
        getNotificationsData();

    }

    private void getNotificationsData() {
        try {
            apiService.getnotifications(userid).enqueue(new Callback<Notifications>() {
                @Override
                public void onResponse(Call<Notifications> call, final Response<Notifications> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful()) {
                            if (onSuccess != null) {
                                onSuccess.accept(response.body());
                            }
                        } else {
                            // TODO: return error
                            if (onError != null) {
                                onError.accept(new Exception());
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<Notifications> call, Throwable t) {
                    Log.d("GetAllData fail -> ", call.toString());
                    // TODO: return error
                    if (onError != null) {
                        onError.accept(t);
                    }
                }
            });
        } catch (Exception e) {
            Log.d("SplashRepository", e.getMessage());
            onError.accept(e);
        }
    }


    public void setOnSuccess(Consumer<Notifications> onSuccess) {
        this.onSuccess = onSuccess;
    }


    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

}
