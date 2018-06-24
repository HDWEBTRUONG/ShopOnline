package com.example.pctruong.appbanhang.presenter.PresenterUser;

/**
 * Created by PCTruong on 19/06/2018.
 */

public interface IPresenterUser {
    void loginUser(String email,String pass);
    void registerUser(String name ,String email,String pass);
}
