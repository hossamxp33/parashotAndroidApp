package com.example.hossam.parashotApp.presentation.screens.home.productsFragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.entities.Products_in_Stories_Model;
import java.util.List;


public class RecycleImagesAdapter extends RecyclerView.Adapter<RecycleImagesAdapter.ViewHolder>  {

    private Context context;
    private List<Products_in_Stories_Model.DataBean.ProductphotosBean> productphotos;


    RecycleImagesAdapter(Context mcontext, List<Products_in_Stories_Model.DataBean.ProductphotosBean> productphotos_from_view) {
        context = mcontext;
        productphotos =productphotos_from_view;
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

