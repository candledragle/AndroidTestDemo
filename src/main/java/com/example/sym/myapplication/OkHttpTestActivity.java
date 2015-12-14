package com.example.sym.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Sheng yanming on 2015/12/10.
 */
public class OkHttpTestActivity extends Activity {

    private String url = "http://test.tatuq.com/api/user/nearbyPerson?token=43f03ef72bec3acb4de2bb5f4654f29f9a9beed4&plat=android&page=0&lon=116.46987&access_token=V1_10019_MTg2MDAwMDE4MDA=_f5a231682e8a8ca1069920d10bec5d9c_1452223010&num=20&model=SM-N7508V&type=1&deviceid=9857D581E72D17C2F432A57F8F6AA0A6&lat=39.892721&channel=official&ver=1.1.0";
    private static boolean DEBUG = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_okhttp_test);
        ListView listView = (ListView) findViewById(R.id.listView);
        new MyAsycTask().execute(url,null,null);

    }

    class MyAsycTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = null;
            String result = null;
            try {
                response = client.newCall(request).execute();
                if (DEBUG) Log.i("sym", response.body().string());
                result = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);

            if (DEBUG)
                Log.i("sym", s);
        }
    }


    class MyAdapter extends BaseAdapter {

        public MyAdapter() {

        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
