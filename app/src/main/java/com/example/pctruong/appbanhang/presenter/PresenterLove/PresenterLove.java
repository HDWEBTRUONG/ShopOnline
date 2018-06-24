package com.example.pctruong.appbanhang.presenter.PresenterLove;

import android.telecom.Call;

import com.example.pctruong.appbanhang.model.object.BaseRespones.BaseRespone;
import com.example.pctruong.appbanhang.model.object.BaseRespones.ResponeDSSanPham;
import com.example.pctruong.appbanhang.model.object.BaseRespones.ResponeLove;
import com.example.pctruong.appbanhang.model.object.Love;
import com.example.pctruong.appbanhang.view.Views.ViewLove;

import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pctruong.appbanhang.view.Activity.Myapplication.apiService;

/**
 * Created by PCTruong on 15/06/2018.
 */

public class PresenterLove implements IPresenterLove{
    ViewLove viewLove;

    public PresenterLove(ViewLove viewLove) {
        this.viewLove = viewLove;
    }

    @Override
    public void addLove(Love love) {
        retrofit2.Call<BaseRespone> call=apiService.addLove(love.masp,love.love);
        call.enqueue(new Callback<BaseRespone>() {
            @Override
            public void onResponse(retrofit2.Call<BaseRespone> call, Response<BaseRespone> response) {
                if(response.body().success){
                    viewLove.themLoveThanhCong(response.body().message);
                }else {
                    viewLove.themLoveKhongThanhCong(response.body().message);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<BaseRespone> call, Throwable t) {
                viewLove.themLoveKhongThanhCong(t.getMessage().toString());
            }
        });
    }

    @Override
    public void deleteLove(int malove) {
        retrofit2.Call<BaseRespone> call=apiService.deleteLove(malove);
        call.enqueue(new Callback<BaseRespone>() {
            @Override
            public void onResponse(retrofit2.Call<BaseRespone> call, Response<BaseRespone> response) {

            }

            @Override
            public void onFailure(retrofit2.Call<BaseRespone> call, Throwable t) {

            }
        });
    }
}
