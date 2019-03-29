package com.ei.mobilliumdemo.ui.allActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ei.mobilliumdemo.R;
import com.ei.mobilliumdemo.base.BaseActivity;
import com.ei.mobilliumdemo.core.helper.Constants;
import com.ei.mobilliumdemo.databinding.ActivityExploreAllBinding;
import com.ei.mobilliumdemo.model.GenericListResponse;
import com.ei.mobilliumdemo.model.models.collection.CollectionModel;
import com.ei.mobilliumdemo.model.models.editorShop.EditorShopModel;
import com.ei.mobilliumdemo.model.models.product.ProductModel;
import com.ei.mobilliumdemo.ui.main.MainViewModel;
import com.ei.mobilliumdemo.ui.main.adapters.collection.CollectionAdapter;
import com.ei.mobilliumdemo.ui.main.adapters.editorShop.EditorShopAdapter;
import com.ei.mobilliumdemo.ui.main.adapters.newShop.NewShopAdapter;
import com.ei.mobilliumdemo.ui.main.adapters.products.ProductsAdapter;

import java.util.List;

public class ExploreAllActivity extends BaseActivity<ActivityExploreAllBinding, MainViewModel> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_explore_all;
    }

    @NonNull
    @Override
    protected Class<MainViewModel> getViewModel() {
        return MainViewModel.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentData(getIntent());
    }

    private void initToolbar(String title) {
        dataBinding.toolbar.setTitle(title);
        setSupportActionBar(dataBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        dataBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dataBinding.toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    private void getIntentData(Intent intent) {
        GenericListResponse response = (GenericListResponse) intent.getSerializableExtra(Constants.EXPLORE_ALL);
        if (response == null) {
            Toast.makeText(this, R.string.error_text, Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        initToolbar(response.getTitle());

        switch (response.getType()) {
            case Constants.TYPE_NEW_PRODUCT:
                initNewProductList(response);
                break;
            case Constants.TYPE_COLLECTION:
                initCollectionList(response);
                break;
            case Constants.TYPE_EDITOR_SHOP:
                initEditorShop(response);
                break;
            case Constants.TYPE_NEW_SHOP:
                initNewShopList(response);
                break;
        }

    }


    private void initNewShopList(GenericListResponse response) {
        List<EditorShopModel> newShopModels = Constants.objectToList(response.getData(), EditorShopModel[].class);
        NewShopAdapter newShopAdapter = new NewShopAdapter(1);
        newShopAdapter.setData(newShopModels);
        initializeAdapter(dataBinding.productList.rvList, newShopAdapter, 1);
        dataBinding.productList.title.setText(response.getTitle());
        dataBinding.productList.root.setBackgroundColor(getResources().getColor(R.color.white));
    }

    private void initEditorShop(GenericListResponse response) {
        List<EditorShopModel> editorShopModels = Constants.objectToList(response.getData(), EditorShopModel[].class);
        EditorShopAdapter editorShopAdapter = new EditorShopAdapter(1);
        editorShopAdapter.setData(editorShopModels);
        initializeAdapter(dataBinding.productList.rvList, editorShopAdapter, 1);
        dataBinding.productList.title.setText(response.getTitle());

    }

    private void initCollectionList(GenericListResponse response) {
        List<CollectionModel> collectionModels = Constants.objectToList(response.getData(), CollectionModel[].class);
        CollectionAdapter collectionAdapter = new CollectionAdapter(1);
        collectionAdapter.setData(collectionModels);
        initializeAdapter(dataBinding.productList.rvList, collectionAdapter, 1);
        dataBinding.productList.title.setText(response.getTitle());
        dataBinding.productList.root.setBackgroundColor(getResources().getColor(R.color.white));
    }

    private void initNewProductList(GenericListResponse response) {
        List<ProductModel> productModels = Constants.objectToList(response.getData(), ProductModel[].class);
        ProductsAdapter productsAdapter = new ProductsAdapter(1);
        productsAdapter.setData(productModels);
        initializeGridAdapter(dataBinding.productList.rvList, productsAdapter);
        dataBinding.productList.title.setText(response.getTitle());
    }


}
