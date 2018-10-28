package com.example.hossam.parashotApp.presentation.screens.home.productsDetailsFragment.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.entities.ProductDetailsModel;

import java.util.List;



public class ImagesAdapterForColor extends RecyclerView.Adapter<ImagesAdapterForColor.ViewHolder>  {

    private Context context;
    private int pos;
    List<ProductDetailsModel.DataBean.ProductcolorsBean> colors;
    public ImagesAdapterForColor(Context context, List<ProductDetailsModel.DataBean.ProductcolorsBean> productcolors) {
        this.context = context;
        colors=productcolors;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_color_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        GradientDrawable drawable = (GradientDrawable)holder.color.getBackground();
        drawable.setColor(Color.parseColor(colors.get(position).getColor_hex()));
    }

    @Override
    public int getItemCount() {
        return colors.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public TextView color;
        public ViewHolder(View view) {

            super(view);
            mView = view;
            color =  itemView.findViewById(R.id.textcolor);

        }
    }

}

