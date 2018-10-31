package com.example.hossam.parashotApp.dataLayer.repositories;

import android.os.AsyncTask;
import android.support.v4.util.Consumer;
import android.util.Log;

import com.example.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.deo.ProductDeo;
import com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.entities.Product;
import com.example.hossam.parashotApp.entities.ProductDetailsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserCartRepository {

    private ApiInterface apiService;
    private Consumer<List<Product>> cartItems;
    private Consumer<Throwable> cartItemsError;
    private Consumer<Throwable> onError;
    private ProductDeo productDeo;
    public UserCartRepository(ApiInterface apiService1, ProductDeo pDeo)
    {
        apiService = apiService1;
        productDeo=pDeo;
    }


    public void GetProductsFromDB(int storid) {
        new ProductAsyncTask(productDeo).execute(storid);
    }

    public void deleteProductFromDB(Product product) {
        new DeleteProductAsyncTask(productDeo).execute(product);
    }
    private  class ProductAsyncTask extends AsyncTask<Integer, List<Product>,  List<Product>> {

        private ProductDeo productdeo;

        public ProductAsyncTask(ProductDeo productDeo) {
            productdeo = productDeo;
        }

        @Override
        protected List<Product> doInBackground(Integer... storeid) {
            List<Product> Allproducts = productdeo.selectAllProductForStore(storeid[0]);
            return Allproducts;
        }

        @Override
        protected void onPostExecute(List<Product> products) {

            if (cartItems != null)
                cartItems.accept(products);
            else {
                if (cartItemsError != null) {
                    cartItemsError.accept(new Exception());
                }
            }
        }
    }

    private static class DeleteProductAsyncTask extends AsyncTask<Product, Void, Void> {
        private ProductDeo productdeo;
        public DeleteProductAsyncTask(ProductDeo productDeo) {
            productdeo = productDeo;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productdeo.deleteProduct(products[0]);
            Product product= productdeo.selectAll();
            List<Product> Allproducts= productdeo.selectAllProductForStore(50);
            return null;
        }
    }


    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }


    public void setcartItemss(Consumer<List<Product>> cartItems) {
        this.cartItems = cartItems;
    }


}
