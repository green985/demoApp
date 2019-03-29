package com.ei.mobilliumdemo.ui.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ei.mobilliumdemo.base.BaseViewModel;
import com.ei.mobilliumdemo.core.repository.DiscoverRepository;
import com.ei.mobilliumdemo.model.GenericListResponse;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * A {@link ViewModel} for {@link MainActivity}
 *
 * @author EiAppcompany
 */
public class MainViewModel extends BaseViewModel {


    private DiscoverRepository discoverRepository ;


    private MutableLiveData<List<GenericListResponse<Object>>> listMutableLiveData = new MutableLiveData<>();

    @Inject
    MainViewModel(@NonNull CompositeDisposable compositeDisposable,
    DiscoverRepository discoverRepository) {
        super(compositeDisposable);
        this.discoverRepository=discoverRepository;
    }


    public void getData(){
        Disposable disposable = discoverRepository.getDataFromApi().subscribe(this::getDataSuccess, this::getDataError);
        getCompositeDisposable().add(disposable);
    }

    private void getDataSuccess(List<GenericListResponse<Object>> genericResponses) {
        if(genericResponses !=null){
            listMutableLiveData.setValue(genericResponses);
        }
    }

    public MutableLiveData<List<GenericListResponse<Object>>> getListMutableLiveData() {
        return listMutableLiveData;
    }
    private void getDataError(Throwable throwable) {

    }
}
