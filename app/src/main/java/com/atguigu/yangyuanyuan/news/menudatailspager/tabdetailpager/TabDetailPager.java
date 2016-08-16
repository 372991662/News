package com.atguigu.yangyuanyuan.news.menudatailspager.tabdetailpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.yangyuanyuan.news.R;
import com.atguigu.yangyuanyuan.news.base.MenuDetailBasePager;
import com.atguigu.yangyuanyuan.news.domain.NewsCenterPagerBean;
import com.atguigu.yangyuanyuan.news.domain.TableDetailPagerBean;
import com.atguigu.yangyuanyuan.news.utils.CacheUtils;
import com.atguigu.yangyuanyuan.news.utils.Constants;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by 杨媛媛 on 2016/8/16 19:20.
 */

//页签详情页面
public class TabDetailPager extends MenuDetailBasePager {
    private ViewPager vp_newsdetails;
    private TextView tv_title;
    private LinearLayout ll_newsdetails;
    private ListView lv_newsdetails;

    private NewsCenterPagerBean.DataBean.ChildrenBean mChilderBean = new NewsCenterPagerBean
            .DataBean.ChildrenBean();
    private String url;

    public TabDetailPager(Context context, NewsCenterPagerBean.DataBean.ChildrenBean childrenBean) {
        super(context);
        this.mChilderBean = childrenBean;
    }


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.tabdetail_pager, null);
        vp_newsdetails = (ViewPager) view.findViewById(R.id.vp_newsdetails);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        ll_newsdetails = (LinearLayout) view.findViewById(R.id.ll_newsdetails);
        lv_newsdetails = (ListView) view.findViewById(R.id.lv_newsdetails);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        //设置ViewPager的数据

        //获取联网请求
        url = Constants.BASE_URL + mChilderBean.getUrl();

        String saveJson = CacheUtils.getString(mContext, url);
        //解析数据
        if (!TextUtils.isEmpty(saveJson)) {
            //解析数据
            processData(saveJson);
        }
        getDataFromNet();
    }


    public void getDataFromNet() {
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //缓存数据
                CacheUtils.putString(mContext, url, result);
                //解析数据
                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "请求失败" + ex);
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

    //解析数据
    private void processData(String saveJson) {
        TableDetailPagerBean tableDetailPagerBean = parsedJson(saveJson);
        String title = tableDetailPagerBean.getData().getNews().get(0).getTitle();
    }

    private TableDetailPagerBean parsedJson(String saveJson) {
        return new Gson().fromJson(saveJson, TableDetailPagerBean.class);
    }
}