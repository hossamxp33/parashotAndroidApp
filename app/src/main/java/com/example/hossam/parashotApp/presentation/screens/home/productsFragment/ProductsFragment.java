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
import com.example.hossam.parashotApp.presentation.screens.home.HomeActivity;
import com.example.hossam.parashotApp.presentation.screens.home.productsFragment.adapter.ProductsAdapter;
import com.example.hossam.parashotApp.presentation.screens.home.productsFragment.helper.AddToCart;
import com.example.hossam.parashotApp.presentation.screens.home.storesFragment.AllStoresViewModelFactory;
import com.example.hossam.parashotApp.presentation.screens.home.userCartFragment.UserCartFragment;

import java.util.Objects;


public class ProductsFragment extends Fragment implements AddToCart {

    private RecyclerView recyclerView;
    private ProductsAdapter productsAdapter; ////productsAdapter adapter
    private ProductsViewModel products_viewModel;
    TextView gotocart,cart_count,productcounttext;
    int stor_id;
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
        gotocart = view.findViewById(R.id.sale);
        cart_count = view.findViewById(R.id.cart_count);
        productcounttext = view.findViewById(R.id.productcounttext);
        ((HomeActivity)Objects.requireNonNull(getActivity())).title.setText(getText(R.string.products));

        stor_id = getArguments().getInt("stor_id");
        products_viewModel = ViewModelProviders.of(this, getViewModelFactory()).get(ProductsViewModel.class);

        products_viewModel.products_MutableLiveData.observe(this, products -> {
            ///////// send viewmodel here to can save in DB from adapter
            productsAdapter = new ProductsAdapter(getActivity(),products.getData(),products_viewModel,ProductsFragment.this);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(productsAdapter);
        });

        products_viewModel.product_count_MutableLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                cart_count.setText(integer+"");
                productcounttext.setText(getText(R.string.youhave)+" "+integer+" "+getText(R.string.productincart));
            }
        });

        gotocart.setOnClickListener(v ->
                {
                    Fragment fragment = new UserCartFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("stor_id",stor_id);
                    fragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,fragment)
                            .addToBackStack(null).commit();
                }
        );

        return view;
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new AllStoresViewModelFactory(getActivity().getApplication(),stor_id);
    }

    @Override
    public void userAdd() {
        int currentcount =Integer.parseInt(cart_count.getText().toString())+1;
        cart_count.setText(currentcount+"");
        productcounttext.setText(getText(R.string.youhave)+" "+currentcount+" "+getText(R.string.productincart));

    }
}
