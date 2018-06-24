package com.example.pctruong.appbanhang.presenter.PresenterMuaHang;

import android.app.Activity;

import com.example.pctruong.UIUtils;
import com.example.pctruong.appbanhang.model.data.XuLyDataBase;
import com.example.pctruong.appbanhang.model.object.BaseRespones.BaseRespone;
import com.example.pctruong.appbanhang.model.object.KhachHang;
import com.example.pctruong.appbanhang.model.object.SanPham;
import com.example.pctruong.appbanhang.view.Views.ViewMuaHang;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pctruong.appbanhang.view.Activity.Myapplication.apiService;

/**
 * Created by PCTruong on 17/06/2018.
 */

public class PresenterMuaHang implements IPresenterMuaHang {
    ViewMuaHang muaHang;
    XuLyDataBase xuLyDataBase;
    public PresenterMuaHang(ViewMuaHang muaHang) {
        this.muaHang = muaHang;
    }

    @Override
    public void muahang(KhachHang khachHang , SanPham sanPham, Activity context) {
        Call<BaseRespone> call=apiService.addMuaHang(khachHang.ten,khachHang.diachi,
                       khachHang.phone,khachHang.email,sanPham.masp,sanPham.soluong
            );
        call.enqueue(new Callback<BaseRespone>() {
            @Override
            public void onResponse(Call<BaseRespone> call, Response<BaseRespone> response) {
                if(response.body().success){
                    muaHang.muaThanhCong(response.body().message);
                }else {
                    muaHang.muaKhongThanhCong(response.body().message);
                }
            }

            @Override
            public void onFailure(Call<BaseRespone> call, Throwable t) {
                if(!UIUtils.isOnline((Activity) context)) {
                    muaHang.muaKhongThanhCong(t.getMessage().toString());
                    xuLyDataBase=new XuLyDataBase(context);
                    xuLyDataBase.ThemKhachhang(khachHang);
                }
            }
        });
    }
}
