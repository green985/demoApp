package com.ei.mobilliumdemo.util;

import android.app.Application;
import android.support.annotation.NonNull;

import com.ei.mobilliumdemo.BuildConfig;
import com.ei.mobilliumdemo.DemoApp;
import com.ei.mobilliumdemo.core.di.qualifier.OnlyApp;
import com.facebook.stetho.Stetho;
import com.jakewharton.threetenabp.AndroidThreeTen;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * A utility class for {@link DemoApp}
 * Only {@link DemoApp} will use this helper class
 * to initialize first run processes. {@link OnlyApp} conducts that constraint.
 *
 * @author EiAppcompany
 */
@Singleton
public final class AppUtil {

    private Application application;
    private Stetho.Initializer initializer;

    /**
     * Default constructor to inject dependencies
     *
     * @param application represents {@link Application}
     * @param initializer represents {@link Stetho.Initializer}
     */
    @Inject
    AppUtil(@NonNull Application application,
            @NonNull Stetho.Initializer initializer) {
        Class clazz = DemoApp.class;

        for (int i = 0; i < clazz.getDeclaredFields().length; i++) {
            if (clazz.getDeclaredFields()[i].getAnnotation(OnlyApp.class) != null
                    && clazz.getDeclaredFields()[i].getType().getSimpleName().equals(this.getClass().getSimpleName())) {
                this.application = application;
                this.initializer = initializer;
            } else {
                i++;
            }
        }
    }

    /**
     * A helper method that setups necessary plugins
     */
    public void setupNecessaryPlugins() {
        /* Timber initialization */
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        /* ABP initialization */
        AndroidThreeTen.init(application.getApplicationContext());

        /* Stetho initialization */
        Stetho.initialize(initializer);

    }
}
