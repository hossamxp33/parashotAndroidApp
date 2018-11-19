package com.example.hossam.parashotApp.presentation.screens.home.userCartFragment.helper;

import java.io.Serializable;

public class ProductInfoToPost implements Serializable {

    int productId,productCount;

    public ProductInfoToPost(int productId, int productCount) {
        this.productId = productId;
        this.productCount = productCount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }
}
