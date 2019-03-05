package com.fajarazay.github.bolaapp;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Fajar Septian on 2019-03-05.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
