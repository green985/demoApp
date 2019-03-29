package com.ei.mobilliumdemo.core.di.module;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * A {@link Module} for {@link Context}
 *
 * @author EiAppcompany
 */
@Module
public abstract class ContextModule {

    /**
     * Provides {@link Context}
     *
     * @param application an instance of {@link Application}
     * @return a context
     */
    @Binds
    abstract Context bindsContext(Application application);
}
