package com.example.hossam.parashotApp.dataLayer.repositories;

import android.support.v4.util.Consumer;

import com.example.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.example.hossam.parashotApp.presentation.screens.home.userCartFragment.helper.ProductInfoToPost;
import com.example.hossam.parashotApp.presentation.screens.home.userCartFragment.helper.ProductModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PaymentRepository {


    private ApiInterface apiService;
    private Consumer<Boolean> onSuccess;
    private Consumer<Throwable> onError;
    List<ProductInfoToPost> productsdata;
    public PaymentRepository(ApiInterface apiService, List<ProductModel> productsdata) {
        this.apiService = apiService;
    }

    public void addOrder(List<ProductModel> ProductModels) {

         apiService.makeOrder(ProductModels).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
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
