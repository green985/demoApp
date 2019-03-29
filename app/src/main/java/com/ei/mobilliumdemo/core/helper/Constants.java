package com.ei.mobilliumdemo.core.helper;

import com.google.gson.Gson;

import org.jetbrains.annotations.Contract;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * A utility class for application constants
 *
 * @author EiAppcompany
 */
public final class Constants {

    public static final String DEMO_PREF_NAME = "Demo_pref";
    public static final int DEFAULT_TIMEOUT = 60;
    public static final int EXECUTOR_THREAD_POOL_OFFSET = 5;
    public static final String DIALOG_TITLE = "title";





    public static final String EXPLORE_ALL = "explore_all";
    public static final String TYPE_FEATURED = "featured";
    public static final String TYPE_NEW_PRODUCT = "new_products";
    public static final String TYPE_CATEGORIES= "categories";
    public static final String TYPE_COLLECTION= "collections";
    public static final String TYPE_EDITOR_SHOP= "editor_shops";
    public static final String TYPE_NEW_SHOP= "new_shops";
    public static final int SPEECH_INPUT = 101 ;


    //Dinamic List Transformation
    public static <T> List<T> objectToList(Object object, Class<T[]> clazz) {
        Gson gson = new Gson();
        String s = gson.toJson(object);
        T[] arr = new Gson().fromJson(s, clazz);
        return Arrays.asList(arr); //or return Arrays.asList(new Gson().fromJson(s, clazz)); for a one-liner
    }



    @Contract(" -> fail")
    public Constants() {
        throw new AssertionError();
    }

    public final static boolean isValidEmail(CharSequence target) {

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();

    }
}
