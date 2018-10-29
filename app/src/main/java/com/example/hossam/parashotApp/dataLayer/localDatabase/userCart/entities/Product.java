package com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Product")

public class Product {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "stor_id")
    private int stor_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "price")
    private String price;

    @ColumnInfo(name = "photo")
    private String photo;


    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "rateStars")
    private float rateStars;

    @ColumnInfo(name = "rateCount")
    private int rateCount;

    public int getStor_id() {
        return stor_id;
    }

    public void setStor_id(int stor_id) {
        this.stor_id = stor_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRateStars() {
        return rateStars;
    }

    public void setRateStars(float rateStars) {
        this.rateStars = rateStars;
    }

    public int getRateCount() {
        return rateCount;
    }

    public void setRateCount(int rateCount) {
        this.rateCount = rateCount;
    }
}
