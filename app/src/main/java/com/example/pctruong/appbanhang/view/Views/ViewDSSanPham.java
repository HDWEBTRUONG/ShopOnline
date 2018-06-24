package com.example.pctruong.appbanhang.view.Views;



import com.example.pctruong.appbanhang.model.object.SanPham;

import java.util.List;

/**
 * Created by PCTruong on 04/06/2018.
 */

public interface ViewDSSanPham {
    void getDSSanPham(List<SanPham> list);
    void getError(String s);
}
