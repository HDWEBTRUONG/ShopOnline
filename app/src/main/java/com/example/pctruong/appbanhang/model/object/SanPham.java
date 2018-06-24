package com.example.pctruong.appbanhang.model.object;

import java.io.Serializable;

/**
 * Created by PCTruong on 04/06/2018.
 */

public class SanPham implements Serializable {
    public int masp, gia, soluong, tongsoluong, soluongdanhgia, soluongyeuthich;
    public float soluongsao;
    public int tongtien;
    public float sosao() {
        if (soluongsao > 0 && soluongdanhgia > 0) {
            return soluongsao / soluongdanhgia;
        } else return 0;
    }

    public String tensp, thongtin, hinhanh;

}
