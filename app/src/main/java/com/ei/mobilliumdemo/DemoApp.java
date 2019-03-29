package com.ei.mobilliumdemo;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.multidex.MultiDex;

import com.ei.mobilliumdemo.core.di.qualifier.OnlyApp;
import com.ei.mobilliumdemo.di.component.AppComponent;
import com.ei.mobilliumdemo.di.component.DaggerAppComponent;
import com.ei.mobilliumdemo.di.component.DaggerBindingComponent;
import com.ei.mobilliumdemo.util.AppUtil;
import com.ei.mobilliumdemo.di.component.BindingComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * A base application that handles core processes
 *
 * @author EiAppcompany
 */
public class DemoApp extends DaggerApplication {

    @OnlyApp
    @Inject
    AppUtil appUtil;

    @Override
    public void onCreate() {
        super.onCreate();
        appUtil.setupNecessaryPlugins();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);

        BindingComponent bindingComponent = DaggerBindingComponent.builder().appComponent(appComponent).build();
        DataBindingUtil.setDefaultComponent(bindingComponent);

        return appComponent;
    }
}
