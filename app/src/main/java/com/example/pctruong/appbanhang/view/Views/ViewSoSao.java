package com.example.pctruong.appbanhang.view.Views;

import com.example.pctruong.appbanhang.model.object.BaseRespones.ResponeSoSao;
import com.example.pctruong.appbanhang.model.object.DanhSachSoSao;

import java.util.List;

/**
 * Created by PCTruong on 17/06/2018.
 */

public interface ViewSoSao {
    void layDanhSachSao(List<DanhSachSoSao> list);
    void layDanhSachSaoLoi(String message);
}
