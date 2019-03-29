package com.ei.mobilliumdemo.model.models.featured;

import android.databinding.Bindable;

import com.ei.mobilliumdemo.BR;
import com.ei.mobilliumdemo.model.BaseObservableModels;
import com.ei.mobilliumdemo.model.helper.cover.CoverModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeaturedModel extends BaseObservableModels {


    @Expose
    @SerializedName("type")
    private String type;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("sub_title")
    private String subTitle;


    @Expose
    @SerializedName("cover")
    private CoverModel cover;



    @Bindable
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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


}
