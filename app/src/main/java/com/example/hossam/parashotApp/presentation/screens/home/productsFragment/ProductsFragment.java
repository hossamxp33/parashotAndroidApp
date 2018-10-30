package com.example.hossam.parashotApp.presentation.screens.home.productsFragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.entities.Products_in_Stories_Model;
import com.example.hossam.parashotApp.presentation.screens.home.productsFragment.adapter.ProductsAdapter;
import com.example.hossam.parashotApp.presentation.screens.home.storesFragment.AllStoresViewModelFactory;
import com.example.hossam.parashotApp.presentation.screens.home.userCart.UserCartFragment;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ProductsFragment extends Fragment {



    private RecyclerView recyclerView;
    private ProductsAdapter productsAdapter;  ////productsAdapter adapter
    private ProductsViewModel products_viewModel;
    TextView gotocart;
    public ProductsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_main_subcategry3, container, false);
        recyclerView = view.findViewById(R.id.recylerview);
        gotocart = view.findViewById(R.id.gotocart);

        products_viewModel = ViewModelProviders.of(this, getViewModelFactory()).get(ProductsViewModel.class);

        products_viewModel.products_MutableLiveData.observe(getActivity(), new Observer<Products_in_Stories_Model>() {
            @Override
            public void onChanged(@Nullable Products_in_Stories_Model products) {
                ///////// send viewmodel here to can save in DB from adapter
                productsAdapter = new ProductsAdapter(getActivity(),products.getData(),products_viewModel);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(productsAdapter);
            }
        });

      gotocart.setOnClickListener(v ->
           getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new UserCartFragment()).addToBackStack(null).commit()
        );

        return view;
    }



    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new AllStoresViewModelFactory(getActivity().getApplication());
    }

}
