package com.example.hossam.parashotApp.presentation.screens.home;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.hossam.parashotApp.presentation.screens.home.categoryFragment.HeaderFooterViewModel;
import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.entities.StoreSettingEntity;
import com.example.hossam.parashotApp.databinding.HeaderFooterBinding;
import com.example.hossam.parashotApp.presentation.screens.home.categoryFragment.CategoryFragment;
import com.example.hossam.parashotApp.presentation.screens.home.myOrderFragment.MYOrderFragment;
import com.example.hossam.parashotApp.presentation.screens.home.productsDetailsFragment.ProductDetailsFragment;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class HomeActivity extends AppCompatActivity {
    public HeaderFooterViewModel headerFooter;
    StoreSettingEntity storeSettingEntity;

    ///////// defind attachBaseContext to install font

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home1);
    //    storeSettingEntity = (StoreSettingEntity) getIntent().getSerializableExtra("AllData");

        if (savedInstanceState == null) {
            Fragment fragment = new ProductDetailsFragment();
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("design", storeSettingEntity.getData().get(0).getStoresettings().get(0).getDesign());
//            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).commit();
        }
       // initDataBinding(storeSettingEntity);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        getSupportFragmentManager().popBackStack();
    }

    public void initDataBinding(StoreSettingEntity storeSettingEntity) {
        HeaderFooterBinding headerfooterBinding = DataBindingUtil.setContentView(this, R.layout.activity_home1);
        StoreSettingEntity.DataBean.StoresettingsBean.DesignBean.FooterBean footer = storeSettingEntity.getData().get(0).getStoresettings().get(0).getDesign().getFooter();
        StoreSettingEntity.DataBean.StoresettingsBean.DesignBean.HeaderBean header = storeSettingEntity.getData().get(0).getStoresettings().get(0).getDesign().getHeader();
        headerFooter = ViewModelProviders.of(this).get(HeaderFooterViewModel.class);
        headerFooter.init(header, footer);
        headerfooterBinding.setFooterSetting(headerFooter);
    }

    public void gotomorefragment(View view) {

    }

    public void gotomyOrder(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MYOrderFragment()).commit();
    }

    public void gotomainfragment(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new CategoryFragment()).commit();

    }
}
