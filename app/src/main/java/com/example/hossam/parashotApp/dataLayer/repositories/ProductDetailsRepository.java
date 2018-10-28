package com.example.hossam.parashotApp.dataLayer.repositories;

import android.os.AsyncTask;
import android.support.v4.util.Consumer;
import android.util.Log;

import com.example.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.example.hossam.parashotApp.dataLayer.localDatabase.homePage.userCart.deo.ProductDeo;
import com.example.hossam.parashotApp.dataLayer.localDatabase.homePage.userCart.entities.Product;
import com.example.hossam.parashotApp.entities.ProductDetailsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductDetailsRepository {

    private ApiInterface apiService;
    private Consumer<ProductDetailsModel> onSuccess;
    private Consumer<Throwable> onError;

    public ProductDetailsRepository(ApiInterface apiService1)
    {
        apiService = apiService1;
    }

    public void getProductDetailsData() {
        try {
            apiService.getProductDetails(1).enqueue(new Callback<ProductDetailsModel>() {
                @Override
                public void onResponse(Call<ProductDetailsModel> call, final Response<ProductDetailsModel> response) {
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
                public void onFailure(Call<ProductDetailsModel> call, Throwable t) {
                    Log.d("GetAllData fail -> ", call.toString());
                    // TODO: return error
                    if (onError != null) {
                        onError.accept(t);
                    }
                }
            });
        } catch (Exception e) {
            Log.d("product details", e.getMessage());
            onError.accept(e);
        }
    }

    public void saveDataInDB(Product data) {

     //   new ProductAsyncTask(footerDao).execute(data);
    }

    private static class ProductAsyncTask extends AsyncTask<Product, Void, Void> {

        private ProductDeo productdeo;

        public ProductAsyncTask(ProductDeo productDeo) {
            productdeo = productDeo;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productdeo.insertProduct(products[0]);
            return null;
        }
    }


    public void setOnSuccess(Consumer<ProductDetailsModel> onSuccess) {
        this.onSuccess = onSuccess;
    }


    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

}
