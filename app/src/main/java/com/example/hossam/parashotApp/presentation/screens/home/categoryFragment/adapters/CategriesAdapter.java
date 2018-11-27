package com.example.hossam.parashotApp.presentation.screens.home.categoryFragment.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.entities.Categories;
import com.example.hossam.parashotApp.helper.PreferenceHelper;
import com.example.hossam.parashotApp.presentation.screens.home.categoryFragment.CategoryFragment;
import com.example.hossam.parashotApp.presentation.screens.home.storesFragment.StoresFragment;
import com.example.hossam.parashotApp.presentation.screens.home.subcategryFragment.SubCategoriesFragment;

import java.util.ArrayList;
import java.util.List;

public class CategriesAdapter extends RecyclerView.Adapter<CategriesAdapter.CustomView> {


    private LayoutInflater layoutInflater;
    private List<Categories.DataBean> dataBeanArrayList;
    private Context context;
    private List<Categories.SliderBean> slides;
    PreferenceHelper preferenceHelper;

    public CategriesAdapter(FragmentActivity activity, List<Categories.DataBean> data,List<Categories.SliderBean> slides1) {
        this.dataBeanArrayList = data;
        this.context =  activity;
        slides = slides1;
        preferenceHelper =new PreferenceHelper(context);
    }


    @Override
    public CategriesAdapter.CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.home1_depart_model, parent, false);

            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        DisplayMetrics displayMetrics =  context.getResources().getDisplayMetrics();
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        //////set percentage from all screen

        layoutParams.width = (width / 3)-20;
        layoutParams.height = layoutParams.width;
        view.setLayoutParams(layoutParams);

        return new CategriesAdapter.CustomView(view);

    }



    @Override
    public void onBindViewHolder(@NonNull CustomView holder, final int position) {

        holder.title.setText(dataBeanArrayList.get(position).getName());
        Glide.with(context)
                .load(dataBeanArrayList.get(position).getPhoto())
                .into(holder.itemImage);

        holder.itemView.setOnClickListener(v -> {

            preferenceHelper.setCURRENTCATEGRY(dataBeanArrayList.get(position).getType());
            if (dataBeanArrayList.get(position).getSubcats().size()>0) {
                Fragment fragment = new SubCategoriesFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("subcategries",  dataBeanArrayList.get(position));
                bundle.putParcelableArrayList("sliders", (ArrayList<? extends Parcelable>) slides);
                fragment.setArguments(bundle);
                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).addToBackStack(null).commit();
            }

            else
            {

                Fragment fragment = new StoresFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("type",0);
                bundle.putInt("categryId",dataBeanArrayList.get(position).getId());
                fragment.setArguments(bundle);
                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).addToBackStack(null).commit();

            }

        });
    }

    @Override
    public int getItemCount() {
        return dataBeanArrayList.size();
    }



     class CustomView extends RecyclerView.ViewHolder {

        private final View mView;
        private ImageView itemImage;
        private TextView title;


        private CustomView(View view) {
            super(view);
            mView = view;
            itemImage =mView.findViewById(R.id.categimage);
            title=mView.findViewById(R.id.cate_name);

        }
    }



}
