package com.ei.mobilliumdemo.model.models.category;

import android.databinding.Bindable;

import com.ei.mobilliumdemo.model.BaseObservableModels;
import com.ei.mobilliumdemo.model.helper.cover.CoverModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryModel extends BaseObservableModels {


    @Expose
    @SerializedName("name")
    private String name;

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
    public CoverModel getLogo() {
        return logo;
    }

    public void setLogo(CoverModel logo) {
        this.logo = logo;
    }


}
