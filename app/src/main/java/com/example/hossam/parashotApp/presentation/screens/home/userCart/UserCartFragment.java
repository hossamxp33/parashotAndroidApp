package com.example.hossam.parashotApp.presentation.screens.home.userCart;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.entities.Categories;
import com.example.hossam.parashotApp.entities.StoreSettingEntity;
import com.example.hossam.parashotApp.presentation.screens.home.categoryFragment.CategoryViewModel;
import com.example.hossam.parashotApp.presentation.screens.home.categoryFragment.CategoryViewModelFactory;
import com.example.hossam.parashotApp.presentation.screens.home.storesFragment.AllStoresViewModelFactory;
import com.example.hossam.parashotApp.presentation.screens.home.subcategryFragment.adapters.SliderPagerAdapter;
import com.example.hossam.parashotApp.presentation.screens.home.subcategryFragment.adapters.SubCategoriesAdapter;
import com.example.hossam.parashotApp.presentation.screens.home.userCart.Adapter.FirstCartAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.Timer;
import java.util.TimerTask;


public class UserCartFragment extends Fragment {


    @SerializedName("records")
    @Expose
    private RecyclerView recyclerView;
    private UserCartViewModel userCartViewModel;
    FirstCartAdapter firstCartAdapter;
    TextView productCount,totalprice;

    public UserCartFragment() {
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
        View view = inflater.inflate(R.layout.fragment_main_product_cart, container, false);
        recyclerView = view.findViewById(R.id.recylerview);
        productCount = view.findViewById(R.id.proCount);
        totalprice = view.findViewById(R.id.totalprice);
        userCartViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(UserCartViewModel.class);

        userCartViewModel.ProductLiveData.observe(this,products ->
        {      productCount.setText(products.size()+"");
                Log.d("fd",products.size()+"");
            firstCartAdapter = new FirstCartAdapter(getActivity(),products,userCartViewModel);
            recyclerView.setAdapter(firstCartAdapter);

            double total=0;
            for (int i=0;i<products.size();i++)
            {
                total+=Double.parseDouble(products.get(i).getPrice());
            }
            totalprice.setText(total+"ريال ");
        }
        );


        return view;
    }

    public void getTotalprice(String text){
        totalprice.setText(text);
    }


    public void setTextView(String text){
        totalprice.setText(text);
    }


    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new AllStoresViewModelFactory(getActivity().getApplication());
    }

}
