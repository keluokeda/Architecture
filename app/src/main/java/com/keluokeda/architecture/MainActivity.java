package com.keluokeda.architecture;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void viewModel(View view) {
        startActivity(new Intent(this,ViewModelActivity.class));
    }

    public void LiveData(View view){
        startActivity(new Intent(this,LiveDataActivity.class));
    }


    public void ShareViewModel(View view){
        startActivity(new Intent(this,ShareViewModelActivity.class));
    }

    public void LifeCycle(View view){
        startActivity(new Intent(this,LifeCycleActivity.class));
    }

    public void room(View view){
        startActivity(new Intent(this,RoomActivity.class));
    }

}
