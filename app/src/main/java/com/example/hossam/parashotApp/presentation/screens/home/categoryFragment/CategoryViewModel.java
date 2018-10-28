package com.example.hossam.parashotApp.presentation.screens.home.categoryFragment;

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
import com.example.hossam.parashotApp.dataLayer.repositories.CategoryRepository;
import com.example.hossam.parashotApp.entities.Categories;

public class CategoryViewModel extends ViewModel {


    private CategoryRepository categoryRepository;
    MutableLiveData<Categories> categoriesLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private String name, imagePath, backColor, textColor;



    public CategoryViewModel() {

    }

    public CategoryViewModel(final CategoryRepository categoryRepository) {

        categoryRepository.setOnSuccess(new Consumer<Categories>() {
            @Override
            public void accept(Categories categories) {
                categoriesLiveData.postValue(categories);
           //     resultImageUrl.set("http://parashot.codesroots.com/library/default/burger.png");
                loading.postValue(false);
            }
        });

        categoryRepository.setOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                errorLiveData.postValue(throwable);
                loading.postValue(false);
            }
        });
        this.categoryRepository = categoryRepository;

        loadData();
    }

    private void loadData() {
        loading.setValue(true);
        categoryRepository.getAllCategoryData();
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
    public static void setText(TextView view, String color) {
        if (color!=null)
        view.setTextColor(Color.parseColor(color));
        else
            view.setBackgroundColor(Color.parseColor("#000000"));
    }


    @BindingAdapter("bind:background")
    public static void setBack(View view, String backgroundcolor) {
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


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackColor() {
        return backColor;
    }

    public void setBackColor(String backColor) {
        this.backColor = backColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

}
