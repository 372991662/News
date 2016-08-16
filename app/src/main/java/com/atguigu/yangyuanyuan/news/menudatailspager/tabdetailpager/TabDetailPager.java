package com.atguigu.yangyuanyuan.news.menudatailspager.tabdetailpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.yangyuanyuan.news.R;
import com.atguigu.yangyuanyuan.news.base.MenuDetailBasePager;
import com.atguigu.yangyuanyuan.news.domain.NewsCenterPagerBean;
import com.atguigu.yangyuanyuan.news.domain.TableDetailPagerBean;
import com.atguigu.yangyuanyuan.news.utils.CacheUtils;
import com.atguigu.yangyuanyuan.news.utils.Constants;
import com.atguigu.yangyuanyuan.news.utils.DensityUtil;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by 杨媛媛 on 2016/8/16 19:20.
 */

//页签详情页面
public class TabDetailPager extends MenuDetailBasePager {
    private ViewPager vp_newsdetails;
    private TextView tv_title;
    private LinearLayout ll_newsdetails;
    private ListView lv_newsdetails;
    private int lastPosition;
    private NewsCenterPagerBean.DataBean.ChildrenBean mChilderBean = new NewsCenterPagerBean
            .DataBean.ChildrenBean();
    private String url;
    //顶部新闻数据
    private List<TableDetailPagerBean.DataBean.TopnewsBean> topnews;

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
        //获取顶部轮播图数据集合
        topnews = tableDetailPagerBean.getData()
                .getTopnews();
        //设置ViewPager数据
        vp_newsdetails.setAdapter(new NewsTopViewPagerAdapter());
        //添加小红点
        addPoint();
        //设置小红点移动和文本变化
        setPointData();

    }

    private void setPointData() {
        //监听页面变化
        vp_newsdetails.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //设置文本
                tv_title.setText(topnews.get(position).getTitle());
                //红点高亮
                //从线性布局取红点
                ImageView lastIv = (ImageView) ll_newsdetails.getChildAt(lastPosition);
                lastIv.setEnabled(false);

                ImageView iv = (ImageView) ll_newsdetails.getChildAt(position);
                iv.setEnabled(true);


                lastPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //默认显示第一条顶部新闻
        tv_title.setText(topnews.get(0).getTitle());
    }

    //添加小红点放发
    private void addPoint() {
        //-------必须移除小红点先
        ll_newsdetails.removeAllViews();
        //添加小点
        for (int i = 0; i < topnews.size(); i++) {
            ImageView iv = new ImageView(mContext);
            iv.setImageResource(R.drawable.point_selector);

            //设置点和点之间间距
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dip2px
                    (mContext, 8), DensityUtil.dip2px(mContext, 8));
            params.rightMargin = 8;
            iv.setLayoutParams(params);

            //默认选中首个红点
            if (i == 0) {
                iv.setEnabled(true);
            } else {
                iv.setEnabled(false);
            }


            ll_newsdetails.addView(iv);
        }
    }

    private TableDetailPagerBean parsedJson(String saveJson) {
        return new Gson().fromJson(saveJson, TableDetailPagerBean.class);
    }

    class NewsTopViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return topnews == null ? 0 : topnews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = new ImageView(mContext);
            iv.setBackgroundResource(R.drawable.home_scroll_default);
            container.addView(iv);
            //联网请求图片
            x.image().bind(iv, Constants.BASE_URL + topnews.get(position).getTopimage());
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}