package com.ei.mobilliumdemo.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;

import com.ei.mobilliumdemo.core.util.widget.CustomDimDialog;


/**
 * A base Dim Dialog class that extends {@link AppCompatDialogFragment}
 * This class helps shows any type of dialog on the screen.
 *
 * @author EiAppcompany
 */
public abstract class BaseCustomDimDialogFragment extends AppCompatDialogFragment {

    private int mTheme;

    /**
     * A default constructor that specifies theme
     *
     * @param theme represents any theme
     */
    public BaseCustomDimDialogFragment(int theme) {
        this.mTheme = theme;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new CustomDimDialog(getContext(), mTheme);
    }
}
