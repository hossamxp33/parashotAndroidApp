package com.example.hossam.parashotApp.dataLayer.repositories;

import android.support.v4.util.Consumer;
import android.util.Log;

import com.example.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.example.hossam.parashotApp.entities.MYOrdersModel;
import com.example.hossam.parashotApp.entities.Products_in_Stories_Model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyOrderRepository {

    private ApiInterface apiService;
    private Consumer<MYOrdersModel> onSuccess;
    private Consumer<Throwable> onError;

    public MyOrderRepository(ApiInterface apiService1)
    {
        apiService = apiService1;
        getMyProductData();
    }

    private void getMyProductData() {
        try {
            apiService.getMyOrders(1).enqueue(new Callback<MYOrdersModel>() {
                @Override
                public void onResponse(Call<MYOrdersModel> call, final Response<MYOrdersModel> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful()) {
                            if (onSuccess != null) {
                                onSuccess.accept(response.body());
                            }
                        }

                        else
                            {
                            // TODO: return error
                            if (onError != null) {
                                onError.accept(new Exception());
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<MYOrdersModel> call, Throwable t) {
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


    public void setOnSuccess(Consumer<MYOrdersModel> onSuccess) {
        this.onSuccess = onSuccess;
    }


    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

}
