package com.example.pctruong.appbanhang.view.Views;



import com.example.pctruong.appbanhang.model.object.ChiTietThuongHieu;

import java.util.List;

/**
 * Created by PCTruong on 02/06/2018.
 */

public interface ViewThuongHieu {
    void getChiTietThuongHieu(List<ChiTietThuongHieu> list);
    void getErrorThuongHieu(String s);
}
