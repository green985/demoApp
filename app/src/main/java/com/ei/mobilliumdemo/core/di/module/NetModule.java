package com.ei.mobilliumdemo.core.di.module;

import android.support.annotation.NonNull;

import com.ei.mobilliumdemo.BuildConfig;
import com.ei.mobilliumdemo.core.api.DemoApi;
import com.ei.mobilliumdemo.core.helper.Constants;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A {@link Module} that handles network injections
 *
 * @author EiAppcompany
 */
@Module
public class NetModule {

    /**
     * Returns an instance of Gson
     *
     * @return an instance of {@link Gson}
     */
    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .serializeNulls()
                .setLenient()
                .create();
    }

    /**
     * Returns an instance of {@link HttpLoggingInterceptor}
     *
     * @return an instance of {@link HttpLoggingInterceptor}
     */
    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(@NonNull HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClient.readTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClient.writeTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        httpClient.interceptors().add(httpLoggingInterceptor);
        httpClient.interceptors().add(new StethoInterceptor());

        return httpClient.build();
    }


    /**
     * Returns an instance of {@link Retrofit}
     *
     * @param okHttpClient represents an instance of {@link OkHttpClient}
     * @param gson         represents an instance of {@link Gson}
     * @return an instance of {@link Retrofit}
     */
    @Provides
    @Singleton
    Retrofit provideRetrofit(@NonNull OkHttpClient okHttpClient, @NonNull Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    /**
     * Returns an instance of DemoApi
     *
     * @param retrofit represents an instance of {@link DemoApi}
     * @return an instance of {@link DemoApi}
     */
    @Provides
    @Singleton
    DemoApi provideIDemoService(@NonNull Retrofit retrofit) {
        return retrofit.create(DemoApi.class);
    }
}
