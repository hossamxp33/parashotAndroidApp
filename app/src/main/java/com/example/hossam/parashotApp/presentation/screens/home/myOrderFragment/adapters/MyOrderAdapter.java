package com.example.hossam.parashotApp.presentation.screens.home.myOrderFragment.adapters;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.databinding.MyOrdersBinding;
import com.example.hossam.parashotApp.entities.MYOrdersModel;
import com.example.hossam.parashotApp.presentation.screens.home.allProductInsideOrderFragment.ProductsInsideOrderFragment;
import com.example.hossam.parashotApp.presentation.screens.home.categoryFragment.CategoryFragment;
import com.example.hossam.parashotApp.presentation.screens.home.categoryFragment.CategoryViewModel;
import com.example.hossam.parashotApp.presentation.screens.home.categoryFragment.adapters.CategriesAdapter;
import com.example.hossam.parashotApp.presentation.screens.home.myOrderFragment.MyOrderViewModel;
import com.example.hossam.parashotApp.presentation.screens.home.productsDetailsFragment.adapters.TextAdapterForStorage;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.CustomView> {

    private LayoutInflater layoutInflater;
    private Context context;
    List<MYOrdersModel.DataBean> orderData;

    public MyOrderAdapter(Context context, List<MYOrdersModel.DataBean> data) {
        this.context = context;
        orderData = data;
    }

    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        MyOrdersBinding myOrdersBinding = DataBindingUtil.inflate(layoutInflater, R.layout.myorder_adapter_item, parent, false);
        return new CustomView(myOrdersBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, int position) {

        MyOrderViewModel myOrderViewModel = new MyOrderViewModel();

        if (orderData.get(position).getStore_id() == 0) {
            if (orderData.get(position).getPhoto() != null)
                myOrderViewModel.setImagePath("http://parashot.codesroots.com/library/orderphoto/" + orderData.get(position).getPhoto());
            else
                myOrderViewModel.setImagePath(orderData.get(position).getStore_icon());

            myOrderViewModel.setName(orderData.get(position).getStorename());
            myOrderViewModel.setStorenamevalue(orderData.get(position).getStorename());
        }

        if (orderData.get(position).getRate()>0)
        {
            myOrderViewModel.setRatestart(orderData.get(position).getRate());
        }

        else {
            if (orderData.get(position).getOrderdetails().size() > 0) {
                myOrderViewModel.setName(orderData.get(position).getOrderdetails().get(0).getProduct().getName());
                myOrderViewModel.setItem_price(orderData.get(position).getOrderdetails().get(0).getProduct().getPrice() + " ريال ");
            }

            try {
                if (orderData.get(position).getOrderdetails().get(0).getProduct().getTotal_rating() != null) {
                    if (orderData.get(position).getOrderdetails().get(0).getProduct().getTotal_rating().size() > 0) {
                        myOrderViewModel.setRatecount("(" + orderData.get(position).getOrderdetails().get(0).getProduct().getTotal_rating().get(0).getCount() + ")");
                        myOrderViewModel.setRatestart(orderData.get(position).getOrderdetails().get(0).getProduct().getTotal_rating().get(0).getStars());
                    } else
                        myOrderViewModel.setRatecount("(0)");

                } else
                    myOrderViewModel.setRatecount("(0)");

                if (orderData.get(position).getOrderdetails().size() > 0) {
                    if (orderData.get(position).getOrderdetails().get(0).getProduct().getProductphotos() != null)
                        myOrderViewModel.setImagePath(orderData.get(position).getOrderdetails().get(0).getProduct().getProductphotos().get(0).getPhoto());
                }
            } catch (Exception e) {
                Log.d("fg", e.getMessage());
            }

        }


        if (orderData.get(position).getStore() != null)
        {
            if (!orderData.get(position).getStore().getName().matches(""))
                myOrderViewModel.setStorenamevalue(orderData.get(position).getStore().getName());
        }

        if (orderData.get(position).getDelivry() != null) {
            if (!orderData.get(position).getDelivry().getName().matches(""))
                myOrderViewModel.setCapitainnamevalue(orderData.get(position).getDelivry().getName());
            else
                myOrderViewModel.setCapitainnamevalue(context.getString(R.string.nodelivery));
        }
        else
            myOrderViewModel.setCapitainnamevalue(context.getString(R.string.nodelivery));

        if (orderData.get(position).getOrder_status()!=null)
            myOrderViewModel.setOrderstatuesvalue(orderData.get(position).getOrder_status());

        if (orderData.get(position).getCreated()!=null)
            myOrderViewModel.setDateValue(getdate(orderData.get(position).getCreated()));


        holder.bind(myOrderViewModel);
        holder.myOrdersBinding.ordercard.setOnClickListener(v ->
        {
            if (orderData.get(position).getStore_id() != 0) {
                Fragment fragment = new ProductsInsideOrderFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("allProduct", (Serializable) orderData.get(position).getOrderdetails());
                fragment.setArguments(bundle);
                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).addToBackStack(null).commit();
            }

        });
    }

    private String getdate(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        try {
            Date dateObj = sdf.parse(date);
            Log.d("newdatein", dateObj.getTime() + "");
            String timestamp = String.valueOf(dateObj.getTime());//  //Example -> in ms
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = formatter.format(new Date(Long.parseLong(timestamp)));
            return dateString;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public int getItemCount() {
        return orderData.size();
    }

    class CustomView extends RecyclerView.ViewHolder {

        MyOrdersBinding myOrdersBinding;

        public CustomView(@NonNull MyOrdersBinding ordersBinding) {
            super(ordersBinding.getRoot());
            this.myOrdersBinding = ordersBinding;
        }

        public void bind(MyOrderViewModel myOrderViewModel) {
            this.myOrdersBinding.setOrdermodel(myOrderViewModel);
            myOrdersBinding.executePendingBindings();
        }

        public MyOrdersBinding getCardBinding() {
            return myOrdersBinding;
        }
    }

}
