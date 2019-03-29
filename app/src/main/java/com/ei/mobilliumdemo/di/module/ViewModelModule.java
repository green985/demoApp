package com.ei.mobilliumdemo.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;


import com.ei.mobilliumdemo.core.di.qualifier.ViewModelKey;
import com.ei.mobilliumdemo.core.viewmodel.DemoViewModelFactory;
import com.ei.mobilliumdemo.ui.main.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * A {@link Module} that injects all ViewModels
 *
 * @author EiAppcompany
 */
@Module
public abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindsMainViewModel(MainViewModel mainViewModel);



    @Binds
    abstract ViewModelProvider.Factory bindsDemoViewModelFactory(@NonNull DemoViewModelFactory demoViewModelFactory);
}
