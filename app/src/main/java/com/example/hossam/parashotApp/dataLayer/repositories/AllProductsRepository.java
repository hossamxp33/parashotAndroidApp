package com.example.hossam.parashotApp.dataLayer.repositories;

import android.os.AsyncTask;
import android.support.v4.util.Consumer;
import android.util.Log;

import com.example.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.deo.ProductDeo;
import com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.entities.Product;
import com.example.hossam.parashotApp.entities.Products_in_Stories_Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllProductsRepository {

    private ApiInterface apiService;
    private Consumer<Products_in_Stories_Model> onSuccess;
    private Consumer<Throwable> onError;
    private ProductDeo productDeo;


    public AllProductsRepository(ApiInterface apiService1 , ProductDeo pDeo)
    {
        apiService = apiService1;
        productDeo=pDeo;
        getAllCategryData();
    }

    private void getAllCategryData() {
        try {
            apiService.getProductsData(1).enqueue(new Callback<Products_in_Stories_Model>() {
                @Override
                public void onResponse(Call<Products_in_Stories_Model> call, final Response<Products_in_Stories_Model> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful()) {
                            if (onSuccess != null) {
                                onSuccess.accept(response.body());
                            }
                        } else {
                            // TODO: return error
                            if (onError != null) {
                                onError.accept(new Exception());
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<Products_in_Stories_Model> call, Throwable t) {
                    Log.d("GetAllData fail -> ", call.toString());
                    // TODO: return error
                    if (onError != null) {
                        onError.accept(t);
                    }
                }
            });
        } catch (Exception e) {
            Log.d("SplashRepository", e.getMessage());
            onError.accept(e);
        }
    }

    public void saveDataInDB(Product data) {
        new AllProductsRepository.ProductAsyncTask(productDeo).execute(data);
    }


    private static class ProductAsyncTask extends AsyncTask<Product, Void, Void> {
        private ProductDeo productdeo;
        public ProductAsyncTask(ProductDeo productDeo) {
            productdeo = productDeo;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productdeo.insertProduct(products[0]);
            Product product= productdeo.selectAll();
            List<Product> Allproducts= productdeo.selectAllProductForStore(50);

            return null;
        }
    }



    public void setOnSuccess(Consumer<Products_in_Stories_Model> onSuccess) {
        this.onSuccess = onSuccess;
    }


    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

}
