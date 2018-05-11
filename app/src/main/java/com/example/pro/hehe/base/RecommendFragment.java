package com.example.pro.hehe.base;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.pro.hehe.R;
import com.example.pro.hehe.factory.UIUtils;

/**
 * Created by 帅哥 on 2018/3/29.
 * Xcq
 */

public class RecommendFragment extends BaseFragment {


    /**
     * 默认状态
     */
    public final static int STATE_UNKOWN=0;

    /**
     * 加载中的状态
     */
    public final static int STATE_LOADING=1;

    /**
     * 加载失败的 状态
     */
    public final static int STATE_ERROR=2;

    /**
     * 加载成功的状态
     */
    public final static int STATE_SUCCESS=3;

    /**
     * 加载空的状态
     */
    public final static int STATE_EMPTY=4;


    private int state=STATE_UNKOWN;


    private View loadingView;
    private View errorView;
    private View emptyView;
    private View successView;

    private FrameLayout frameLayout;



    //根据状态显示不同的布局


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(frameLayout==null){
            frameLayout = new FrameLayout(getContext());

            init();
            show();
        }



       return frameLayout;
    }

    /**
     * 将布局添加到帧布局中
     */
    private void init() {
        if(loadingView==null){
            loadingView=createLoadingView();
            frameLayout.addView(loadingView,new FrameLayout.LayoutParams
                    (FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));

        }

        if(errorView==null){
            errorView=createErrorView();
            frameLayout.addView(loadingView,new FrameLayout.LayoutParams
                    (FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));

        }

        if(emptyView==null){
            emptyView=createEmptyView();
            frameLayout.addView(loadingView,new FrameLayout.LayoutParams
                    (FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));

        }

        showPager();

    }




    /**
     * 请求网络 修改状态
     */
    public void show() {
        if(state ==STATE_UNKOWN || state==STATE_ERROR ||state==STATE_EMPTY){

            state=STATE_LOADING;
            load();
        }
        showPager();

    }

    private void load() {
        //
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                setState(LoadResult.success);
            }


        }).start();
    }

    /**
     * 设置状态
     * @param result
     */
    private void setState(LoadResult result) {

        state=result.getValue();

        //在主线程运行
        showPager();

    }

    /**
     * 请求结果的枚举
     */
    public enum LoadResult{

        error(STATE_ERROR),success(STATE_SUCCESS),empty(STATE_EMPTY);
        int value;
        LoadResult(int value){
            this.value=value;

        }
        public int getValue(){
            return value;
        }

    }

    /**
     * 根据不同的状态显示不同的布局
     */
    private void showPager() {

        if(loadingView!=null){
            loadingView.setVisibility(state==STATE_UNKOWN || state==STATE_LOADING
            ?View.VISIBLE:View.GONE);
        }

        if(errorView!=null){
            errorView.setVisibility(state==STATE_ERROR ? View.VISIBLE:View.GONE);
        }

        if(emptyView !=null){
            emptyView.setVisibility(state==STATE_EMPTY?View.VISIBLE:View.GONE);

        }

        if(state ==STATE_SUCCESS && successView ==null){
            successView=createSuccessView();
            frameLayout.addView(successView,new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT
            ));
        }


    }




    private View createEmptyView() {
        return  UIUtils.inflate(R.layout.activity_main);


    }

    private View createErrorView() {
        return  UIUtils.inflate(R.layout.activity_main);


    }

    private View createLoadingView() {
        return  UIUtils.inflate(R.layout.activity_main);


    }

    private View createSuccessView() {

        TextView tv=new TextView(getContext());
        tv.setText("加载成功");
        return tv;
      }

}
