package com.ei.mobilliumdemo.model.models.editorShop;

import android.databinding.Bindable;

import com.ei.mobilliumdemo.model.BaseObservableModels;
import com.ei.mobilliumdemo.model.helper.cover.CoverModel;
import com.ei.mobilliumdemo.model.models.product.ProductModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EditorShopModel extends BaseObservableModels {


    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("definition")
    private String definition;


    @Expose
    @SerializedName("cover")
    private CoverModel cover;

    @Expose
    @SerializedName("logo")
    private CoverModel logo;


    @Expose
    @SerializedName("product_count")
    private int productCount;


    @Expose
    @SerializedName("popular_products")
    private List<ProductModel> popularProduct;




    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
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


    public List<ProductModel> getPopularProduct() {
        return popularProduct;
    }

    public void setPopularProduct(List<ProductModel> popularProduct) {
        this.popularProduct = popularProduct;
    }


    @Bindable
    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }
}
