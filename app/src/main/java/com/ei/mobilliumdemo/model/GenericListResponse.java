package com.ei.mobilliumdemo.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.ei.mobilliumdemo.BR;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenericListResponse<T> extends BaseObservableModels {


    @Expose
    @SerializedName("type")
    private String type;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName(value = "products", alternate = {"categories", "collections", "shops", "featured"})
    private List<T> listData;

    @Bindable
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        notifyPropertyChanged(BR.type);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public void setListData(List<T> listData) {
        this.listData = listData;
    }


    @Bindable
    public List<T> getData() {
        return listData;
    }


}
