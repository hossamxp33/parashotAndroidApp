package com.example.hossam.parashotApp.presentation.screens.home.productsDetailsFragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.entities.ImageToShowModel;
import com.example.hossam.parashotApp.entities.ProductDetailsModel;
import com.example.hossam.parashotApp.helper.BroadcastHelper;
import com.example.hossam.parashotApp.presentation.screens.home.productsDetailsFragment.adapters.ImagesAdapterForColor;
import com.example.hossam.parashotApp.presentation.screens.home.productsDetailsFragment.adapters.ImagesAdapterForSideImages;
import com.example.hossam.parashotApp.presentation.screens.home.productsDetailsFragment.adapters.SliderPagerAdapter;
import com.example.hossam.parashotApp.presentation.screens.home.productsDetailsFragment.adapters.TextAdapterForStorage;
import com.example.hossam.parashotApp.presentation.screens.home.storesFragment.AllStoresViewModelFactory;

import java.util.ArrayList;
import java.util.Objects;


public class ProductDetailsFragment extends Fragment {



    private ProductDetailsViewModel productDetailsViewModel;
    TextView description,name,markaname,storename,productrinfo,ratesnum,price;
    ViewPager  viewPager;
    ImageView showmore,gotodet,imageZoom;
    LinearLayoutManager layoutManager;
    ArrayList<ImageToShowModel> images = new ArrayList<>();


    ///////////////definition of all recycles
    private RecyclerView recyclerViewforsideimages,recyclerViewforstorage,recyclerViewforitemcolors;

    ///////////////definition of all adapters
    ImagesAdapterForColor imagesAdapterForColor;
   ImagesAdapterForSideImages imagesAdapterForSideImages;
    SliderPagerAdapter sliderPagerAdapter;
   TextAdapterForStorage textAdapterForStorage;

   String productinfo; //////to goto next activity

    RatingBar ratingBar;
    public ProductDetailsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        findFromXml(view);
        productDetailsViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(ProductDetailsViewModel.class);

        layoutManager =new LinearLayoutManager(getActivity());
        recyclerViewforsideimages.setLayoutManager(layoutManager);
        recyclerViewforsideimages.setNestedScrollingEnabled(true);
        recyclerViewforsideimages.setHasFixedSize(false);

        productDetailsViewModel.productDetails_MutableLiveData.observe(this, new Observer<ProductDetailsModel>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(@Nullable ProductDetailsModel products) {
                assert products != null;
                setDatatoView(products);
                if (products.getData().get(0).getProductphotos()!=null)
                sliderPagerAdapter = new SliderPagerAdapter(getActivity(),products.getData().get(0).getProductphotos());
                imagesAdapterForSideImages = new ImagesAdapterForSideImages(getActivity(),products.getData().get(0).getProductphotos());
                imagesAdapterForColor = new ImagesAdapterForColor(getActivity(),products.getData().get(0).getProductcolors());
                textAdapterForStorage = new TextAdapterForStorage(getActivity(),products.getData().get(0).getProductsizes());
                viewPager.setAdapter(sliderPagerAdapter);
                recyclerViewforsideimages.setAdapter(imagesAdapterForSideImages);
                recyclerViewforitemcolors.setAdapter(imagesAdapterForColor);
                recyclerViewforstorage.setAdapter(textAdapterForStorage);
            }
        });

        productDetailsViewModel.errorLiveData.observe(this, throwable -> {
            Snackbar.make(getView(),throwable.getMessage(),Snackbar.LENGTH_LONG).show();
        });

        showmore.setOnClickListener(v ->  {
                int totalItemCount = recyclerViewforsideimages.getAdapter().getItemCount();
                if (totalItemCount <= 0) return;
                int lastVisibleItemIndex = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemIndex >= totalItemCount) return;
                layoutManager.smoothScrollToPosition(recyclerViewforsideimages,null,lastVisibleItemIndex+1);

        });

        gotodet.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),ProductInfoActivity.class);
            intent.putExtra("product_info",productinfo);
            startActivity(intent);
        });

        imageZoom.setOnClickListener(v -> {

            Intent intent = new Intent(getActivity(), DetailImageActivity.class);
            intent.putParcelableArrayListExtra("data", images);
            intent.putExtra("pos",viewPager.getCurrentItem());
            Objects.requireNonNull(getActivity()).startActivity(intent);
        });


        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setDatatoView(ProductDetailsModel products) {
        description.setText(products.getData().get(0).getDescription());
        name.setText(products.getData().get(0).getName());
        markaname.setText(products.getData().get(0).getBrand());
        storename.setText(products.getData().get(0).getStore().getName());
        ratesnum.setText("("+products.getData().get(0).getTotal_rating().get(0).getCount()+")");
        ratingBar.setRating((float) products.getData().get(0).getTotal_rating().get(0).getStars());
        productinfo = products.getData().get(0).getProduct_info();
        price.setText(products.getData().get(0).getPrice()+"ريال ");

        for (int i = 0; i < products.getData().get(0).getProductphotos().size(); i++) {
            ImageToShowModel imageModel = new ImageToShowModel();
            imageModel.setName("Image " + i);
            imageModel.setUrl(products.getData().get(0).getProductphotos().get(i).getPhoto());
            images.add(imageModel);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            productrinfo.setText(Html.fromHtml(products.getData().get(0).getProduct_info(), Html.FROM_HTML_MODE_COMPACT));
        }

        else
            productrinfo.setText(Html.fromHtml(products.getData().get(0).getProduct_info()));

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
//        productrinfo.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>", Html.FROM_HTML_MODE_COMPACT));
//
//    else
//        productrinfo.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>"));


    }


    Receiver receiver;
    boolean isReciverRegistered = false;
    @Override
    public void onResume() {
        super.onResume();
        if (receiver == null) {
            receiver = new Receiver();
            IntentFilter filter = new IntentFilter(BroadcastHelper.ACTION_NAME);
            getActivity().registerReceiver(receiver, filter);
            isReciverRegistered = true;
        }
    }

    @Override
    public void onDestroy() {
        if (isReciverRegistered) {
            if (receiver != null)
                getActivity().unregisterReceiver(receiver);
        }
        super.onDestroy();
    }


    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            Log.v("r", "receive " + arg1.getStringExtra(BroadcastHelper.BROADCAST_EXTRA_METHOD_NAME));
            String methodName = arg1.getStringExtra(BroadcastHelper.BROADCAST_EXTRA_METHOD_NAME);
            if (methodName != null && methodName.length() > 0) {
                Log.v("receive", methodName);
                switch (methodName) {
                    case "image_position":
                        int   position = arg1.getIntExtra("position",0);
                        viewPager.setCurrentItem(position);
                        break;

                    default:
                        break;
                }
            }
        }
    }

    private void findFromXml(View view) {

        description=view.findViewById(R.id.description);
        name=view.findViewById(R.id.name);
        markaname=view.findViewById(R.id.markaname);
        storename=view.findViewById(R.id.storename);
        productrinfo=view.findViewById(R.id.item_detailsvalues);
        ratesnum=view.findViewById(R.id.ratesnum);
        ratingBar=view.findViewById(R.id.rates);
        recyclerViewforsideimages = view.findViewById(R.id.recylerviewimages);
        recyclerViewforitemcolors = view.findViewById(R.id.recylerviewcolors);
        recyclerViewforstorage = view.findViewById(R.id.recylerviewstorage);
        viewPager = view.findViewById(R.id.viewpager);
        showmore = view.findViewById(R.id.showmore);
        gotodet = view.findViewById(R.id.gotodet);
        price = view.findViewById(R.id.price);
        imageZoom = view.findViewById(R.id.imageZoom);

    }




    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new AllStoresViewModelFactory(getActivity().getApplication());
    }

}
