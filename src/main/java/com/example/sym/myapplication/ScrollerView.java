package com.example.sym.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

/**
 * Created by sym on 11/28/15.
 */
public class ScrollerView extends FrameLayout{
    public ScrollerView(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.view_scroll_h,this);
    }
}
