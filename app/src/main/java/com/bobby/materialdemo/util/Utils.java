package com.bobby.materialdemo.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

/**
 * Created by bobby on 2015/4/23.
 */
public class Utils {
    public static DisplayMetrics getScreenMetrics(Context context) {
        if (context ==null) {
            return null;
        }
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    public static int getScreenHeight(Context context) {
        if (context == null) {
            return 0;
        }
        return  getScreenMetrics(context).heightPixels;
    }

    public static int dip2px(float dipValue, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2sp(int px, Context context) {
        float scaledDensity =context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / scaledDensity);
    }

    public static int px2dip(int pxValue, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
