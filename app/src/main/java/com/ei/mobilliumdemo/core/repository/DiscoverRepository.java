package com.ei.mobilliumdemo.core.repository;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ei.mobilliumdemo.core.api.DemoApi;
import com.ei.mobilliumdemo.core.repository.base.BaseRepository;
import com.ei.mobilliumdemo.model.GenericListResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A repository layer for list section items
 *
 * @author EiAppcompany
 */
public class DiscoverRepository extends BaseRepository {


    @Inject
    DiscoverRepository(@NonNull DemoApi demoApi,
                       @NonNull Context context) {
        super(demoApi, context);
    }


    public Single<List<GenericListResponse<Object>>> getDataFromApi() {
        return getDemoApi().getDataFromApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(this::interceptGenericError);
    }

}
