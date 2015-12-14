package com.example.sym.myapplication;

import android.app.Activity;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;

/**
 * Created by Sheng yanming on 2015/12/11.
 */
public class DrawableActivity extends Activity {

    private ShapeDrawable mCircle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_layout);
    }

    private void createCircleDrawable(){
        float radius = 20;
        final float density = getBaseContext().getResources().getDisplayMetrics().density;
        final int diameter = (int) (radius*density*2);
        final int shadowYOffset = (int) (density*1.75f);
        final  int shadowXOffset = (int) (density*1.75f);


    }

    private class OvalShadow extends OvalShape{

        public OvalShadow(int shadowRadius,int circleDiammer){
            super();

        }
    }
}
