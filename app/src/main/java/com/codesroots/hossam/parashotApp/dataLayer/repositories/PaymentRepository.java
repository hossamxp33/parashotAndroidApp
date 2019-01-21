package com.codesroots.hossam.parashotApp.dataLayer.repositories;

import android.os.AsyncTask;
import android.support.v4.util.Consumer;
import android.util.Log;

import com.codesroots.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.parashotApp.dataLayer.localDatabase.userCart.deo.ProductDeo;
import com.codesroots.hossam.parashotApp.dataLayer.localDatabase.userCart.entities.Product;
import com.codesroots.hossam.parashotApp.entities.MakeOrder;
import com.codesroots.hossam.parashotApp.helper.PreferenceHelper;
import com.codesroots.hossam.parashotApp.presentation.screens.home.userCartFragment.helper.ProductInfoToPost;
import com.codesroots.hossam.parashotApp.presentation.screens.home.userCartFragment.helper.ProductModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PaymentRepository {


    private ApiInterface apiService;
    private Consumer<Integer> onSuccess;
    private Consumer<Throwable> onError;
    List<ProductInfoToPost> productsdata;
    ProductDeo productDeo;

    public PaymentRepository(ApiInterface apiService, ProductDeo productDeo1, List<ProductModel> productsdata) {
        this.apiService = apiService;
        this.productDeo = productDeo1;
    }

    public void addOrder(List<ProductModel> ProductModels) {
        apiService.makeOrderProduct(PreferenceHelper.getUserId(), ProductModels, "bearer " + PreferenceHelper.getToken()).enqueue(new Callback<MakeOrder>() {
            @Override
            public void onResponse(Call<MakeOrder> call, final Response<MakeOrder> response) {
                try {
                    if (response != null)
                        onSuccess.accept(response.body().getId());
                    else
                        onSuccess.accept(0);
                } catch (Exception e) {
                    onError.accept(e.getCause());
                }
            }

            @Override
            public void onFailure(Call<MakeOrder> call, Throwable t) {
                if (onError != null) {
                    onError.accept(t);
                }
            }
        });
    }

    public void delecteCart(int storid) {
        new DeleteProductAsyncTask(productDeo).execute(storid);
    }

    private class DeleteProductAsyncTask extends AsyncTask<Integer, Void, Void> {
        private ProductDeo productdeo;

        public DeleteProductAsyncTask(ProductDeo productDeo) {
            productdeo = productDeo;
        }

        @Override
        protected Void doInBackground(Integer... id) {
            productdeo.deleteAllRows(id[0]);
            return null;
        }
    }


    public void addOrderFromGoogle(List<ProductModel> ProductModels, MultipartBody.Part part) {

        apiService.makeOrder(PreferenceHelper.getUserId(), ProductModels, part, "bearer " + PreferenceHelper.getToken()).enqueue(new Callback<MakeOrder>() {
            @Override
            public void onResponse(Call<MakeOrder> call, final Response<MakeOrder> response) {
                try {
                    onSuccess.accept(response.body().getId());
                } catch (Exception e) {
                    onError.accept(e.getCause());
                }
            }

            @Override
            public void onFailure(Call<MakeOrder> call, Throwable t) {
                if (onError != null) {
                    onError.accept(t);
                }
            }
        });
    }

    public void setOnSuccess(Consumer<Integer> onSuccess) {
        this.onSuccess = onSuccess;
    }

    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }


}
