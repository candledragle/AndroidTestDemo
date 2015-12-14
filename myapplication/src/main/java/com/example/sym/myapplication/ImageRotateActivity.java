package com.example.sym.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.net.URL;
import java.net.URLConnection;

/**
 * Created by sym on 12/3/15.
 */
public class ImageRotateActivity extends Activity {

    private String url ="http://upload.tatuq.com/photo/2015-10-06/5612bff94a74f.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_rotate);

        MyAsyncTask task = new MyAsyncTask();
        task.execute(url);

    }


    class MyAsyncTask extends AsyncTask<String, Integer, Bitmap> {

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            ImageView imageView = (ImageView) findViewById(R.id.image);

            Matrix matrix = new Matrix();
            matrix.postScale(50,50);
            matrix.postRotate(45);

            Log.i("sym","the bitmap is "+bitmap);

            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Bitmap.createBitmap(bitmap, 0, 0, 50, 50,matrix,true);

            imageView.setImageBitmap(bitmap);

        }

        @Override
        protected Bitmap doInBackground(String... params) {

            Bitmap bitmap = null;
            try {
                URL url = new URL(params[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                bitmap = BitmapFactory.decodeStream(connection.getInputStream());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }
}
