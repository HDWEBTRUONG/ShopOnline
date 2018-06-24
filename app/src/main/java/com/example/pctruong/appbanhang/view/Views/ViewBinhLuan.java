package com.example.pctruong.appbanhang.view.Views;

import com.example.pctruong.appbanhang.model.object.BinhLuan;

import java.util.List;

/**
 * Created by PCTruong on 05/06/2018.
 */

public interface ViewBinhLuan {
    void binhluanThanhCong(String message);
    void binhluankThanhCong(String message);
    void layBinhLuan(List<BinhLuan> binhLuanList);
}
