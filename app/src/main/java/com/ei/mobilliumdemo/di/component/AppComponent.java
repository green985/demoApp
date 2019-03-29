package com.ei.mobilliumdemo.di.component;

import android.app.Application;

import com.ei.mobilliumdemo.DemoApp;
import com.ei.mobilliumdemo.core.di.module.AppModule;
import com.ei.mobilliumdemo.di.module.ActivityBuilder;
import com.ei.mobilliumdemo.di.module.FragmentBuilder;
import com.ei.mobilliumdemo.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * The main component that holds and services all modules within its builder.
 *
 * @author EiAppcompany
 */
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class,
        FragmentBuilder.class,
        ViewModelModule.class})
public interface AppComponent extends AndroidInjector<DemoApp> {

    /**
     * A method that injects the activity
     * @param instance represents an instance of {@link DemoApp}
     */
    @Override
    void inject(DemoApp instance);

    /**
     * A {@link Component.Builder} that initializes necessary implementations
     */
    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
