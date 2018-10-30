package com.example.hossam.parashotApp.presentation.screens.home.userCart.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.entities.Product;
import com.example.hossam.parashotApp.databinding.UserCartBinding;
import com.example.hossam.parashotApp.presentation.screens.home.userCart.UserCartViewModel;

import java.util.List;

public class FirstCartAdapter extends RecyclerView.Adapter<FirstCartAdapter.CustomView> {

    private LayoutInflater layoutInflater;
    private List<Product> productList;
    private Context context;
    UserCartViewModel userCartViewModel1;

    public FirstCartAdapter(Context context ,List<Product> productList,UserCartViewModel viewModel)
    {
        this.productList = productList;
        this.context =  context;
        this.userCartViewModel1= viewModel;
    }


    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        UserCartBinding productCartBinding = DataBindingUtil.inflate
                (layoutInflater,R.layout.first_product_cart_adapter_item,parent,false);

        return new CustomView(productCartBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, int position) {

        UserCartViewModel userCartViewModel = new UserCartViewModel();
        userCartViewModel.setName(productList.get(position).getName());
        userCartViewModel.setImagepath(productList.get(position).getPhoto());
        userCartViewModel.setPrice(productList.get(position).getPrice()+"ريال ");
        userCartViewModel.setRetecount("("+productList.get(position).getRateCount()+")");
        userCartViewModel.setRatestart(productList.get(position).getRateStars());
        holder.bind(userCartViewModel);

        holder.userCartBinding.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userCartViewModel.deleteProductFromDB(productList.get(position),userCartViewModel1.userCartRepository);
                productList.remove(position);
                notifyDataSetChanged();
          //      context.getFragmentManager().findFragmentByTag(FRAGMENT_TAG)
            }
        });

        holder.userCartBinding.quintityPlus.setOnClickListener((View.OnClickListener) v ->

                    holder.userCartBinding.quintityValue.setText((Integer.parseInt(holder.userCartBinding.quintityValue.getText().toString())+1)+"")
        );

        holder.userCartBinding.quintityMinus.setOnClickListener((View.OnClickListener) v ->
                {
                    if (Integer.parseInt(holder.userCartBinding.quintityValue.getText().toString()) > 1)
                    holder.userCartBinding.quintityValue.setText((Integer.parseInt(holder.userCartBinding.quintityValue.getText().toString())-1)+"");
                    else
                        holder.userCartBinding.quintityValue.setError("لا يمكن ان تكون الكمية اقل من واحد");

                });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class CustomView extends RecyclerView.ViewHolder {

        UserCartBinding userCartBinding;

        public CustomView(UserCartBinding cartBinding) {
            super(cartBinding.getRoot());
            this.userCartBinding = cartBinding;
        }

        public void bind(UserCartViewModel userCartViewModel)
        {
           this.userCartBinding.setCartmodel(userCartViewModel);
            userCartBinding.executePendingBindings();
        }

        public UserCartBinding  getCardBinding()
        {
            return  userCartBinding;
        }

    }

}
