package com.atguigu.yangyuanyuan.news.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.atguigu.yangyuanyuan.news.R;
import com.atguigu.yangyuanyuan.news.base.BaseFragment;
import com.atguigu.yangyuanyuan.news.base.BaseViewPager;
import com.atguigu.yangyuanyuan.news.pager.GovaffairPager;
import com.atguigu.yangyuanyuan.news.pager.HomePager;
import com.atguigu.yangyuanyuan.news.pager.NewsCenterPager;
import com.atguigu.yangyuanyuan.news.pager.SettingPager;
import com.atguigu.yangyuanyuan.news.pager.SmartServicePager;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/8/14.
 */
public class MainFragment extends BaseFragment {

    @ViewInject(R.id.vp_main)
    private ViewPager vp_main;

    @ViewInject(R.id.rg_main)
    private RadioGroup rg_main;

    private List<BaseViewPager> basePagers;

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.main_fragment, null);
        //vp_main = (ViewPager) view.findViewById(R.id.vp_main);
        //rg_main = (RadioGroup) view.findViewById(R.id.rg_main);

        //把视图注入框架中
        x.view().inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        //初始化页面并且放入集合中
        basePagers = new ArrayList<>();
        basePagers.add(new HomePager(mContext));
        basePagers.add(new NewsCenterPager(mContext));
        basePagers.add(new SmartServicePager(mContext));
        basePagers.add(new GovaffairPager(mContext));
        basePagers.add(new SettingPager(mContext));
        //设置默认选中的页面
        rg_main.check(R.id.rb_main_home);
        //设置适配器
        vp_main.setAdapter(new BasePagerAdapter());

    }

    class BasePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return basePagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BaseViewPager viewPager = basePagers.get(position);

            viewPager.initData();
            //--------------------------
            initData();
            View rootView = viewPager.rootView;
            container.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
