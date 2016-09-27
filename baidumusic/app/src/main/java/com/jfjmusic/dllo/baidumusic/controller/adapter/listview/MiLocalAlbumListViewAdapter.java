package com.jfjmusic.dllo.baidumusic.controller.adapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.model.bean.LocalMusicBean;
import com.jfjmusic.dllo.baidumusic.model.bean.MiLocalAlbumBean;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 我的-->本地音乐---专辑--的listView的适配器
 */
public class MiLocalAlbumListViewAdapter extends BaseAdapter{
    private Context context;
    private List<MiLocalAlbumBean> datas;

    public MiLocalAlbumListViewAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<MiLocalAlbumBean> datas) {
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
            convertView= LayoutInflater.from(context).inflate(R.layout.item_fragment_mi_local_album,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        //int height= ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.HEIGHT);
        //viewHolder.mImageView.

        //Picasso.with(context).load(datas.get(position).getPic_s210()).into(viewHolder.mImageView);

       // viewHolder.titleTv.setText(datas.get(position).getTitle());
        //viewHolder.singerTv.setText(datas.get(position).getSinger());
        return convertView;
    }

    public class ViewHolder {
        private TextView titleTv,singerTv;

        public ViewHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.item_fra_mi_local_title);
            singerTv = (TextView) view.findViewById(R.id.item_fra_mi_local_singer);
        }
    }
}
