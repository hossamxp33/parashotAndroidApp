package com.codesroots.hossam.parashotApp.dataLayer.apiData;

import android.database.Observable;

import com.codesroots.hossam.parashotApp.entities.Categories;
import com.codesroots.hossam.parashotApp.entities.DealsModel;
import com.codesroots.hossam.parashotApp.entities.DeliveryComments;
import com.codesroots.hossam.parashotApp.entities.DeliveryOffers;
import com.codesroots.hossam.parashotApp.entities.FavProduct;
import com.codesroots.hossam.parashotApp.entities.MakeOrder;
import com.codesroots.hossam.parashotApp.entities.OffersModel;
import com.codesroots.hossam.parashotApp.entities.OrderEdit;
import com.codesroots.hossam.parashotApp.entities.RatessOfProductModel;
import com.codesroots.hossam.parashotApp.entities.LoginResponseModel;
import com.codesroots.hossam.parashotApp.entities.MYOrdersModel;
import com.codesroots.hossam.parashotApp.entities.ProductDetailsModel;
import com.codesroots.hossam.parashotApp.entities.Products_in_Stories_Model;
import com.codesroots.hossam.parashotApp.entities.RegisterResponseModel;
import com.codesroots.hossam.parashotApp.entities.StoreSettingEntity;
import com.codesroots.hossam.parashotApp.entities.StoresList;
import com.codesroots.hossam.parashotApp.entities.StoresFromGoogleModel;
import com.codesroots.hossam.parashotApp.entities.Notifications;
import com.codesroots.hossam.parashotApp.presentation.screens.chatAndMapActivity.entities.AddMessage;
import com.codesroots.hossam.parashotApp.presentation.screens.chatAndMapActivity.entities.ChatList;
import com.codesroots.hossam.parashotApp.presentation.screens.chatAndMapActivity.entities.chatmessages;
import com.codesroots.hossam.parashotApp.presentation.screens.home.userCartFragment.helper.ProductModel;
import java.util.List;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
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

    @GET("products/ProductList/{storid}/{userid}.json")
    @Headers("Accept: Application/json")
    Call<Products_in_Stories_Model> getProductsData(
            @Path(value = "storid") int storid,
            @Path(value = "userid") int userid
    );

    @GET("favourite/getuserfavourite/{userid}.json")
    @Headers("Accept: Application/json")
    Call<FavProduct> getFavProductsData(
            @Path(value = "userid") int userid,
            @Header("authorization") String auth
    );


    @GET("products/getproduct/{productid}/{userid}.json")
    @Headers("Accept: Application/json")
    Call<ProductDetailsModel> getProductDetails(
            @Path(value = "productid") int productid,
            @Path(value = "userid") int userid
    );


    @GET("orders/getorders/{userid}.json")
    @Headers("Accept: Application/json")
    Call<MYOrdersModel> getMyOrders(
            @Path(value = "userid") int userid,
            @Header("authorization") String auth
    );


    ////////////// make order from google stors
    @Multipart
    @POST("orders/androidorder/2/{userid}.json")
    Call<MakeOrder> makeOrder(
            @Path(value = "userid") int userid,
            @Part ("orders")List<ProductModel> models,
            @Part MultipartBody.Part file,
            @Header("authorization") String auth
    );

    ////////////// make order from application stors
    @POST("orders/androidorder/1/{userid}.json")
    @Headers("Accept: Application/json")
    Call<MakeOrder> makeOrderProduct(
            @Path(value = "userid") int userid,
            @Body List<ProductModel> models,
            @Header("authorization") String auth
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


    @FormUrlEncoded
    @POST("orders/edit/{orderid}.json")
    Call<ResponseBody> editOrderStatues(
            @Path(value = "orderid") int orderid,
            @Field("delivry_id") int delivry,
            @Field("order_status") int status,
            @Field("delivery_price") String price
    );

    @FormUrlEncoded
    @POST("orders/edit/{orderid}.json")
    Call<OrderEdit> editOrderStatuesData(
            @Path(value = "orderid") int orderid,
            @Field("order_status") int order_status
    );

    @GET("products/getallproductratesbyid/{productid}/{type}.json")
    @Headers("Accept: Application/json")
    Call<RatessOfProductModel> getProductsRate(
            @Path(value = "productid") int productid,
            @Path(value = "type") int type
    );

    @GET("delivries/getdeliveryrate/{deliveryId}.json")
    @Headers("Accept: Application/json")
    Call<DeliveryComments> getDeliveryRates(
            @Path(value = "deliveryId") int deliveryId
    );


    @GET("offers/getoffers.json")
    Call<OffersModel> getOffers();


    @GET("deals/getdeals.json")
    Call<DealsModel> getDeals();


    @GET("Deliveryoffers/getorderdelivery/{orderid}.json")
    @Headers("Accept: Application/json")
    Call<DeliveryOffers> getDeliveryOffers(
            @Path(value = "orderid") int orderid
    );


    @Multipart
    @POST("favourite/addfavourite.json")
    Call<ResponseBody> addfavourite(
            @Part("user_id") RequestBody user_id,
            @Part("product_id") RequestBody product_id,
            @Part("smallstore_id") RequestBody smallstore_id
    );

    @DELETE("favourite/delete/{favid}.json")
    @Headers("Accept: Application/json")
    Call<ResponseBody> deleteFav(
            @Path(value = "favid") int favid
    );

    @GET("Notifications/getnotifications/{userid}.json")
    @Headers("Accept: Application/json")
    Call<Notifications> getnotifications(
            @Path(value = "userid") int userid
    );


    @Multipart
    @POST("productrates/add.json")
    Call<ResponseBody> productrates(
            @Part("user_id") RequestBody user_id,
            @Part("product_id") RequestBody product_id,
            @Part("comment") RequestBody comment,
            @Part("rate") RequestBody rate
    );

    @Multipart
    @POST("deliveryrates/add.json")
    Call<ResponseBody> deliveryrates(
            @Part("user_id") RequestBody user_id,
            @Part("product_id") RequestBody product_id,
            @Part("comment") RequestBody comment,
            @Part("rate") RequestBody rate
    );


    @GET("chats/chatBTWusers/{userid}.json")
    @Headers("Accept: Application/json")
    Call<ChatList> getChatList(
            @Path(value = "order_id") int userid
    );


    @FormUrlEncoded
    @POST("chats/chatBTWusers/{pageid}.json")
    Call<chatmessages> getChatData(
            @Path("pageid") int page,
            @Field("order_id") int order_id
    );


    @Multipart
    @POST("chats/add.json")
    Call<AddMessage> addmessages(
            @Part("user_id") RequestBody user_id,
            @Part("order_id") RequestBody order_id,
            @Part("room_id") RequestBody room_id,
            @Part("post") RequestBody post,
            @Part  MultipartBody.Part photo
    );
}

