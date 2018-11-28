package com.example.hossam.parashotApp.presentation.screens.home.deliveryComments;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.hossam.parashotApp.dataLayer.apiData.ApiClient;
import com.example.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.example.hossam.parashotApp.dataLayer.repositories.DeliveryCommentsRepository;
import com.example.hossam.parashotApp.dataLayer.repositories.ProductRatesRepository;
import com.example.hossam.parashotApp.presentation.screens.home.userCartFragment.helper.ProductModel;

import java.util.List;

public class DeliveryComentsViewModelFactory implements ViewModelProvider.Factory {


    private Application application;
    int deliveryid;


    List<ProductModel> productList; ///// alldata of product send to serever when user parches
    public DeliveryComentsViewModelFactory(@NonNull Application application1) {
        application = application1;
    }

    public DeliveryComentsViewModelFactory(Application application1, int deliveryid1) {
        application=application1;
        deliveryid = deliveryid1;
    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == DeliveryCommentsViewModel.class)
                 {
            return (T) new DeliveryCommentsViewModel(getDeliveryComments());
        }

        throw new IllegalArgumentException("Invalid view model class type");
    }


    @NonNull
    private DeliveryCommentsRepository getDeliveryComments() {
        return new DeliveryCommentsRepository(getApiService(), deliveryid);
    }


    private ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }

}
