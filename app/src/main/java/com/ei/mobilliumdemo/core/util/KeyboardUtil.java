package com.ei.mobilliumdemo.core.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.jetbrains.annotations.Contract;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * A class that handles keyboard processes such as hiding and showing
 *
 * @author EiAppcompany
 */
@Singleton
public class KeyboardUtil {

    private InputMethodManager mInputMethodManager;

    @Inject
    @Contract("null->fail")
    public KeyboardUtil(@NonNull Context context) {
        mInputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
    }

    /**
     * Hides keyboard from screen
     *
     * @param activity that has the functionality
     */
    @Contract("null->fail")
    public void hideKeyboard(@NonNull Activity activity) {
        if (activity.getCurrentFocus() != null) {
            assert mInputMethodManager != null;
            mInputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * Hides keyboard from screen
     *
     * @param view represents any {@link View}
     */
    @Contract("null->fail")
    public void hideKeyboard(@NonNull View view) {
        assert mInputMethodManager != null;
        mInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Shows keyboard on screen
     */
    public void showKeyboard() {
        if (mInputMethodManager != null) {
            mInputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }
}
