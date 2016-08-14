package com.atguigu.yangyuanyuan.news.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.atguigu.yangyuanyuan.news.R;

import java.util.ArrayList;

public class GuideActivity extends Activity {
    private ViewPager vp_guide;
    private Button btn_guide_start;
    private LinearLayout ll_guide_points;
    private ArrayList<ImageView> imageViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();
        initData();
    }

    private void initData() {
        int[] pic = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
        imageViews = new ArrayList<>();
        for (int i = 0; i < pic.length; i++) {
            ImageView imageView = new ImageView(this);
            //设置背景
            imageView.setBackgroundResource(pic[i]);
            imageViews.add(imageView);

            //创建点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_normal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
            if (i != 0) {
                params.leftMargin = 10;
            }
            point.setLayoutParams(params);

            ll_guide_points.addView(point);
        }

        vp_guide.setAdapter(new ViewPagerAdapter());
    }

    private void initView() {
        vp_guide = (ViewPager) findViewById(R.id.vp_guide);
        btn_guide_start = (Button) findViewById(R.id.btn_guide_start);
        ll_guide_points = (LinearLayout) findViewById(R.id.ll_guide_points);
    }

    //ViewPagerAdapter
    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //container 是ViewPager的容器
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViews.get(position));
        }
    }
}
