package com.example.hossam.parashotApp.presentation.screens.home.myOrderFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.hossam.parashotApp.dataLayer.repositories.MyOrderRepository;
import com.example.hossam.parashotApp.entities.MYOrdersModel;

import java.util.ArrayList;
import java.util.List;


public class MyOrderViewModel extends ViewModel {

    private MyOrderRepository myOrder_repository;
    MutableLiveData<List<MYOrdersModel.DataBean>> myordersComplete_MutableLiveData = new MutableLiveData<List<MYOrdersModel.DataBean>>();
    MutableLiveData<List<MYOrdersModel.DataBean>> myordersNotComplete_MutableLiveData = new MutableLiveData<List<MYOrdersModel.DataBean>>();
    private List<MYOrdersModel.DataBean> myordersComplete=new ArrayList<>();
    private List<MYOrdersModel.DataBean> myordersNotComplete=new ArrayList<>();

    private MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();

    private String name, imagePath, item_description, storenamevalue,
            capitainnamevalue, orderstatuesvalue, orderdatevalue, item_price, ratecount, ratestart;


    public MyOrderViewModel() {
    }

    public MyOrderViewModel(final MyOrderRepository repository) {

        repository.setOnSuccess(orders -> {

//            for (int i = 0; i < orders.getData().size(); i++) {
//                if (orders.getData().get(i)!=null) {
//                    if (orders.getData().get(i).getOrderdetails()!=null) {
//                        if (orders.getData().get(i).getOrderdetails().get(0).getOrder_status().matches("1"))
//                            myordersComplete.add(orders.getData().get(i));
//                        else
//                            myordersNotComplete.add(orders.getData().get(i));
//                    }
//                }
//            }

            myordersComplete_MutableLiveData.postValue(orders.getData());
         ///   myordersNotComplete_MutableLiveData.postValue(orders.getData());
            loading.postValue(false);
        });

        repository.setOnError(throwable -> {
            errorLiveData.postValue(throwable);
            loading.postValue(false);
        });
        this.myOrder_repository = repository;
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getStorenamevalue() {
        return storenamevalue;
    }

    public void setStorenamevalue(String storenamevalue) {
        this.storenamevalue = storenamevalue;
    }

    public String getCapitainnamevalue() {
        return capitainnamevalue;
    }

    public void setCapitainnamevalue(String capitainnamevalue) {
        this.capitainnamevalue = capitainnamevalue;
    }

    public String getOrderstatuesvalue() {
        return orderstatuesvalue;
    }

    public void setOrderstatuesvalue(String orderstatuesvalue) {
        this.orderstatuesvalue = orderstatuesvalue;
    }

    public String getOrderdatevalue() {
        return orderdatevalue;
    }

    public void setOrderdatevalue(String orderdatevalue) {
        this.orderdatevalue = orderdatevalue;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getRatecount() {
        return ratecount;
    }

    public void setRatecount(String ratecount) {
        this.ratecount = ratecount;
    }

    public String getRatestart() {
        return ratestart;
    }

    public void setRatestart(String ratestart) {
        this.ratestart = ratestart;
    }
}
