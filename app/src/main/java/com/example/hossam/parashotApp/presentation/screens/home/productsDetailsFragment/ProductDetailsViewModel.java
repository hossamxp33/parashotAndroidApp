package com.example.hossam.parashotApp.presentation.screens.home.productsDetailsFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.hossam.parashotApp.dataLayer.repositories.ProductDetailsRepository;
import com.example.hossam.parashotApp.entities.ProductDetailsModel;

public class ProductDetailsViewModel extends ViewModel {


    private ProductDetailsRepository productDetailsRepository;
    MutableLiveData<ProductDetailsModel> productDetails_MutableLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();


    public ProductDetailsViewModel() {
    }

    public ProductDetailsViewModel(final ProductDetailsRepository repository) {


        repository.setOnSuccess(products -> {
            productDetails_MutableLiveData.postValue(products);
            loading.postValue(false);
        });

        repository.setOnError(throwable -> {
            errorLiveData.postValue(throwable);
            loading.postValue(false);
        });

        this.productDetailsRepository = repository;
        loadData();
    }

    private void loadData() {
        loading.setValue(true);
        productDetailsRepository.getProductDetailsData();
    }



}
