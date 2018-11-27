package com.example.hossam.parashotApp.presentation.screens.home.myOrderFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.entities.MYOrdersModel;
import com.example.hossam.parashotApp.helper.PreferenceHelper;
import com.example.hossam.parashotApp.presentation.screens.home.HomeActivity;
import com.example.hossam.parashotApp.presentation.screens.home.myOrderFragment.adapters.MyOrderAdapter;
import java.util.List;
import java.util.Objects;


public class MYOrderFragment extends Fragment {

    MyOrderViewModel myOrderViewModel;
    private RecyclerView recyclerView;
    MyOrderAdapter myOrderAdapter;
    int userid;
    TextView txtnotfound;
    PreferenceHelper preferenceHelper;
    private FrameLayout progress;
    List<MYOrdersModel.DataBean> completOrders, notcompleteOrders;
    public MYOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_myorders, container, false);
        preferenceHelper = new PreferenceHelper(getActivity());
        ((HomeActivity)Objects.requireNonNull(getActivity())).title.setText(getText(R.string.myorder));

        userid = preferenceHelper.getUserId();
        recyclerView = view.findViewById(R.id.recylerview);
        progress = view.findViewById(R.id.progress);
        txtnotfound = view.findViewById(R.id.txtnotfound);


        myOrderViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(MyOrderViewModel.class);

        myOrderViewModel.allMyOrders.observe(this, myOrdersModel ->
                {
                    assert myOrdersModel != null;
                    notcompleteOrders =myOrdersModel.notCommpleteOrderData;
                    completOrders =myOrdersModel.commpleteOrderData;
                    myOrderAdapter = new MyOrderAdapter(getActivity(), notcompleteOrders);
                    recyclerView.setAdapter(myOrderAdapter);
                });


        myOrderViewModel.loading.observe(this, loading ->
                progress.setVisibility(loading ? View.VISIBLE : View.GONE));

        ChipGroup chipGroup = view.findViewById(R.id.chiporders);
        chipGroup.setOnCheckedChangeListener((chipGroup1, i) -> {
            Chip chip = chipGroup1.findViewById(i);
            if (chip != null) {
                for (int i2 = 0; i2 < chipGroup1.getChildCount(); ++i2) {
                    chipGroup1.getChildAt(i2).setClickable(true);
                }
                chip.setClickable(false);
            }

            if (i==2131296394) {
                if (notcompleteOrders.size()>0) {
                    myOrderAdapter = new MyOrderAdapter(getActivity(), notcompleteOrders);
                    recyclerView.setAdapter(myOrderAdapter);
                    recyclerView.setVisibility(View.VISIBLE);
                    txtnotfound.setVisibility(View.GONE);
                }
                else {
                    txtnotfound.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
            }

            else if (i==2131296392)
            {
                if (completOrders.size()>0) {
                    myOrderAdapter = new MyOrderAdapter(getActivity(), completOrders);
                    recyclerView.setAdapter(myOrderAdapter);
                    recyclerView.setVisibility(View.VISIBLE);
                    txtnotfound.setVisibility(View.GONE);
                }

                else {
                    txtnotfound.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }

            }
        });

        return view;
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new MyOrderViewModelFactory(getActivity().getApplication(),userid);
    }
}
