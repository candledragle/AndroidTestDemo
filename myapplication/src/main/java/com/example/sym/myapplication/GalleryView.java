package com.example.sym.myapplication;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by sym on 11/28/15.
 * 分析有两个地方不太确定，
 * 1、cell的内容
 * 2、cell要填充的数据
 */
public class GalleryView  extends FrameLayout implements
        WrapperRecyclerView.OnItemScrollChangeListener{

    private static boolean DEBUG = true;
    private int WIDTH = 125 ;
    private ImageView mImageView;
    private String url = "http://pic14.nipic.com/20110522/7411759_164157418126_2.jpg";

    public GalleryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GalleryView(Context context) {
        this(context, null);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.view_gallery, this);
        mImageView = (ImageView) rootView.findViewById(R.id.imageView);
        WrapperRecyclerView recyclerView = (WrapperRecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setOnItemScrollChangeListener(this);

        //获得屏幕的宽度
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        int widthPixels = dm.widthPixels;

        if(DEBUG) Log.i("sym","the width of screen is "+widthPixels);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mImageView.getLayoutParams();
        params.height = widthPixels;
        params.width = widthPixels;
        mImageView.setLayoutParams(params);

        GalleryAdapter adapter = new GalleryAdapter(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 刷新数据
     */
    public void refresh(List<String> urls){

    }

    @Override
    public void onChange(View view, int postion) {
        //// TODO: 11/28/15 获得
        if(DEBUG) Log.i("sym","the current position is "+postion);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        View maskLayer = view.findViewById(R.id.maskLayer);
        maskLayer.setSelected(true);
        ImageLoader.getInstance().displayImage(url, imageView);

    }

    class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>{

        private Context mContext;

        public GalleryAdapter(Context context){
            mContext = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(mContext);
            View inflate = inflater.inflate(R.layout.view_topic_cell,parent,false);
            ViewHolder viewHolder = new ViewHolder(inflate);
            viewHolder.mImageView = (ImageView) inflate.findViewById(R.id.image);
            viewHolder.mMaskLayer = inflate.findViewById(R.id.maskLayer);
/*            ImageView imageView = new ImageView(mContext);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(WIDTH, WIDTH);
            FrameLayout frameLayout = new FrameLayout(mContext);
            frameLayout.addView(imageView);
            //params.setMargins(10,0,0,0);
            imageView.setLayoutParams(params);*/

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            ImageLoader.getInstance().displayImage(url,holder.mImageView);
        }

        @Override
        public int getItemCount() {

            //// TODO: 11/28/15 cell的数目
            return 40;
        }


        class ViewHolder extends RecyclerView.ViewHolder{

            public ViewHolder(View itemView) {
                super(itemView);
            }

            public ImageView mImageView;
            public View mMaskLayer;
        }
    }
}
