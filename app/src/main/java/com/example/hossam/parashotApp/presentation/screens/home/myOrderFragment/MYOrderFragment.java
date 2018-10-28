package com.example.hossam.parashotApp.presentation.screens.home.myOrderFragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.entities.MYOrdersModel;
import com.example.hossam.parashotApp.entities.Products_in_Stories_Model;
import com.example.hossam.parashotApp.presentation.screens.home.myOrderFragment.adapters.MyOrderAdapter;
import com.example.hossam.parashotApp.presentation.screens.home.productsFragment.ProductsViewModel;
import com.example.hossam.parashotApp.presentation.screens.home.productsFragment.adapter.ProductsAdapter;
import com.example.hossam.parashotApp.presentation.screens.home.storesFragment.AllStoresViewModelFactory;

import java.util.List;


public class MYOrderFragment extends Fragment {

    MyOrderViewModel myOrderViewModel;
    private RecyclerView recyclerView;
    MyOrderAdapter myOrderAdapter;
    int COMMPLET_ORDERS_FLAG=1;

    List<MYOrdersModel.DataBean> complet,notcomplete;
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
        recyclerView = view.findViewById(R.id.recylerview);

        myOrderViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(MyOrderViewModel.class);

//        myOrderViewModel.myordersComplete_MutableLiveData.observe(getActivity(), myOrdersModel ->
//                {
//                    complet=myOrdersModel;
//                    myOrderAdapter = new MyOrderAdapter(getActivity(),notcomplete);
//                }
//        );

        myOrderViewModel.myordersComplete_MutableLiveData.observe(getActivity(), myOrdersModel ->
                {
                    notcomplete=myOrdersModel;
                    myOrderAdapter = new MyOrderAdapter(getActivity(),myOrdersModel);
                }
        );

//        if (COMMPLET_ORDERS_FLAG==1)
//            myOrderAdapter = new MyOrderAdapter(getActivity(),complet);
//        else


        recyclerView.setAdapter(myOrderAdapter);
        return view;
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new AllStoresViewModelFactory(getActivity().getApplication());
    }
}
