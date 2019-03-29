package com.ei.mobilliumdemo.ui.main.adapters.newShop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ei.mobilliumdemo.BR;
import com.ei.mobilliumdemo.base.BaseRecyclerViewAdapter;
import com.ei.mobilliumdemo.base.BaseViewHolder;
import com.ei.mobilliumdemo.databinding.NewShopItemLayoutBinding;
import com.ei.mobilliumdemo.model.models.editorShop.EditorShopModel;
import com.google.gson.Gson;

import java.util.List;

public class NewShopAdapter extends BaseRecyclerViewAdapter<EditorShopModel, NewShopAdapter.NewShopViewHolder> {

    int ori =0;

    public NewShopAdapter() {

    }

    public NewShopAdapter(int ori) {
        this.ori = ori;
    }

    @NonNull
    @Override
    public NewShopAdapter.NewShopViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        NewShopItemLayoutBinding newShopItemLayoutBinding = NewShopItemLayoutBinding.inflate(inflater, viewGroup, false);
        return new NewShopAdapter.NewShopViewHolder(newShopItemLayoutBinding.getRoot());
    }

    /**
     * Binds relative ViewHolder to make items visible on the screen
     *
     * @param relatedVideoViewHolder a ViewHolder below
     * @param i                      represents position in model
     */
    @Override
    public void onBindViewHolder(@NonNull NewShopAdapter.NewShopViewHolder relatedVideoViewHolder, int i) {
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

    class NewShopViewHolder extends BaseViewHolder<EditorShopModel, NewShopItemLayoutBinding, NewShopAdapter.NewShopItemListener> {

        /**
         * A default constructor that initializes layout
         *
         * @param itemView represents an instance of {@link View}
         */
        NewShopViewHolder(@NonNull View itemView) {
            super(itemView);
            setViewForOri(ori);
        }

        @Override
        protected void bindTo(@NonNull EditorShopModel editorShopModel) {
            dataBinding.setVariable(BR.newShopResponse, editorShopModel);
            Log.d("deneme_bastı","=="+new Gson().toJson(editorShopModel));
            dataBinding.executePendingBindings();
        }

        /**
         * Helps set item listener
         *
         * @param listener represents a listener
         */
        @Override
        protected void setListener(@NonNull NewShopAdapter.NewShopItemListener listener) {
            this.listener = listener;
        }
    }

    /**
     * An interface for profile items
     */
    public interface NewShopItemListener {

        void onNewShopItemClick(EditorShopModel categoryModel);
    }
}