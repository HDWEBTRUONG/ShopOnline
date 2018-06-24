package com.example.pctruong.appbanhang.model.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PCTruong on 02/06/2018.
 */

public class ApiClient {
    public static final String BASE_URL = "http://192.168.1.90/weblazada/";
  //  public static final String BASE_URL = "http://192.168.11.104/weblazada/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
