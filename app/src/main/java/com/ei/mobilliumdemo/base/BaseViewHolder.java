package com.ei.mobilliumdemo.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseViewHolder<Data, DB extends ViewDataBinding, L> extends RecyclerView.ViewHolder {

    protected DB dataBinding;
    protected L listener;

    /**
     * A default constructor that initializes layout
     *
     * @param itemView represents an instance of {@link View}
     */
    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);

        dataBinding = DataBindingUtil.getBinding(itemView);
    }

    public void setViewForOri(int ori){
        if(ori == 1){
            itemView.getRootView().setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    /**
     * Helps bind model into view components
     *
     * @param data represents a model
     */
    protected abstract void bindTo(@NonNull Data data);

    /**
     * Helps set item listener
     *
     * @param listener represents a listener
     */
    protected abstract void setListener(@NonNull L listener);
}
