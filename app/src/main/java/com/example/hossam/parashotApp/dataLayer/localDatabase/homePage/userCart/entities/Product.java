package com.example.hossam.parashotApp.dataLayer.localDatabase.homePage.userCart.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Product")

public class Product {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "stor_id")
    private int stor_id;


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


}
