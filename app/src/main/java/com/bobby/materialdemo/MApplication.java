package com.bobby.materialdemo;

import android.app.Application;

import com.bobby.materialdemo.network.RequestManager;

/**
 * Created by ting on 15/4/23.
 */
public class MApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        RequestManager.init(this);
    }
}
