package com.ei.mobilliumdemo.di.component;

import android.databinding.DataBindingComponent;

import com.ei.mobilliumdemo.core.di.qualifier.DataBinding;
import com.ei.mobilliumdemo.di.module.BindingModule;

import dagger.Component;

/**
 * A component for {@link android.databinding.DataBindingUtil} that gets {@link AppComponent} as dependency.
 *
 * @author EiAppcompany
 */
@DataBinding
@Component(dependencies = AppComponent.class, modules = BindingModule.class)
public interface BindingComponent extends DataBindingComponent {}
