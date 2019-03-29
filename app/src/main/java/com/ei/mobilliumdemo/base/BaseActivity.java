package com.ei.mobilliumdemo.base;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.ei.mobilliumdemo.R;
import com.ei.mobilliumdemo.core.helper.Constants;
import com.ei.mobilliumdemo.core.util.KeyboardUtil;
import com.ei.mobilliumdemo.core.util.PreferenceUtil;
import com.ei.mobilliumdemo.core.util.widget.ResponsiveGridLayoutManager;
import com.google.gson.Gson;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * The base activity which conducts generic processes for all activities
 *
 * @author EiAppcompany
 */
public abstract class BaseActivity<DB extends ViewDataBinding, VM extends ViewModel> extends DaggerAppCompatActivity {

    protected DB dataBinding;
    protected VM viewModel;

    private ProgressDialog mProgressDialog;
    private Intent mIntent;

    private MutableLiveData<Boolean> connectionResetListener = new MutableLiveData<>();

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    KeyboardUtil keyboardUtil;
    @Inject
    PreferenceUtil preferenceUtil;

    /**
     * Returns layout id
     *
     * @return layout id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * Returns ViewModel
     *
     * @return ViewModel
     */
    @NonNull
    protected abstract Class<VM> getViewModel();

    /**
     * Returns an instance of {@link PreferenceUtil}
     *
     * @return an instance of {@link PreferenceUtil}
     */
    @NonNull
    protected PreferenceUtil getPreferenceUtil() {
        return preferenceUtil;
    }

    /**
     * Handles first run things
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel());

        mProgressDialog = new ProgressDialog();
    }

    @UiThread
    protected void showDialog() {
        if (mProgressDialog.isAdded()) {
            return;
        }
        mProgressDialog.show(getSupportFragmentManager(), Constants.DIALOG_TITLE);
    }

    /**
     * Dismiss the loading dialog
     */
    @UiThread
    protected void dismissDialog() {
        if (mProgressDialog.isAdded()) {
            mProgressDialog.dismiss();
            return;
        }
        try {
            mProgressDialog.dismiss();
        } catch (Exception ignore) {

        }
    }

    protected synchronized void initializeAdapter(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.Adapter adapter, int orientation) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(getApplicationContext(), R.anim.layout_animation_slide_right);
        recyclerView.setLayoutAnimation(animationController);
        LinearLayoutManager layoutManager;

        if (orientation == 0) {
            layoutManager =
                    new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        } else {
            layoutManager =
                    new LinearLayoutManager(recyclerView.getContext());
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setOnFlingListener(null);

    }

    protected synchronized void initializeGridAdapter(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.Adapter adapter) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        GridLayoutManager gridLayoutManager;
        gridLayoutManager = new GridLayoutManager(recyclerView.getContext(),2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setOnFlingListener(null);

    }

}
