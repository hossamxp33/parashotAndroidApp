package com.example.hossam.parashotApp.presentation.screens.home.productsFragment.adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.entities.Product;
import com.example.hossam.parashotApp.databinding.ProductBindings;
import com.example.hossam.parashotApp.entities.Products_in_Stories_Model;
import com.example.hossam.parashotApp.presentation.screens.home.productsDetailsFragment.ProductDetailsFragment;
import com.example.hossam.parashotApp.presentation.screens.home.productsFragment.ProductsViewModel;
import java.util.List;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    LayoutInflater  layoutInflater;
    Context mcontext;
    RecycleImagesAdapter recycle_images_adapter;
    List<Products_in_Stories_Model.DataBean> productData;
    ProductsViewModel productsViewModel1;

    public ProductsAdapter(FragmentActivity activity, List<Products_in_Stories_Model.DataBean> products_from_view,ProductsViewModel viewModel) {
        mcontext=activity;
        productData = products_from_view;
        productsViewModel1 = viewModel;
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
        holder.productBindings.addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mcontext,((FragmentActivity) mcontext).getResources().getString(R.string.addsuccess),Toast.LENGTH_SHORT).show();
                Product product = new Product();
                product.setName(productData.get(position).getName());
                product.setProduct_id(productData.get(position).getId());
                product.setPhoto(productData.get(position).getProductphotos().get(0).getPhoto());
                product.setStor_id(Integer.parseInt(productData.get(position).getStore_id()));
                product.setPrice(productData.get(position).getPrice());
                product.setRateCount(productData.get(position).getTotal_rating().get(0).getCount());
                product.setRateStars(productData.get(position).getTotal_rating().get(0).getStars());
                ProductsViewModel  productsViewModel = new ProductsViewModel();
                productsViewModel.storeData(product,productsViewModel1.allProducts_repository);
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
