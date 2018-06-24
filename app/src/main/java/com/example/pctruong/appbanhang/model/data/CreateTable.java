package com.example.pctruong.appbanhang.model.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import retrofit2.http.PUT;

/**
 * Created by PhamTruong on 30/05/2017.
 */

public class CreateTable extends SQLiteOpenHelper {
    private static final String DB_NAME="MuaHangDB";
    public static String TB_MuaHang="MuaHang";
    public static String TB_KHACHHANG="KHACHHANG";
    public static String TB_USER="TB_USER";
    public static String TB_YeuThich="YeuThich";
    public static String TB_MASP="MASP";
    public static String TB_TENSP="TENSP";
    public static String TB_SOLUONG="SOLUONG";
    public static String TB_TONGSOLUONG="TONGSOLUONG";
    public static String TB_GIA="GIA";
    public static String TB_HINHANHSP="HINHANHSP";
    public static String TB_TONGTIEN="TONGTIEN";
    public static String TB_TENKH="TENKH";
    public static String TB_DT="DIENTHOAI";
    public static String TB_EMAIL="EMAIL";
    public static String TB_DIACHI="DIACHI";
    public static String TB_NAME="NAME";
    public static String TB_UID="UID";



    public CreateTable(Context context) {
        super(context,DB_NAME,null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbMuHang=" create table " +TB_MuaHang+
                "(" + TB_MASP + " integer primary key ," + TB_TENSP + " text," +
                TB_SOLUONG + " integer, " + TB_TONGSOLUONG + " integer, "  + TB_GIA + " integer , " + TB_HINHANHSP + "  text , " + TB_TONGTIEN + "  integer "+
                ")";
        String tbyeuthich=" create table " +TB_YeuThich+
                "(" + TB_MASP + " integer primary key ," + TB_TENSP + " text," +
                TB_SOLUONG + " integer, "  + TB_TONGSOLUONG + " integer, " + TB_GIA + " integer , " + TB_HINHANHSP + " text ," +  TB_TONGTIEN+ " integer " +
                ")";

        String tbkhachhang=" create table " + TB_KHACHHANG + "("+ TB_TENKH + "text primary key , "  + TB_DIACHI +" text ,"
                + TB_DT + "text ," + TB_EMAIL + " text  "  + " )";
        String tbuser="create table " +TB_USER + "(" + TB_NAME + " text primary key , " + TB_UID + " text , " + TB_EMAIL + " text " +")";
        db.execSQL(tbMuHang);
        db.execSQL(tbyeuthich);
        db.execSQL(tbkhachhang);
        db.execSQL(tbuser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TB_MuaHang);
        db.execSQL("drop table if exists " + TB_YeuThich);
        db.execSQL("drop table if exists " + TB_USER);
        onCreate(db);
    }
}
