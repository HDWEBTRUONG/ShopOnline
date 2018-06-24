package com.example.pctruong.appbanhang.presenter.PresenterBinhLuan;

import com.example.pctruong.appbanhang.model.object.BinhLuan;

/**
 * Created by PCTruong on 05/06/2018.
 */

public interface IPresenterBinhLuan {
    void themBinhLuan(BinhLuan binhLuan);
    void layBinhLuanTheoMa(int masp);
    void countComment(int masp);
    void layDanhSachSoSao(int masp);
}
