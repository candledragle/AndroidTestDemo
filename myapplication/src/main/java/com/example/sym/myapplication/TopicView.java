package com.example.sym.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by sym on 11/27/15.
 */
public class TopicView extends FrameLayout {

    private View mRootView;
    private RecyclerView mRecyclerView;
    private String url = "http://pic14.nipic.com/20110522/7411759_164157418126_2.jpg";

    public TopicView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context);
    }
    public TopicView(Context context) {
        this(context, null);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mRootView = inflater.inflate(R.layout.view_topic, this);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerView);

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //设置适配器
        TopicAdapter adapter = new TopicAdapter(getContext());
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
}
