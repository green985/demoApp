package com.ei.mobilliumdemo.core.repository.base;

import android.content.Context;
import android.support.annotation.NonNull;


import com.ei.mobilliumdemo.R;
import com.ei.mobilliumdemo.core.api.DemoApi;
import com.ei.mobilliumdemo.model.GenericListResponse;

import org.jetbrains.annotations.Contract;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;

/**
 * A base repository class that provides an instance of {@link DemoApi}
 *
 * @author EiAppcompany
 */
public class BaseRepository {

    private DemoApi demoApi;
    private Context context;
    /**
     * A default constructor
     *
     * @param demoApi represents an instance of {@link DemoApi}
     * @param context   represents an instance of {@link Context}
     */
    public BaseRepository(@NonNull DemoApi demoApi,
                          @NonNull Context context) {
        this.demoApi=demoApi;
        this.context = context;

    }


    /**
     * Returns an instance of {@link DemoApi}
     *
     * @return an instance of {@link DemoApi}
     */
    protected DemoApi getDemoApi() {
        return demoApi;
    }

    /**
     * Merges a response with in-case error.
     *
     * @param <T> represents any generic item
     * @return a result as {@link Single}
     */
    @Contract("null->fail")
    protected <T> Single<List<GenericListResponse<T>>> interceptGenericError(@NonNull Response<List<GenericListResponse<T>>> genericResponse) {
        int requestCode = genericResponse.code();
        if (requestCode <= 501 && requestCode >= 400) {
            return Single.error(new Throwable(context.getString(R.string.network_error_msg)));
        }

        if(genericResponse.body()==null){
            return Single.error(new Throwable(context.getString(R.string.network_error_msg)));
        }
        return Single.just(genericResponse.body());
    }
}
