package com.example.hossam.parashotApp.presentation.screens.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.helper.GpsTracker;
import com.example.hossam.parashotApp.helper.PreferenceHelper;
import com.example.hossam.parashotApp.presentation.screens.home.categoryFragment.CategoryFragment;
import com.example.hossam.parashotApp.presentation.screens.home.loginFragment.LoginFragment;
import com.example.hossam.parashotApp.presentation.screens.home.myOrderFragment.MYOrderFragment;
import com.example.hossam.parashotApp.presentation.screens.home.userCartFragment.UserCartFragment;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class HomeActivity extends AppCompatActivity {
    public TextView title;
    GpsTracker gpsTracker;
    PreferenceHelper preferenceHelper;
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

        title = findViewById(R.id.title);
        gotomainfragment(null);

        preferenceHelper = new PreferenceHelper(this);
        //////// get current user location

        gpsTracker = new GpsTracker(HomeActivity.this);
        if (gpsTracker.canGetLocation()) {
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            preferenceHelper.setCURRENTLAT(String.valueOf(latitude));
            preferenceHelper.setCURRENTLONG(String.valueOf(longitude));
        }
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

        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new CategoryFragment()).addToBackStack(null).commit();

    }

}
