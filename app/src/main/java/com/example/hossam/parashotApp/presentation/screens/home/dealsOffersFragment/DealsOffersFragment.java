package com.example.hossam.parashotApp.presentation.screens.home.dealsOffersFragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.entities.DealsModel;
import com.example.hossam.parashotApp.entities.OffersModel;
import com.example.hossam.parashotApp.entities.User;
import com.example.hossam.parashotApp.presentation.screens.home.HomeActivity;
import com.example.hossam.parashotApp.presentation.screens.home.dealsOffersFragment.adapters.DealsAdapter;
import com.example.hossam.parashotApp.presentation.screens.home.dealsOffersFragment.adapters.OffersAdapter;
import com.example.hossam.parashotApp.presentation.screens.home.dealsOffersFragment.adapters.RecyclerItemClickListener;
import com.example.hossam.parashotApp.presentation.screens.home.productsDetailsFragment.ProductDetailsFragment;
import com.example.hossam.parashotApp.presentation.screens.home.storesFragment.AllStoresViewModelFactory;
import com.example.hossam.parashotApp.presentation.screens.home.userCartFragment.UserCartFragment;
import com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.entities.Product;

import java.util.Objects;

import co.ceryle.segmentedbutton.SegmentedButtonGroup;

public class DealsOffersFragment extends Fragment {

    DealsOffersViewModel dealsOffersViewModel;
    private RecyclerView recyclerView;
    OffersAdapter offersAdapter;
    DealsAdapter dealsAdapter;
    OffersModel currentOffers ;
    DealsModel currentDeals;
    FrameLayout progress ;

    int lastchecked = 0 ;

    public DealsOffersFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_offers_deals, container, false);
       // Log.d("prevFrag", getCallerFragment());
        ((HomeActivity)Objects.requireNonNull(getActivity())).title.setText(getText(R.string.fragment_offers));
        dealsOffersViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(DealsOffersViewModel.class);

        progress =view.findViewById(R.id.progress);
        recyclerView = view.findViewById(R.id.recylerview);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
    @Override
    public void onItemClick(View view, int position) {

        if (recyclerView.getAdapter() instanceof  OffersAdapter){

          int product_Id  = currentOffers.getData().get(position).getProduct().getId();
          int store_Id  = currentOffers.getData().get(position).getProduct().getSmallstore_id();
            int count  = currentOffers.getData().get(position).getProduct().getAmount();
            Log.d("currentoffer", String.valueOf(currentOffers.getData().get(position).getProduct().getAmount()));
            Fragment productFragment=new ProductDetailsFragment();
            Bundle args = new Bundle();
            args.putInt("store_id", store_Id);
            args.putInt("product_id", product_Id);
            args.putInt("count", count);
            productFragment.setArguments(args);
            ((FragmentActivity) getActivity()).getSupportFragmentManager().beginTransaction().
                    replace(R.id.main_frame, productFragment).addToBackStack(null).commit();

        }else if (recyclerView.getAdapter() instanceof  DealsAdapter){
            DealsModel.Data currentDeal  = currentDeals.getData().get(position);
            int product_Id  = currentDeal.getProduct().getId();
            int store_Id  = currentDeal.getProduct().getSmallstore_id();
            dealsOffersViewModel.getStore_id().setValue(store_Id);
            int count  = currentDeal.getPieces();
            int totalPrice  = Integer.parseInt(currentDeal.getProduct().getLast_price()) * count;

            Product product = new Product();
            product.setProduct_id(product_Id);
            product.setName(currentDeal.getProduct().getName());
            product.setStor_id(store_Id);
            product.setPhoto(currentDeal.getProduct().getProductphotos().get(0).getPhoto());
            product.setPrice(currentDeal.getProduct().getLast_price());
            product.setRateCount(currentDeal.getProduct().getTotal_rating().get(0).getCount());
            product.setRateStars(currentDeal.getProduct().getTotal_rating().get(0).getStars());
            product.setCount(count);

    /*        Log.d( "sizeForStore:bef " , String.valueOf(dealsOffersViewModel.getAllforStore(dealsOffersViewModel.getStore_id().getValue())));
            dealsOffersViewModel.setupConsumer();
            dealsOffersViewModel.storeData(product);*/

          /*  Log.d( "onItemClick: " ,product.toString());
            Fragment userCartFragment=new UserCartFragment();
            Bundle args = new Bundle();
            args.putInt("stor_id", store_Id);
            userCartFragment.setArguments(args);*/
            Fragment userCartFragment=new UserCartFragment();
            Bundle args = new Bundle();
            args.putInt("store_id", store_Id);
            args.putInt("product_id", product_Id);
            args.putInt("count", count);
            args.putInt("total_price", totalPrice);
            args.putSerializable("product" , product);
            userCartFragment.setArguments(args);
            ((FragmentActivity) getActivity()).getSupportFragmentManager().beginTransaction().
                    replace(R.id.main_frame, userCartFragment).addToBackStack(null).commit();

        }

    }

    @Override
    public void onLongItemClick(View view, int position) {

    }
}));


       dealsOffersViewModel.dealsLD.observe(this, deals ->
                {
                    assert deals != null;
                    currentDeals=deals;
                    dealsAdapter = new DealsAdapter(getActivity(), deals);
                   // recyclerView.setAdapter(dealsAdapter);
                }
        );
        dealsOffersViewModel.offersLD.observe(this, offers ->
                {
                    assert offers != null;
                    currentOffers =offers;

                    offersAdapter = new OffersAdapter(getActivity(), offers);
                if (recyclerView.getAdapter()==null) recyclerView.setAdapter(offersAdapter);

                }
        );

        dealsOffersViewModel.getLoading().observe(this,new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) progress.setVisibility(View.VISIBLE);
                else progress.setVisibility(View.GONE);
            }
        });

/*

        dealsOffersViewModel.is_stored.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {

                if (aBoolean) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.addsuccess), Toast.LENGTH_SHORT).show();
                    Fragment userCartFragment=new UserCartFragment();
                    Bundle args = new Bundle();
                    Log.d( "sizeForStore: " , String.valueOf(dealsOffersViewModel.getAllforStore(dealsOffersViewModel.getStore_id().getValue())));
                    args.putInt("store_id", dealsOffersViewModel.getStore_id().getValue());
                    userCartFragment.setArguments(args);

                    ((FragmentActivity) getActivity()).getSupportFragmentManager().beginTransaction().
                            replace(R.id.main_frame, userCartFragment).addToBackStack("deal").commit();
                }

                else Toast.makeText(getActivity(),getResources().getString(R.string.aleadyfound),Toast.LENGTH_SHORT).show();
                }
        });

*/

        ChipGroup chipGroup = view.findViewById(R.id.chipGroup);
        Log.e("lastCheckLV ",String.valueOf(dealsOffersViewModel.getLastchecked()!=null && dealsOffersViewModel.getLastchecked().getValue()==2131296395) );

     /*   if (dealsOffersViewModel.getLastchecked()!=null && dealsOffersViewModel.getLastchecked().getValue()==2131296395){
            Log.e("lastCheck ","truee");
            recyclerView.setAdapter(dealsAdapter);

        }*/
        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int i) {
                Chip chip = chipGroup.findViewById(i);
                if (chip != null) {
                    for (int i2 = 0; i2 < chipGroup.getChildCount(); ++i2) {

                        chipGroup.getChildAt(i2).setClickable(true);
                    }
                    chip.setClickable(false);
                }

               // Log.e("onCheckedChanged: ", chip.getText().toString());

             if (i==2131296397) {
                /* offersAdapter = new OffersAdapter(getActiv
                ity(), currentOffers);*/
                 recyclerView.setAdapter(offersAdapter);
                 dealsOffersViewModel.getLastchecked().setValue(2131296397);
                }
                else if (i==2131296395)
            {
               /* dealsAdapter = new DealsAdapter(getActivity(), currentDeals);*/
                recyclerView.setAdapter(dealsAdapter);
                dealsOffersViewModel.getLastchecked().setValue(2131296395);
            }


            }
        });

        return view;
    }

    private String getCallerFragment(){

        int count = ((FragmentActivity) getActivity()).getSupportFragmentManager().getBackStackEntryCount();
        if (count-2>=0)  return ((FragmentActivity) getActivity()).getSupportFragmentManager().getBackStackEntryAt(count - 2).getName();
      else return "none";
    }


    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new AllStoresViewModelFactory(getActivity().getApplication());
    }


}
