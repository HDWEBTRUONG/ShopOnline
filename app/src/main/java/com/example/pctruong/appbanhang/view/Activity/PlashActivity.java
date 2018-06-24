package com.example.pctruong.appbanhang.view.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pctruong.ManagerSharedPreference;
import com.example.pctruong.UIUtils;
import com.example.pctruong.appbanhang.R;
import com.example.pctruong.appbanhang.model.data.CreateTable;
import com.example.pctruong.appbanhang.model.data.XuLyDataBase;
import com.example.pctruong.appbanhang.push_fcm.ActivitySendPushNotification;
import com.example.pctruong.appbanhang.push_fcm.EndPoints;
import com.example.pctruong.appbanhang.push_fcm.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class PlashActivity extends AppCompatActivity {
    ManagerSharedPreference managerSharedPreference;
    String token;
    XuLyDataBase xuLyDataBase;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_plash);
        xuLyDataBase=new XuLyDataBase(this);

        HashMap<String,String> user=xuLyDataBase.getUserDetails();
        email=user.get(CreateTable.TB_EMAIL);

        managerSharedPreference=new ManagerSharedPreference(this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (UIUtils.isOnline(PlashActivity.this)) {
                    token = SharedPrefManager.getInstance(PlashActivity.this).getDeviceToken();
                    sendTokenToServer();
                    if(managerSharedPreference.isLoggedIn()){
                       startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }else {
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    }
                    finish();
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                } else {
                    Toast.makeText(PlashActivity.this, "No connect Internet", Toast.LENGTH_SHORT).show();
                }
            }
        }, 10000);




    }

    private void sendTokenToServer() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoints.URL_REGISTER_DEVICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(PlashActivity.this, obj.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PlashActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("token",token);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
