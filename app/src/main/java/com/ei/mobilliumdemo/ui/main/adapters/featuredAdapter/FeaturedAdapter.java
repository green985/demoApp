package com.ei.mobilliumdemo.ui.main.adapters.featuredAdapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.ei.mobilliumdemo.BR;
import com.ei.mobilliumdemo.base.BasePagerAdapter;
import com.ei.mobilliumdemo.databinding.FeaturedSliderLayoutBinding;
import com.ei.mobilliumdemo.model.models.featured.FeaturedModel;
import java.util.List;

public class FeaturedAdapter extends BasePagerAdapter {

    private List<FeaturedModel> models;
    public FeaturedAdapter(@NonNull List<FeaturedModel> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(collection.getContext());
        FeaturedSliderLayoutBinding homeSliderItemLayoutBinding =
                FeaturedSliderLayoutBinding.inflate(inflater, collection, false);
        collection.addView(homeSliderItemLayoutBinding.getRoot());

        FeaturedModel featuredModel = models.get(position);
        homeSliderItemLayoutBinding.setVariable(BR.featuredModel, featuredModel);
        homeSliderItemLayoutBinding.executePendingBindings();

        return homeSliderItemLayoutBinding.getRoot();
    }

    @Override
    protected int getItemCount() {
        return models.size();
    }
}
