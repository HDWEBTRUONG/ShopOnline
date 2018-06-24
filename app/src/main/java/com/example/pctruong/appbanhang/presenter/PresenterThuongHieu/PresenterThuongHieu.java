package com.example.pctruong.appbanhang.presenter.PresenterThuongHieu;



import com.example.pctruong.appbanhang.model.api.ApiClient;
import com.example.pctruong.appbanhang.model.api.ApiInterface;
import com.example.pctruong.appbanhang.model.object.BaseRespones.ResponeCTThuongHieu;
import com.example.pctruong.appbanhang.view.Views.ViewThuongHieu;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pctruong.appbanhang.view.Activity.Myapplication.apiService;

/**
 * Created by PCTruong on 02/06/2018.
 */

public class PresenterThuongHieu implements IPresenterThuongHieu {
    ViewThuongHieu viewThuongHieu;

    public PresenterThuongHieu(ViewThuongHieu viewThuongHieu) {
        this.viewThuongHieu = viewThuongHieu;

    }


    @Override
    public void getChiTietThuongHieu() {
        Call<ResponeCTThuongHieu> call = apiService.getChiTietThuongHieu();
        call.enqueue(new Callback<ResponeCTThuongHieu>() {
            @Override
            public void onResponse(Call<ResponeCTThuongHieu> call, Response<ResponeCTThuongHieu> response) {
                if (response.body().success) {
                    viewThuongHieu.getChiTietThuongHieu(response.body().chitietthuonghieu);
                }else {
                    viewThuongHieu.getErrorThuongHieu(response.body().message);
                }
            }

            @Override
            public void onFailure(Call<ResponeCTThuongHieu> call, Throwable t) {
                viewThuongHieu.getErrorThuongHieu(t.getMessage());
            }
        });

    }

}
