package com.example.hossam.parashotApp.presentation.screens.home.userCart.helper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductModel {


    @SerializedName("user_id")
    @Expose
    private int user_id;


    @SerializedName("store_id")
    @Expose
    private int store_id;

    @SerializedName("product_id")
    @Expose
    private int product_id;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("user_lat")
    @Expose
    private String user_lat;

    @SerializedName("user_long")
    @Expose
    private String user_long;

    @SerializedName("payment_id")
    @Expose
    private int payment_id;


    @SerializedName("notice")
    @Expose
    private String notice;


    @SerializedName("price")
    @Expose
    private String price;

    public ProductModel(int user_id, int store_id,
                        int product_id, String address, String type,
                        String user_lat, String user_long, int payment_id, String notice, String price) {
        this.user_id = user_id;
        this.store_id = store_id;
        this.product_id = product_id;
        this.address = address;
        this.type = type;
        this.user_lat = user_lat;
        this.user_long = user_long;
        this.payment_id = payment_id;
        this.notice = notice;
        this.price = price;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser_lat() {
        return user_lat;
    }

    public void setUser_lat(String user_lat) {
        this.user_lat = user_lat;
    }

    public String getUser_long() {
        return user_long;
    }

    public void setUser_long(String user_long) {
        this.user_long = user_long;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
