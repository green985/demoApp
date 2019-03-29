package com.ei.mobilliumdemo.ui.main.adapters.products;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ei.mobilliumdemo.BR;
import com.ei.mobilliumdemo.R;
import com.ei.mobilliumdemo.base.BaseRecyclerViewAdapter;
import com.ei.mobilliumdemo.base.BaseViewHolder;
import com.ei.mobilliumdemo.databinding.ProductItemLayoutBinding;
import com.ei.mobilliumdemo.model.models.product.ProductModel;

import java.util.List;

public class ProductsAdapter extends BaseRecyclerViewAdapter<ProductModel, ProductsAdapter.ProfileViewHolder> {

    int ori =0;

    public ProductsAdapter() {

    }

    public ProductsAdapter(int ori) {
        this.ori = ori;
    }

    @NonNull
    @Override
    public ProductsAdapter.ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        ProductItemLayoutBinding profileItemLayoutBinding = ProductItemLayoutBinding.inflate(inflater, viewGroup, false);
        return new ProductsAdapter.ProfileViewHolder(profileItemLayoutBinding.getRoot());
    }

    /**
     * Binds relative ViewHolder to make items visible on the screen
     *
     * @param relatedVideoViewHolder a ViewHolder below
     * @param i                      represents position in model
     */
    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ProfileViewHolder relatedVideoViewHolder, int i) {
        ProductModel profileModel = data.get(i);

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
    public void setData(@Nullable List<ProductModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    /**
     * A {@link RecyclerView.ViewHolder} that binds items into view components
     */
    class ProfileViewHolder extends BaseViewHolder<ProductModel, ProductItemLayoutBinding, ProductItemListener> {

        /**
         * A default constructor that initializes layout
         *
         * @param itemView represents an instance of {@link View}
         */
        ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            setViewForOri(ori);
        }

        /**
         * Helps bind model into view components
         *
         * @param productModel represents {@link ProductModel}
         */
        @Override
        protected void bindTo(@NonNull ProductModel productModel) {
            dataBinding.setVariable(BR.productResponse, productModel);
            if(productModel.getOldPrice()!=0){
                dataBinding.oldPrice.setText(String.format(itemView.getContext().getString(R.string.tl_format),productModel.getOldPrice()));
                dataBinding.oldPrice.setPaintFlags(dataBinding.oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }else{
                dataBinding.oldPrice.setVisibility(View.GONE);
            }
            dataBinding.executePendingBindings();
        }

        /**
         * Helps set item listener
         *
         * @param listener represents a listener
         */
        @Override
        protected void setListener(@NonNull ProductItemListener listener) {
            this.listener = listener;
        }
    }

    /**
     * An interface for profile items
     */
    public interface ProductItemListener {

        void onProductItemClick(ProductModel productModel);
    }
}