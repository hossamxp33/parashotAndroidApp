package com.example.hossam.parashotApp.presentation.screens.home.storesFragment.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.entities.AllStoriesModel;
import com.example.hossam.parashotApp.presentation.screens.home.productsFragment.ProductsFragment;
import com.example.hossam.parashotApp.presentation.screens.home.storesFragment.StoresViewModel;
import com.example.hossam.parashotApp.databinding.ResturantMenu1Binding;

import java.util.List;

public class AllStoriesAdapter extends RecyclerView.Adapter<AllStoriesAdapter.CustomView> {

    private LayoutInflater layoutInflater;
    private List<AllStoriesModel.DataBean> arrayList;
    private Context context;


    public AllStoriesAdapter(Context context , List<AllStoriesModel.DataBean> arrayList)
    {
        this.arrayList = arrayList;
        this.context =  context;
    }

    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ResturantMenu1Binding subCategry1Binding = DataBindingUtil.inflate(layoutInflater, R.layout.first_resturant_adapter_item,parent,false);
        return new CustomView(subCategry1Binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, int position) {

        StoresViewModel storesViewModel = new StoresViewModel();

        storesViewModel.setName(arrayList.get(position).getName());
        storesViewModel.setPlace(arrayList.get(position).getPlace());

        Log.d("sizz",arrayList.get(position).getStorerates().size()+"");

        if (arrayList.get(position).getStorerates().size()>0) {
            storesViewModel.setChat(arrayList.get(position).getStorerates().get(0).getCommentcount() + "");
            storesViewModel.setLike(arrayList.get(position).getLikes().get(0).getCount() + "");
            storesViewModel.setRatecount( "(" + arrayList.get(position).getStorerates().get(0).getCount() + ")" );
            storesViewModel.setRatestar( arrayList.get(position).getStorerates().get(0).getStars());

        }

        else {
            storesViewModel.setChat("0");
            storesViewModel.setLike("0");
            storesViewModel.setRatecount( "(0)" );
            storesViewModel.setRatestar(0);
        }
       storesViewModel.setLogo(arrayList.get(position).getLogo());
        holder.bind(storesViewModel);

        holder.resturantMenu1Binding.storelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new ProductsFragment()).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class CustomView extends RecyclerView.ViewHolder {

        ResturantMenu1Binding resturantMenu1Binding;

        public CustomView(ResturantMenu1Binding subCategory1Binding) {
            super(subCategory1Binding.getRoot());
            this.resturantMenu1Binding = subCategory1Binding;
        }

        public void bind( StoresViewModel subCat_itemViewModel)
        {
            this.resturantMenu1Binding.setResturantlistmodel(subCat_itemViewModel);
            resturantMenu1Binding.executePendingBindings();
        }

        public ResturantMenu1Binding  getCardBinding()
        {
            return  resturantMenu1Binding;
        }

    }

}
