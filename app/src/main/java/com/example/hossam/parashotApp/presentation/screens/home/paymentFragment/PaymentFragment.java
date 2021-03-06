package com.example.hossam.parashotApp.presentation.screens.home.paymentFragment;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.helper.PreferenceHelper;
import com.example.hossam.parashotApp.presentation.screens.home.HomeActivity;
import com.example.hossam.parashotApp.presentation.screens.home.categoryFragment.CategoryFragment;
import com.example.hossam.parashotApp.presentation.screens.home.finishMakeOrderFragment.FinishOrderFragment;
import com.example.hossam.parashotApp.presentation.screens.home.makeOrderFromGoogleStoresFeragment.ImagePass;
import com.example.hossam.parashotApp.presentation.screens.home.storesFragment.AllStoresViewModelFactory;
import com.example.hossam.parashotApp.presentation.screens.home.userCartFragment.helper.ProductInfoToPost;
import com.example.hossam.parashotApp.presentation.screens.home.userCartFragment.helper.ProductModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.hossam.parashotApp.presentation.screens.getUserLocation.GetUserLocationActivity.FULL_ADDRESS;
import static com.example.hossam.parashotApp.presentation.screens.getUserLocation.GetUserLocationActivity.USER_LANG;
import static com.example.hossam.parashotApp.presentation.screens.getUserLocation.GetUserLocationActivity.USER_LAT;


public class PaymentFragment extends Fragment {


    public PaymentFragment() {
        // Required empty public constructor
    }

    ImageView master, mada, cach;
    List<ProductInfoToPost> productList;
    ArrayList<ProductModel> ProductModels = new ArrayList<>();
    PaymentViewModel paymentViewModel;
    ImagePass imagePass;
    Boolean isFromGoogle;
    int paymentway, userid;
    PreferenceHelper preferenceHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.payment_fragment, container, false);

        preferenceHelper = new PreferenceHelper(getActivity());
        userid = preferenceHelper.getUserId();
        ((HomeActivity) Objects.requireNonNull(getActivity())).title.setText(getText(R.string.paymenpage));
        paymentViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(PaymentViewModel.class);

        assert getArguments() != null;
        productList = getArguments().getParcelableArrayList("products");
        imagePass = getArguments().getParcelable("photo");
        isFromGoogle = getArguments().getBoolean("fromgoogle");

        master = view.findViewById(R.id.master);
        mada = view.findViewById(R.id.mada);
        cach = view.findViewById(R.id.cash);

        master.setOnClickListener(v ->
                {
                    paymentway = 1;
                    showDialog();
                }
        );

        mada.setOnClickListener(v ->
                {
                    paymentway = 2;
                    showDialog();
                }
        );

        cach.setOnClickListener(v ->
                {
                    paymentway = 3;
                    showDialog();
                }
        );

        return view;
    }

    private void showDialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_for_sale, null);
        dialogBuilder.setView(dialogView);
        final EditText storagetxt = dialogView.findViewById(R.id.dialogEditText);
        TextView save;
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        alertDialog.show();
        save = dialogView.findViewById(R.id.save);

        save.setOnClickListener(v -> {

            /////////collect data if itis from google
            if (isFromGoogle) {

                if (imagePass != null) {
                    ProductModels.add(new ProductModel(userid, getArguments().getInt("storid"), 0,
                            getArguments().getString("store_address"),
                            getArguments().getString("user_adress"),
                            getArguments().getString("user_lat"),
                            getArguments().getString("user_lang"),
                            paymentway, getArguments().getString("notes"), "",
                            imagePass.getPhoto_part(), getArguments().getString("store_icon"),
                            getArguments().getString("store_name"), "0", 0, getArguments().getString("delivery_time"),
                            getArguments().getString("store_lat"), getArguments().getString("store_lang"), 0,
                            getArguments().getFloat("store_rate")));
                    paymentViewModel.saveDataFromGoogle(ProductModels, imagePass.getPhoto_part());

                } else {
                    ProductModels.add(new ProductModel(userid,
                            getArguments().getInt("storid"), 0,
                            getArguments().getString("store_address"),
                            getArguments().getString("user_adress"),
                            getArguments().getString("user_lat"),
                            getArguments().getString("user_lang"),
                            paymentway, getArguments().getString("notes"), "",
                            null, getArguments().getString("store_icon"),
                            getArguments().getString("store_name"), "0", 0, getArguments().getString("delivery_time"),
                            getArguments().getString("store_lat"), getArguments().getString("store_lang"), 0,
                            getArguments().getFloat("store_rate")));
                    paymentViewModel.saveDataFromGoogle(ProductModels, null);
                }
            }

            /////////collect data if itis from API
            else {
                for (int i = 0; i < productList.size(); i++) {
                    Log.i("forin", "showDialog: hossam");
                    ProductModels.add(new ProductModel(userid, getArguments().getInt("storid"), productList.get(i).getProductId(),
                            String.valueOf(productList.get(i).getProductCount()),
                            getArguments().getString("USER_ADDRESS"), "jk",
                            getArguments().getString("USER_LAT"), getArguments().getString("USER_LANG"), paymentway,
                            "saied is here", getArguments().getString("price"), 0
                    ));
                }

                paymentViewModel.saveData(ProductModels);
            }

            paymentViewModel.saveResultLiveData.observe(this, aBoolean ->
                    {
                        if (getActivity() != null) {
                            FragmentManager fm = getActivity().getSupportFragmentManager();
                            for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                                Fragment fragment = fm.findFragmentById(R.id.main_frame);
                                if (!(fragment instanceof CategoryFragment))
                                    fm.popBackStack();
                            }
                            alertDialog.cancel();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new CategoryFragment()).addToBackStack(null).commit();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new FinishOrderFragment()).addToBackStack(null).commit();
                        }
                    }
            );
        });
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new AllStoresViewModelFactory(getActivity().getApplication());
    }

}
