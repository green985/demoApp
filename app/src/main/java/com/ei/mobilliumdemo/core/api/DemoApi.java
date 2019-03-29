package com.ei.mobilliumdemo.core.api;

import com.ei.mobilliumdemo.model.GenericListResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * The main services that handles all endpoint processes
 *
 * @author EiAppcompany
 */
public interface DemoApi {


    @GET("discover")
    Single<Response<List<GenericListResponse<Object>>>> getDataFromApi();

}
