package com.example.hossam.parashotApp.presentation.screens.home.storesFragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hossam.parashotApp.helper.ProgressDialogHelper;
import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.entities.AllStoriesModel;
import com.example.hossam.parashotApp.entities.StoreSettingEntity;
import com.example.hossam.parashotApp.presentation.screens.home.HomeActivity;
import com.example.hossam.parashotApp.presentation.screens.home.storesFragment.adapter.AllStoriesAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class StoresFragment extends Fragment {


    @SerializedName("records")
    @Expose
    private RecyclerView recyclerView;
    private StoresViewModel stores_viewModel;
    List<AllStoriesModel.DataBean> allStories=new ArrayList<>();
    AllStoriesAdapter storesAdapter;
    int type,cate_or_sub_ID;
    public StoresFragment() {
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

        View view = inflater.inflate(R.layout.fragment_first_resturant_list, container, false);
        recyclerView = view.findViewById(R.id.recylerview);
        ProgressDialogHelper.showSimpleProgressDialog(getActivity(), false);

        ((HomeActivity)Objects.requireNonNull(getActivity())).title.setText(getText(R.string.stores));

         assert getArguments() != null;
         type=getArguments().getInt("type");
         cate_or_sub_ID=getArguments().getInt("categryId");

        stores_viewModel = ViewModelProviders.of(this,getViewModelFactory()).get(StoresViewModel.class);

        stores_viewModel.loading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isLoading) {

                if (isLoading != null && isLoading) {
                    ProgressDialogHelper.showSimpleProgressDialog(getActivity(), false);
                }

                else
                {
                    ProgressDialogHelper.removeSimpleProgressDialog();
                }
            }
        });

        stores_viewModel.allStoriesModelMutableLiveData.observe(getActivity(), allStoriesModel -> {

            allStories.addAll(allStoriesModel.getData());
            if (storesAdapter==null)
            {
                storesAdapter = new AllStoriesAdapter(getActivity(),allStories);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
                recyclerView.setAdapter(storesAdapter);

            }
            else
                storesAdapter.notifyDataSetChanged();

        });

        stores_viewModel.allStoriesFromGoogleModelMutableLiveData.observe(getActivity(),storesFromGoogleModel ->
                {
                    for (int i=0;i<storesFromGoogleModel.getResults().size();i++)
                    {
                        AllStoriesModel.DataBean store = new AllStoriesModel.DataBean();
                        store.setName(storesFromGoogleModel.getResults().get(i).getName());
                        store.setLogo(storesFromGoogleModel.getResults().get(i).getIcon());
                        AllStoriesModel.DataBean.StoreratesBean storeratesBean = new AllStoriesModel.DataBean.StoreratesBean();
                        storeratesBean.setStars((int) storesFromGoogleModel.getResults().get(i).getRating());
                        List<AllStoriesModel.DataBean.StoreratesBean> storeratesBeans= new ArrayList<>();
                        storeratesBeans.add(storeratesBean);
                        store.setStorerates(storeratesBeans);
                        if (storesFromGoogleModel.getResults().get(i).getPhotos()!=null) {
                            store.setPhoto(storesFromGoogleModel.getResults().get(i).getPhotos().get(0).getPhoto_reference());
                            store.setMaxwidth(storesFromGoogleModel.getResults().get(i).getPhotos().get(0).getWidth());
                        }
                        store.setFrom_google(true);
                        allStories.add(store);
                    }
                    if (storesAdapter==null)
                    {
                        storesAdapter = new AllStoriesAdapter(getActivity(),allStories);
                        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
                        recyclerView.setAdapter(storesAdapter);
                    }
                    else
                    storesAdapter.notifyDataSetChanged();
                }
        );
        stores_viewModel.errorLiveData.observe(this, new Observer<Throwable>() {
                    @Override
                    public void onChanged(@Nullable Throwable throwable) {
                        // todo show errorى
                        assert throwable != null;
                        Toast.makeText(getActivity(),getResources().getString(R.string.erroroccur)+
                                throwable.getCause().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
        );

        return view;
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new AllStoresViewModelFactory(getActivity().getApplication(),type,cate_or_sub_ID);
    }

}
