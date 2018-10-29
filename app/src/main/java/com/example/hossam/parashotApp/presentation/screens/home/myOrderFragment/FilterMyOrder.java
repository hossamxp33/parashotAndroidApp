package com.example.hossam.parashotApp.presentation.screens.home.myOrderFragment;

import com.example.hossam.parashotApp.entities.MYOrdersModel;

import java.util.List;

public class FilterMyOrder {

    List<MYOrdersModel.DataBean> commpleteOrderData;
    List<MYOrdersModel.DataBean> notCommpleteOrderData;

    public FilterMyOrder(List<MYOrdersModel.DataBean> commpleteOrderData, List<MYOrdersModel.DataBean> notCommpleteOrderData) {
        this.commpleteOrderData = commpleteOrderData;
        this.notCommpleteOrderData = notCommpleteOrderData;
    }
}
