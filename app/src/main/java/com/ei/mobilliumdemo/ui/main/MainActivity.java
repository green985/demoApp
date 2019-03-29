package com.ei.mobilliumdemo.ui.main;

import android.arch.lifecycle.Observer;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import com.ei.mobilliumdemo.R;
import com.ei.mobilliumdemo.base.BaseActivity;
import com.ei.mobilliumdemo.binding.ImageBindingAdapter;
import com.ei.mobilliumdemo.core.helper.Constants;
import com.ei.mobilliumdemo.databinding.ActivityMainBinding;
import com.ei.mobilliumdemo.model.GenericListResponse;
import com.ei.mobilliumdemo.model.models.category.CategoryModel;
import com.ei.mobilliumdemo.model.models.collection.CollectionModel;
import com.ei.mobilliumdemo.model.models.editorShop.EditorShopModel;
import com.ei.mobilliumdemo.model.models.featured.FeaturedModel;
import com.ei.mobilliumdemo.model.models.product.ProductModel;
import com.ei.mobilliumdemo.ui.allActivity.ExploreAllActivity;
import com.ei.mobilliumdemo.ui.main.adapters.category.CategoryAdapter;
import com.ei.mobilliumdemo.ui.main.adapters.collection.CollectionAdapter;
import com.ei.mobilliumdemo.ui.main.adapters.editorShop.EditorShopAdapter;
import com.ei.mobilliumdemo.ui.main.adapters.featuredAdapter.FeaturedAdapter;
import com.ei.mobilliumdemo.ui.main.adapters.newShop.NewShopAdapter;
import com.ei.mobilliumdemo.ui.main.adapters.products.ProductsAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Inject
    ImageBindingAdapter bindingAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @NonNull
    @Override
    protected Class<MainViewModel> getViewModel() {
        return MainViewModel.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        dataBinding.searchToolbar.voiceButton.setOnClickListener(this::startVoiceInput);
    }

    public void getData() {
        showDialog();
        viewModel.getData();
        viewModel.getListMutableLiveData().observe(this, new Observer<List<GenericListResponse<Object>>>() {
            @Override
            public void onChanged(@Nullable List<GenericListResponse<Object>> genericListResponses) {
                dismissDialog();
                dataBinding.root.setVisibility(View.VISIBLE);
                if (genericListResponses == null) return;

                for (GenericListResponse response : genericListResponses) {
                    switch (response.getType()) {
                        case Constants.TYPE_FEATURED:
                            initFeaturedViewPager(response);
                            break;
                        case Constants.TYPE_NEW_PRODUCT:
                            initNewProductList(response);
                            break;
                        case Constants.TYPE_CATEGORIES:
                            initCategoriesList(response);
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

                dataBinding.root.setBackground(null);
            }
        });
    }

    private void startVoiceInput(View v ) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Bir Şeyler Söyleyin");
        try {
            startActivityForResult(intent, Constants.SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    dataBinding.searchToolbar.searchText.setText(result.get(0));
                }
                break;
            }
        }
    }

    private void initNewShopList(GenericListResponse response) {
        List<EditorShopModel> newShopModels = Constants.objectToList(response.getData(), EditorShopModel[].class);
        NewShopAdapter newShopAdapter = new NewShopAdapter();
        newShopAdapter.setData(newShopModels);
        initializeAdapter(dataBinding.newShopLayout.rvList, newShopAdapter, 0);
        dataBinding.newShopLayout.title.setText(response.getTitle());
        dataBinding.newShopLayout.root.setBackgroundColor(getResources().getColor(R.color.white));
        dataBinding.newShopLayout.allText.setOnClickListener(v -> setExploreButtonClick(response));
    }

    private void initEditorShop(GenericListResponse response) {
        List<EditorShopModel> editorShopModels = Constants.objectToList(response.getData(), EditorShopModel[].class);
        EditorShopAdapter editorShopAdapter = new EditorShopAdapter();
        editorShopAdapter.setData(editorShopModels);
        initializeAdapter(dataBinding.editorShopLayout.rvList, editorShopAdapter, 0);
        dataBinding.editorShopLayout.title.setText(response.getTitle());
        bindingAdapter.bindBackground(dataBinding.editorShopLayout.root, editorShopModels.get(0).getCover().getMediumCover().getUrl());
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(dataBinding.editorShopLayout.rvList);
        dataBinding.editorShopLayout.rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (recyclerView.getLayoutManager() == null) {
                        return;
                    }
                    View centerView = snapHelper.findSnapView(recyclerView.getLayoutManager());
                    if (centerView == null) {
                        return;
                    }
                    int pos = recyclerView.getLayoutManager().getPosition(centerView);
                    bindingAdapter.bindBackground(dataBinding.editorShopLayout.root, editorShopModels.get(pos).getCover().getMediumCover().getUrl());
                }
            }
        });
        dataBinding.editorShopLayout.allText.setOnClickListener(v -> setExploreButtonClick(response));
    }

    private void initCollectionList(GenericListResponse response) {
        List<CollectionModel> collectionModels = Constants.objectToList(response.getData(), CollectionModel[].class);
        CollectionAdapter collectionAdapter = new CollectionAdapter();
        collectionAdapter.setData(collectionModels);
        initializeAdapter(dataBinding.collectionLayout.rvList, collectionAdapter, 0);
        dataBinding.collectionLayout.title.setText(response.getTitle());
        dataBinding.collectionLayout.root.setBackgroundColor(getResources().getColor(R.color.white));
        dataBinding.collectionLayout.allText.setOnClickListener(v -> setExploreButtonClick(response));
    }

    private void initCategoriesList(GenericListResponse response) {
        List<CategoryModel> categoryModels = Constants.objectToList(response.getData(), CategoryModel[].class);
        CategoryAdapter categoryAdapter = new CategoryAdapter(null);
        categoryAdapter.setData(categoryModels);
        initializeAdapter(dataBinding.categoryLayout.rvList, categoryAdapter, 0);
        dataBinding.categoryLayout.title.setText(response.getTitle());
        dataBinding.categoryLayout.allText.setVisibility(View.GONE);
        dataBinding.categoryLayout.root.setBackgroundColor(getResources().getColor(R.color.category_list_background));
    }

    private void initNewProductList(GenericListResponse response) {
        List<ProductModel> productModels = Constants.objectToList(response.getData(), ProductModel[].class);
        ProductsAdapter productsAdapter = new ProductsAdapter();
        productsAdapter.setData(productModels);
        initializeAdapter(dataBinding.productLayout.rvList, productsAdapter, 0);
        dataBinding.productLayout.title.setText(response.getTitle());
        dataBinding.productLayout.allText.setOnClickListener(v -> setExploreButtonClick(response));
    }


    private void initFeaturedViewPager(GenericListResponse response) {
        List<FeaturedModel> featuredModelList = Constants.objectToList(response.getData(), FeaturedModel[].class);
        if (featuredModelList.size() != 0) {
            FeaturedAdapter adapter = new FeaturedAdapter(featuredModelList);
            dataBinding.featuredSlider.setAdapter(adapter);
            dataBinding.featuredSlider.startAutoScroll(2000);
            dataBinding.featuredSlider.setCycle(true);
            dataBinding.indicator.setViewPager(dataBinding.featuredSlider);
            dataBinding.featuredSlider.setStopWhenTouch(true);
        } else {
            dataBinding.sliderRoot.setVisibility(View.GONE);
        }
    }

    private void setExploreButtonClick(GenericListResponse response) {
        Intent intent = new Intent(this, ExploreAllActivity.class);
        intent.putExtra(Constants.EXPLORE_ALL, response);
        startActivity(intent);
    }


}
