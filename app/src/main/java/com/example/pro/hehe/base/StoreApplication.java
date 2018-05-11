package com.example.pro.hehe.base;

import android.os.Handler;

/**
 * Created by 帅哥 on 2018/3/29.
 * Xcq
 */

public class StoreApplication extends App {


    private static int mMainThreadId;
    private static Handler mHandler;

    @Override
    public void onCreate(){
        super.onCreate();

        mHandler=new Handler();

    }

    /**
     * 返回主线程的pid
     * @return
     */
    public static int getMainThreadId(){
        return mMainThreadId;
    }

    /**
     * 返回Handler
     * @return
     */
    public static Handler getHandler(){
        return mHandler;
    }
}
