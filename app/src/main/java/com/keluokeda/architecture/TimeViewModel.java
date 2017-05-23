package com.keluokeda.architecture;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;



public class TimeViewModel extends ViewModel {

    private MutableLiveData<Integer> mIntegerMutableLiveData = new MutableLiveData<>();

    public TimeViewModel() {
    }

    public MutableLiveData<Integer> getIntegerMutableLiveData() {
        return mIntegerMutableLiveData;
    }
}
