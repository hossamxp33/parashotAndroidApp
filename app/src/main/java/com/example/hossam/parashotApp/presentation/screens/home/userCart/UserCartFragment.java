package com.example.hossam.parashotApp.presentation.screens.home.userCart;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import android.widget.Toast;

import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.dataLayer.apiData.ApiClientLocal;
import com.example.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.entities.Product;
import com.example.hossam.parashotApp.entities.Categories;
import com.example.hossam.parashotApp.entities.StoreSettingEntity;
import com.example.hossam.parashotApp.helper.ProgressDialogHelper;
import com.example.hossam.parashotApp.presentation.screens.home.categoryFragment.CategoryViewModel;
import com.example.hossam.parashotApp.presentation.screens.home.categoryFragment.CategoryViewModelFactory;
import com.example.hossam.parashotApp.presentation.screens.home.paymentFragment.PaymentFragment;
import com.example.hossam.parashotApp.presentation.screens.home.storesFragment.AllStoresViewModelFactory;
import com.example.hossam.parashotApp.presentation.screens.home.subcategryFragment.SubCategoriesFragment;
import com.example.hossam.parashotApp.presentation.screens.home.subcategryFragment.adapters.SliderPagerAdapter;
import com.example.hossam.parashotApp.presentation.screens.home.subcategryFragment.adapters.SubCategoriesAdapter;
import com.example.hossam.parashotApp.presentation.screens.home.userCart.Adapter.FirstCartAdapter;
import com.example.hossam.parashotApp.presentation.screens.home.userCart.helper.CartPrice;
import com.example.hossam.parashotApp.presentation.screens.home.userCart.helper.ProductInfoToPost;
import com.example.hossam.parashotApp.presentation.screens.home.userCart.helper.ProductModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viewpagerindicator.CirclePageIndicator;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserCartFragment extends Fragment implements CartPrice {


    @SerializedName("records")
    @Expose
    private RecyclerView recyclerView;
    private UserCartViewModel userCartViewModel;
    FirstCartAdapter firstCartAdapter;
    TextView productCount,totalprice,sale;
    double total=0;
    List<Product> productList;
    List<ProductInfoToPost> products=new ArrayList<>();
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
        sale = view.findViewById(R.id.sale);
        userCartViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(UserCartViewModel.class);

        userCartViewModel.ProductLiveData.observe(this,products ->
        {
            productList =products;
            productCount.setText(products.size()+"");
                Log.d("fd",products.size()+"");
            firstCartAdapter = new FirstCartAdapter(getActivity(),products,userCartViewModel,this);
            recyclerView.setAdapter(firstCartAdapter);

            for (int i=0;i<products.size();i++)
            {
                total+=Double.parseDouble(products.get(i).getPrice());

            }
            totalprice.setText(total+"ريال ");
        }
        );

        sale.setOnClickListener(v ->
                {
                    int current_count =Integer.parseInt(productCount.getText().toString());

                    if (current_count>0)
                    {
                        for (int i=0;i<productList.size();i++)
                        {
                            products.add(new ProductInfoToPost(productList.get(i).getProduct_id(),2));

                        }

                        Fragment fragment = new PaymentFragment();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("products", (Serializable) products);
                        fragment.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,fragment).addToBackStack(null).commit();
                    }

                    else

                        Toast.makeText(getActivity(),getText(R.string.noitem),Toast.LENGTH_SHORT).show();
                }
        );

        return view;
    }





    public void setTextView(String text){
        totalprice.setText(text);
    }


    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new AllStoresViewModelFactory(getActivity().getApplication());
    }

    @Override
    public void plusItem(int position) {
        total+=Double.parseDouble(productList.get(position).getPrice());
        totalprice.setText(total+"ريال ");
    }

    @Override
    public void minusItem(int position) {
        total-=Double.parseDouble(productList.get(position).getPrice());
        totalprice.setText(total+"ريال ");
    }

    @Override
    public void deleteItem(int position,int count) {

        total-=(Double.parseDouble(productList.get(position).getPrice()))*count;
        totalprice.setText(total+" ريال ");
        int current_count =Integer.parseInt(productCount.getText().toString())-1;
        productCount.setText(current_count+"");
    }
}
