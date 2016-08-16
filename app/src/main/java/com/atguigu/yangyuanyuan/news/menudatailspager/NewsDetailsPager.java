package com.atguigu.yangyuanyuan.news.menudatailspager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.yangyuanyuan.news.R;
import com.atguigu.yangyuanyuan.news.base.MenuDetailBasePager;
import com.atguigu.yangyuanyuan.news.domain.NewsCenterPagerBean;
import com.atguigu.yangyuanyuan.news.menudatailspager.tabdetailpager.TabDetailPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 杨媛媛 on 2016/8/15 23:59.
 */
public class NewsDetailsPager extends MenuDetailBasePager {
    private ViewPager vp_newsmenu_detail;
    //页签页面的数据
    private List<NewsCenterPagerBean.DataBean.ChildrenBean> newsDataChildren = new ArrayList<>();
    //页签页面的集合
    private List<TabDetailPager> tabDetailPagers = new ArrayList<>();

    public NewsDetailsPager(Context context, NewsCenterPagerBean.DataBean newsData) {
        super(context);
        newsDataChildren = newsData.getChildren();
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.newsmenu_detail_pager, null);
        vp_newsmenu_detail = (ViewPager) view.findViewById(R.id.vp_newsmenu_detail);
        return view;
    }

    //给ViewPager设置数据
    //1.准备数据 集合
    //2.设置适配器
    @Override
    public void initData() {
        super.initData();

        Log.e("TAG", "新闻详情页面数据被初始化了..");

        //创建页面并添加集合，将新闻数据传递
        for (int i = 0; i < newsDataChildren.size(); i++) {
            tabDetailPagers.add(new TabDetailPager(mContext, newsDataChildren.get(i)));
        }

        //设置适配器
        vp_newsmenu_detail.setAdapter(new NewsMenuDetailAdapter());
    }

    //viewPager的适配器
    class NewsMenuDetailAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return tabDetailPagers == null ? 0 : tabDetailPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabDetailPager detailPager = tabDetailPagers.get(position);
            //初始化数据
            detailPager.initData();
            View rootView = detailPager.rootView;
            container.addView(rootView);
            return rootView;
        }
    }
}
