package com.atguigu.yangyuanyuan.news.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/8/13.
 */
public class CacheUtils {


    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("News", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }
}
