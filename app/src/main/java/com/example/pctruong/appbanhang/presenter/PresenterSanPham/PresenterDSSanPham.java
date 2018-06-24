package com.example.pctruong.appbanhang.presenter.PresenterSanPham;



import com.example.pctruong.appbanhang.model.api.ApiClient;
import com.example.pctruong.appbanhang.model.api.ApiInterface;
import com.example.pctruong.appbanhang.model.object.BaseRespones.ResponeDSSanPham;
import com.example.pctruong.appbanhang.view.Views.ViewDSSanPham;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pctruong.appbanhang.view.Activity.Myapplication.apiService;

/**
 * Created by PCTruong on 04/06/2018.
 */

public class PresenterDSSanPham implements IPresenterDSSanPham {
    ViewDSSanPham viewDSSanPham;
    public PresenterDSSanPham(ViewDSSanPham viewDSSanPham) {
        this.viewDSSanPham = viewDSSanPham;
    }

    @Override
    public void getDSSanPham(int mathuonghieu,String sort) {
        Call<ResponeDSSanPham> call=apiService.getListProductId(mathuonghieu,sort);
        call.enqueue(new Callback<ResponeDSSanPham>() {
            @Override
            public void onResponse(Call<ResponeDSSanPham> call, Response<ResponeDSSanPham> response) {
                if(response.body().success){
                    viewDSSanPham.getDSSanPham(response.body().sanpham);
                }else {
                    viewDSSanPham.getError(response.body().message);
                }
            }

            @Override
            public void onFailure(Call<ResponeDSSanPham> call, Throwable t) {
                 viewDSSanPham.getError(t.getMessage());
            }
        });
    }
}
