package com.tablayout_week_one.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.tablayout_week_one.R;
import com.tablayout_week_one.adapter.MyAdapter;
import com.tablayout_week_one.bean.Data;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * 创建人：xuyaxi
 * 创建时间：2017/7/7 20:50
 */

@ContentView(R.layout.my_fragment)
public class MyFragment extends Fragment {

    @ViewInject(R.id.lv)
    private ListView lv;

    private int id;
    private List<Data.TngouBean> list = new ArrayList<>();
    private MyAdapter adapter;

    public static MyFragment getInstance(int id) {
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = x.view().inject(this, inflater, container);
        // tv.setText(getArguments().getInt("id"));
        id = getArguments().getInt("id");

        adapter = new MyAdapter(list, getActivity());
        lv.setAdapter(adapter);
        loadData();
        return view;
    }

    private void loadData() {
        RequestParams requestparams = new RequestParams("http://www.tngou.net/api/top/list");
        requestparams.addQueryStringParameter("page", "1");
        requestparams.addQueryStringParameter("rows", "10");
        requestparams.addQueryStringParameter("id", id + "");

        x.http().get(requestparams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Data data = new Gson().fromJson(result, Data.class);
                list.addAll(data.getTngou());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
}
