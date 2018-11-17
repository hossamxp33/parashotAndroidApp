package com.example.hossam.parashotApp.presentation.screens.home.categoryFragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.example.hossam.parashotApp.presentation.screens.home.HomeActivity;
import com.example.hossam.parashotApp.presentation.screens.home.categoryFragment.adapters.CategriesAdapter;
import com.example.hossam.parashotApp.presentation.screens.home.categoryFragment.adapters.SliderPagerAdapter;
import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.entities.Categories;
import com.example.hossam.parashotApp.entities.StoreSettingEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class CategoryFragment extends Fragment {


    @SerializedName("records")
    @Expose
    private RecyclerView recyclerView;
    private CategriesAdapter customAdapter;  ////categry adapter
    ViewPager viewPager;
    private FrameLayout progress;
    private SliderPagerAdapter sliderPagerAdapter;  /// slider adapter
    private CategoryViewModel categoryViewModel;
    StoreSettingEntity.DataBean.StoresettingsBean.DesignBean designBean;
    CirclePageIndicator indicator;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    public CategoryFragment() {
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
        View view = inflater.inflate(R.layout.fragment_main_home1, container, false);
        recyclerView = view.findViewById(R.id.recylerview);
        viewPager = view.findViewById(R.id.vp_slider);
        progress = view.findViewById(R.id.progress);


        assert getArguments() != null;
       // designBean = (StoreSettingEntity.DataBean.StoresettingsBean.DesignBean) getArguments().getSerializable("design");

        ((HomeActivity)Objects.requireNonNull(getActivity())).title.setText(getText(R.string.categry));

        categoryViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(CategoryViewModel.class);

        categoryViewModel.loading.observe(this, loading ->
                progress.setVisibility(loading ? View.VISIBLE : View.GONE));

        categoryViewModel.categoriesLiveData.observe(this, new Observer<Categories>() {
            @Override
            public void onChanged(@Nullable Categories categories) {
                customAdapter = new CategriesAdapter(getActivity(), categories.getData(),categories.getSlider());
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                sliderPagerAdapter = new SliderPagerAdapter(getActivity(), categories.getSlider());
                viewPager.setAdapter(sliderPagerAdapter);
                viewPager.setClipToPadding(false);
                viewPager.setPadding(100, 0, 100, 0);
                viewPager.setPageMargin(40);
                indicator = view.findViewById(R.id.indicator);
                indicator.setViewPager(viewPager);
                init(categories.getSlider().size());
                recyclerView.setAdapter(customAdapter);
            }
        });

        categoryViewModel.errorLiveData.observe(this, throwable ->
                showSnackBar(view, throwable.getMessage()));

        return view;
    }

    private void showSnackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    private void init(int size) {

        final float density = getResources().getDisplayMetrics().density;
        //Set circle indicator radius
        indicator.setRadius(4 * density);
        NUM_PAGES = size;
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);


        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new CategoryViewModelFactory(getActivity().getApplication());
    }

}
