package com.example.hossam.parashotApp.presentation.screens.home.deliverOffersFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.Consumer;

import com.example.hossam.parashotApp.dataLayer.repositories.AllStorsRepository;
import com.example.hossam.parashotApp.dataLayer.repositories.DeliveryOffersRepository;
import com.example.hossam.parashotApp.entities.DeliveryOffers;
import com.example.hossam.parashotApp.entities.StoresList;

import java.util.List;

public class DeliveryOffersViewModel extends ViewModel {

    private DeliveryOffersRepository deliveryOffersRepository;
    MutableLiveData<List<DeliveryOffers.DataBean>> Deliveryoffers =new MutableLiveData<List<DeliveryOffers.DataBean>>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();
    String place,ratecount,chat,name, cover,logo,ratenum;
    int ratestar;
    String like="0";

    public DeliveryOffersViewModel() {

    }

    public DeliveryOffersViewModel(final DeliveryOffersRepository deliveryOffersRepository) {

        deliveryOffersRepository.setOnSuccess(new Consumer<DeliveryOffers>() {
            @Override
            public void accept(DeliveryOffers deliveryOffers) {
                Deliveryoffers.postValue(deliveryOffers.getData());
                loading.postValue(false);
            }
        });

        deliveryOffersRepository.setOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                errorLiveData.postValue(throwable);
                loading.postValue(false);
            }
        });
        this.deliveryOffersRepository = deliveryOffersRepository;
    }

}
