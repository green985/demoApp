package com.ei.mobilliumdemo.core.util.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * A responsive {@link GridLayoutManager} that behaves according to the width
 *
 * @author EiAppcompany
 */
public class ResponsiveGridLayoutManager extends GridLayoutManager {

    private int minItemWidth;

    /**
     * A default constructor specifies minimum width
     *
     * @param context      represents an instance of {@link Context}
     * @param minItemWidth indicates the minimum width
     */
    public ResponsiveGridLayoutManager(Context context, int minItemWidth) {
        super(context, 1);
        this.minItemWidth = minItemWidth;
    }

    /**
     * Re-sets the settings after layout update
     *
     * @param recycler represents an instance of {@link RecyclerView}
     * @param state    indicates recyclerview state
     */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler,
                                 RecyclerView.State state) {
        updateSpanCount();
        super.onLayoutChildren(recycler, state);
    }

    /**
     * Updates the span count
     */
    private void updateSpanCount() {

        int spanCount = getWidth() / minItemWidth;
        if (spanCount < 1) {
            spanCount = 1;
        }
        this.setSpanCount(spanCount);
    }
}
