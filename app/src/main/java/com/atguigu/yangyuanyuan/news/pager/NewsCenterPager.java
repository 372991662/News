package com.atguigu.yangyuanyuan.news.pager;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.yangyuanyuan.news.activity.MainActivity;
import com.atguigu.yangyuanyuan.news.base.BaseViewPager;
import com.atguigu.yangyuanyuan.news.domain.NewsCenterPagerBean;
import com.atguigu.yangyuanyuan.news.fragment.LeftmenuFragment;
import com.atguigu.yangyuanyuan.news.utils.Constants;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by 杨媛媛 on 2016/8/15 18:45.
 */
public class NewsCenterPager extends BaseViewPager {


    private List<NewsCenterPagerBean.DataBean> leftMenuData;

    public NewsCenterPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        ib_basepager_btn.setVisibility(View.VISIBLE);

        tv_basepager_title.setText("新闻页面");
        TextView tv = new TextView(mContext);
        tv.setText("新闻内容");
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.RED);
        tv.setTextSize(25);

        initListener();

        //添加字数图
        fl_basepager.addView(tv);

        //联网请求数据
        getDataFromNet();
    }

    private void initListener() {
        ib_basepager_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) mContext;
                SlidingMenu slidingMenu = mainActivity.getSlidingMenu();
                slidingMenu.toggle();
            }
        });
    }

    //使用Xutils联网请求数据
    private void getDataFromNet() {
        RequestParams params = new RequestParams(Constants.NEWS_CENTER_PAGER_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "请求成功");
                //解析数据
                processData(result);
                //设置ListView适配器

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "请求错误" + ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG", "请求取消" + cex);
            }

            @Override
            public void onFinished() {
                Log.e("TAG", "请求结束");
            }
        });
    }

    //解析json数据,显示
    private void processData(String json) {
        NewsCenterPagerBean bean = parsedJson(json);
        String title = bean.getData().get(0).getChildren().get(1).getTitle();
        Log.e("TAG", title);

        //给左侧菜单传递数据
        leftMenuData = bean.getData();
        MainActivity mainActivity = (MainActivity) mContext;
        LeftmenuFragment leftmenuFragment = (LeftmenuFragment) mainActivity.getLeftmenuFragment();
        //传递数据给左侧菜单
        leftmenuFragment.setData(leftMenuData);
    }

    //解析json数据
    private NewsCenterPagerBean parsedJson(String json) {
        Gson gson = new Gson();
        NewsCenterPagerBean bean = gson.fromJson(json, NewsCenterPagerBean.class);
        return bean;
    }
}
