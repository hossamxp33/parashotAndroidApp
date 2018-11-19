package com.example.hossam.parashotApp.presentation.screens.home.paymentFragment;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.presentation.screens.home.HomeActivity;
import com.example.hossam.parashotApp.presentation.screens.home.finishMakeOrderFragment.FinishOrderFragment;
import com.example.hossam.parashotApp.presentation.screens.home.makeOrderFromGoogleStoresFeragment.ImagePass;
import com.example.hossam.parashotApp.presentation.screens.home.storesFragment.AllStoresViewModelFactory;
import com.example.hossam.parashotApp.presentation.screens.home.userCartFragment.helper.ProductInfoToPost;
import com.example.hossam.parashotApp.presentation.screens.home.userCartFragment.helper.ProductModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.MultipartBody;


public class PaymentFragment extends Fragment {



    public PaymentFragment() {
        // Required empty public constructor
    }

    ImageView master,mada,cach;
    List<ProductInfoToPost> productList;
    List<ProductModel> ProductModels=new ArrayList<>();
    PaymentViewModel paymentViewModel;
    ImagePass imagePass;
    Boolean isFromGoogle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.payment_fragment, container, false);

        ((HomeActivity)Objects.requireNonNull(getActivity())).title.setText(getText(R.string.paymenpage));
        paymentViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(PaymentViewModel.class);


        productList = (List<ProductInfoToPost>) getArguments().getSerializable("products");
         imagePass =  getArguments().getParcelable("photo");
          isFromGoogle =  getArguments().getBoolean("fromgoogle");

         if (isFromGoogle)
         {
             if (imagePass!=null)
            ProductModels.add(new ProductModel(2,getArguments().getInt("storid"),0,
                    "asdf","jk",
                    "","",1,getArguments().getString("notes"),"",
                    imagePass.getPhoto_part(), getArguments().getString("store_icon"),
                    getArguments().getString("store_name"),"0",0,getArguments().getString("delivery_time"),
                    getArguments().getString("store_lat"),getArguments().getString("store_lang")));
             else
                 ProductModels.add(new ProductModel(2,getArguments().getInt("storid"),0,
                         getArguments().getString("user_adress"),"jk",
                         "","",1,getArguments().getString("notes"),"",
                         null, getArguments().getString("store_icon"),
                         getArguments().getString("store_name"),"0",0,getArguments().getString("delivery_time"),
                         getArguments().getString("store_lat"),getArguments().getString("store_lang")));

         }
        else {
            for (int i = 0; i < productList.size(); i++) {
                ProductModels.add(new ProductModel(2, getArguments().getInt("storid"), productList.get(i).getProductId(),
                        "asdf", "jk",
                        "", "", 1, "osama is here", ""
                       ));
            }
        }
        master = view.findViewById(R.id.master);
        mada = view.findViewById(R.id.mada);
        cach = view.findViewById(R.id.cash);

        master.setOnClickListener(v ->
                showDialog()
        );

        mada.setOnClickListener(v ->
                    showDialog()
        );

        cach.setOnClickListener(v ->
                showDialog()
        );

        return view;
    }

    private void showDialog ()
    {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_for_sale,null);
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

                if (isFromGoogle)
                paymentViewModel.saveData(ProductModels,imagePass.getPhoto_part());
                else
                    paymentViewModel.saveData(ProductModels,null);

                paymentViewModel.saveResultLiveData.observe(getActivity(),aBoolean ->
                        {
                           getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new FinishOrderFragment()).addToBackStack(null).commit();
                            alertDialog.cancel();
                        }
                );
            }
        });
    }


    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new AllStoresViewModelFactory(getActivity().getApplication());
    }

}
