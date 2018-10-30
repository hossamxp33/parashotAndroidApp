package com.example.hossam.parashotApp.presentation.screens.home.allProductInsideOrderFragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.entities.MYOrdersModel;
import com.example.hossam.parashotApp.entities.Products_in_Stories_Model;
import com.example.hossam.parashotApp.entities.StoreSettingEntity;
import com.example.hossam.parashotApp.presentation.screens.home.allProductInsideOrderFragment.adapter.ProductsInsideOrderAdapter;
import com.example.hossam.parashotApp.presentation.screens.home.productsFragment.ProductsViewModel;
import com.example.hossam.parashotApp.presentation.screens.home.productsFragment.adapter.ProductsAdapter;
import com.example.hossam.parashotApp.presentation.screens.home.storesFragment.AllStoresViewModelFactory;

import java.util.List;


public class ProductsInsideOrderFragment extends Fragment {



    private RecyclerView recyclerView;
    private ProductsInsideOrderAdapter productsInsideOrderAdapter;
    List<MYOrdersModel.DataBean.OrderdetailsBean> orderdetailsBeans;
    public ProductsInsideOrderFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_product_inside_orders, container, false);
        recyclerView = view.findViewById(R.id.recylerview);

        assert getArguments() != null;
        orderdetailsBeans = (List<MYOrdersModel.DataBean.OrderdetailsBean>) getArguments().getSerializable("allProduct");
        productsInsideOrderAdapter = new ProductsInsideOrderAdapter(getActivity(),orderdetailsBeans);
        recyclerView.setAdapter(productsInsideOrderAdapter);
        return view;
    }

}
