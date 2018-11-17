package com.example.hossam.parashotApp.presentation.screens.home.storesFragment.adapter;

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
import com.example.hossam.parashotApp.entities.StoresList;
import com.example.hossam.parashotApp.presentation.screens.home.makeOrderFromGoogleStoresFeragment.MakeOrderFromGoogleFragment;
import com.example.hossam.parashotApp.presentation.screens.home.productsFragment.ProductsFragment;
import com.example.hossam.parashotApp.presentation.screens.home.storesFragment.StoresViewModel;
import com.example.hossam.parashotApp.databinding.ResturantMenu1Binding;

import java.util.ArrayList;
import java.util.List;

public class AllStoriesAdapter extends RecyclerView.Adapter<AllStoriesAdapter.CustomView> {

    private LayoutInflater layoutInflater;
    private List<StoresList.DataBean> arrayList;
    private Context context;


    public AllStoriesAdapter(Context context, List<StoresList.DataBean> arrayList) {
        this.arrayList = new ArrayList<>(arrayList);
        this.context = context;
    }

    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ResturantMenu1Binding subCategry1Binding = DataBindingUtil.inflate(layoutInflater, R.layout.first_resturant_adapter_item, parent, false);
        return new CustomView(subCategry1Binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, int position) {

        StoresViewModel storesViewModel = new StoresViewModel();

        storesViewModel.setName(arrayList.get(position).getName());
        storesViewModel.setPlace(arrayList.get(position).getPlace());
        if (arrayList.get(position).isFrom_google()) {
            storesViewModel.setCover("https://maps.googleapis.com/maps/api/place/photo?maxwidth=" + arrayList.get(position).getMaxwidth() +
                    "&photoreference=" + arrayList.get(position).getCover() +
                    "&key=" + context.getText(R.string.google_maps_key));
        }
        else
            storesViewModel.setCover(arrayList.get(position).getCover());


        if (arrayList.get(position).getStorerates().size() > 0) {
            storesViewModel.setChat(arrayList.get(position).getStorerates().get(0).getCommentcount() + "");
            if (arrayList.get(position).getLikes() != null)
                storesViewModel.setLike(arrayList.get(position).getLikes().get(0).getCount() + "");
            storesViewModel.setRatecount("(" + arrayList.get(position).getStorerates().get(0).getCount() + ")");
            storesViewModel.setRatestar(arrayList.get(position).getStorerates().get(0).getStars());

        } else {
            storesViewModel.setChat("0");
            storesViewModel.setLike("0");
            storesViewModel.setRatecount("(0)");
            storesViewModel.setRatestar(0);
        }

        storesViewModel.setLogo(arrayList.get(position).getLogo());
        holder.bind(storesViewModel);

        holder.resturantMenu1Binding.storelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!arrayList.get(position).isFrom_google()) {
                    Fragment fragment = new ProductsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("stor_id", arrayList.get(position).getId());
                    fragment.setArguments(bundle);
                    ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().
                            replace(R.id.main_frame, fragment).addToBackStack(null).commit();
                } else {

                    Fragment fragment = new MakeOrderFromGoogleFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("stor_id", 1);
                    bundle.putString("name", arrayList.get(position).getName());
                    bundle.putString("image","https://maps.googleapis.com/maps/api/place/photo?maxwidth=" + arrayList.get(position).getMaxwidth() +
                            "&photoreference=" + arrayList.get(position).getCover() +
                            "&key=" + context.getText(R.string.google_maps_key));

                    bundle.putString("logo",arrayList.get(position).getLogo());
                    bundle.putString("store_name",arrayList.get(position).getName());
                    bundle.putString("store_lat", String.valueOf(arrayList.get(position).getLatitude()));
                    bundle.putString("store_lang", String.valueOf(arrayList.get(position).getLongitude()));
                    bundle.putString("description","title");
                    fragment.setArguments(bundle);
                    ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().
                            replace(R.id.main_frame, fragment).addToBackStack(null).commit();
                }
            }

        });
    }

    public void setItems(ArrayList<StoresList.DataBean> newList){
        arrayList.clear();
        arrayList.addAll(newList);

        notifyDataSetChanged();
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

        public void bind(StoresViewModel subCat_itemViewModel) {
            this.resturantMenu1Binding.setResturantlistmodel(subCat_itemViewModel);
            resturantMenu1Binding.executePendingBindings();
        }

        public ResturantMenu1Binding getCardBinding() {
            return resturantMenu1Binding;
        }

    }

}
