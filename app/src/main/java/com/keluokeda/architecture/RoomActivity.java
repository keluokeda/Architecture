package com.keluokeda.architecture;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.orhanobut.logger.Logger;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoomActivity extends LifecycleActivity {

    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_sign)
    EditText mEtSign;
    @BindView(R.id.brn_insert)
    Button mBrnInsert;
    @BindView(R.id.lv_content)
    ListView mLvContent;
    @BindView(R.id.et_search)
    EditText mEtSearch;

    private UserViewModel mUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        ButterKnife.bind(this);

        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        mUserViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                initAdapter(users);
            }
        });

    }

    public void search(View view) {
        String search = mEtSearch.getText().toString();
        List<User> users = mUserViewModel.getUserDao().findUserWithName(search);
        initAdapter(users);
    }


    private void initAdapter(final List<User> users) {
        if (users == null) {
            return;
        }
        CommonAdapter<User> commonAdapter = new CommonAdapter<User>(this, R.layout.item_user, users) {
            @Override
            protected void convert(ViewHolder viewHolder, User item, int position) {
                viewHolder.setText(R.id.tv_name, item.getName())
                        .setText(R.id.tv_sign, item.getSign());
            }
        };
        mLvContent.setAdapter(commonAdapter);
        Logger.d("new items coming " + users.toString());

        mLvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialog(users.get(position));
            }
        });


    }


    private void showDialog(final User user) {
        View view = View.inflate(this, R.layout.layout_dialog, null);
        final EditText etName = (EditText) view.findViewById(R.id.et_name);
        final EditText etSign = (EditText) view.findViewById(R.id.et_sign);
        etName.setText(user.getName());
        etSign.setText(user.getSign());
        new AlertDialog.Builder(this).setTitle("操作").setView(view).setPositiveButton("更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                user.setName(etName.getText().toString());
                user.setSign(etSign.getText().toString());
                mUserViewModel.getUserDao().updateUser(user);
                dialog.dismiss();
            }
        }).setNegativeButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mUserViewModel.getUserDao().deleteUser(user);
                dialog.dismiss();
            }
        }).show();
    }

    @OnClick(R.id.brn_insert)
    public void onViewClicked() {
        String name = mEtName.getText().toString();
        String sign = mEtSign.getText().toString();

        User user = new User();
        user.setName(name);
        user.setSign(TextUtils.isEmpty(sign) ? UUID.randomUUID().toString() : sign);

        mUserViewModel.insertUser(user);
        mEtName.setText(null);
        mEtSign.setText(null);
    }


}
