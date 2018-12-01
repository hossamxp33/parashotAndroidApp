package com.example.hossam.parashotApp.presentation.screens.home.favoriteFragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.entities.FavProduct;
import com.example.hossam.parashotApp.entities.Products_in_Stories_Model;

import java.util.List;


public class RecycleImagesAdapter extends RecyclerView.Adapter<RecycleImagesAdapter.ViewHolder>  {

    private Context context;
    private List<FavProduct.DataBean.ProductBean.ProductphotosBean> productphotos;



    public RecycleImagesAdapter(Context mcontext, List<FavProduct.DataBean.ProductBean.ProductphotosBean> productphotos1) {
        context = mcontext;
        productphotos =productphotos1;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.third_subcategry_for_viewpager, parent, false);

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        DisplayMetrics displayMetrics =  context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;

        //////set percentage from all screen

        layoutParams.width = (width / 3)-10;
        view.setLayoutParams(layoutParams);


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

