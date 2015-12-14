package com.example.sym.myapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Sheng yanming on 2015/12/4.
 */
public class TextViewDrawableActivity extends Activity {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_drawable);

        //点赞动画
        TextView textView = (TextView) findViewById(R.id.text);
        AnimationDrawable drawable = (AnimationDrawable) getResources().getDrawable(R.drawable.icon_huangsezan, null);

    }
}
