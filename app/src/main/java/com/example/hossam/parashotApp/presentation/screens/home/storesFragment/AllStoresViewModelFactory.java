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
import com.example.hossam.parashotApp.dataLayer.repositories.UserCartRepository;
import com.example.hossam.parashotApp.presentation.screens.home.myOrderFragment.MyOrderViewModel;
import com.example.hossam.parashotApp.presentation.screens.home.productsDetailsFragment.ProductDetailsViewModel;
import com.example.hossam.parashotApp.presentation.screens.home.productsFragment.ProductsViewModel;
import com.example.hossam.parashotApp.presentation.screens.home.userCart.UserCartViewModel;

public class AllStoresViewModelFactory implements ViewModelProvider.Factory {


    private Application application;
    int typeid,categry;
    public AllStoresViewModelFactory(@NonNull Application application1) {
        application = application1;

    }

    public AllStoresViewModelFactory(Application application1, int type, int cate_or_sub_id) {
        application=application1;
        typeid =type;
        categry = cate_or_sub_id;
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
        else if (modelClass == UserCartViewModel.class)
        {
            return (T) new UserCartViewModel(getUserCartRepositry());
        }
        throw new IllegalArgumentException("Invalid view model class type");
    }

    @NonNull
    private AllStoriesRepository getStoriesRepositry() {
        return new AllStoriesRepository(getApiService(),typeid,categry);
    }

    @NonNull
    private AllProductsRepository getProductsRepositry() {
        return new AllProductsRepository(getApiService(),getProductDeo());
    }


    @NonNull
    private ProductDetailsRepository getProductDetailsRepositry() {
        return new ProductDetailsRepository(getApiServiceLocal(),getProductDeo());
    }


    @NonNull
    private UserCartRepository getUserCartRepositry() {
        return new UserCartRepository(getApiServiceLocal(),getProductDeo());
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
