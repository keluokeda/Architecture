package com.keluokeda.architecture;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.UUID;

/**
 * 一个Activity下的多个Fragment 共享数据
 */
public class ShareViewModelActivity extends AppCompatActivity {
    private ShareViewModel mShareViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_view_model);
        mShareViewModel = ViewModelProviders.of(this).get(ShareViewModel.class);

        getSupportFragmentManager().beginTransaction().add(R.id.container0,new ShareFragment())
                .add(R.id.container1,new ShareFragment()).commit();
    }


    public void change(View view) {
        mShareViewModel.getStringMutableLiveData().setValue(UUID.randomUUID().toString());
    }


    public static final class ShareFragment extends LifecycleFragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


            View view = inflater.inflate(R.layout.fragment_text, container, false);
            final TextView textView = (TextView) view.findViewById(R.id.tv_content);
            ShareViewModel shareViewModel = ViewModelProviders.of(getActivity()).get(ShareViewModel.class);
            shareViewModel.getStringMutableLiveData().observe(this, new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    textView.setText(ShareFragment.this.toString()+"=="+s);
                }
            });
            return view;
        }
    }


}
