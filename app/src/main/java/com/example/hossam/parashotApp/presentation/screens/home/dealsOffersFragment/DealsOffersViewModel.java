package com.example.hossam.parashotApp.presentation.screens.home.dealsOffersFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.util.Consumer;

import com.example.hossam.parashotApp.dataLayer.localDatabase.userCart.entities.Product;
import com.example.hossam.parashotApp.dataLayer.repositories.OffersRepository;
import com.example.hossam.parashotApp.entities.DealsModel;
import com.example.hossam.parashotApp.entities.OffersModel;

public class DealsOffersViewModel extends ViewModel {

    private OffersRepository offersRepository;
    MutableLiveData<OffersModel> offersLD = new MutableLiveData<OffersModel>();
    MutableLiveData<DealsModel> dealsLD = new MutableLiveData<DealsModel>();
    private MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private MutableLiveData<Integer> lastchecked = new MutableLiveData<>();

    public MutableLiveData<Integer> getStore_id() {
        return store_id;
    }

    private MutableLiveData<Integer> store_id = new MutableLiveData<>();

    public MutableLiveData<Boolean> is_stored = new MutableLiveData<>();

    public MutableLiveData<Integer> getLastchecked() {
        return lastchecked;
    }


    public DealsOffersViewModel(final OffersRepository repository) {

        repository.setOnDealsSuccess(deals -> {
            dealsLD.postValue(deals);
            loading.postValue(false);
        });
        repository.setOnOffersSuccess(offers -> {
            offersLD.postValue(offers);
            loading.postValue(false);
        });

        repository.setOnError(throwable -> {
            errorLiveData.postValue(throwable);
            loading.postValue(false);
        });
        this.lastchecked.setValue(0);


        this.offersRepository = repository;
    }


    public void storeData(Product dataBeans) {
        offersRepository.saveDataInDB(dataBeans);
    }

    public int getAllforStore(int storeID) {
        int size = offersRepository.selectAllfromStore(storeID).size();
        return size;
    }

    public void setupConsumer() {
        offersRepository.setbooleanConsumerForAdd(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) {
                is_stored.postValue(aBoolean);
            }
        });
    }
 /*   public void setupSizeConsumer(){
        offersRepository.setSizeConsumer(new Consumer<Integer>() {
            @Override
            public void accept(Integer size) {
                .postValue(size);
            }
        });
    }*/


}
