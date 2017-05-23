package com.keluokeda.architecture;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.Random;

/**
 * 简单的数据使用{@link #onSaveInstanceState(Bundle)}保存，
 * 复杂的使用{@link android.arch.lifecycle.ViewModel}保存
 */
public class ViewModelActivity extends LifecycleActivity {
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model);

        mTextView = (TextView) findViewById(R.id.tv_content);

        IntegerViewModel integerViewModel = ViewModelProviders.of(this).get(IntegerViewModel.class);

        Logger.d("integerViewModel toString = "+integerViewModel.toString());
        if (integerViewModel.getInteger() != null) {

            int value = integerViewModel.getInteger();
            mTextView.setText(String.format("value is %d",value));
            Logger.d("get value from viewmodel "+value);
        }else {
            int value = new Random().nextInt(10);
            mTextView.setText(String.format("value is %d",value));
            integerViewModel.setInteger(value);
            Logger.d("set value to viewmodel "+value);
        }
    }



    public void add(View view){
        recreate();
    }
}
