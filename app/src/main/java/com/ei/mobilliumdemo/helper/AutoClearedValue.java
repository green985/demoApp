package com.ei.mobilliumdemo.helper;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * A generic class that gets a Fragment instance and dispose its values onDestroy automatically.
 * @param <T> a {@link Fragment}
 *
 * @author EiAppcompany
 */
public class AutoClearedValue<T> {
    private T value;

    /**
     * Handles auto clearing when target Fragment is destroyed
     *
     * @param fragment a fragment
     * @param value    a DataBinding value
     */
    public AutoClearedValue(@NonNull Fragment fragment, @NonNull T value) {
        FragmentManager fragmentManager = fragment.getFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.registerFragmentLifecycleCallbacks(
                    new FragmentManager.FragmentLifecycleCallbacks() {
                        @Override
                        public void onFragmentViewDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
                            if (f == fragment) {
                                AutoClearedValue.this.value = null;
                                fragmentManager.unregisterFragmentLifecycleCallbacks(this);
                            }
                        }
                    }, false);
        }

        this.value = value;
    }

    /**
     * Returns T
     *
     * @return T
     */
    public T get() {
        return value;
    }
}
