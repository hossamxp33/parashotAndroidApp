package com.example.hossam.parashotApp.presentation.screens.home.loginFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.util.Consumer;

import com.example.hossam.parashotApp.dataLayer.repositories.LoginRepository;
import com.example.hossam.parashotApp.entities.LoginResponseModel;
import com.example.hossam.parashotApp.entities.User;


public class LoginViewModel extends ViewModel {

    private LoginRepository loginRepository;
    MutableLiveData<LoginResponseModel> loginLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();
    MutableLiveData<Integer> coderesponse = new MutableLiveData<>();

    public LoginViewModel() {
    }

    public void login (User user)
    {
        loginRepository.login(user);

    }

    public LoginViewModel(final LoginRepository repository) {

        repository.setOnSuccess(new Consumer<LoginResponseModel>() {
            @Override
            public void accept(LoginResponseModel model) {
                loginLiveData.postValue(model);
                loading.postValue(false);
            }
        });

        repository.setOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                errorLiveData.postValue(throwable);
                loading.postValue(false);
            }
        });

        repository.setcodeSuccess(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                coderesponse.postValue(integer);
            }
        });

        this.loginRepository = repository;
    }


}
