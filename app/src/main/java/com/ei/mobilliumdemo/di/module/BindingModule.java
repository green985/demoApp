package com.ei.mobilliumdemo.di.module;


import android.databinding.BindingAdapter;
import com.ei.mobilliumdemo.binding.ImageBindingAdapter;
import com.ei.mobilliumdemo.core.di.qualifier.DataBinding;
import dagger.Module;
import dagger.Provides;

/**
 * A module that handles {@link BindingAdapter} classes as generic.
 *
 * @author EiAppcompany
 */
@Module
public class BindingModule {

    /**
     * Returns an instance of {@link ImageBindingAdapter}
     *
     * @return an instance of {@link ImageBindingAdapter}
     */
    @DataBinding
    @Provides
    ImageBindingAdapter bindsImageBindingAdapter() {
        return new ImageBindingAdapter();
    }
}
