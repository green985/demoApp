package com.ei.mobilliumdemo.ui.main.adapters.collection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ei.mobilliumdemo.BR;
import com.ei.mobilliumdemo.base.BaseRecyclerViewAdapter;
import com.ei.mobilliumdemo.base.BaseViewHolder;
import com.ei.mobilliumdemo.databinding.CollectionItemLayoutBinding;
import com.ei.mobilliumdemo.model.models.collection.CollectionModel;

import java.util.List;

public class CollectionAdapter extends BaseRecyclerViewAdapter<CollectionModel, CollectionAdapter.CollectionViewHolder> {

    int ori =0;

    public CollectionAdapter() {

    }

    public CollectionAdapter(int ori) {
        this.ori = ori;
    }
    @NonNull
    @Override
    public CollectionAdapter.CollectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        CollectionItemLayoutBinding profileItemLayoutBinding = CollectionItemLayoutBinding.inflate(inflater, viewGroup, false);
        return new CollectionAdapter.CollectionViewHolder(profileItemLayoutBinding.getRoot());
    }

    /**
     * Binds relative ViewHolder to make items visible on the screen
     *
     * @param relatedVideoViewHolder a ViewHolder below
     * @param i                      represents position in model
     */
    @Override
    public void onBindViewHolder(@NonNull CollectionAdapter.CollectionViewHolder relatedVideoViewHolder, int i) {
        CollectionModel profileModel = data.get(i);

        if (profileModel != null) {
            relatedVideoViewHolder.bindTo(profileModel);
        }
    }

    /**
     * Returns item count
     *
     * @return item count
     */
    @Override
    public int getItemCount() {
        if (data == null) return 0;

        return data.size();
    }

    /**
     * A method that sets passed model to internal one
     *
     * @param data a remote model
     */
    @Override
    public void setData(@Nullable List<CollectionModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class CollectionViewHolder extends BaseViewHolder<CollectionModel, CollectionItemLayoutBinding, CollectionAdapter.CategoryItemListener> {

        /**
         * A default constructor that initializes layout
         *
         * @param itemView represents an instance of {@link View}
         */
        CollectionViewHolder(@NonNull View itemView) {
            super(itemView);
            setViewForOri(ori);
        }

        @Override
        protected void bindTo(@NonNull CollectionModel collectionModel) {
            dataBinding.setVariable(BR.collectionResponse, collectionModel);
            dataBinding.executePendingBindings();
        }

        /**
         * Helps set item listener
         *
         * @param listener represents a listener
         */
        @Override
        protected void setListener(@NonNull CollectionAdapter.CategoryItemListener listener) {
            this.listener = listener;
        }
    }

    /**
     * An interface for profile items
     */
    public interface CategoryItemListener {

        void onCategoryItemClick(CollectionModel categoryModel);
    }
}