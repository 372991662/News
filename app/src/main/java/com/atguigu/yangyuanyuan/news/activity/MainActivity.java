package com.atguigu.yangyuanyuan.news.activity;

import android.os.Bundle;

import com.atguigu.yangyuanyuan.news.R;
import com.atguigu.yangyuanyuan.news.utils.DensityUtil;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置主页面
        setContentView(R.layout.activity_main);
        //设置左侧菜单
        setBehindContentView(R.layout.activity_leftmenu);

        SlidingMenu sm = getSlidingMenu();
        //设置右侧菜单
        /*sm.setSecondaryMenu(R.layout.activity_rightmenu);*/
        //设置显示模式
        sm.setMode(SlidingMenu.LEFT);
        //设置滑动模式
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置占据主页宽度
        sm.setBehindOffset(DensityUtil.dip2px(this, 200));
    }
}
