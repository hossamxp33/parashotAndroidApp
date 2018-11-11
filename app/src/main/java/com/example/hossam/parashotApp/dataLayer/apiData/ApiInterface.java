package com.example.hossam.parashotApp.dataLayer.apiData;

import com.example.hossam.parashotApp.entities.Categories;
import com.example.hossam.parashotApp.entities.MYOrdersModel;
import com.example.hossam.parashotApp.entities.ProductDetailsModel;
import com.example.hossam.parashotApp.entities.Products_in_Stories_Model;
import com.example.hossam.parashotApp.entities.StoreSettingEntity;
import com.example.hossam.parashotApp.entities.AllStoriesModel;
import com.example.hossam.parashotApp.presentation.screens.home.userCart.helper.ProductModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @GET("smallstores/getsmallstoredata/{cate_subCat_id}/{type}.json")
    @Headers("Accept: Application/json")
    Call<AllStoriesModel> getSubCategriesData(
            @Path(value = "cate_subCat_id") int storid,
            @Path(value = "type") int userId
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


    @POST("orders/addorder.json")
    @Headers("Accept: Application/json")
    Call<ResponseBody> makeOrder(
            @Body List<ProductModel> models
            );

}

