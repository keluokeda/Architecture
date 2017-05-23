package com.keluokeda.architecture;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import java.util.UUID;

public class LiveDataActivity extends LifecycleActivity {

    private MutableLiveData<String> mStringMutableLiveData = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);

        final TextView textView = (TextView) findViewById(R.id.tv_content);
        mStringMutableLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

    }

    public void change(View view){
        String uuid = UUID.randomUUID().toString();
        mStringMutableLiveData.postValue(uuid);
    }
}
