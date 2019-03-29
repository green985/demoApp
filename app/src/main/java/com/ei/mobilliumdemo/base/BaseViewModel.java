package com.ei.mobilliumdemo.base;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;

/**
 * A base {@link ViewModel} for classes which extends by {@link ViewModel}
 * not {@link android.arch.lifecycle.AndroidViewModel}
 *
 * @author EiAppcompany
 */
public abstract class BaseViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable;

    /**
     * A default constructor that gets dependencies
     *
     * @param compositeDisposable represents an instance of {@link CompositeDisposable}
     */
    public BaseViewModel(@NonNull CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
    }

    /**
     * Returns {@link BaseViewModel#compositeDisposable}
     *
     * @return {@link BaseViewModel#compositeDisposable}
     */
    protected CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    /**
     * Clears references
     */
    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
