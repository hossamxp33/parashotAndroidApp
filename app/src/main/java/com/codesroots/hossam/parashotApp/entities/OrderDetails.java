package com.codesroots.hossam.parashotApp.entities;

import com.google.gson.annotations.SerializedName;

public class OrderDetails {

    String storename,storeaddress,clientname,clientaddress,
            productname,productPrice,storelat,storelang;

    int id,paymentWay;

    @SerializedName("userId")
    int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @SerializedName("user_lat")
     String userlat;

    @SerializedName("user_long")
    String userlang ;

    public OrderDetails() {
    }


    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getStoreaddress() {
        return storeaddress;
    }

    public void setStoreaddress(String storeaddress) {
        this.storeaddress = storeaddress;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getClientaddress() {
        return clientaddress;
    }

    public void setClientaddress(String clientaddress) {
        this.clientaddress = clientaddress;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public int getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(int paymentWay) {
        this.paymentWay = paymentWay;
    }

    public String getUserlat() {
        return userlat;
    }

    public void setUserlat(String userlat) {
        this.userlat = userlat;
    }

    public String getUserlang() {
        return userlang;
    }

    public void setUserlang(String userlang) {
        this.userlang = userlang;
    }

    public String getStorelat() {
        return storelat;
    }

    public void setStorelat(String storelat) {
        this.storelat = storelat;
    }

    public String getStorelang() {
        return storelang;
    }

    public void setStorelang(String storelang) {
        this.storelang = storelang;
    }
}
