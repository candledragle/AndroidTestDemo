package com.example.sym.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by sym on 11/28/15.
 */
public class WrapperRecyclerView extends RecyclerView {

    private View mCurrentView;
    private OnItemScrollChangeListener mOnItemScrollChangeListener;

    public WrapperRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapperRecyclerView(Context context) {
        this(context, null);
    }

    public void setOnItemScrollChangeListener(OnItemScrollChangeListener listener){
        mOnItemScrollChangeListener = listener;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        mCurrentView = getChildAt(0);

        if(null != mOnItemScrollChangeListener){
            mOnItemScrollChangeListener.onChange(mCurrentView,getChildAdapterPosition(mCurrentView));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        if(e.getAction() == MotionEvent.ACTION_DOWN){
            mCurrentView = getChildAt(0);

            if(null != mOnItemScrollChangeListener){
                mOnItemScrollChangeListener.onChange(mCurrentView,getChildAdapterPosition(mCurrentView));
            }
        }

        return super.onTouchEvent(e);
    }

    interface OnItemScrollChangeListener{
        public void onChange(View view,int postion);
    }
}
