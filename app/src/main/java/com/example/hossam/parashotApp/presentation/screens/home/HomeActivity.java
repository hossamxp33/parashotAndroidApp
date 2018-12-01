package com.example.hossam.parashotApp.presentation.screens.home;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.helper.GpsTracker;
import com.example.hossam.parashotApp.helper.PreferenceHelper;
import com.example.hossam.parashotApp.presentation.screens.LanguageActivity;
import com.example.hossam.parashotApp.presentation.screens.home.categoryFragment.CategoryFragment;
import com.example.hossam.parashotApp.presentation.screens.home.dealsOffersFragment.DealsOffersFragment;
import com.example.hossam.parashotApp.presentation.screens.home.favoriteFragment.FavoriteFragment;
import com.example.hossam.parashotApp.presentation.screens.home.loginFragment.LoginFragment;
import com.example.hossam.parashotApp.presentation.screens.home.myOrderFragment.MYOrderFragment;
import com.example.hossam.parashotApp.presentation.screens.home.notificationFragment.NotificationsFragment;

import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public TextView title;
    GpsTracker gpsTracker;
    PreferenceHelper preferenceHelper;
    DrawerLayout drawerLayout;
    NavigationView mNavigationView;
    String arabic = "0";
    String eng = "1";
    String check_lang;
    ///////// defind attachBaseContext to install font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // Make to run your application only in portrait mode

        title = findViewById(R.id.title);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new CategoryFragment()).addToBackStack(null).commit();

        drawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        preferenceHelper = new PreferenceHelper(this);
        check_lang = preferenceHelper.getLanguage();
        initializeLanguage(check_lang);
        findViewById(R.id.menu).setOnClickListener(v ->
                {
                    RotateAnimation anim = new RotateAnimation(0f, 350f, 15f, 15f);
                    anim.setInterpolator(new LinearInterpolator());
                    anim.setRepeatCount(Animation.INFINITE);
                    anim.setDuration(700);
                    // Start animating the image
                    final ImageView splash = (ImageView) findViewById(R.id.menu);
                    splash.startAnimation(anim);
                    // Later.. stop the animation
                    splash.setAnimation(null);
                    drawerLayout.openDrawer(Gravity.END);
                }
        );


        //////// get current user location


        gpsTracker = new GpsTracker(HomeActivity.this);
        if (gpsTracker.canGetLocation()) {
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            preferenceHelper.setCURRENTLAT(String.valueOf(latitude));
            preferenceHelper.setCURRENTLONG(String.valueOf(longitude));
        }
    }

    private void initializeLanguage(String check_lang) {

        if (check_lang == null) {
            Locale.getDefault().getDisplayLanguage();
            if (Locale.getDefault().getDisplayLanguage() != null && Locale.getDefault().getDisplayLanguage().equals("العربية")) {
                preferenceHelper.setLanguage(arabic);
                String languageToLoad = "العربية";
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                HomeActivity.this.getResources().updateConfiguration(config, HomeActivity.this.getResources().getDisplayMetrics());
            }

            else if (Locale.getDefault().getDisplayLanguage() != null && Locale.getDefault().getDisplayLanguage().equals("English")) {
                preferenceHelper.setLanguage(eng);
                String languageToLoad = "en";
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                HomeActivity.this.getResources().updateConfiguration(config, HomeActivity.this.getResources().getDisplayMetrics());
            }
        }
        else {
            if ( check_lang.equals("0")) {
                String languageToLoad = "ar";
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                HomeActivity.this.getResources().updateConfiguration(config, HomeActivity.this.getResources().getDisplayMetrics());

            } else if (check_lang.equals("1")) {
                String languageToLoad = "en";
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                HomeActivity.this.getResources().updateConfiguration(config, HomeActivity.this.getResources().getDisplayMetrics());
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //getSupportFragmentManager().popBackStack();
    }

    public void gotodealsfragment(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new DealsOffersFragment()).addToBackStack(null).commit();

    }

    public void gotoFavsfragment(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new FavoriteFragment()).addToBackStack(null).commit();

    }



    public void gotomyOrder(View view) {
        if (preferenceHelper.getUserId() > 0)
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MYOrderFragment()).addToBackStack(null).commit();
        else
            Snackbar.make(view, getText(R.string.loginfirst), Snackbar.LENGTH_LONG).show();
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

    public void gotoNotifications(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new NotificationsFragment()).addToBackStack(null).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.lang_change:
                drawerLayout.closeDrawers();
                startActivity(new Intent(this, LanguageActivity.class));
                break;
        }
        return true;
    }
}
