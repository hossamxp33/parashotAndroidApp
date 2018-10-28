package com.example.hossam.parashotApp.presentation.screens.home.productsDetailsFragment.adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.entities.ProductDetailsModel;

import java.util.List;


/**
 * Created by hossam on 21/07/2018.
 */

public class TextAdapterForStorage extends RecyclerView.Adapter<TextAdapterForStorage.ViewHolder>  {

    private Context ctx;
    List<ProductDetailsModel.DataBean.ProductsizesBean> sizes;
    public TextAdapterForStorage(Context ctx, List<ProductDetailsModel.DataBean.ProductsizesBean> productsizes) {
        this.ctx = ctx;
        this.sizes = productsizes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_storage_item, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.ivtext.setText(sizes.get(position).getSize()+" جيجا بايت ");
        holder.ivtext.setOnClickListener(v -> {
            holder.cardView.setCardElevation(10);
        });

    }

    @Override
    public int getItemCount() {
        return sizes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;


        public TextView ivtext;
        CardView cardView;
        public ViewHolder(View view) {

            super(view);
            mView = view;
            ivtext =  itemView.findViewById(R.id.textdesc);
            cardView =  itemView.findViewById(R.id.card_view);

        }
    }

}

