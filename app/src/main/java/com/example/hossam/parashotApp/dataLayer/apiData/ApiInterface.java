package com.example.hossam.parashotApp.dataLayer.apiData;

import com.example.hossam.parashotApp.entities.Categories;
import com.example.hossam.parashotApp.entities.MYOrdersModel;
import com.example.hossam.parashotApp.entities.ProductDetailsModel;
import com.example.hossam.parashotApp.entities.Products_in_Stories_Model;
import com.example.hossam.parashotApp.entities.StoreSettingEntity;
import com.example.hossam.parashotApp.entities.AllStoriesModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;


public interface ApiInterface {

    @GET("stores/getalluserstoredata/{userId}.json")
    @Headers("Accept: Application/json")
    Call<StoreSettingEntity> getStoreSettingData(
            @Path(value = "userId") int userId
    );


    @GET("Categories/GetALLCat.json")
    @Headers("Accept: Application/json")
    Call<Categories> getCatigriesData(
    );


    @GET("smallstores/getsmallstoredata/{storid}/{userId}.json")
    @Headers("Accept: Application/json")
    Call<AllStoriesModel> getSubCategriesData(
            @Path(value = "storid") int storid,
            @Path(value = "userId") int userId
    );

    @GET("products/ProductList/{storid}.json")
    @Headers("Accept: Application/json")
    Call<Products_in_Stories_Model> getProductsData(
            @Path(value = "storid") int storid
    );

    @GET("products/getproduct/{storid}.json")
    @Headers("Accept: Application/json")
    Call<ProductDetailsModel> getProductDetails(
            @Path(value = "storid") int storid
    );

    @GET("orders/getorders/{userid}.json")
    @Headers("Accept: Application/json")
    Call<MYOrdersModel> getMyOrders(
            @Path(value = "userid") int userid
    );


}

