package com.ei.mobilliumdemo.ui.main.adapters.category;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ei.mobilliumdemo.BR;
import com.ei.mobilliumdemo.base.BaseRecyclerViewAdapter;
import com.ei.mobilliumdemo.base.BaseViewHolder;
import com.ei.mobilliumdemo.databinding.CategoryItemLayoutBinding;
import com.ei.mobilliumdemo.model.models.category.CategoryModel;

import java.util.List;

public class CategoryAdapter extends BaseRecyclerViewAdapter<CategoryModel, CategoryAdapter.ProfileViewHolder> {

    private CategoryAdapter.CategoryItemListener categoryItemListener;

    public CategoryAdapter(CategoryAdapter.CategoryItemListener categoryItemListener) {
        this.categoryItemListener = categoryItemListener;
    }

    @NonNull
    @Override
    public CategoryAdapter.ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        CategoryItemLayoutBinding profileItemLayoutBinding = CategoryItemLayoutBinding.inflate(inflater, viewGroup, false);
        return new CategoryAdapter.ProfileViewHolder(profileItemLayoutBinding.getRoot());
    }

    /**
     * Binds relative ViewHolder to make items visible on the screen
     *
     * @param relatedVideoViewHolder a ViewHolder below
     * @param i                      represents position in model
     */
    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ProfileViewHolder relatedVideoViewHolder, int i) {
        CategoryModel profileModel = data.get(i);

        if (profileModel != null) {
            relatedVideoViewHolder.setListener(categoryItemListener);
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
    public void setData(@Nullable List<CategoryModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class ProfileViewHolder extends BaseViewHolder<CategoryModel, CategoryItemLayoutBinding, CategoryAdapter.CategoryItemListener> {

        /**
         * A default constructor that initializes layout
         *
         * @param itemView represents an instance of {@link View}
         */
        ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        protected void bindTo(@NonNull CategoryModel categoryModel) {
            dataBinding.setVariable(BR.categoryResponse, categoryModel);
            dataBinding.executePendingBindings();
        }

        /**
         * Helps set item listener
         *
         * @param listener represents a listener
         */
        @Override
        protected void setListener(@NonNull CategoryAdapter.CategoryItemListener listener) {
            this.listener = listener;
        }
    }

    /**
     * An interface for profile items
     */
    public interface CategoryItemListener {

        void onCategoryItemClick(CategoryModel categoryModel);
    }
}