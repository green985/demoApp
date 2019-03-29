package com.ei.mobilliumdemo.core.util.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * A {@link ViewPager} which doesn't support swiping
 *
 * @author EiAppcompany
 */
public class NonSwipeableViewPager extends ViewPager {

    private boolean enabled;

    /**
     * A default constructor
     *
     * @param context a {@link Context}
     */
    public NonSwipeableViewPager(@NonNull Context context) {
        super(context);
        init();
    }

    /**
     * A default constructor
     *
     * @param context a {@link Context}
     * @param attrs   if possible an {@link AttributeSet}
     */
    public NonSwipeableViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * Initializes the view with the necessary declarations
     */
    private void init() {
        this.enabled = true;
    }

    /**
     * This method determines if there this ViewPager should allow swiping
     *
     * @param ev a {@link MotionEvent}
     * @return true or false
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (this.enabled) {
            return super.onTouchEvent(ev);
        }

        return false;
    }

    /**
     * This method JUST determines whether we want to intercept the motion.
     * If we return true, onMotionEvent will be called and we do the actual
     * scrolling there.
     *
     * @param ev a {@link MotionEvent}
     * @return true or false
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (this.enabled) {
            return super.onInterceptTouchEvent(ev);
        }

        return false;
    }

    /**
     * A method which determines whether or not this ViewPager should have a swiping functionality
     *
     * @param enabled default true, if set to false swiping will be disabled
     */
    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
