package com.example.pctruong.appbanhang.presenter.PresenterTimKiem;

import com.example.pctruong.appbanhang.model.object.BaseRespones.ResponeDSSanPham;
import com.example.pctruong.appbanhang.view.Views.ViewTimKiem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pctruong.appbanhang.view.Activity.Myapplication.apiService;

/**
 * Created by PCTruong on 17/06/2018.
 */

public class PresenterTimKiem implements IPresenterTimKiem {
    ViewTimKiem viewTimKiem;

    public PresenterTimKiem(ViewTimKiem viewTimKiem) {
        this.viewTimKiem = viewTimKiem;
    }

    @Override
    public void timKiemSanPham(String tensp) {
        Call<ResponeDSSanPham> call=apiService.timKiemSanPham(tensp);
        call.enqueue(new Callback<ResponeDSSanPham>() {
            @Override
            public void onResponse(Call<ResponeDSSanPham> call, Response<ResponeDSSanPham> response) {
                if(response.body().success){
                    viewTimKiem.layDSTimKiem(response.body().sanpham);
                }
            }

            @Override
            public void onFailure(Call<ResponeDSSanPham> call, Throwable t) {

            }
        });

    }
}
