package com.atguigu.yangyuanyuan.news.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.atguigu.yangyuanyuan.news.base.BaseFragment;

/**
 * Created by Administrator on 2016/8/14.
 */
public class LeftmenuFragment extends BaseFragment {

    @Override
    public View initView() {
        TextView text = new TextView(getActivity());
        text.setText("左边菜单");
        text.setTextColor(Color.WHITE);
        text.setTextSize(60);
        return text;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
