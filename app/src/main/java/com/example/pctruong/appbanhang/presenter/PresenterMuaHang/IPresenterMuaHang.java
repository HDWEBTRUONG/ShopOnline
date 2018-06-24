package com.example.pctruong.appbanhang.presenter.PresenterMuaHang;

import android.app.Activity;
import android.content.Context;

import com.example.pctruong.appbanhang.model.object.KhachHang;
import com.example.pctruong.appbanhang.model.object.SanPham;

/**
 * Created by PCTruong on 17/06/2018.
 */

public interface IPresenterMuaHang {
    void muahang(KhachHang khachHang , SanPham sanPham, Activity context);
}
