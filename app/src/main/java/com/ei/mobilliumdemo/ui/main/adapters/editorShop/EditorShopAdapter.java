package com.ei.mobilliumdemo.ui.main.adapters.editorShop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ei.mobilliumdemo.BR;
import com.ei.mobilliumdemo.base.BaseRecyclerViewAdapter;
import com.ei.mobilliumdemo.base.BaseViewHolder;
import com.ei.mobilliumdemo.databinding.EditorItemLayoutBinding;
import com.ei.mobilliumdemo.model.models.editorShop.EditorShopModel;

import java.util.List;

public class EditorShopAdapter extends BaseRecyclerViewAdapter<EditorShopModel, EditorShopAdapter.EditorViewHolder> {

    int ori =0;

    public EditorShopAdapter() {

    }

    public EditorShopAdapter(int ori) {
        this.ori = ori;
    }

    @NonNull
    @Override
    public EditorShopAdapter.EditorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        EditorItemLayoutBinding profileItemLayoutBinding = EditorItemLayoutBinding.inflate(inflater, viewGroup, false);
        return new EditorShopAdapter.EditorViewHolder(profileItemLayoutBinding.getRoot());
    }

    /**
     * Binds relative ViewHolder to make items visible on the screen
     *
     * @param relatedVideoViewHolder a ViewHolder below
     * @param i                      represents position in model
     */
    @Override
    public void onBindViewHolder(@NonNull EditorShopAdapter.EditorViewHolder relatedVideoViewHolder, int i) {
        EditorShopModel profileModel = data.get(i);

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
    public void setData(@Nullable List<EditorShopModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class EditorViewHolder extends BaseViewHolder<EditorShopModel, EditorItemLayoutBinding, EditorShopAdapter.EditorItemListener> {

        /**
         * A default constructor that initializes layout
         *
         * @param itemView represents an instance of {@link View}
         */
        EditorViewHolder(@NonNull View itemView) {
            super(itemView);
            setViewForOri(ori);
        }

        @Override
        protected void bindTo(@NonNull EditorShopModel editorShopModel) {
            dataBinding.setVariable(BR.editorShopResponse, editorShopModel);
            dataBinding.executePendingBindings();
        }

        /**
         * Helps set item listener
         *
         * @param listener represents a listener
         */
        @Override
        protected void setListener(@NonNull EditorShopAdapter.EditorItemListener listener) {
            this.listener = listener;
        }
    }

    /**
     * An interface for profile items
     */
    public interface EditorItemListener {

        void onEditorItemClick(EditorShopModel categoryModel);
    }
}