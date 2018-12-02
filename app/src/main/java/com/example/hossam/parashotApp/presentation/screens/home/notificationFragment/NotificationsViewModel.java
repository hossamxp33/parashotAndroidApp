package com.example.hossam.parashotApp.presentation.screens.home.notificationFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.util.Consumer;

import com.example.hossam.parashotApp.dataLayer.repositories.DeliveryCommentsRepository;
import com.example.hossam.parashotApp.dataLayer.repositories.NotificationRepository;
import com.example.hossam.parashotApp.entities.DeliveryComments;
import com.example.hossam.parashotApp.entities.Notifications;

public class NotificationsViewModel extends ViewModel {


    private NotificationRepository productRatesRepository;
    MutableLiveData<Notifications> notificationsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();


    public NotificationsViewModel() {
    }

    public NotificationsViewModel(final NotificationRepository notificationRepository) {

        notificationRepository.setOnSuccess(new Consumer<Notifications>() {
            @Override
            public void accept(Notifications  notifications) {
                notificationsMutableLiveData.postValue(notifications);
                loading.postValue(false);
            }
        });


        notificationRepository.setOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                errorLiveData.postValue(throwable);
                loading.postValue(false);
            }
        });

        this.productRatesRepository = notificationRepository;
    }


}
