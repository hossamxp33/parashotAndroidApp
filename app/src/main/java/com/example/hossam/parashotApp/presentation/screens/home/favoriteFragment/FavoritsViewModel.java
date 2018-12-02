package com.example.hossam.parashotApp.presentation.screens.home.favoriteFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.hossam.parashotApp.dataLayer.repositories.FavoriteProductsRepository;
import com.example.hossam.parashotApp.entities.FavProduct;

public class FavoritsViewModel extends ViewModel {


    public FavoriteProductsRepository favoriteProductsRepository;
    public MutableLiveData<FavProduct> products_MutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> deleteFromFavoriteLiveData = new MutableLiveData<>();

    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();


    public FavoritsViewModel() {

    }

    public FavoritsViewModel(final FavoriteProductsRepository repository) {

        repository.setOnSuccess(products -> {
            products_MutableLiveData.postValue(products);
            loading.postValue(false);
        });

        repository.setOnError(throwable -> {
            errorLiveData.postValue(throwable);
            loading.postValue(false);
        });

        repository.setDeleteFromFavResult(aBoolean ->
                deleteFromFavoriteLiveData.postValue(aBoolean)
        );


        this.favoriteProductsRepository = repository;

    }

    public  void DeleteFav (int favID, FavoriteProductsRepository favoriteProductsRepository)
    {
        favoriteProductsRepository.DeleteFav(favID);
    }



}
