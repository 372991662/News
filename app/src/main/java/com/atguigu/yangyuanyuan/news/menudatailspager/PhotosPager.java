package com.atguigu.yangyuanyuan.news.menudatailspager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.yangyuanyuan.news.base.MenuDetailBasePager;

/**
 * Created by 杨媛媛 on 2016/8/15 23:59.
 */
public class PhotosPager extends MenuDetailBasePager {
    private TextView tv;

    public PhotosPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        tv = new TextView(mContext);

        TextView tv = new TextView(mContext);
        tv.setText("组图页面");
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.RED);
        tv.setTextSize(25);

        //添加字数图
        return tv;
    }

    @Override
    public void initData() {
        super.initData();

    }
}
