package com.example.hossam.parashotApp.dataLayer.apiData;

import com.example.hossam.parashotApp.entities.Categories;
import com.example.hossam.parashotApp.entities.RatessOfProductModel;
import com.example.hossam.parashotApp.entities.LoginResponseModel;
import com.example.hossam.parashotApp.entities.MYOrdersModel;
import com.example.hossam.parashotApp.entities.ProductDetailsModel;
import com.example.hossam.parashotApp.entities.Products_in_Stories_Model;
import com.example.hossam.parashotApp.entities.RegisterResponseModel;
import com.example.hossam.parashotApp.entities.StoreSettingEntity;
import com.example.hossam.parashotApp.entities.StoresList;
import com.example.hossam.parashotApp.entities.StoresFromGoogleModel;
import com.example.hossam.parashotApp.presentation.screens.home.userCartFragment.helper.ProductModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


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

    @GET("smallstores/getsmallstoredata/{cateOrUbbcategryId}/{type}.json")
    @Headers("Accept: Application/json")
    Call<StoresList> getStorsData(
            @Path(value = "cateOrUbbcategryId") int storid,
            @Path(value = "type") int userId
    );


    @GET("https://maps.googleapis.com/maps/api/place/nearbysearch/json?radius=3000")
    @Headers("Accept: Application/json")
    Call<StoresFromGoogleModel> getStoresfroomgooglesData(
            @Query("key")String key ,
            @Query("location")String location,
            @Query("types")String types
    );


    @GET("products/ProductList/{storid}.json")
    @Headers("Accept: Application/json")
    Call<Products_in_Stories_Model> getProductsData(
            @Path(value = "storid") int storid
    );

    @GET("products/getproduct/{productid}.json")
    @Headers("Accept: Application/json")
    Call<ProductDetailsModel> getProductDetails(
            @Path(value = "productid") int productid
    );

    @GET("orders/getorders/{userid}.json")
    @Headers("Accept: Application/json")
    Call<MYOrdersModel> getMyOrders(
            @Path(value = "userid") int userid
    );

    @Multipart
    @POST("orders/androidorder/2.json")
    Call<ResponseBody> makeOrder(
            @Part ("orders")List<ProductModel> models,
            @Part MultipartBody.Part file
    );

    @POST("orders/androidorder/1.json")
    Call<ResponseBody> makeOrderProduct(
            @Body List<ProductModel> models

    );

    @Multipart
    @POST("users/add.json")
    Call<RegisterResponseModel> register(
            @Part("username") RequestBody username,
            @Part("password") RequestBody password,
            @Part("gender") RequestBody gender,
            @Part("phone") RequestBody phone,
            @Part("birthdate") RequestBody birthdate
            );

    @Multipart
    @POST("users/token.json")
    Call<LoginResponseModel> login(
            @Part("username") RequestBody username,
            @Part("password") RequestBody password
    );


    @GET("products/getallproductratesbyid/{productid}.json")
    @Headers("Accept: Application/json")
    Call<RatessOfProductModel> getProductsRate(
            @Path(value = "productid") int productid
    );

}

