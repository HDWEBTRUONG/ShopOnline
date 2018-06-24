package com.example.pctruong.appbanhang.presenter.PresenterBinhLuan;

import com.example.pctruong.appbanhang.model.api.ApiClient;
import com.example.pctruong.appbanhang.model.object.BaseRespones.BaseRespone;
import com.example.pctruong.appbanhang.model.object.BaseRespones.ResponeBinhLuan;
import com.example.pctruong.appbanhang.model.object.BaseRespones.ResponeSoSao;
import com.example.pctruong.appbanhang.model.object.BinhLuan;
import com.example.pctruong.appbanhang.model.object.DanhSachSoSao;
import com.example.pctruong.appbanhang.view.Views.ViewBinhLuan;
import com.example.pctruong.appbanhang.view.Views.ViewCountComment;
import com.example.pctruong.appbanhang.view.Views.ViewSoSao;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pctruong.appbanhang.view.Activity.Myapplication.apiService;

/**
 * Created by PCTruong on 05/06/2018.
 */

public class PresenterBinhLuan implements IPresenterBinhLuan{
    ViewBinhLuan viewBinhLuan;
    ViewCountComment viewCountComment;
    ViewSoSao viewSoSao;
    public PresenterBinhLuan(ViewCountComment viewCountComment ) {
        this.viewCountComment = viewCountComment;
    }

    public PresenterBinhLuan(ViewBinhLuan viewBinhLuan , ViewSoSao viewSoSao) {
        this.viewBinhLuan = viewBinhLuan;
        this.viewSoSao=viewSoSao;
    }



    @Override
    public void themBinhLuan(BinhLuan binhLuan) {
        Call<BaseRespone> call=apiService.addBinhLuan(binhLuan.masp,binhLuan.noidung,binhLuan.sosao,binhLuan.username);
        call.enqueue(new Callback<BaseRespone>() {
            @Override
            public void onResponse(Call<BaseRespone> call, Response<BaseRespone> response) {
                if(response.body().success){
                    viewBinhLuan.binhluanThanhCong(response.body().message);
                }else {
                    viewBinhLuan.binhluankThanhCong(response.body().message);
                }
            }

            @Override
            public void onFailure(Call<BaseRespone> call, Throwable t) {
                  viewBinhLuan.binhluankThanhCong(call.toString());
            }
        });
    }

    @Override
    public void layBinhLuanTheoMa(int masp) {
        Call<ResponeBinhLuan> call=apiService.layBinhLuanTheoMa(masp);
        call.enqueue(new Callback<ResponeBinhLuan>() {
            @Override
            public void onResponse(Call<ResponeBinhLuan> call, Response<ResponeBinhLuan> response) {
                  if(response.body().success) {
                      viewBinhLuan.layBinhLuan(response.body().binhluan);
                  }else {
                      viewBinhLuan.binhluankThanhCong(response.body().message);
                  }
            }

            @Override
            public void onFailure(Call<ResponeBinhLuan> call, Throwable t) {
                viewBinhLuan.binhluankThanhCong(call.toString());
            }
        });
    }

    @Override
    public void countComment(int masp) {
        Call<BaseRespone> call=apiService.getCountComment(masp);
        call.enqueue(new Callback<BaseRespone>() {
            @Override
            public void onResponse(Call<BaseRespone> call, Response<BaseRespone> response) {
                if(!response.body().success){
                }else {
                    viewCountComment.error(response.body().message);
                }
            }

            @Override
            public void onFailure(Call<BaseRespone> call, Throwable t) {
               viewCountComment.error(t.getMessage());
            }
        });
    }

    @Override
    public void layDanhSachSoSao(int masp) {
        Call<ResponeSoSao> call=apiService.layDanhSachSoSao(masp);
        call.enqueue(new Callback<ResponeSoSao>() {
            @Override
            public void onResponse(Call<ResponeSoSao> call, Response<ResponeSoSao> response) {
                if(response.body().success) {
                    viewSoSao.layDanhSachSao(response.body().sosao);
                }else {
                   viewSoSao.layDanhSachSaoLoi(response.body().message);
                }
            }

            @Override
            public void onFailure(Call<ResponeSoSao> call, Throwable t) {
                viewSoSao.layDanhSachSaoLoi(call.toString());
            }
        });

    }
}
