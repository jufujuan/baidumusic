package com.jfjmusic.dllo.baidumusic.controller.adapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jfjmusic.dllo.baidumusic.R;
import com.jfjmusic.dllo.baidumusic.model.bean.KtvAllSingBean;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 */
public class KtvListViewAdapter extends BaseAdapter{
    private Context context;
    private List<KtvAllSingBean> datas;

    public KtvListViewAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<KtvAllSingBean> datas) {
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
            convertView= LayoutInflater.from(context).inflate(R.layout.item_fragment_ktv_all_sing,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.nameTv.setText(datas.get(position).getName());
        viewHolder.numberTv.setText(datas.get(position).getNumber()+"人唱过");
        return convertView;
    }

    class ViewHolder{
        private TextView nameTv;
        private TextView numberTv;

        public ViewHolder(View view) {
            nameTv = (TextView) view.findViewById(R.id.item_fra_ktv_song_name);
            numberTv = (TextView) view.findViewById(R.id.item_fra_ktv_song_number);
        }
    }
}
