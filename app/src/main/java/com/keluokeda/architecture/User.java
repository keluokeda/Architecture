package com.keluokeda.architecture;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {


    private
    @PrimaryKey
    String name;
    private String sign;

    public User() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
