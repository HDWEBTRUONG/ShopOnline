package com.example.pctruong.appbanhang.model.api;



import com.example.pctruong.appbanhang.model.object.BaseRespones.BaseRespone;
import com.example.pctruong.appbanhang.model.object.BaseRespones.ResponeBinhLuan;
import com.example.pctruong.appbanhang.model.object.BaseRespones.ResponeCTThuongHieu;
import com.example.pctruong.appbanhang.model.object.BaseRespones.ResponeDSSanPham;
import com.example.pctruong.appbanhang.model.object.BaseRespones.ResponeSoSao;
import com.example.pctruong.appbanhang.model.object.BaseRespones.ResponeThuongHieu;
import com.example.pctruong.appbanhang.model.object.BinhLuan;
import com.example.pctruong.appbanhang.model.object.DanhSachSoSao;
import com.example.pctruong.appbanhang.model.object.SanPham;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by PCTruong on 02/06/2018.
 */

public interface ApiInterface {
    @GET("sanpham/chitietthuonghieu.php")
    Call<ResponeCTThuongHieu> getChiTietThuongHieu();


    @GET("sanpham/thuonghieu.php")
    Call<ResponeThuongHieu> getThuongHieu();

    @GET("sanpham/danhsachsanpham.php")
    Call<ResponeDSSanPham> getListProductId(@Query("mathuonghieu") int mathuonghieu ,@Query("sort") String sort);

    @GET("sanpham/timkiemsanpham.php")
    Call<ResponeDSSanPham> timKiemSanPham(@Query("tensp") String tensp);

    @POST("fcm/RegisterDevice.php")
    Call<BaseRespone> registerDevice(@Field("token") String token, @Field("email") String email);

    @GET("binhluan/laybinhluantheoma.php")
    Call<ResponeBinhLuan> layBinhLuanTheoMa(@Query("masp") int masp);

    @FormUrlEncoded
    @POST("binhluan/thembinhluan.php")
    Call<BaseRespone> addBinhLuan(@Field("masp") int masp ,@Field("noidung") String noidung,@Field("sosao") int sosao,@Field("username") String username);



    @FormUrlEncoded
    @POST("love/themlove.php")
    Call<BaseRespone> addLove(@Field("masp") int masp ,@Field("love") String love);

    @FormUrlEncoded
    @POST("sanpham/themhoadon.php")
    Call<BaseRespone> addMuaHang(@Field("hoten") String hoten,@Field("diachi") String diachi ,
                                 @Field("sodt") String sodt,@Field("email") String email,
                                 @Field("masp") int masp ,@Field("soluong") int soluong
                                 );

    @GET("binhluan/demsobinhluan.php")
    Call<BaseRespone> getCountComment(@Query("masp") int masp);

    @GET("love/deletelove.php")
    Call<BaseRespone> deleteLove(@Query("malove") int malove);

    @GET("sanpham/laydanhsachsosao.php")
    Call<ResponeSoSao> layDanhSachSoSao(@Query("masp") int masp);

    @FormUrlEncoded
    @POST("login/login.php")
    Call<BaseRespone> loginUser(@Field("email") String email,@Field("password") String password);

    @FormUrlEncoded
    @POST("login/register.php")
    Call<BaseRespone> registerUser(@Field("email") String email,@Field("password") String password,@Field("name") String name);


}
