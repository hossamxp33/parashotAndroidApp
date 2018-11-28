package com.example.hossam.parashotApp.dataLayer.repositories;

import android.support.v4.util.Consumer;
import android.util.Log;

import com.example.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.example.hossam.parashotApp.entities.DeliveryOffers;
import com.example.hossam.parashotApp.entities.StoresFromGoogleModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DeliveryOffersRepository {

    private ApiInterface apiService;
    private Consumer<DeliveryOffers> onSuccess;
    private Consumer<StoresFromGoogleModel> onSuccessGooglePlaces;
    private Consumer<Throwable> onError;
    private int orderid;
    public DeliveryOffersRepository(ApiInterface apiService1, int orderid1)
    {
        apiService = apiService1;
        orderid = orderid1;
        getDeliveryOffers();
    }

    private void getDeliveryOffers() {
        try {
            apiService.getDeliveryOffers(orderid).enqueue(new Callback<DeliveryOffers>() {
                @Override
                public void onResponse(Call<DeliveryOffers> call, final Response<DeliveryOffers> response) {
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
                public void onFailure(Call<DeliveryOffers> call, Throwable t) {
                    Log.d("GetAllData fail -> ", call.toString());
                    // TODO: return error
                    if (onError != null) {
                        onError.accept(t);
                    }
                }
            });
        } catch (Exception e) {
            Log.d("DeliveryRepository", e.getMessage());
            onError.accept(e);
        }
    }

    public void setOnSuccess(Consumer<DeliveryOffers> onSuccess) {
        this.onSuccess = onSuccess;
    }


    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

}
