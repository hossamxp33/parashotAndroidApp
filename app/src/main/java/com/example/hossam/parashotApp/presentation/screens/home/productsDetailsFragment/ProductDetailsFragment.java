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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.entities.Product;
import com.example.hossam.parashotApp.entities.ImageToShowModel;
import com.example.hossam.parashotApp.entities.ProductDetailsModel;
import com.example.hossam.parashotApp.helper.BroadcastHelper;
import com.example.hossam.parashotApp.presentation.screens.home.HomeActivity;
import com.example.hossam.parashotApp.presentation.screens.home.allProductInsideOrderFragment.ProductsInsideOrderFragment;
import com.example.hossam.parashotApp.presentation.screens.home.productsDetailsFragment.adapters.ImagesAdapterForColor;
import com.example.hossam.parashotApp.presentation.screens.home.productsDetailsFragment.adapters.ImagesAdapterForSideImages;
import com.example.hossam.parashotApp.presentation.screens.home.productsDetailsFragment.adapters.SliderPagerAdapter;
import com.example.hossam.parashotApp.presentation.screens.home.productsDetailsFragment.adapters.TextAdapterForStorage;
import com.example.hossam.parashotApp.presentation.screens.home.ratesOfProduct.RatesOfProductFragment;

import java.util.ArrayList;
import java.util.Objects;


public class ProductDetailsFragment extends Fragment {



    private ProductDetailsViewModel productDetailsViewModel;
    TextView description,name,markaname,storename,productrinfo,ratesnum,price,gotocart, cartProductsCount, productcount;
    ViewPager  viewPager;
    ImageView showmore,gotodet,imageZoom;
    LinearLayoutManager layoutManager;
    ArrayList<ImageToShowModel> images = new ArrayList<>();
    RelativeLayout addtoCart;
    ///////////////definition of all recycles
    private RecyclerView recyclerViewforsideimages,recyclerViewforstorage,recyclerViewforitemcolors;

    ///////////////definition of all adapters
    ImagesAdapterForColor imagesAdapterForColor;
   ImagesAdapterForSideImages imagesAdapterForSideImages;
    SliderPagerAdapter sliderPagerAdapter;
   TextAdapterForStorage textAdapterForStorage;

    String productinfo; //////to goto next activity
    RatingBar ratingBar;
    ProductDetailsModel detailsModel;
    int store_id,productid;
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

        ((HomeActivity)Objects.requireNonNull(getActivity())).title.setText(getText(R.string.product_details));

        store_id = getArguments().getInt("store_id");
        productid = getArguments().getInt("product_id");

        productDetailsViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(ProductDetailsViewModel.class);

        productDetailsViewModel.productDetails_MutableLiveData.observe(this, new Observer<ProductDetailsModel>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(@Nullable ProductDetailsModel products) {
                assert products != null;
                setDatatoView(products);

                detailsModel = products;
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

        productDetailsViewModel.product_count_MutableLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                cartProductsCount.setText(integer+"");
                productcount.setText(getText(R.string.youhave)+" "+integer+" "+getText(R.string.productincart));

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


        ratingBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                    Fragment fragment = new RatesOfProductFragment();
                    Bundle bundle =new Bundle();
                    bundle.putInt("product_id",detailsModel.getData().get(0).getId());
                    fragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,fragment).addToBackStack(null).commit();
                return false;
            }
        });
        addtoCart.setOnClickListener(v -> {

            Product product = new Product();
            product.setName(detailsModel.getData().get(0).getName());
            product.setProduct_id(detailsModel.getData().get(0).getId());
            product.setPhoto(detailsModel.getData().get(0).getProductphotos().get(0).getPhoto());
            product.setStor_id(detailsModel.getData().get(0).getSmallstore().getId());
            product.setPrice(detailsModel.getData().get(0).getPrice());
            product.setRateCount(detailsModel.getData().get(0).getTotal_rating().get(0).getCount());
            product.setRateStars(detailsModel.getData().get(0).getTotal_rating().get(0).getStars());
            productDetailsViewModel.storeData(product);

            productDetailsViewModel.stor_or_not_MutableLiveData.observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(@Nullable Boolean aBoolean) {

                    if (aBoolean) {
                        Toast.makeText(getActivity(), getResources().getString(R.string.addsuccess), Toast.LENGTH_SHORT).show();
                        int currentcount =Integer.parseInt(cartProductsCount.getText().toString())+1;
                        cartProductsCount.setText(currentcount+"");
                        productcount.setText(getText(R.string.youhave)+" "+currentcount+" "+getText(R.string.productincart));
                    }

                    else
                        Toast.makeText(getActivity(),getResources().getString(R.string.aleadyfound),Toast.LENGTH_SHORT).show();

                }
            });
        });


        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setDatatoView(ProductDetailsModel products) {
        description.setText(products.getData().get(0).getDescription());
        name.setText(products.getData().get(0).getName());
        markaname.setText(products.getData().get(0).getBrand());
        storename.setText(products.getData().get(0).getSmallstore().getName());
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

        gotocart.setOnClickListener(v ->
                {
                    Fragment fragment = new ProductsInsideOrderFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("stor_id",store_id);
                    fragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,fragment)
                            .addToBackStack(null).commit();
                }
        );
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
        addtoCart = view.findViewById(R.id.addtoCart);
        gotocart = view.findViewById(R.id.sale);
        cartProductsCount = view.findViewById(R.id.cart_count);
        productcount = view.findViewById(R.id.productcounttext);

        layoutManager =new LinearLayoutManager(getActivity());
        recyclerViewforsideimages.setLayoutManager(layoutManager);
        recyclerViewforsideimages.setNestedScrollingEnabled(true);
        recyclerViewforsideimages.setHasFixedSize(false);

    }




    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new ProductDetailsModelFactory(getActivity().getApplication(),productid,store_id);
    }

}
