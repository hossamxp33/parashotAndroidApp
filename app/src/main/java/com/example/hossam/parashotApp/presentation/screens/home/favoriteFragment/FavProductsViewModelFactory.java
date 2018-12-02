package com.example.hossam.parashotApp.presentation.screens.home.favoriteFragment;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.hossam.parashotApp.dataLayer.apiData.ApiClient;
import com.example.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.example.hossam.parashotApp.dataLayer.repositories.FavoriteProductsRepository;
import com.example.hossam.parashotApp.presentation.screens.home.deliveryComments.DeliveryCommentsViewModel;
import com.example.hossam.parashotApp.presentation.screens.home.userCartFragment.helper.ProductModel;

import java.util.List;

public class FavProductsViewModelFactory implements ViewModelProvider.Factory {


    private Application application;
    int userid;


    List<ProductModel> productList; ///// alldata of product send to serever when user parches
    public FavProductsViewModelFactory(@NonNull Application application1) {
        application = application1;
    }

    public FavProductsViewModelFactory(Application application1, int user_ID) {
        application=application1;
        userid = user_ID;
    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == FavoritsViewModel.class)
                 {
            return (T) new FavoritsViewModel(getFavProducts());
        }

        throw new IllegalArgumentException("Invalid view model class type");
    }


    @NonNull
    private FavoriteProductsRepository getFavProducts() {
        return new FavoriteProductsRepository(getApiService(), userid);
    }


    private ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }

}
