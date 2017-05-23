package com.keluokeda.architecture;

import android.arch.lifecycle.ViewModel;



public class IntegerViewModel extends ViewModel {
    private Integer mInteger;


    public Integer getInteger() {
        return mInteger;
    }

    public void setInteger(Integer integer) {
        mInteger = integer;
    }
}
