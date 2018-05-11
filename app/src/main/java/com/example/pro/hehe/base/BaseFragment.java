package com.example.pro.hehe.base;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.example.pro.hehe.MainActivity;
import com.example.pro.hehe.R;

/**
 * Created by 帅哥 on 2018/3/17.
 * Xcq
 */

public class BaseFragment extends Fragment {



    public void setPadding(final ViewGroup layout){

        final MainActivity activity = (MainActivity) getActivity();

        final View viewById = activity.findViewById(R.id.tab_layout);


        viewById.post(new Runnable() {
            @Override
            public void run() {

                int height = viewById.getHeight();
                layout.setPadding(0,height+activity.getStatusBarHeight(),0,0);

            }
        });
    }

}
