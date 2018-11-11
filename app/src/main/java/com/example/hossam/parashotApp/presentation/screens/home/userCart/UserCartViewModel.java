package com.example.hossam.parashotApp.presentation.screens.home.userCart;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.support.v4.util.Consumer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.deo.ProductDeo;
import com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.entities.Product;
import com.example.hossam.parashotApp.dataLayer.repositories.CategoryRepository;
import com.example.hossam.parashotApp.dataLayer.repositories.UserCartRepository;
import com.example.hossam.parashotApp.entities.Categories;

import java.util.List;

public class UserCartViewModel extends ViewModel {


    public UserCartRepository userCartRepository;
    MutableLiveData<List<Product>> ProductLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();

    String name,imagepath,retecount,price;
    float ratestart;

    public UserCartViewModel() {
    }


    public UserCartViewModel(final UserCartRepository userCartRepository) {

        userCartRepository.setcartItemss(new Consumer<List<Product>>() {
            @Override
            public void accept(List<Product> products) {
                ProductLiveData.postValue(products);
                loading.postValue(false);
            }
        });

        userCartRepository.setOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                errorLiveData.postValue(throwable);
                loading.postValue(false);
            }
        });

        this.userCartRepository = userCartRepository;

        userCartRepository.GetProductsFromDB(1);
    }

    public void deleteProductFromDB(Product product, UserCartRepository userCartRepository) {
        userCartRepository.deleteProductFromDB(product);
        userCartRepository.setcartItemss(new Consumer<List<Product>>() {
            @Override
            public void accept(List<Product> products) {
                ProductLiveData.postValue(products);
                loading.postValue(false);
            }
        });

    }

    public ObservableField<String> resultImageUrl = new ObservableField<>();


    public void imageUrlUpdated(String url) {
        resultImageUrl.set(url);
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getRetecount() {
        return retecount;
    }

    public void setRetecount(String retecount) {
        this.retecount = retecount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public float getRatestart() {
        return ratestart;
    }

    public void setRatestart(float ratestart) {
        this.ratestart = ratestart;
    }
}
