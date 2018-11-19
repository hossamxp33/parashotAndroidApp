package com.example.hossam.parashotApp.dataLayer.repositories;

import android.support.v4.util.Consumer;
import android.util.Log;

import com.example.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.example.hossam.parashotApp.entities.AllStoriesModel;
import com.example.hossam.parashotApp.entities.StoresFromGoogleModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllStoriesRepository {

    private ApiInterface apiService;
    private Consumer<AllStoriesModel> onSuccess;
    private Consumer<StoresFromGoogleModel> onSuccessGooglePlaces;
    private Consumer<Throwable> onError;
    private int categry,type;
    public AllStoriesRepository(ApiInterface apiService1, int typeid, int categryid)
    {
        apiService = apiService1;
        categry = categryid;
        type = typeid;
        getAllStoriesData();
        getAllStoresFromGoogleData();
    }

    private void getAllStoriesData() {
        try {
            apiService.getSubCategriesData(categry,type).enqueue(new Callback<AllStoriesModel>() {
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


    private void getAllStoresFromGoogleData() {
        try {
            apiService.getStoresfroomgooglesData().enqueue(new Callback<StoresFromGoogleModel>() {
                @Override
                public void onResponse(Call<StoresFromGoogleModel> call, final Response<StoresFromGoogleModel> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful()) {
                            if (onSuccess != null) {
                                onSuccessGooglePlaces.accept(response.body());
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
                public void onFailure(Call<StoresFromGoogleModel> call, Throwable t) {
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

    public void setOnSuccessGooglePlaces(Consumer<StoresFromGoogleModel> onSuccessGooglePlaces) {
        this.onSuccessGooglePlaces = onSuccessGooglePlaces;
    }


    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

}
