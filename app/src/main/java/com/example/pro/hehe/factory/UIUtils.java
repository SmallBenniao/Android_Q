package com.example.pro.hehe.factory;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;

import com.example.pro.hehe.base.StoreApplication;

/**
 * Created by 帅哥 on 2018/3/29.
 * Xcq
 */

public class UIUtils {

    /**
     * 获取上下文
     * @return
     */
    public static Context getContext(){
        return StoreApplication.getContext();
    }

    /**
     *
     * @return
     */
    public static Resources getResources(){


        return getContext().getResources();
    }

    public static View inflate(int id){

        return View.inflate(getContext(),id,null);

    }


}
