package com.ei.mobilliumdemo.model.models.product;

import android.databinding.Bindable;

import com.ei.mobilliumdemo.BR;
import com.ei.mobilliumdemo.model.BaseObservableModels;
import com.ei.mobilliumdemo.model.helper.Shop.ShopModel;
import com.ei.mobilliumdemo.model.helper.cover.CoverModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductModel  extends BaseObservableModels {

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("sub_title")
    private String subTitle;

    @Expose
    @SerializedName("slug")
    private String slug;

    @Expose
    @SerializedName("shop")
    private ShopModel shopModel;

    @Expose
    @SerializedName("price")
    private int price;

    @Expose
    @SerializedName("old_price")
    private int oldPrice;

    @Expose
    @SerializedName("images")
    private List<CoverModel> images;

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
    public List<CoverModel> getImages() {
        return images;
    }

    public void setImages(List<CoverModel> images) {
        this.images = images;
    }


    @Bindable
    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
        notifyPropertyChanged(BR.slug);
    }


    @Bindable
    public ShopModel getShopModel() {
        return shopModel;
    }

    public void setShopModel(ShopModel shopModel) {
        this.shopModel = shopModel;
    }
    @Bindable
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Bindable
    public int getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }

}
