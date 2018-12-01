package com.example.hossam.parashotApp.presentation.screens.home.favoriteFragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.helper.PreferenceHelper;
import com.example.hossam.parashotApp.presentation.screens.home.HomeActivity;
import com.example.hossam.parashotApp.presentation.screens.home.favoriteFragment.adapter.FavProductsAdapter;
import com.example.hossam.parashotApp.presentation.screens.home.productsFragment.ProductsViewModel;
import com.example.hossam.parashotApp.presentation.screens.home.productsFragment.adapter.ProductsAdapter;
import com.example.hossam.parashotApp.presentation.screens.home.productsFragment.helper.AddToCart;

import java.util.Objects;


public class FavoriteFragment extends Fragment  {

    private RecyclerView recyclerView;
    private FavProductsAdapter favProductsAdapter; ////productsAdapter adapter
    private FavoritsViewModel products_viewModel;
    TextView gotocart,cart_count,productcounttext;
    int userid;
    PreferenceHelper preferenceHelper;
    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fav_product, container, false);
        recyclerView = view.findViewById(R.id.recylerview);

         preferenceHelper =  new PreferenceHelper(getActivity());

        ((HomeActivity)Objects.requireNonNull(getActivity())).title.setText(getText(R.string.favproducts));

        userid = preferenceHelper.getUserId();

        userid = 113;

        products_viewModel = ViewModelProviders.of(this, getViewModelFactory()).get(FavoritsViewModel.class);


        products_viewModel.products_MutableLiveData.observe(this, products -> {
            ///////// send viewmodel here to can save in DB from adapter
            favProductsAdapter = new FavProductsAdapter(getActivity(),products.getData());
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(favProductsAdapter);
        });

        return view;
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new FavProductsViewModelFactory(getActivity().getApplication(), userid);
    }

}
