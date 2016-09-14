package com.jfjmusic.dllo.baidumusic.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.model.bean.MLChartBean;
import com.jfjmusic.dllo.baidumusic.utils.L;
import com.jfjmusic.dllo.baidumusic.utils.T;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 乐库-->排行-----的listView的适配器
 */
public class MLChartListViewAdapter extends BaseAdapter{
    private Context context;
    private List<MLChartBean.ContentBean> datas;

    public MLChartListViewAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<MLChartBean.ContentBean> datas) {
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
        ViewHolder viewHolder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_fragment_chart,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
   //     viewHolder.mImageView.setImageResource(datas.get(position).getPic_s192());
        L.d("bug",datas.get(position).getName());
        viewHolder.titleTv.setText(datas.get(position).getName());
        viewHolder.oneTv.setText(datas.get(position).getMyContent().get(0).getTitle());
        viewHolder.twoTv.setText(datas.get(position).getMyContent().get(1).getTitle());
        viewHolder.threeTv.setText(datas.get(position).getMyContent().get(2).getTitle());
        return convertView;
    }

    public class ViewHolder {
        private ImageView mImageView;
        private TextView titleTv;
        private TextView oneTv;
        private TextView twoTv;
        private TextView threeTv;

        public ViewHolder(View view) {
            mImageView = (ImageView) view.findViewById(R.id.ml_chart_img_icon);
            titleTv = (TextView) view.findViewById(R.id.ml_chart_tv_title);
            oneTv = (TextView) view.findViewById(R.id.ml_chart_tv_one);
            twoTv = (TextView) view.findViewById(R.id.ml_chart_tv_two);
            threeTv = (TextView) view.findViewById(R.id.ml_chart_tv_three);
        }
    }
}
