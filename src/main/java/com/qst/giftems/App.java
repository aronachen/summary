package com.qst.giftems;

import android.app.Application;

import com.qst.giftems.model.User;

public class App extends Application {
    /** 当前登录用户 */
    public User user;

    @Override
    public void onCreate() {
        super.onCreate();
        user = new User();
        user.id = 1;
        user.name = "test";
        user.mobileToken = "ABCDEFGH";
    }
}
