package com.ei.mobilliumdemo.model.helper.Shop;

import android.databinding.Bindable;

import com.ei.mobilliumdemo.BR;
import com.ei.mobilliumdemo.model.BaseObservableModels;
import com.ei.mobilliumdemo.model.helper.cover.CoverModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShopModel extends BaseObservableModels {

    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("sub_title")
    private String subTitle;


    @Expose
    @SerializedName("cover")
    private CoverModel cover;

    @Expose
    @SerializedName("logo")
    private CoverModel logo;


    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Bindable
    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle= subTitle;
        notifyPropertyChanged(BR.subTitle);
    }


    @Bindable
    public CoverModel getCover() {
        return cover;
    }

    public void setCover(CoverModel cover) {
        this.cover = cover;
    }
    @Bindable
    public CoverModel getLogo() {
        return logo;
    }

    public void setLogo(CoverModel logo) {
        this.logo = logo;
    }

}
