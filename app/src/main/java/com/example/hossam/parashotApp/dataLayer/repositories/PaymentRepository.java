package com.example.hossam.parashotApp.dataLayer.repositories;

import android.os.AsyncTask;
import android.support.v4.util.Consumer;
import android.util.Log;

import com.example.hossam.parashotApp.dataLayer.apiData.ApiClientLocal;
import com.example.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.example.hossam.parashotApp.dataLayer.localDatabase.homePage.dao.FooterDao;
import com.example.hossam.parashotApp.dataLayer.localDatabase.homePage.entities.FooterEntity;
import com.example.hossam.parashotApp.entities.StoreSettingEntity;
import com.example.hossam.parashotApp.helper.ProgressDialogHelper;
import com.example.hossam.parashotApp.presentation.screens.home.userCart.helper.ProductModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PaymentRepository {


    private ApiInterface apiService;
    private Consumer<Boolean> onSuccess;
    private Consumer<Throwable> onError;
    List<ProductModel> ProductModels=new ArrayList<>();

    public PaymentRepository(ApiInterface apiService, List<ProductModel> productsdata) {
        this.apiService = apiService;
        ProductModels = productsdata;
    }

    private void addOrder() {

         apiService.makeOrder(ProductModels).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                ProgressDialogHelper.removeSimpleProgressDialog();
                if (response.body() != null) {
                    if (response.isSuccessful())
                    {
                        if (onSuccess != null) {
                            onSuccess.accept(true);
                        }
                    }
                    else
                    {
                        // TODO: return error
                        if (onError != null) {
                            onError.accept(new Exception());
                        }
                    }

                    // TODO: return error
                    if (onError != null) {
                        onError.accept(new Exception());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ProgressDialogHelper.removeSimpleProgressDialog();
                if (onError != null) {
                    onError.accept(t);
                }
            }
        });
    }

    public void setOnSuccess(Consumer<Boolean> onSuccess) {
        this.onSuccess = onSuccess;
    }

    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }


}
