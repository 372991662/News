package com.atguigu.yangyuanyuan.news.menudatailspager.tabdetailpager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.yangyuanyuan.news.base.MenuDetailBasePager;
import com.atguigu.yangyuanyuan.news.domain.NewsCenterPagerBean;

/**
 * Created by 杨媛媛 on 2016/8/16 19:20.
 */

//页签详情页面
public class TabDetailPager extends MenuDetailBasePager {

    private TextView tv;
    private NewsCenterPagerBean.DataBean.ChildrenBean mChilderBean = new NewsCenterPagerBean.DataBean.ChildrenBean();

    public TabDetailPager(Context context, NewsCenterPagerBean.DataBean.ChildrenBean childrenBean) {
        super(context);
        this.mChilderBean = childrenBean;
    }


    @Override
    public View initView() {
        tv = new TextView(mContext);
        tv.setTextColor(Color.RED);
        tv.setTextSize(30);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }

    @Override
    public void initData() {
        super.initData();

        //设置ViewPager的数据
        tv.setText(mChilderBean.getTitle());
    }
}
