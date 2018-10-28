package com.example.hossam.parashotApp.presentation.screens.home.myOrderFragment.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.databinding.CategriesBinding;
import com.example.hossam.parashotApp.databinding.MyOrdersBinding;
import com.example.hossam.parashotApp.entities.MYOrdersModel;
import com.example.hossam.parashotApp.presentation.screens.home.categoryFragment.adapters.CategriesAdapter;
import com.example.hossam.parashotApp.presentation.screens.home.myOrderFragment.MyOrderViewModel;
import com.example.hossam.parashotApp.presentation.screens.home.productsDetailsFragment.adapters.TextAdapterForStorage;

import java.util.ArrayList;
import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.CustomView> {

    private LayoutInflater layoutInflater;
    private Context context;
    List<MYOrdersModel.DataBean> orderData;

    public MyOrderAdapter(Context context, List<MYOrdersModel.DataBean> data)
    {
        this.context =  context;
        orderData = data;
    }

    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        MyOrdersBinding myOrdersBinding = DataBindingUtil.inflate(layoutInflater, R.layout.myorder_adapter_item,parent,false);
        return new CustomView(myOrdersBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, int position) {

        MyOrderViewModel myOrderViewModel =new MyOrderViewModel();
       myOrderViewModel.setName(orderData.get(position).getOrderdetails().get(0).getProduct().getName());

    }

    @Override
    public int getItemCount() {
        return orderData.size();
    }

    class CustomView extends RecyclerView.ViewHolder {

        MyOrdersBinding myOrdersBinding;

        public CustomView(@NonNull MyOrdersBinding ordersBinding) {
            super(ordersBinding.getRoot());
            this.myOrdersBinding=ordersBinding;
        }
    }

}
