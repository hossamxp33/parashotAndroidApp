package com.example.hossam.parashotApp.presentation.screens.home.myOrderFragment;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.hossam.parashotApp.dataLayer.apiData.ApiClient;
import com.example.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.example.hossam.parashotApp.dataLayer.repositories.MyOrderRepository;

public class MyOrderViewModelFactory implements ViewModelProvider.Factory {


    private Application application;
    int userid;//// for getorder to this user



    public MyOrderViewModelFactory(Application application1, int user_id) {
        application = application1;
        userid =user_id;
    }


    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {


         if (modelClass == MyOrderViewModel.class)
        {
            return (T) new MyOrderViewModel(getMyOrderRepositry(userid));
        }

        throw new IllegalArgumentException("Invalid view model class type");
    }




    @NonNull
    private MyOrderRepository getMyOrderRepositry(int userid) {
        return new MyOrderRepository(getApiService(),userid);
    }


    private ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }


}
