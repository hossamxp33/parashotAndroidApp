package com.example.hossam.parashotApp.presentation.screens.home.categoryFragment.adapters;

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
import com.example.hossam.parashotApp.databinding.CategriesBinding;
import com.example.hossam.parashotApp.entities.Categories;
import com.example.hossam.parashotApp.entities.StoreSettingEntity;
import com.example.hossam.parashotApp.presentation.screens.home.categoryFragment.CategoryViewModel;
import com.example.hossam.parashotApp.presentation.screens.home.storesFragment.StoresFragment;
import com.example.hossam.parashotApp.presentation.screens.home.subcategryFragment.SubCategoriesFragment;

import java.util.List;

public class CategriesAdapter extends RecyclerView.Adapter<CategriesAdapter.CustomView> {


    private LayoutInflater layoutInflater;
    private List<Categories.DataBean> dataBeanArrayList;
    private Context context;
    private StoreSettingEntity.DataBean.StoresettingsBean.DesignBean designBean;


    public CategriesAdapter(FragmentActivity activity, List<Categories.DataBean> data,
                            StoreSettingEntity.DataBean.StoresettingsBean.DesignBean design) {
        this.dataBeanArrayList = data;
        this.context =  activity;
        this.designBean = design;
    }

    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        CategriesBinding cardBinding = DataBindingUtil.inflate(layoutInflater, R.layout.home1_depart_model,parent,false);
        return new CustomView(cardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, final int position) {

        CategoryViewModel category_viewModel = new CategoryViewModel();
        category_viewModel.setName(dataBeanArrayList.get(position).getName());
        category_viewModel.setImagePath(dataBeanArrayList.get(position).getPhoto());
        category_viewModel.imageUrlUpdated(dataBeanArrayList.get(position).getPhoto());
        category_viewModel.setBackColor(designBean.getBody().getCategorybackground());;
        category_viewModel.setTextColor(designBean.getBody().getFont_color());;

        holder.bind(category_viewModel);

        holder.cardBinding.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dataBeanArrayList.get(position).getSubcats().size()>0) {
                    Fragment fragment = new SubCategoriesFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("subcategries", dataBeanArrayList.get(position));
                    bundle.putSerializable("design", designBean);
                    fragment.setArguments(bundle);
                    ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).addToBackStack(null).commit();
                }

                else
                    ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new StoresFragment()).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBeanArrayList.size();
    }

    class CustomView extends RecyclerView.ViewHolder {

        CategriesBinding cardBinding;

        public CustomView(CategriesBinding cardBinding) {
            super(cardBinding.getRoot());
            this.cardBinding = cardBinding;
        }


        public void bind(CategoryViewModel category_viewModel)
        {
            this.cardBinding.setCategriesmodelbinding(category_viewModel);
            cardBinding.executePendingBindings();
        }

        public CategriesBinding  getCardBinding()
        {
            return  cardBinding;
        }

    }


}