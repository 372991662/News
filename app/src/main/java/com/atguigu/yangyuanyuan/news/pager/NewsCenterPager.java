package com.atguigu.yangyuanyuan.news.pager;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.atguigu.yangyuanyuan.news.base.BaseViewPager;
import com.atguigu.yangyuanyuan.news.domain.NewsCenterPagerBean;
import com.atguigu.yangyuanyuan.news.utils.Constants;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by 杨媛媛 on 2016/8/15 18:45.
 */
public class NewsCenterPager extends BaseViewPager {

    private RequestParams params;

    public NewsCenterPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();

        tv_basepager_title.setText("新闻页面");
        TextView tv = new TextView(mContext);
        tv.setText("新闻内容");
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.RED);
        tv.setTextSize(25);

        //添加字数图
        fl_basepager.addView(tv);

        //联网请求数据
        getDataFromNet();
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

    }

    //解析json数据
    private NewsCenterPagerBean parsedJson(String json) {
        Gson gson = new Gson();
        NewsCenterPagerBean bean = gson.fromJson(json, NewsCenterPagerBean.class);
        return bean;
    }
}
