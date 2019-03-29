package com.ei.mobilliumdemo.model.helper.cover;

import android.databinding.Bindable;

import com.ei.mobilliumdemo.BR;
import com.ei.mobilliumdemo.model.BaseObservableModels;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoverModel extends BaseObservableModels {


    @Expose
    @SerializedName("url")
    private String url;

    @Expose
    @SerializedName("medium")
    private OtherCoverModel mediumCover;
    @Expose
    @SerializedName("thumbnail")
    private OtherCoverModel thumbnailCover;

    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.url);
    }

    @Bindable
    public OtherCoverModel getMediumCover() {
        return mediumCover;
    }

    public void setMediumCover(OtherCoverModel mediumCover) {
        this.mediumCover = mediumCover;
        notifyPropertyChanged(BR.mediumCover);
    }

    @Bindable
    public OtherCoverModel getOtherCover() {
        return thumbnailCover;
    }

    public void setOtherCover(OtherCoverModel thumbnailCover) {
        this.thumbnailCover = thumbnailCover;
        //notifyPropertyChanged(BR.thumbnailCover);
    }


}
