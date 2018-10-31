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
import com.example.hossam.parashotApp.presentation.screens.home.storesFragment.adapter.AllStoriesAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class StoresFragment extends Fragment {


    @SerializedName("records")
    @Expose
    private RecyclerView recyclerView;
    private StoresViewModel stores_viewModel;
    StoreSettingEntity.DataBean.StoresettingsBean.DesignBean designBean;

    AllStoriesAdapter allStoriesAdapter;

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

        stores_viewModel = ViewModelProviders.of(this, getViewModelFactory()).get(StoresViewModel.class);
        stores_viewModel.loading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isLoading) {
                if (isLoading != null && isLoading) {
                    ProgressDialogHelper.showSimpleProgressDialog(getActivity(), false);
                } else {
                    ProgressDialogHelper.removeSimpleProgressDialog();
                }
            }
        });

        stores_viewModel.allStoriesModelMutableLiveData.observe(getActivity(), new Observer<AllStoriesModel>() {
            @Override
            public void onChanged(@Nullable AllStoriesModel allStoriesModel) {

               allStoriesAdapter = new AllStoriesAdapter(getActivity(),allStoriesModel.getData());
               recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
               recyclerView.setAdapter(allStoriesAdapter);
            }
        });

        stores_viewModel.errorLiveData.observe(this, new Observer<Throwable>() {
                    @Override
                    public void onChanged(@Nullable Throwable throwable) {
                        // todo show error

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
        return new AllStoresViewModelFactory(getActivity().getApplication());
    }

}
