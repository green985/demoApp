package com.ei.mobilliumdemo.base;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.ei.mobilliumdemo.helper.AutoClearedValue;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatDialogFragment;

/**
 * The base dialog fragment which conducts generic processes for all dialog fragments.
 *
 * @author EiAppcompany
 */
public abstract class BaseDialogFragment<DB extends ViewDataBinding, VM extends ViewModel> extends DaggerAppCompatDialogFragment {

    protected DB dataBinding;
    protected VM viewModel;
    protected AutoClearedValue<DB> autoClearedDataBindingValue;
    protected AutoClearedValue<VM> autoClearedDataViewModelValue;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

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
     * Handles first run things
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel());
        autoClearedDataViewModelValue = new AutoClearedValue<>(this, viewModel);
    }

    /**
     * Setups necessary inflates for views
     *
     * @param inflater           which inflates layout
     * @param container          which holds layout
     * @param savedInstanceState no need for a static orientation
     * @return view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        autoClearedDataBindingValue = new AutoClearedValue<>(this, dataBinding);

        Objects.requireNonNull(getDialog().getWindow()).setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        return dataBinding.getRoot();
    }
}
