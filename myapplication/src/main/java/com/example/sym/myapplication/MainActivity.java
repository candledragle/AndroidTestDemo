package com.example.sym.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String url = "http://pic14.nipic.com/20110522/7411759_164157418126_2.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.activity_main, null);
        setContentView(view);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        DisplayImageOptions displayOptionPersist = new DisplayImageOptions.Builder().
                cacheInMemory(true).
                cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.
                Builder(getApplicationContext()).
                defaultDisplayImageOptions(displayOptionPersist).
                memoryCache(new LruMemoryCache(2 * 1024 * 1024)).
                memoryCacheSize(2 * 1024 * 1024).
                build();
        ImageLoader.getInstance().init(config);

        /*RelativeLayout rootView = (RelativeLayout) view.findViewById(R.id.rootView);
        ListView lv = (ListView) rootView.findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter();
        lv.setAdapter(adapter);
        lv.setDividerHeight(10);*/



       /* TopicView topicView = new TopicView(this);
        rootView.addView(topicView);*/
    }



    class MyAdapter extends BaseAdapter{

        public MyAdapter(){

        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public Object getItem(int position) {

            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(null == convertView){
                /*LayoutInflater inflater = LayoutInflater.from(MainActivity.this);

                View mRootView = inflater.inflate(R.layout.view_gallery,null);
                convertView = mRootView;*/
                GalleryView galleryView = new GalleryView(MainActivity.this);
                convertView = galleryView;
            }

            /*RecyclerView mRecyclerView = (RecyclerView) convertView.findViewById(R.id.recyclerView);

            //设置布局管理器
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecyclerView.setLayoutManager(linearLayoutManager);

            //设置适配器
            TopicAdapter adapter = new TopicAdapter(MainActivity.this);
            mRecyclerView.setAdapter(adapter);*/

            return convertView;
        }
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View mRootView = inflater.inflate(R.layout.view_topic,null);
        RecyclerView mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerView);

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //设置适配器
        TopicAdapter adapter = new TopicAdapter(this);
        mRecyclerView.setAdapter(adapter);
    }

    class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder>{

        private LayoutInflater mInflater;

        public TopicAdapter(Context context){
            mInflater = LayoutInflater.from(context);
        }

        /**
         * 创建ViewHolder Recycler的缓存单位
         *
         * @param viewGroup
         * @param i
         * @return
         */
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View cell = mInflater.inflate(R.layout.view_topic_cell, viewGroup,false);
            ViewHolder viewHolder = new ViewHolder(cell);
            viewHolder.mImageView = (ImageView) cell.findViewById(R.id.image);
            return viewHolder;
        }

        /**
         * 设置值
         * @param viewHolder
         * @param i
         */
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            //todo 为Viewholder中的ImageView填充数据
            //viewHolder.mImageView.setBackgroundColor(Color.parseColor("#123" + i + "45" + i + "6"));
            ImageLoader.getInstance().displayImage(url,viewHolder.mImageView);
        }

        @Override
        public int getItemCount() {
            //// TODO: 11/27/15 设置cell的个数

            return 40;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            public ViewHolder(View itemView) {
                super(itemView);
            }

            public ImageView mImageView;
        }
    }

   /* public void setListViewHeightBasedOnChildren(RecyclerView listView) {
        TopicAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_popup_window:
                Intent intent = new Intent(this, PopUpWindowActivity.class);
                startActivity(intent);
                break;
            case R.id.action_setting:
                break;
            case R.id.imageRotate:
                Intent imageRotateIntent = new Intent(this, ImageRotateActivity.class);
                startActivity(imageRotateIntent);
                break;
            case R.id.bashedLine:
                Intent dashIntent = new Intent(this, DashedActivity.class);
                startActivity(dashIntent);

                break;
        }
        return true;
    }
}
