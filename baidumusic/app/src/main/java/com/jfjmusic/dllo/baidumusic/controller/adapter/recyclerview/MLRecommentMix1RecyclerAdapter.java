package com.jfjmusic.dllo.baidumusic.controller.adapter.recyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.model.bean.MLRecommendBean;
import com.jfjmusic.dllo.baidumusic.utils.L;
import com.jfjmusic.dllo.baidumusic.utils.ScreenSizeUtil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by dllo on 16/9/14.
 * 乐库-->推荐-->新碟上架----的recyclerview的Adapter
 */
public class MLRecommentMix1RecyclerAdapter extends RecyclerView.Adapter<MLRecommentMix1RecyclerAdapter.ViewHolder> {

    private Context context;
    private ViewHolder viewHolder;
    private List<MLRecommendBean.ResultBean.Mix1Bean.Mix1ResultBean> datas;

    private int height = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.WIDTH) / 3 - 40;
    private int width = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.WIDTH) / 3 - 40;

    public MLRecommentMix1RecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<MLRecommendBean.ResultBean.Mix1Bean.Mix1ResultBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fragment_ml_recommend_mix1, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        //int pp=holder.getAdapterPosition();

        //L.d("复用中的:"+pp);
//        AsyncTask asyncTask = (AsyncTask) holder.img.getTag(1);
//        asyncTask.cancel(true);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        L.d("绑布局" + position);
        //holder.setIsRecyclable(false);//设置不使用复用机制(该方法会使性能下降)

       // Picasso.with(context).load(datas.get(position).getPic_300()).resize(height, width).into(viewHolder.img);
        holder.titleTv.setText(datas.get(position).getTitle());
        holder.nametv.setText(datas.get(position).getAuthor());

        final String imgurl = datas.get(position).getPic();
        /**
         * 在这里解决了RecyclerView缓存机制导致图片显示错乱的问题
         */
        /************************************/
        //为imageView设置Tag,内容是该imageView等待加载的图片url
        holder.img.setTag(imgurl);

        //先设置图片占位符
        holder.img.setImageDrawable(context.getDrawable(R.mipmap.ic_classify_img02));
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    URL url = new URL(datas.get(position).getPic());
                    Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
                    return bitmap;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                //加载完毕后判断该imageView等待的图片url是不是加载完毕的这张
                //如果是则为imageView设置图片,否则说明imageView已经被重用到其他item
                if(imgurl.equals(holder.img.getTag())) {
                    holder.img.setImageBitmap((Bitmap) o);
                }
            }
        };
        asyncTask.execute();
        /******************************************/
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(height,width);
        holder.img.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() != 0 ? 6 : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView titleTv;
        private TextView nametv;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.item_fra_ml_recommend_mix1_img);
            titleTv = (TextView) itemView.findViewById(R.id.item_fra_ml_recommend_mix1_title);
            nametv = (TextView) itemView.findViewById(R.id.item_fra_ml_recommend_mix1_name);
        }
    }
}
