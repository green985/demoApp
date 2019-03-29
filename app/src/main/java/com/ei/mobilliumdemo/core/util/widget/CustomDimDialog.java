package com.ei.mobilliumdemo.core.util.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;


import com.ei.mobilliumdemo.R;

import org.jetbrains.annotations.Contract;

import java.util.Objects;

/**
 * A custom dialog that can be customised in accordance with different requests.
 * For example, a {@link android.widget.ProgressBar} or {@link android.support.v7.app.AlertDialog} styled.
 *
 * @author EiAppcompany
 */
public class CustomDimDialog extends AppCompatDialog {

    /**
     * A default constructor
     *
     * @param context a {@link Context}
     */
    public CustomDimDialog(Context context) {
        super(context);
        init();
    }

    /**
     * A default constructor
     *
     * @param context a {@link Context}
     * @param theme   a specified theme by user
     */
    public CustomDimDialog(Context context, int theme) {
        super(context, theme);
        init();
    }

    /**
     * A default constructor
     *
     * @param context    a {@link Context}
     * @param cancelable indicates whether or not dialog can be cancellable
     */
    protected CustomDimDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    /**
     * A method that initializes dialog with its attributes
     */
    private void init() {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    /**
     * Handles first run things
     *
     * @param savedInstanceState no need for a static orientation
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().post(() -> {
            Objects.requireNonNull(getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            }
            getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        });
    }

    /**
     * Sets content
     *
     * @param view represents view to be set
     */
    @Override
    public void setContentView(View view) {
        if (view != null) {
            super.setContentView(wrap(view));
        }
    }

    /**
     * A method that prepares and manipulates its main content and returns a {@link View}
     *
     * @param content a {@link View}
     * @return a {@link View}
     */
    @Contract("null->fail")
    private View wrap(@NonNull View content) {
        Resources res = getContext().getResources();
        int verticalMargin = res.getDimensionPixelSize(R.dimen.dialog_vertical_margin);
        int horizontalMargin = res.getDimensionPixelSize(R.dimen.dialog_horizontal_margin);
        FrameLayout frameLayout = new FrameLayout(getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        layoutParams.setMargins(horizontalMargin, verticalMargin, horizontalMargin, verticalMargin);
        frameLayout.addView(content, layoutParams);
        frameLayout.setBackground(new ColorDrawable(ResourcesCompat.getColor(res, R.color.scrim, getContext().getTheme())));

        return frameLayout;
    }
}
