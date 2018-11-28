package com.example.hossam.parashotApp.presentation.screens.home.allProductInsideOrderFragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.entities.MYOrdersModel;
import com.example.hossam.parashotApp.entities.Products_in_Stories_Model;

import java.util.List;


public class RecycleImagesInsideOrderAdapter extends RecyclerView.Adapter<RecycleImagesInsideOrderAdapter.ViewHolder>  {

    private Context context;
    private List<MYOrdersModel.DataBean.OrderdetailsBean.ProductBean.ProductphotosBean> productphotos;



    public RecycleImagesInsideOrderAdapter(Context mcontext,
                                           List<MYOrdersModel.DataBean.OrderdetailsBean.ProductBean.ProductphotosBean> productphotos1) {
        context = mcontext;
        productphotos =productphotos1;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.third_subcategry_for_viewpager, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

            Glide.with(context).load(productphotos.get(position).getPhoto()).into(holder.Image);
    }

    @Override
    public int getItemCount() {
        return productphotos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private ImageView Image;

        public ViewHolder(View view) {
            super(view);
            mView = view;
           Image=mView.findViewById(R.id.item_image);
        }
    }
}

