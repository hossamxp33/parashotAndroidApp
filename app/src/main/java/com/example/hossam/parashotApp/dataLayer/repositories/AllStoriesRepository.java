package com.example.hossam.parashotApp.dataLayer.repositories;

import android.support.v4.util.Consumer;
import android.util.Log;

import com.example.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.example.hossam.parashotApp.entities.AllStoriesModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllStoriesRepository {

    private ApiInterface apiService;
    private Consumer<AllStoriesModel> onSuccess;
    private Consumer<Throwable> onError;

    public AllStoriesRepository(ApiInterface apiService1)
    {
        apiService = apiService1;
        getAllCategryData();
    }

    private void getAllCategryData() {
        try {
            apiService.getSubCategriesData(1,0).enqueue(new Callback<AllStoriesModel>() {
                @Override
                public void onResponse(Call<AllStoriesModel> call, final Response<AllStoriesModel> response) {
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
                public void onFailure(Call<AllStoriesModel> call, Throwable t) {
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


    public void setOnSuccess(Consumer<AllStoriesModel> onSuccess) {
        this.onSuccess = onSuccess;
    }


    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

}
