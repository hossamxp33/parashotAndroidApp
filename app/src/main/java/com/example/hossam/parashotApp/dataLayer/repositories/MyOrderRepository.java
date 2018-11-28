package com.example.hossam.parashotApp.dataLayer.repositories;

import android.support.v4.util.Consumer;
import android.util.Log;

import com.example.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.example.hossam.parashotApp.entities.MYOrdersModel;
import com.example.hossam.parashotApp.entities.Products_in_Stories_Model;
import com.example.hossam.parashotApp.presentation.screens.home.myOrderFragment.FilterMyOrder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyOrderRepository {

    private ApiInterface apiService;
    private Consumer<FilterMyOrder> onSuccess;
    private Consumer<Throwable> onError;

    public MyOrderRepository(ApiInterface apiService1, int userid)
    {
        apiService = apiService1;
        getMyProductData(userid);
    }

    private void getMyProductData(int userid) {
        try {
            apiService.getMyOrders(userid).enqueue(new Callback<MYOrdersModel>() {
                @Override
                public void onResponse(Call<MYOrdersModel> call, final Response<MYOrdersModel> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful()) {
                            if (onSuccess != null) {
                                onSuccess.accept(filterData(response.body()));
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
                    else
                        onError.accept(new Exception());

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

    private FilterMyOrder filterData(MYOrdersModel body) {

        List<MYOrdersModel.DataBean> commpleteOrderData=new ArrayList<>();
        List<MYOrdersModel.DataBean> notCommpleteOrderData=new ArrayList<>();

        for (int i=0;i<body.getData().size();i++)
        {
            if (body.getData().get(i).getOrder_status().matches("3"))
                commpleteOrderData.add(body.getData().get(i));
            else
                notCommpleteOrderData.add(body.getData().get(i));

        }

        return new FilterMyOrder(commpleteOrderData,notCommpleteOrderData);
    }


    public void setOnSuccess(Consumer<FilterMyOrder> onSuccess) {
        this.onSuccess = onSuccess;
    }


    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

}
