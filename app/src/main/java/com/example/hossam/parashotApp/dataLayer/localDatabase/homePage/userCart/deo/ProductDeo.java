package com.example.hossam.parashotApp.dataLayer.localDatabase.homePage.userCart.deo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.example.hossam.parashotApp.dataLayer.localDatabase.homePage.userCart.entities.Product;


@Dao
public interface ProductDeo {

    @Insert
    public void insertProduct(Product productDeo);

    @Update
    public void upateProduct(Product productDeo);

    @Delete
    public void deleteProduct(Product productDeo);

}
