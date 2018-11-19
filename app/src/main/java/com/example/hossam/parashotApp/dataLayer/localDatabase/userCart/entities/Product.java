package com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Product")

public class Product implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "stor_id")
    private int stor_id;

    @ColumnInfo(name = "product_id")
    private int product_id;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @ColumnInfo(name = "count")
    private int count;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", stor_id=" + stor_id +
                ", product_id=" + product_id +
                ", price='" + price + '\'' +
                ", photo='" + photo + '\'' +
                ", name='" + name + '\'' +
                ", rateStars=" + rateStars +
                ", rateCount=" + rateCount +
                '}';
    }
}
