package com.example.hossam.parashotApp.presentation.screens.home.productsDetailsFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.util.Consumer;

import com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.entities.Product;
import com.example.hossam.parashotApp.dataLayer.repositories.ProductDetailsRepository;
import com.example.hossam.parashotApp.entities.ProductDetailsModel;

public class ProductDetailsViewModel extends ViewModel {


    private ProductDetailsRepository productDetailsRepository;
    MutableLiveData<ProductDetailsModel> productDetails_MutableLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();
    public MutableLiveData<Boolean> stor_or_not_MutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> product_count_MutableLiveData = new MutableLiveData<>();

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

        repository.setbooleanConsumerForAdd(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) {
                stor_or_not_MutableLiveData.postValue(aBoolean);
            }
        });

        repository.setProductCount(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                product_count_MutableLiveData.postValue(integer);
            }
        });

        this.productDetailsRepository = repository;
        loadData();
        getCount(1);
    }

    private void loadData() {
        loading.setValue(true);
        productDetailsRepository.getProductDetailsData();
    }


    public void storeData(Product dataBeans) {
        productDetailsRepository.saveDataInDB(dataBeans);
    }
    public void getCount(int storid) {

        productDetailsRepository.getProductCount(storid);
    }
}
