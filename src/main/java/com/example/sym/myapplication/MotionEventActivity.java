package com.example.sym.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by Sheng yanming on 2015/12/11.
 */
public class MotionEventActivity extends Activity {

    private TextView textView;
    private MyHander mHandler = new MyHander(this);

    static class MyHander extends Handler {

        private WeakReference<MotionEventActivity> c;

        public MyHander(MotionEventActivity clazz) {
            c = new WeakReference(clazz);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0) {
                Bundle data = msg.getData();
                String content = data.getString("content");
                c.get().textView.setText(content);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_event_layout);
        textView = (TextView) findViewById(R.id.testContent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event); //提取用户执行的动作
        int actionPointerId = 0;
        Message msg = Message.obtain();
        msg.what = 0;
        Bundle bundle = new Bundle();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                actionPointerId = MotionEventCompat.getPointerId(event,0);//获得触点Id
                Log.i("sym", "action_down is performed: "+actionPointerId);
                bundle.putString("content", "action_down is performed: "+actionPointerId);
                break;
            case MotionEvent.ACTION_MOVE:
                float x = MotionEventCompat.getX(event,actionPointerId);
                float y = MotionEventCompat.getY(event, actionPointerId);
                Log.i("sym", "action_motion is performed: \n x:"+x+"  y:"+y);
                bundle.putString("content", "action_move is performed: \n x:"+x+"  y:"+y);
                break;
            case MotionEvent.ACTION_UP:
                Log.i("sym", "action_up is performed");
                bundle.putString("content", "action_up is performed");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i("sym", "action_cancel is performed");
                bundle.putString("content", "action_cancel is performed");
                break;
            case MotionEvent.ACTION_OUTSIDE:
                Log.i("sym", "action_outside is performed");
                bundle.putString("content", "action_outside is performed");
                break;
        }

        msg.setData(bundle);
        mHandler.sendMessage(msg);
        return true;
    }
}
