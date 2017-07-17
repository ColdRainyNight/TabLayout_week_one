package com.tablayout_week_one.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tablayout_week_one.R;
import com.tablayout_week_one.bean.Data;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;


/**
 * 类描述：
 * 创建人：xuyaxi
 * 创建时间：2017/7/8 10:50
 */
public class MyAdapter extends BaseAdapter {

    private List<Data.TngouBean> list;
    private Context mContext;

    public MyAdapter(List<Data.TngouBean> list, Context context) {
        this.list = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item, null);
            holder = new ViewHolder();
            x.view().inject(holder, view);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Data.TngouBean bean = list.get(i);
        holder.name.setText(bean.getTitle());
        x.image().bind(holder.image,"http://tnfs.tngou.net/image"+bean.getImg());
        return view;
    }

    class ViewHolder {
        @ViewInject(R.id.name)
        TextView name;
        @ViewInject(R.id.image)
        ImageView image;
    }
}
