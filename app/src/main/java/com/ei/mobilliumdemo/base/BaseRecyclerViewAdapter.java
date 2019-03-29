package com.ei.mobilliumdemo.base;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.util.List;


/**
 * A base {@link RecyclerView.Adapter} that includes a method of specified types
 * @param <V> a {@link RecyclerView.ViewHolder} from adapter
 * @param <T> a model
 *
 * @author EiAppcompany
 */
public abstract class BaseRecyclerViewAdapter<T, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {

    protected List<T> data;


    /**
     * A method that sets passed model to internal one
     * @param data a remote model
     */
    public abstract void setData(@Nullable List<T> data);
}
