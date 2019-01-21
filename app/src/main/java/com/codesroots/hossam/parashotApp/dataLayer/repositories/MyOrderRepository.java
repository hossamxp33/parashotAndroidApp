package com.codesroots.hossam.parashotApp.dataLayer.repositories;

import android.support.v4.util.Consumer;
import android.util.Log;
import com.codesroots.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.parashotApp.entities.MYOrdersModel;
import com.codesroots.hossam.parashotApp.entities.OrderEdit;
import com.codesroots.hossam.parashotApp.helper.PreferenceHelper;
import com.codesroots.hossam.parashotApp.presentation.screens.home.myOrderFragment.FilterMyOrder;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrderRepository {

    private ApiInterface apiService;
    private Consumer<FilterMyOrder> onSuccess;
    private Consumer<Throwable> onError;
    private Consumer<Boolean> onEditResult;

    int userid;
    public MyOrderRepository(ApiInterface apiService1, int userid1)
    {
        apiService = apiService1;
        userid = userid1;
        getMyProductData();
    }


    public void  editOrder (int orderId ,int statues)
    {
        apiService.editOrderStatuesData(orderId,statues).enqueue(new Callback<OrderEdit>() {
            @Override
            public void onResponse(Call<OrderEdit> call, final Response<OrderEdit> response) {
                if (response.body().isEditorder()) {
                        if (onEditResult != null) {
                            onEditResult.accept(true);
                    }
                }
                else
                    onEditResult.accept(false);
            }

            @Override
            public void onFailure(Call<OrderEdit> call, Throwable t) {
                if (onEditResult != null) {
                    onEditResult.accept(true);
                }
            }
        });
    }

    public void getData ()
    {
        getMyProductData();
    }

    private void  getMyProductData() {
        try {
            apiService.getMyOrders(userid,"bearer "+PreferenceHelper.getToken()).enqueue(new Callback<MYOrdersModel>() {
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
            if (body.getData().get(i).getOrder_status().matches("4"))
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

    public void setOnEditResult(Consumer<Boolean> onEditResult1) {
        this.onEditResult = onEditResult1;
    }
}
