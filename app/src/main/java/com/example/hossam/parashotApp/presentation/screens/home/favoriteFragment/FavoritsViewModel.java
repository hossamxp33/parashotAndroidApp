package com.example.hossam.parashotApp.presentation.screens.home.favoriteFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.support.v4.util.Consumer;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.entities.Product;
import com.example.hossam.parashotApp.dataLayer.repositories.AllProductsRepository;
import com.example.hossam.parashotApp.dataLayer.repositories.FavoriteProductsRepository;
import com.example.hossam.parashotApp.entities.FavProduct;
import com.example.hossam.parashotApp.entities.Products_in_Stories_Model;

import okhttp3.RequestBody;

public class FavoritsViewModel extends ViewModel {


    public FavoriteProductsRepository allProducts_repository;
    public MutableLiveData<FavProduct> products_MutableLiveData = new MutableLiveData<>();

    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();

    String name,ratecount,price;
    int ratestars;
    public FavoritsViewModel() {

    }

    public FavoritsViewModel(final FavoriteProductsRepository repository) {

        repository.setOnSuccess(new Consumer<FavProduct>() {
            @Override
            public void accept(FavProduct products) {
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

        this.allProducts_repository = repository;

    }

    public int getRatestars() {
        return ratestars;
    }

    public void setRatestars(int ratestars) {
        this.ratestars = ratestars;
    }

    public void storeData(Product dataBeans, AllProductsRepository allProducts_repository) {
        allProducts_repository.saveDataInDB(dataBeans);
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

}
