package com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.deo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.entities.Product;

import java.util.List;


@Dao
public interface ProductDeo {

    @Insert
    public void insertProduct(Product productDeo);

    @Update
    public void upateProduct(Product productDeo);

    @Delete
    public void deleteProduct(Product productDeo);

    @Query("select * from Product ")
    public Product selectAll();

    @Query("select * from Product where stor_id like :storeid")
    public List<Product> selectAllProductForStore(int storeid);


    @Query("SELECT COUNT(id) FROM Product WHERE stor_id like :store_id")
    int getNumberOfRows(int store_id);


    @Query("SELECT COUNT(*) FROM Product WHERE stor_id like :stor_id")
    int chieckItemExists(int stor_id);

}
