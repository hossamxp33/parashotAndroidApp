package com.example.hossam.parashotApp.presentation.screens.splash;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.example.hossam.parashotApp.dataLayer.apiData.ApiClient;
import com.example.hossam.parashotApp.dataLayer.apiData.ApiInterface;
import com.example.hossam.parashotApp.dataLayer.localDatabase.homePage.dao.FooterDao;
import com.example.hossam.parashotApp.dataLayer.localDatabase.LocalDatabase;
import com.example.hossam.parashotApp.dataLayer.repositories.SplashRepository;

public class SplashViewModelFactory implements ViewModelProvider.Factory {

    private Application application;
    public SplashViewModelFactory(@NonNull Application application1) {
        application = application1;
    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == SplashViewModel.class) {
            return (T) new SplashViewModel(getSplashRepository());
        }
        throw new IllegalArgumentException("Invalid view model class type");
    }

    @NonNull
    private SplashRepository getSplashRepository() {
        return new SplashRepository(getFooterDao(), getApiService());
    }

    private ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }

    private FooterDao getFooterDao() {
        return LocalDatabase.getInstance(application).footerDao();
    }
}
