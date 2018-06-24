package com.example.pctruong.appbanhang.view.Activity;

import android.app.Application;

import com.example.pctruong.appbanhang.model.api.ApiClient;
import com.example.pctruong.appbanhang.model.api.ApiInterface;

/**
 * Created by PCTruong on 05/06/2018.
 */

public class Myapplication extends Application {
    public static ApiInterface apiService;
    @Override
    public void onCreate() {
        super.onCreate();
        apiService =
                ApiClient.getClient().create(ApiInterface.class);
    }
}
