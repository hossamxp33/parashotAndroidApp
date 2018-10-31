package com.example.hossam.parashotApp.presentation.screens.home.storesFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.support.v4.util.Consumer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.hossam.parashotApp.dataLayer.repositories.AllStoriesRepository;
import com.example.hossam.parashotApp.entities.AllStoriesModel;

public class StoresViewModel extends ViewModel {


    private AllStoriesRepository allStoriesRepository;
    MutableLiveData<AllStoriesModel> allStoriesModelMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();

    String place,ratecount,chat,like,name,photo,logo,ratenum;

    int ratestar;

    public StoresViewModel() {
    }

    public StoresViewModel(final AllStoriesRepository allStoriesRepository) {

        allStoriesRepository.setOnSuccess(new Consumer<AllStoriesModel>() {
            @Override
            public void accept(AllStoriesModel allStoriesModel) {
                allStoriesModelMutableLiveData.postValue(allStoriesModel);
                loading.postValue(false);
            }
        });

        allStoriesRepository.setOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                errorLiveData.postValue(throwable);
                loading.postValue(false);
            }
        });
        this.allStoriesRepository = allStoriesRepository;
    }

    public ObservableField<String> resultImageUrl = new ObservableField<>();

    public void imageUrlUpdated(String url) {
        resultImageUrl.set(url);
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }

    @BindingAdapter("bind:textstr")
    public static void settext(TextView view, String color) {
        if (color!=null)
        view.setTextColor(Color.parseColor(color));
        else
            view.setBackgroundColor(Color.parseColor("#000000"));
    }


    @BindingAdapter("bind:background")
    public static void setback(View view, String backgroundcolor) {
        if (backgroundcolor!=null)
            view.setBackgroundColor(Color.parseColor(backgroundcolor));
        else
            view.setBackgroundColor(Color.parseColor("#ffffff"));
    }


    @BindingAdapter("bind:background")
    public static void onClickItem(View view,String backgroundcolor) {
        if (backgroundcolor!=null)
            view.setBackgroundColor(Color.parseColor(backgroundcolor));
        else
            view.setBackgroundColor(Color.parseColor("#ffffff"));
    }

    public int getRatestar() {
        return ratestar;
    }

    public void setRatestar(int ratestar) {
        this.ratestar = ratestar;
    }

    public String getRatenum() {
        return ratenum;
    }

    public void setRatenum(String ratenum) {
        this.ratenum = ratenum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getRatecount() {
        return ratecount;
    }

    public void setRatecount(String ratecount) {
        this.ratecount = ratecount;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }
}
