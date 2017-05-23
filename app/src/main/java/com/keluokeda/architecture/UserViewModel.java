package com.keluokeda.architecture;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.orhanobut.logger.Logger;

import java.util.List;


public class UserViewModel extends AndroidViewModel {

    private AppDatabase mAppDatabase;
    private UserDao mUserDao;
    private final LiveData<List<User>> mUsers;

    public UserViewModel(Application application) {
        super(application);
        mAppDatabase = AppDatabase.getAppDatabase(application);
        mUserDao = mAppDatabase.userModel();
        mUsers = mUserDao.getAllUser();
    }


    public LiveData<List<User>> getUsers() {
        return mUsers;
    }

    public void insertUser(User user) {
        long result = mUserDao.insertUser(user);
        Logger.d("insert user is " + user.toString() + " , result is " + result);
    }

    public UserDao getUserDao() {
        return mUserDao;
    }
}
