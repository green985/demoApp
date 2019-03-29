package com.ei.mobilliumdemo.core.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.ei.mobilliumdemo.core.helper.Constants;
import com.facebook.stetho.Stetho;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * The main {@link Module} that holds all modules and provides instances of core things at the same time
 *
 * @author EiAppcompany
 */
@Module(includes = {ContextModule.class, NetModule.class})
public class AppModule {

    /**
     * Returns an instance of {@link SharedPreferences}
     *
     * @param context a context
     * @return an instance {@link SharedPreferences}
     */
    @Provides
    SharedPreferences provideSharedPreference(@NonNull Context context) {
        return context.getSharedPreferences(Constants.DEMO_PREF_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Returns an instance of {@link CompositeDisposable}
     *
     * @return an instance of {@link CompositeDisposable}
     */
    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    /**
     * Returns {@link Stetho.InitializerBuilder} to prepare debugging bridge
     *
     * @param context an instance of {@link Context}
     * @return a {@link Stetho.InitializerBuilder}
     */
    @Provides
    @Singleton
    Stetho.InitializerBuilder provideStethoInitializerBuilder(Context context) {
        Stetho.InitializerBuilder initializerBuilder = Stetho.newInitializerBuilder(context);
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context));
        initializerBuilder.enableDumpapp(Stetho.defaultDumperPluginsProvider(context));

        return initializerBuilder;
    }

    /**
     * Returns {@link Stetho.Initializer} to prepare debugging bridge
     *
     * @param initializerBuilder an instance of {@link Stetho.InitializerBuilder}
     * @return a {@link Stetho.Initializer}
     */
    @Provides
    @Singleton
    Stetho.Initializer provideStethoInitializer(Stetho.InitializerBuilder initializerBuilder) {
        return initializerBuilder.build();
    }
}
