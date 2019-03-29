package com.ei.mobilliumdemo.binding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.ei.mobilliumdemo.base.BaseProgress;
import com.ei.mobilliumdemo.core.di.module.DemoGlide;

import javax.inject.Inject;

/**
 * A {@link BindingAdapter} that binds several types of image into views.
 *
 * @author EiAppcompany
 */
public class ImageBindingAdapter {

    /**
     * A default constructor that injects dependencies
     */
    @Inject
    public ImageBindingAdapter() {
    }

    /**
     * Binds drawable image into view
     *
     * @param target represents target view
     * @param resId  represents the image as drawable
     */
    @BindingAdapter(value = "android:src")
    public void bindDrawableImage(ImageView target, @DrawableRes int resId) {
        DemoGlide.with(target.getContext())
                .asDrawable()
                .load(resId)
                .into(target);
    }

    /**
     * Binds drawable image into view
     *
     * @param target represents target view
     * @param url    represents the remote path
     */
    @BindingAdapter(value = "android:src")
    public void bindRemoteImage(ImageView target, String url) {
        if (url == null || TextUtils.isEmpty(url)) {
            DemoGlide.with(target.getContext()).clear(target);
        }

        DemoGlide.with(target.getContext())
                .asDrawable()
                .placeholder(new BaseProgress(target.getContext()))
                .load(url)
                .into(target);

    }@BindingAdapter(value = "android:src")
    public void bindBackground(ConstraintLayout target, String url) {
        if (url == null || TextUtils.isEmpty(url)) {
            DemoGlide.with(target.getContext()).clear(target);
        }
        Log.d("state_change","bindBackgorundGirdi");
        Log.d("state_change","url="+url);
        DemoGlide.with(target.getContext())
                .asDrawable()
                .placeholder(new BaseProgress(target.getContext()))
                .load(url)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            target.setBackground(resource);
                            Log.d("state_change","tamamlandı.");
                        }
                    }
                });

    }
}
