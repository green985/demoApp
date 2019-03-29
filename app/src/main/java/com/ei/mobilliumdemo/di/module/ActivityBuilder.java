package com.ei.mobilliumdemo.di.module;

import com.ei.mobilliumdemo.ui.allActivity.ExploreAllActivity;
import com.ei.mobilliumdemo.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * A {@link Module} that injects all activities
 *
 * @author EiAppcompany
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract MainActivity contributesMainActivityInjector();
    @ContributesAndroidInjector
    abstract ExploreAllActivity contributesExploreAllActivityInjector();


}
