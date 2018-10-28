package com.example.hossam.parashotApp.presentation.screens.home.productsFragment.adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.databinding.ProductBindings;
import com.example.hossam.parashotApp.entities.Products_in_Stories_Model;
import com.example.hossam.parashotApp.presentation.screens.home.productsDetailsFragment.ProductDetailsFragment;
import com.example.hossam.parashotApp.presentation.screens.home.productsFragment.ProductsViewModel;
import com.example.hossam.parashotApp.presentation.screens.home.storesFragment.StoresFragment;

import java.util.List;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    LayoutInflater  layoutInflater;
    Context mcontext;
    RecycleImagesAdapter recycle_images_adapter;
    List<Products_in_Stories_Model.DataBean> productData;
    public ProductsAdapter(FragmentActivity activity, List<Products_in_Stories_Model.DataBean> products_from_view) {
        mcontext=activity;
        productData = products_from_view;
    }

    @Override
    public ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {

        if(layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        ProductBindings productBindings = DataBindingUtil.inflate(layoutInflater, R.layout.third_subcategry_adapter_item,parent,false);
        return new ProductsAdapter.ViewHolder(productBindings);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        recycle_images_adapter = new RecycleImagesAdapter(mcontext,productData.get(position).getProductphotos());
        holder.productBindings.viewpager.setAdapter(recycle_images_adapter);

        ProductsViewModel products_viewModel = new ProductsViewModel();
        products_viewModel.setName(productData.get(position).getName());
        products_viewModel.setPrice(productData.get(position).getPrice()+" ريال ");

        if (productData.get(position).getTotal_rating().size()>0) {
            products_viewModel.setRatestars(productData.get(position).getTotal_rating().get(0).getStars());
            products_viewModel.setRatecount("(" + productData.get(position).getTotal_rating().get(0).getCount() + ")");
        }

        else{
            products_viewModel.setRatestars(0);
            products_viewModel.setRatecount("(0)");
        }


        holder.bind(products_viewModel);
        holder.productBindings.productview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

((FragmentActivity) mcontext).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new ProductDetailsFragment()).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
      return  productData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ProductBindings productBindings;

        public ViewHolder(ProductBindings bindings) {
            super(bindings.getRoot());
            this.productBindings = bindings;
        }


        public void bind(ProductsViewModel products_viewModel)
        {
            this.productBindings.setProductdata(products_viewModel);
            productBindings.executePendingBindings();
        }

        public ProductBindings  getCardBinding()
        {
            return  productBindings;
        }

//        }
    }
}
