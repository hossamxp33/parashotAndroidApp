package com.example.hossam.parashotApp.presentation.screens.home.paymentFragment;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.dataLayer.apiData.ApiClientLocal;
import com.example.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.entities.Product;
import com.example.hossam.parashotApp.entities.Categories;
import com.example.hossam.parashotApp.helper.ProgressDialogHelper;
import com.example.hossam.parashotApp.presentation.screens.home.finishMakeOrderFragment.FinishOrderFragment;
import com.example.hossam.parashotApp.presentation.screens.home.myOrderFragment.MYOrderFragment;
import com.example.hossam.parashotApp.presentation.screens.home.storesFragment.AllStoresViewModelFactory;
import com.example.hossam.parashotApp.presentation.screens.home.userCart.Adapter.FirstCartAdapter;
import com.example.hossam.parashotApp.presentation.screens.home.userCart.UserCartViewModel;
import com.example.hossam.parashotApp.presentation.screens.home.userCart.helper.ProductInfoToPost;
import com.example.hossam.parashotApp.presentation.screens.home.userCart.helper.ProductModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PaymentFragment extends Fragment {



    public PaymentFragment() {
        // Required empty public constructor
    }

    ImageView master,mada,cach;
    List<ProductInfoToPost> productList;
    List<ProductModel> ProductModels=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.payment_fragment, container, false);


        productList = (List<ProductInfoToPost>) getArguments().getSerializable("products");

        for (int i=0;i<productList.size();i++)
        {
            ProductModels.add(new ProductModel(1,1,productList.get(i).getProductId(),"asdf","jk",
                    "","",1,"osama is here",""));
        }

        master = view.findViewById(R.id.master);
        mada = view.findViewById(R.id.mada);
        cach = view.findViewById(R.id.cash);


        master.setOnClickListener(v ->
                {
                    showDialog();
                }
        );

        mada.setOnClickListener(v ->
                {
                    showDialog();
                }
        );

        cach.setOnClickListener(v ->
                {
                    showDialog();
                }
        );
        return view;
    }

    private void showDialog ()
    {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_for_sale, null);
        dialogBuilder.setView(dialogView);
        final EditText storagetxt =  dialogView.findViewById(R.id.dialogEditText);
        TextView save;
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        alertDialog.show();
        save =dialogView.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new FinishOrderFragment()).addToBackStack(null).commit();
                alertDialog.cancel();
                addPost();
            }
        });
    }



    private void addPost() {

        ProgressDialogHelper.showSimpleProgressDialog(getActivity(), false);
        ApiInterface apiService =
                ApiClientLocal.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiService.makeOrder(ProductModels);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                ProgressDialogHelper.removeSimpleProgressDialog();
                if (response.body() != null) {

                    if (response.isSuccessful())
                    {
                    }
                }

                else {
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ProgressDialogHelper.removeSimpleProgressDialog();
                Log.d("fail",call.toString());
                // Toast.makeText(getActivity(), getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new AllStoresViewModelFactory(getActivity().getApplication());
    }

}
