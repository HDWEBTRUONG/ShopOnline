package com.example.pctruong.appbanhang.model.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.pctruong.appbanhang.model.object.KhachHang;
import com.example.pctruong.appbanhang.model.object.SanPham;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by PCTruong on 13/06/2018.
 */

public class XuLyDataBase {

    Context context;
    SQLiteDatabase db;
    CreateTable createTable;

    public XuLyDataBase(Context context) {
        this.context = context;
        createTable = new CreateTable(context);
    }

    public boolean ThemSanPham(SanPham sanpham) {
        db = createTable.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CreateTable.TB_MASP, sanpham.masp);
        values.put(CreateTable.TB_TENSP, sanpham.tensp);
        values.put(CreateTable.TB_SOLUONG, 1);
        values.put(CreateTable.TB_TONGSOLUONG, sanpham.soluong);
        values.put(CreateTable.TB_GIA, sanpham.gia);
        values.put(CreateTable.TB_HINHANHSP, sanpham.hinhanh);
        values.put(CreateTable.TB_TONGTIEN, sanpham.gia);
        long id = db.insert(CreateTable.TB_MuaHang, null, values);
        if (id > 0) {
            return true;
        }
        return false;
    }

    public boolean ThemUser(String name, String uid,String email) {
        db = createTable.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CreateTable.TB_NAME, name);
        values.put(CreateTable.TB_UID, uid);
        values.put(CreateTable.TB_EMAIL,email);
        long id = db.insert(CreateTable.TB_USER, null, values);
        if (id > 0) {
            return true;
        }
        return false;
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "select * from " + CreateTable.TB_USER;

        db = createTable.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put(CreateTable.TB_NAME, cursor.getString(0));
            user.put(CreateTable.TB_UID, cursor.getString(1));
            user.put(CreateTable.TB_EMAIL,cursor.getString(2));
        }
        cursor.close();
        db.close();
        return user;
    }

    public boolean ThemKhachhang(KhachHang khachHang) {
        db = createTable.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CreateTable.TB_TENKH, khachHang.ten);
        values.put(CreateTable.TB_DIACHI, khachHang.diachi);
        values.put(CreateTable.TB_DT, khachHang.phone);
        values.put(CreateTable.TB_EMAIL, khachHang.email);
        long id = db.insert(CreateTable.TB_KHACHHANG, null, values);
        if (id > 0) {
            return true;
        }
        return false;
    }

    public boolean ThemSanPhamYeuThich(SanPham sanpham) {
        db = createTable.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CreateTable.TB_MASP, sanpham.masp);
        values.put(CreateTable.TB_TENSP, sanpham.tensp);
        values.put(CreateTable.TB_SOLUONG, 1);
        values.put(CreateTable.TB_GIA, sanpham.gia);
        values.put(CreateTable.TB_TONGSOLUONG, sanpham.soluong);
        values.put(CreateTable.TB_TONGTIEN, sanpham.gia);
        values.put(CreateTable.TB_HINHANHSP, sanpham.hinhanh);
        long id = db.insert(CreateTable.TB_YeuThich, null, values);
        if (id > 0) {
            return true;
        }
        return false;
    }

    public int getUser() {
        db = createTable.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + CreateTable.TB_USER, null);
        if (cursor.getCount() > 0) {
            return 1;
        }
        return 0;
    }

    public List<SanPham> LaySanPhamMuaHang() {
        db = createTable.getReadableDatabase();
        List<SanPham> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(" select * from " + CreateTable.TB_MuaHang, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SanPham sanPham = new SanPham();
            sanPham.masp = (cursor.getInt(cursor.getColumnIndex(CreateTable.TB_MASP)));
            sanPham.tensp = (cursor.getString(cursor.getColumnIndex(CreateTable.TB_TENSP)));
            sanPham.tongsoluong = (cursor.getInt(cursor.getColumnIndex(CreateTable.TB_TONGSOLUONG)));
            sanPham.soluong = (cursor.getInt(cursor.getColumnIndex(CreateTable.TB_SOLUONG)));
            sanPham.gia = (cursor.getInt(cursor.getColumnIndex(CreateTable.TB_GIA)));
            sanPham.hinhanh = (cursor.getString(cursor.getColumnIndex(CreateTable.TB_HINHANHSP)));
            sanPham.tongtien = (cursor.getInt(cursor.getColumnIndex(CreateTable.TB_TONGTIEN)));
            list.add(sanPham);
            cursor.moveToNext();
        }
        return list;
    }

    public List<SanPham> LaySanPhamYeuThich() {
        db = createTable.getReadableDatabase();
        List<SanPham> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(" select * from " + CreateTable.TB_YeuThich, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SanPham sanPham = new SanPham();
            sanPham.masp = (cursor.getInt(cursor.getColumnIndex(CreateTable.TB_MASP)));
            sanPham.tensp = (cursor.getString(cursor.getColumnIndex(CreateTable.TB_TENSP)));
            sanPham.tongsoluong = (cursor.getInt(cursor.getColumnIndex(CreateTable.TB_TONGSOLUONG)));
            sanPham.soluong = (cursor.getInt(cursor.getColumnIndex(CreateTable.TB_SOLUONG)));
            sanPham.gia = (cursor.getInt(cursor.getColumnIndex(CreateTable.TB_GIA)));
            sanPham.hinhanh = (cursor.getString(cursor.getColumnIndex(CreateTable.TB_HINHANHSP)));
            sanPham.tongtien = (cursor.getInt(cursor.getColumnIndex(CreateTable.TB_TONGTIEN)));
            list.add(sanPham);
            cursor.moveToNext();
        }
        return list;
    }


    public boolean CapNhatLaiDuLieu(int masp, int soluong, int gia) {
        db = createTable.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(CreateTable.TB_SOLUONG, soluong);
        values.put(CreateTable.TB_TONGTIEN, soluong * gia);
        long id = db.update(CreateTable.TB_MuaHang, values, CreateTable.TB_MASP + " = " + masp, null);
        if (id != 0) {
            return true;
        }
        return false;
    }

    public boolean CapNhatLaiDuLieuYeuThich(int masp, int soluong, int gia) {
        db = createTable.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(CreateTable.TB_SOLUONG, soluong);
        values.put(CreateTable.TB_TONGTIEN, soluong * gia);
        long id = db.update(CreateTable.TB_YeuThich, values, CreateTable.TB_MASP + " = " + masp, null);
        if (id != 0) {
            return true;
        }
        return false;
    }

    public boolean XoaSanPhamTheoMa(int masp) {
        db = createTable.getReadableDatabase();
        long id = db.delete(CreateTable.TB_MuaHang, CreateTable.TB_MASP + "=?", new String[]{String.valueOf(masp)});
        if (id != 0) {
            return true;
        }
        return false;
    }

    public boolean XoaSanPhamYeuThichTheoMa(int masp) {
        db = createTable.getReadableDatabase();
        long id = db.delete(CreateTable.TB_YeuThich, CreateTable.TB_MASP + "=?", new String[]{String.valueOf(masp)});
        if (id != 0) {
            return true;
        }
        return false;
    }

    public boolean XoaTable() {
        db = createTable.getReadableDatabase();
        long id = db.delete(CreateTable.TB_MuaHang, null, null);
        if (id != 0) {
            return true;
        }
        return false;
    }

}
