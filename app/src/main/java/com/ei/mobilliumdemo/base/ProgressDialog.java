package com.ei.mobilliumdemo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ei.mobilliumdemo.R;

public class ProgressDialog extends BaseCustomDimDialogFragment {

    /**
     * A default constructor
     */
    public ProgressDialog() {
        super(R.style.Theme_Demo_Dialog);
    }

    /**
     * Inflates layout
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setCancelable(false);
        return inflater.inflate(R.layout.fragment_progress_dialog, container, false);
    }
}
