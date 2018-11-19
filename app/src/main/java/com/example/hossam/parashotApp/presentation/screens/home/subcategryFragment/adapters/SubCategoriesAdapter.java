package com.example.hossam.parashotApp.presentation.screens.home.subcategryFragment.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.databinding.SubCategriesBinding;
import com.example.hossam.parashotApp.entities.Categories;
import com.example.hossam.parashotApp.entities.StoreSettingEntity;
import com.example.hossam.parashotApp.presentation.screens.home.storesFragment.StoresFragment;
import com.example.hossam.parashotApp.presentation.screens.home.subcategryFragment.SubCategoryViewModel;

import java.util.List;

public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesAdapter.CustomView> {


    private LayoutInflater layoutInflater;
    private List<Categories.DataBean.SubcatsBean> dataBeanArrayList;
    private Context context;
    private StoreSettingEntity.DataBean.StoresettingsBean.DesignBean designBean;


    public SubCategoriesAdapter(FragmentActivity activity, List<Categories.DataBean.SubcatsBean> subcats) {

        this.dataBeanArrayList = subcats;
        this.context =  activity;
    }

    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        SubCategriesBinding cardBinding = DataBindingUtil.inflate(layoutInflater, R.layout.home1_subcategries_model,parent,false);
        return new CustomView(cardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, final int position) {

        SubCategoryViewModel categoryViewModel = new SubCategoryViewModel();
        categoryViewModel.setName(dataBeanArrayList.get(position).getName());
        categoryViewModel.setImagepath(dataBeanArrayList.get(position).getPhoto());
        categoryViewModel.imageUrlUpdated(dataBeanArrayList.get(position).getPhoto());

        holder.bind(categoryViewModel);

        holder.cardBinding.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new StoresFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("type",1);
                bundle.putInt("categryId",dataBeanArrayList.get(position).getId());
                fragment.setArguments(bundle);
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().
                                    replace(R.id.main_frame,fragment).
                                        addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBeanArrayList.size();
    }

    class CustomView extends RecyclerView.ViewHolder {

        SubCategriesBinding cardBinding;
        public CustomView(SubCategriesBinding cardBinding) {
            super(cardBinding.getRoot());
            this.cardBinding = cardBinding;
        }

        public void bind(SubCategoryViewModel categoryViewModel)
        {
            this.cardBinding.setSubcategriesmodelbinding(categoryViewModel);
            cardBinding.executePendingBindings();
        }

        public SubCategriesBinding  getCardBinding()
        {
            return  cardBinding;
        }

    }

}
