package com.ei.mobilliumdemo.model.models.collection;

import android.databinding.Bindable;

import com.ei.mobilliumdemo.model.BaseObservableModels;
import com.ei.mobilliumdemo.model.helper.cover.CoverModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CollectionModel extends BaseObservableModels {


    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("definition")
    private String definition;


    @Expose
    @SerializedName("cover")
    private CoverModel cover;


    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Bindable
    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition= definition;
    }

    @Bindable
    public CoverModel getCover() {
        return cover;
    }

    public void setCover(CoverModel cover) {
        this.cover = cover;
    }


}
