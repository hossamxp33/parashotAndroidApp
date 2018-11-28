package com.example.hossam.parashotApp.presentation.screens.home.deliverOffersFragment.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.entities.Categories;
import com.example.hossam.parashotApp.entities.DeliveryOffers;
import com.example.hossam.parashotApp.helper.PreferenceHelper;
import com.example.hossam.parashotApp.presentation.screens.home.storesFragment.StoresFragment;
import com.example.hossam.parashotApp.presentation.screens.home.subcategryFragment.SubCategoriesFragment;

import java.util.ArrayList;
import java.util.List;

public class DeliveryOffersAdapter extends RecyclerView.Adapter<DeliveryOffersAdapter.CustomView> {


    private Context context;
    private  List<DeliveryOffers.DataBean> allOffers;
    PreferenceHelper preferenceHelper;


    public DeliveryOffersAdapter(FragmentActivity activity, List<DeliveryOffers.DataBean> allOffersData) {

        this.context =  activity;
        allOffers = allOffersData;
    }


    @Override
    public DeliveryOffersAdapter.CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.deliveryoffers_adapter_item, parent, false);

            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        DisplayMetrics displayMetrics =  context.getResources().getDisplayMetrics();
        int height = displayMetrics.heightPixels;
        //////set percentage from all screen
        layoutParams.height = (int) (height * 0.35);
        view.setLayoutParams(layoutParams);

        return new DeliveryOffersAdapter.CustomView(view);

    }



    @Override
    public void onBindViewHolder(@NonNull CustomView holder, final int position) {

        holder.capitainname.setText(allOffers.get(position).getOrder().getDelivry().getName());
       // holder.distance.setText(allOffers.get(position).getOrder().getDelivry().getName());
        holder.price.setText(allOffers.get(position).getPrice()+context.getText(R.string.costunit));
//        Glide.with(context)
//                .load(dataBeanArrayList.get(position).getPhoto())
//                .into(holder.itemImage);


    }

    @Override
    public int getItemCount() {
        return allOffers.size();
    }


     class CustomView extends RecyclerView.ViewHolder {

        private final View mView;
        private ImageView itemImage;
        private TextView capitainname,distance,price;

        private CustomView(View view) {
            super(view);
            mView = view;
            itemImage =mView.findViewById(R.id.categimage);
            capitainname=mView.findViewById(R.id.capitainnamevalue);
            distance=mView.findViewById(R.id.home_end_address);
            price=mView.findViewById(R.id.costvalue);
        }
    }



}
