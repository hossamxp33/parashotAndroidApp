package com.example.hossam.parashotApp.dataLayer.repositories;

import android.os.AsyncTask;
import android.support.v4.util.Consumer;
import android.util.Log;
import com.example.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.deo.ProductDeo;
import com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.entities.Product;
import com.example.hossam.parashotApp.entities.ProductDetailsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductDetailsRepository {

    private ApiInterface apiService;
    private Consumer<ProductDetailsModel> onSuccess;
    private Consumer<Throwable> onError;
    private ProductDeo productDeo;
    private Consumer<Boolean> resultChieckForAdd;
    private Consumer<Integer> counter;
    private int productid;
    public ProductDetailsRepository(ApiInterface apiService1, ProductDeo pDeo,int productid1)
    {
        apiService = apiService1;
        productDeo=pDeo;
        productid = productid1;
    }

    public void getProductDetailsData() {
        try {
            apiService.getProductDetails(productid).enqueue(new Callback<ProductDetailsModel>() {
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
        new ProductAsyncTask(productDeo).execute(data);
    }

    public void getProductCount(int storid) {
        new ProductCountAsyncTask(productDeo).execute(storid);
    }

    private  class ProductAsyncTask extends AsyncTask<Product, Void, Void> {
        private ProductDeo productdeo;
        public ProductAsyncTask(ProductDeo productDeo) {
            productdeo = productDeo;
        }

        @Override
        protected Void doInBackground(Product... products) {

            int count = productdeo.chieckItemExists(products[0].getProduct_id());
            if (count>0) {
                resultChieckForAdd.accept(false);
            }
            else {
                productdeo.insertProduct(products[0]);
                resultChieckForAdd.accept(true);
            }
            return null;
        }
    }


    private  class ProductCountAsyncTask extends AsyncTask<Integer, Void, Void> {
        private ProductDeo productdeo;
        public ProductCountAsyncTask(ProductDeo productDeo) {
            productdeo = productDeo;
        }

        @Override
        protected Void doInBackground(Integer... products) {
            int count = productdeo.getNumberOfRows(products[0]);
            counter.accept(count);
            return null;
        }
    }


    public void setProductCount(Consumer<Integer> prod_count) {
        this.counter= prod_count;
    }


    public void setbooleanConsumerForAdd(Consumer<Boolean> booleanConsumerForAdd) {
        this.resultChieckForAdd = booleanConsumerForAdd;
    }

    public void setOnSuccess(Consumer<ProductDetailsModel> onSuccess) {
        this.onSuccess = onSuccess;
    }


    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

}
