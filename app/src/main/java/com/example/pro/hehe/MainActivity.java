package com.example.pro.hehe;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import com.example.pro.hehe.adapter.FixPagerAdapter;
import com.example.pro.hehe.factory.FragmentFactory;
import com.example.pro.hehe.utils.fragments.CategoryFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.status_bar)
    View statusBar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.main_viewpager)
    ViewPager mainViewpager;


    private FixPagerAdapter fixPagerAdapter;
    private String[] titles={"推荐","分类","排行","管理","我的"};
    private List<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }


    private void initView() {


        intViewPagerFragment();
    }

    //对fragment进行绑定
    private void intViewPagerFragment() {

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT) {

        statusBar.post(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams layoutParams=statusBar.getLayoutParams();

                layoutParams.height=getStatusBarHeight();


                statusBar.setLayoutParams(layoutParams);
            }
        });



            //设置顶部状态栏 透明
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        tabLayout.setTabTextColors(getResources().getColor(R.color.black_alpha_5_1),getResources().getColor(R.color.black_alpha_5));

        fixPagerAdapter=new FixPagerAdapter(getSupportFragmentManager());

        fragments=new ArrayList<>();
        for(int i=0;i<titles.length;i++){
            //    new RecommendFragment();
            //
            fragments.add(FragmentFactory.createFragment(i));
        }
        fixPagerAdapter.setTitles(titles);
        fixPagerAdapter.setFragments(fragments);

        mainViewpager.setAdapter(fixPagerAdapter);
        //将ViewPager与TabLayout绑定
        tabLayout.setupWithViewPager(mainViewpager);
        //显示样式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        setHeight(getStatusBarHeight());


        mainViewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){


            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                if(position ==0){

                    statusBar.setBackgroundColor(getResources().getColor(R.color.black_alpha_6));
                    tabLayout.setBackgroundColor(getResources().getColor(R.color.black_alpha_6));
                    tabLayout.setTabTextColors(getResources().getColor(R.color.black_alpha_5_1),getResources().getColor(R.color.black_alpha_5));

                }else{

                    statusBar.setBackgroundColor(getResources().getColor(R.color.colorAccent_3));
                    tabLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent_2));
                    tabLayout.setTabTextColors(getResources().getColor(R.color.colorAccent_3),getResources().getColor(R.color.black_alpha_5));

                }

            }
        });

    }



    int height2;

    public void setHeight(final int top){


        tabLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tabLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height2=tabLayout.getHeight()+top;
                ((CategoryFragment)fragments.get(1)).setPadding(height2);

            }
        });

    }


    public int getHeight2() {
        return height2;
    }

    /**
     * 获取状态栏的高度
     */
    public  int getStatusBarHeight() {

        Class<?> aClass = null;
        try {
            //通过反射获取到类
            aClass = Class.forName("com.android.internal.R$dimen");
            //创建对象
            Object o = aClass.newInstance();
            //拿取属性
            Field status_bar_height = aClass.getField("status_bar_height");
            Object ol = status_bar_height.get(o);
            int height = Integer.parseInt(ol.toString());

            //      获得的像素值 转换成dp
            // getDimensionPixelSize则不管写的是dp还是sp还是px,都会乘以denstiy（屏幕密度）.
            return getResources().getDimensionPixelSize(height);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
