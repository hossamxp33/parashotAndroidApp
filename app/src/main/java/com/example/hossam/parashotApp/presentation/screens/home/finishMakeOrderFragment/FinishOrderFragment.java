package com.example.hossam.parashotApp.presentation.screens.home.finishMakeOrderFragment;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProvider;
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
import com.example.hossam.parashotApp.presentation.screens.home.myOrderFragment.MYOrderFragment;
import com.example.hossam.parashotApp.presentation.screens.home.paymentFragment.PaymentFragment;
import com.example.hossam.parashotApp.presentation.screens.home.storesFragment.AllStoresViewModelFactory;


public class FinishOrderFragment extends Fragment {



    public FinishOrderFragment() {
        // Required empty public constructor
    }

    TextView gotodelivery;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.first_finish_order, container, false);
        gotodelivery = view.findViewById(R.id.gotodelivery);
        gotodelivery.setOnClickListener(v ->
   getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MYOrderFragment()).addToBackStack(null).commit()

        );
        return view;
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new AllStoresViewModelFactory(getActivity().getApplication());
    }

}
