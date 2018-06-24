package com.example.pctruong.appbanhang.presenter.PresenterUser;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.pctruong.ManagerSharedPreference;
import com.example.pctruong.appbanhang.model.data.XuLyDataBase;
import com.example.pctruong.appbanhang.model.object.BaseRespones.BaseRespone;
import com.example.pctruong.appbanhang.view.Views.ViewLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pctruong.appbanhang.view.Activity.Myapplication.apiService;

/**
 * Created by PCTruong on 19/06/2018.
 */

public class PresenterUser implements IPresenterUser {
    ViewLogin viewLogin;
    XuLyDataBase xuLyDataBase;

    public PresenterUser(ViewLogin viewLogin, Context context) {
        this.viewLogin = viewLogin;
        xuLyDataBase=new XuLyDataBase(context);

    }

    @Override
    public void loginUser(String email, String pass) {
        Call<BaseRespone> call = apiService.loginUser(email, pass);
        call.enqueue(new Callback<BaseRespone>() {
            @Override
            public void onResponse(Call<BaseRespone> call, Response<BaseRespone> response) {
                if (response.body().success) {
                    viewLogin.loginThanhCong(response.body().message);
                    int count=xuLyDataBase.getUser();
                    if(count==0){
                        xuLyDataBase.ThemUser(response.body().name,response.body().uid,email);
                    }
                } else {
                    viewLogin.loginKhongThanhCong(response.body().message);
                }
            }

            @Override
            public void onFailure(Call<BaseRespone> call, Throwable t) {
                viewLogin.loginKhongThanhCong(t.getMessage().toString());
            }
        });
    }

    @Override
    public void registerUser(String email, String pass, String name) {
        Call<BaseRespone> call = apiService.registerUser(email, pass, name);
        call.enqueue(new Callback<BaseRespone>() {
            @Override
            public void onResponse(Call<BaseRespone> call, Response<BaseRespone> response) {
                if (response.body().success) {
                    viewLogin.loginThanhCong(response.body().message);
                    xuLyDataBase.ThemUser(name,response.body().uid,email);
                } else {
                    viewLogin.loginKhongThanhCong(response.body().message);
                }
            }

            @Override
            public void onFailure(Call<BaseRespone> call, Throwable t) {
                viewLogin.loginKhongThanhCong(t.getMessage().toString());
            }
        });
    }
}
