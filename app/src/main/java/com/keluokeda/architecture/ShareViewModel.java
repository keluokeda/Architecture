package com.keluokeda.architecture;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.orhanobut.logger.Logger;


public class ShareViewModel extends ViewModel {
    private MutableLiveData<String> mStringMutableLiveData = new MutableLiveData<>();

    public void setValue(String value){
        mStringMutableLiveData.setValue(value);
    }

    public MutableLiveData<String> getStringMutableLiveData(){
        return mStringMutableLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Logger.d("onCleared");
    }
}
