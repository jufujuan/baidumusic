package com.jfjmusic.dllo.baidumusic.controller.adapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.model.bean.KtvAllSingBean;
import com.jfjmusic.dllo.baidumusic.model.bean.MLChartDetailsBean;
import com.jfjmusic.dllo.baidumusic.utils.ScreenSizeUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 二级界面排行榜的详情界面
 */
public class MLChartDetailsListViewAdapter extends BaseAdapter{
    private Context context;
    private List<MLChartDetailsBean.SongListBean> datas;
    private int height = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.WIDTH) / 7 ;
    private int width = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.WIDTH) / 7;
    public MLChartDetailsListViewAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<MLChartDetailsBean.SongListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas!=null&&datas.size()!=0?datas.size():0;
    }

    @Override
    public Object getItem(int position) {
        return datas!=null&&datas.size()!=0?datas.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder  viewHolder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_fra_chart_details,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(datas.get(position).getPic_big()).resize(width,height).into(viewHolder.imgBg);
        if (position==1){
            viewHolder.imgColor.setImageResource(R.mipmap.img_king_mask01);
        }else if (position==2){
            viewHolder.imgColor.setImageResource(R.mipmap.img_king_mask02);
        }else if (position==3){
            viewHolder.imgColor.setImageResource(R.mipmap.img_king_mask03);
        }else{
            viewHolder.imgColor.setImageResource(R.mipmap.img_king_mask1);
        }
        viewHolder.titleTv.setText(datas.get(position).getTitle());
        viewHolder.singerTv.setText(datas.get(position).getAuthor());
        String p=null;
        if (position<9){
            p="0"+(position+1);
        }else{
            p=String.valueOf(position);
        }
        viewHolder.orderTv.setText(p);
        return convertView;
    }

    class ViewHolder{
        private ImageView imgBg,imgColor;
        private TextView titleTv,singerTv,orderTv;

        public ViewHolder(View view) {
            imgBg = (ImageView) view.findViewById(R.id.item_fra_ml_chart_details_img);
            imgColor = (ImageView) view.findViewById(R.id.item_fra_ml_chart_details_img_color);
            titleTv = (TextView) view.findViewById(R.id.item_fra_ml_chart_details_title);
            singerTv = (TextView) view.findViewById(R.id.item_fra_ml_chart_details_singer);
            orderTv = (TextView) view.findViewById(R.id.item_fra_ml_chart_details_number);
        }
    }
}
