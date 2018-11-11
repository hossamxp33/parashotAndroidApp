package com.example.hossam.parashotApp.presentation.screens.home.myOrderFragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.entities.MYOrdersModel;
import com.example.hossam.parashotApp.presentation.screens.home.HomeActivity;
import com.example.hossam.parashotApp.presentation.screens.home.myOrderFragment.adapters.MyOrderAdapter;
import com.example.hossam.parashotApp.presentation.screens.home.storesFragment.AllStoresViewModelFactory;

import java.util.List;
import java.util.Objects;

import co.ceryle.segmentedbutton.SegmentedButtonGroup;


public class MYOrderFragment extends Fragment {

    MyOrderViewModel myOrderViewModel;
    private RecyclerView recyclerView;
    MyOrderAdapter myOrderAdapter;


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

        ((HomeActivity)Objects.requireNonNull(getActivity())).title.setText(getText(R.string.myorder));

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

        SegmentedButtonGroup segmentedButtonGroup = view.findViewById(R.id.segment);
        segmentedButtonGroup.setOnClickedButtonListener(new SegmentedButtonGroup.OnClickedButtonListener() {
            @Override
            public void onClickedButton(int position) {
                if (position==0)
                {
                    myOrderAdapter = new MyOrderAdapter(getActivity(), notcompleteOrders);
                    recyclerView.setAdapter(myOrderAdapter);
                }
                else
                {
                    myOrderAdapter = new MyOrderAdapter(getActivity(), completOrders);
                    recyclerView.setAdapter(myOrderAdapter);
                }
            }
        });
        segmentedButtonGroup.setPosition(2, 0);

        return view;
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new AllStoresViewModelFactory(getActivity().getApplication());
    }
}
