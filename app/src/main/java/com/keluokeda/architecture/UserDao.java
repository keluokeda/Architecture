package com.keluokeda.architecture;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUser();

    //如果主键重复 返回-1
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertUser(User user);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    int updateUser(User user);

    @Delete
    int deleteUser(User user);

    @Query("SELECT * FROM user WHERE name LIKE :search OR sign LIKE :search")
    List<User> findUserWithName(String search);
}
