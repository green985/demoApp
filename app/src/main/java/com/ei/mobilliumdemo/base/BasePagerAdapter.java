package com.ei.mobilliumdemo.base;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * A base {@link PagerAdapter} that creates necessary implementations on its own.
 *
 * @author EiAppcompany
 */
public abstract class BasePagerAdapter extends PagerAdapter {

    /**
     * Destroys views when they are no longer needed
     *
     * @param collection main container that places each layout into its own
     * @param position   for each layout that was inflated
     * @param view       represents each view that was inflated
     */

    @Override
    public void destroyItem(@NonNull ViewGroup collection, int position, @NonNull Object view) {
        collection.removeView((View) view);
    }

    /**
     * Returns item count
     *
     * @return item count
     */
    @Override
    public int getCount() {
        return getItemCount();
    }

    /**
     * Determines whether a page View is associated with a specific key object as returned by instantiateItem(ViewGroup, int)
     *
     * @param view   represents view
     * @param object represents remote view
     * @return {@code true} if view = object, {@code false} otherwise
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    /**
     * Saves the state of each fragment
     *
     * @return a {@link Parcelable}
     */
    @Override
    public Parcelable saveState() {
        Bundle bundle = (Bundle) super.saveState();
        if (bundle != null) {
            bundle.putParcelableArray("states", null); /* Never maintain any states from the base class, just null it out */
        }
        return bundle;
    }

    /**
     * Returns item count
     *
     * @return item count
     */
    protected abstract int getItemCount();
}
