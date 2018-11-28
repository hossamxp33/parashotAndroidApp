package com.example.hossam.parashotApp.dataLayer.repositories;

import android.os.AsyncTask;
import android.support.v4.util.Consumer;
import android.util.Log;
import com.example.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.deo.ProductDeo;
import com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.entities.Product;
import com.example.hossam.parashotApp.entities.Products_in_Stories_Model;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllProductsRepository {

    private ApiInterface apiService;
    private Consumer<Products_in_Stories_Model> onSuccess;
    private Consumer<Boolean> booleanConsumerForAdd;
    private Consumer<Integer> productsCount;
    private Consumer<Throwable> onError;
    private int store_id;


    private ProductDeo productDeo;


    public AllProductsRepository(ApiInterface apiService1, ProductDeo pDeo, int storeid)
    {
        apiService = apiService1;
        productDeo=pDeo;
        store_id = storeid;
        getAllProductData();
    }

    public void getAllProduct()
    {
        this.getAllProductData();
    }

    private void getAllProductData() {
        try {
            apiService.getProductsData(store_id).enqueue(new Callback<Products_in_Stories_Model>() {
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

    public void getProductCount() {
        new AllProductsRepository.ProductCountAsyncTask(productDeo).execute(store_id);
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
                booleanConsumerForAdd.accept(false);

            }
            else {
                productdeo.insertProduct(products[0]);
                booleanConsumerForAdd.accept(true);
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
            productsCount.accept(count);
            return null;
        }
    }



    public void setOnSuccess(Consumer<Products_in_Stories_Model> onSuccess) {
        this.onSuccess = onSuccess;
    }

    public void setbooleanConsumerForAdd(Consumer<Boolean> booleanConsumerForAdd) {
        this.booleanConsumerForAdd = booleanConsumerForAdd;
    }

    public void setProductsCount(Consumer<Integer> counter) {
        this.productsCount = counter;
    }

    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

}
