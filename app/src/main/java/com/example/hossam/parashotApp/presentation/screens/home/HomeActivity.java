package com.example.hossam.parashotApp.presentation.screens.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.entities.StoreSettingEntity;
import com.example.hossam.parashotApp.databinding.HeaderFooterBinding;
import com.example.hossam.parashotApp.entities.StoreSettingEntity;
import com.example.hossam.parashotApp.presentation.screens.getUserLocation.GetUserLOcationActivity;
import com.example.hossam.parashotApp.presentation.screens.home.categoryFragment.CategoryFragment;
import com.example.hossam.parashotApp.presentation.screens.home.categoryFragment.HeaderFooterViewModel;
import com.example.hossam.parashotApp.presentation.screens.home.loginFragment.LoginFragment;
import com.example.hossam.parashotApp.presentation.screens.home.categoryFragment.HeaderFooterViewModel;
import com.example.hossam.parashotApp.presentation.screens.home.myOrderFragment.MYOrderFragment;
import com.example.hossam.parashotApp.presentation.screens.home.userCartFragment.UserCartFragment;
import com.example.hossam.parashotApp.presentation.screens.home.userCart.UserCartFragment;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "hash";
    public HeaderFooterViewModel headerFooter;
    StoreSettingEntity storeSettingEntity;
    public TextView title;
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
        setContentView(R.layout.activity_home);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //getSupportFragmentManager().popBackStack();
    }


    public void gotomorefragment(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new UserCartFragment()).addToBackStack(null).commit();

    }

    public void gotomyOrder(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MYOrderFragment()).addToBackStack(null).commit();
    }

    public void gotoregister(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new LoginFragment()).addToBackStack(null).commit();
    }

    public void gotomainfragment(View view) {
      getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new CategoryFragment()).addToBackStack(null).commit();

    }


}
