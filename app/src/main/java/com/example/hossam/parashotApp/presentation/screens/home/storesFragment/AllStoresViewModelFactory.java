package com.example.hossam.parashotApp.presentation.screens.home.storesFragment;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.hossam.parashotApp.dataLayer.apiData.ApiClient;
import com.example.hossam.parashotApp.dataLayer.apiData.ApiClientLocal;
import com.example.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.example.hossam.parashotApp.dataLayer.localDatabase.LocalDatabase;
import com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.deo.ProductDeo;
import com.example.hossam.parashotApp.dataLayer.repositories.AllProductsRepository;
import com.example.hossam.parashotApp.dataLayer.repositories.AllStoriesRepository;
import com.example.hossam.parashotApp.dataLayer.repositories.MyOrderRepository;
import com.example.hossam.parashotApp.dataLayer.repositories.ProductDetailsRepository;
import com.example.hossam.parashotApp.presentation.screens.home.myOrderFragment.MyOrderViewModel;
import com.example.hossam.parashotApp.presentation.screens.home.productsDetailsFragment.ProductDetailsViewModel;
import com.example.hossam.parashotApp.presentation.screens.home.productsFragment.ProductsViewModel;

public class AllStoresViewModelFactory implements ViewModelProvider.Factory {


    private Application application;
    public AllStoresViewModelFactory(@NonNull Application application1) {
        application = application1;
    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == StoresViewModel.class)
                 {
            return (T) new StoresViewModel(getStoriesRepositry());
        }
        else if (modelClass == ProductsViewModel.class)
        {
            return (T) new ProductsViewModel(getProductsRepositry());
        }
        else if (modelClass == ProductDetailsViewModel.class)
        {
            return (T) new ProductDetailsViewModel(getProductDetailsRepositry());
        }

        else if (modelClass == MyOrderViewModel.class)
        {
            return (T) new MyOrderViewModel(getMyOrderRepositry());
        }

        throw new IllegalArgumentException("Invalid view model class type");
    }

    @NonNull
    private AllStoriesRepository getStoriesRepositry() {
        return new AllStoriesRepository(getApiService());
    }

    @NonNull
    private AllProductsRepository getProductsRepositry() {
        return new AllProductsRepository(getApiService());
    }


    @NonNull
    private ProductDetailsRepository getProductDetailsRepositry() {
        return new ProductDetailsRepository(getApiServiceLocal(),getProductDeo());
    }

    @NonNull
    private MyOrderRepository getMyOrderRepositry() {
        return new MyOrderRepository(getApiServiceLocal());
    }

    private ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }

    private ApiInterface getApiServiceLocal() {
        return ApiClientLocal.getClient().create(ApiInterface.class);
    }

    private ProductDeo getProductDeo() {
        return LocalDatabase.getInstance(application).productDeo();
    }

}
