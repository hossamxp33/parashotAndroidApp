package com.example.hossam.parashotApp.presentation.screens.home.productsFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.util.Consumer;
import com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.entities.Product;
import com.example.hossam.parashotApp.dataLayer.repositories.AllProductsRepository;
import com.example.hossam.parashotApp.entities.Products_in_Stories_Model;

public class ProductsViewModel extends ViewModel {
    public AllProductsRepository allProducts_repository;
    MutableLiveData<Products_in_Stories_Model> products_MutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> storProductInDBResult = new MutableLiveData<>();
    public MutableLiveData<Boolean> addToFavoriteLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> product_count_MutableLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();

    String name,ratecount,price;
    int ratestars;
    public ProductsViewModel() {
    }

    public ProductsViewModel(final AllProductsRepository repository) {

        repository.setOnSuccess(new Consumer<Products_in_Stories_Model>() {
            @Override
            public void accept(Products_in_Stories_Model products) {
                products_MutableLiveData.postValue(products);
                loading.postValue(false);
            }
        });

        repository.setOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                errorLiveData.postValue(throwable);
                loading.postValue(false);
            }
        });

        repository.setbooleanConsumerForAdd(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) {
                storProductInDBResult.postValue(aBoolean);
            }
        });

        repository.setProductsCount(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                product_count_MutableLiveData.postValue(integer);
            }
        });

        repository.setAddToToFavResult(aBoolean ->
                addToFavoriteLiveData.postValue(aBoolean)
        );

        this.allProducts_repository = repository;
        allProducts_repository.getAllProduct();
        getCount();
    }


    public  void AddToFav (int user_id , int product_id, int smallstore_id, AllProductsRepository allProducts_repository1 )
    {
        allProducts_repository1.AddToFav(user_id,product_id,smallstore_id);
    }



    public void storeData(Product dataBeans, AllProductsRepository allProducts_repository) {
        allProducts_repository.saveDataInDB(dataBeans);
    }


    public void getCount() {
        allProducts_repository.getProductCount();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRatecount() {
        return ratecount;
    }

    public void setRatecount(String ratecount) {
        this.ratecount = ratecount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getRatestars() {
        return ratestars;
    }

    public void setRatestars(int ratestars) {
        this.ratestars = ratestars;
    }
}
