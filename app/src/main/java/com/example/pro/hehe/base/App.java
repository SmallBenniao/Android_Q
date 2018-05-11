package com.example.pro.hehe.base;

import android.app.Application;

/**
 * Created by 帅哥 on 2018/3/29.
 * Xcq
 */

public class App extends Application {

    private static App context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }

    public static App getContext(){
        return context;
    }
}
