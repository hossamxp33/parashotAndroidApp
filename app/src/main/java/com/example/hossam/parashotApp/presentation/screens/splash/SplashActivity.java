package com.example.hossam.parashotApp.presentation.screens.splash;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.hossam.parashotApp.helper.ProgressDialogHelper;
import com.example.hossam.parashotApp.presentation.screens.home.HomeActivity;
import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.entities.StoreSettingEntity;

public class SplashActivity extends AppCompatActivity {

    private SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();

//        ProgressDialogHelper.showSimpleProgressDialog(SplashActivity.this, false);
//
//        splashViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(SplashViewModel.class);
//        splashViewModel.loading.observe(this, new Observer<Boolean>() {
//            @Override
//            public void onChanged(@Nullable Boolean isLoading) {
//                if (isLoading != null && isLoading) {
//                    ProgressDialogHelper.showSimpleProgressDialog(SplashActivity.this, false);
//                } else {
//                    ProgressDialogHelper.removeSimpleProgressDialog();
//                }
//            }
//        });
//
//        splashViewModel.storeSettingLiveData.observe(this, new Observer<StoreSettingEntity>() {
//                    @Override
//                    public void onChanged(@Nullable StoreSettingEntity storeSettingEntity) {
//                        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
//                        intent.putExtra("AllData",  storeSettingEntity);
//                        startActivity(intent);
//                        finish();
//                    }
//                }
//               );
//
//        splashViewModel.errorLiveData.observe(this, new Observer<Throwable>() {
//                    @Override
//                    public void onChanged(@Nullable Throwable throwable) {
//                        // todo show error
//         Toast.makeText(SplashActivity.this,getResources().getString(R.string.erroroccur)+
//                        throwable.getCause().getMessage(),Toast.LENGTH_SHORT).show();
//                    }
//                }
//        );
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new SplashViewModelFactory(getApplication());
    }
}
