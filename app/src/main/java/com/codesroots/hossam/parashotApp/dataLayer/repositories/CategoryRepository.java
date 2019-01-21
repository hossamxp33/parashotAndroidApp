package com.codesroots.hossam.parashotApp.dataLayer.repositories;

import android.support.v4.util.Consumer;
import android.util.Log;
import com.codesroots.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.parashotApp.entities.Categories;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryRepository {

    private ApiInterface apiService;
    private Consumer<Categories> onSuccess;
    private Consumer<Throwable> onError;

    public CategoryRepository(ApiInterface apiService)
    {
        this.apiService = apiService;
    }

    public void getAllCategoryData() {
        try {
            apiService.getCatigriesData().enqueue(new Callback<Categories>() {
                @Override
                public void onResponse(Call<Categories> call, final Response<Categories> response) {
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
                public void onFailure(Call<Categories> call, Throwable t) {
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


    public void setOnSuccess(Consumer<Categories> onSuccess) {
        this.onSuccess = onSuccess;
    }


    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

}
