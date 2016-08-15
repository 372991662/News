package com.atguigu.yangyuanyuan.news.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.atguigu.yangyuanyuan.news.R;
import com.atguigu.yangyuanyuan.news.base.BaseFragment;

/**
 * Created by Administrator on 2016/8/14.
 */
public class MainFragment extends BaseFragment {
    private ViewPager vp_main;
    private RadioGroup rg_main;

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.main_fragment, null);
        vp_main = (ViewPager) view.findViewById(R.id.vp_main);
        rg_main = (RadioGroup) view.findViewById(R.id.rg_main);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //设置默认选中的页面
        rg_main.check(R.id.rb_main_home);
    }
}
