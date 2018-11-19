package com.example.hossam.parashotApp.presentation.screens.home.myOrderFragment;

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
    PreferenceHelper preferenceHelper;
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

        myOrderViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(MyOrderViewModel.class);
        myOrderViewModel.allMyOrders.observe(this, myOrdersModel ->
                {
                    assert myOrdersModel != null;
                    notcompleteOrders =myOrdersModel.notCommpleteOrderData;
                    completOrders =myOrdersModel.commpleteOrderData;
                    myOrderAdapter = new MyOrderAdapter(getActivity(), notcompleteOrders);
                    recyclerView.setAdapter(myOrderAdapter);
                }
                );


        ChipGroup chipGroup = view.findViewById(R.id.chipGroup);

        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int i) {
                Chip chip = chipGroup.findViewById(i);
                if (chip != null) {
                    for (int i2 = 0; i2 < chipGroup.getChildCount(); ++i2) {
                        chipGroup.getChildAt(i2).setClickable(true);
                    }
                    chip.setClickable(false);
                }

                if (i==2131296391) {
                    myOrderAdapter = new MyOrderAdapter(getActivity(), notcompleteOrders);
                    recyclerView.setAdapter(myOrderAdapter);
                }

                else if (i==2131296393)
                {
                    myOrderAdapter = new MyOrderAdapter(getActivity(), completOrders);
                    recyclerView.setAdapter(myOrderAdapter);
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
