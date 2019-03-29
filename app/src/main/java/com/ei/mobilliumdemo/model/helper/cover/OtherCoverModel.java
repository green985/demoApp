package com.ei.mobilliumdemo.model.helper.cover;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.ei.mobilliumdemo.BR;
import com.ei.mobilliumdemo.model.BaseObservableModels;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtherCoverModel extends BaseObservableModels {


    @Expose
    @SerializedName("url")
    private String url;

    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.url);
    }



}
