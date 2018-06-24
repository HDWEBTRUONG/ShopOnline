package com.example.pctruong.appbanhang.view.Activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.pctruong.Constants;
import com.example.pctruong.appbanhang.R;
import com.example.pctruong.appbanhang.model.data.XuLyDataBase;
import com.example.pctruong.appbanhang.model.object.KhachHang;
import com.example.pctruong.appbanhang.model.object.SanPham;
import com.example.pctruong.appbanhang.presenter.PresenterMuaHang.PresenterMuaHang;
import com.example.pctruong.appbanhang.push_fcm.ActivitySendPushNotification;
import com.example.pctruong.appbanhang.push_fcm.EndPoints;
import com.example.pctruong.appbanhang.push_fcm.MyVolley;
import com.example.pctruong.appbanhang.view.Views.ViewMuaHang;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddCartActivity extends BaseActivity implements ViewMuaHang {

    @BindView(R.id.ipName)
    TextInputLayout ipName;


    @BindView(R.id.ipPhone)
    TextInputLayout ipPhone;

    @BindView(R.id.ipAddress)
    TextInputLayout ipAddress;

    @BindView(R.id.ipEmail)
    TextInputLayout ipEmail;

    @BindView(R.id.edName)
    TextInputEditText edName;

    @BindView(R.id.edPhone)
    TextInputEditText edPhone;

    @BindView(R.id.edAddress)
    TextInputEditText edAddress;

    @BindView(R.id.edEmail)
    TextInputEditText edEmail;
    @BindView(R.id.ln_menu)
    LinearLayout ln_menu;
    @BindView(R.id.txtDongBo)
    TextView txtDongBo;
    String name, phone, address, email;

    XuLyDataBase xuLyDataBase;
    List<SanPham> sanPhamList;
    PresenterMuaHang presenterMuaHang;
    KhachHang khachHang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        xuLyDataBase = new XuLyDataBase(this);
        sanPhamList = xuLyDataBase.LaySanPhamMuaHang();
        presenterMuaHang = new PresenterMuaHang(this);




    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_addcart;
    }

    public void onClick(View view) {

        name = edName.getText().toString().trim();
        phone = edPhone.getText().toString().trim();
        address = edAddress.getText().toString().trim();
        email = edEmail.getText().toString().trim();

        if (name.isEmpty()) {
            ipName.setError("Not empty");
            ipName.setEnabled(true);

        } else {
            ipName.setError("");
            ipName.setEnabled(false);
        }

        if (phone.isEmpty()) {
            ipPhone.setError("Not empty");
            ipPhone.setEnabled(true);
        } else {
            ipPhone.setError("");
            ipPhone.setEnabled(false);
        }

        if (address.isEmpty()) {
            ipAddress.setError("Not empty");
            ipAddress.setEnabled(true);
        } else {
            ipAddress.setError("");
            ipAddress.setEnabled(false);
        }

        if (email.isEmpty()) {
            ipEmail.setError("Not empty ");
            ipEmail.setEnabled(true);
        } else {
            ipEmail.setError("");
            ipEmail.setEnabled(false);

        }

        for (int i = 0; i < sanPhamList.size(); i++) {
            SanPham sanPham = new SanPham();
            sanPham.masp = sanPhamList.get(i).masp;
            sanPham.soluong = sanPhamList.get(i).soluong;
            khachHang = new KhachHang();
            khachHang.ten = name;
            khachHang.diachi = address;
            khachHang.phone = phone;
            khachHang.email = email;
            if(!name.isEmpty()||!phone.isEmpty()||!address.isEmpty()||!email.isEmpty()) {
                edName.setHint("");
                edPhone.setHint("");
                edAddress.setHint("");
                edEmail.setHint("");
                if(!isValidEmail(email)){
                    ipEmail.setError("Nhập sai email ");
                    ipEmail.setEnabled(true);
                    return;
                }
                if(phone.length()<10){
                    ipPhone.setError("Số điện thoại nhập chưa đúng ");
                    ipPhone.setEnabled(true);
                    return;
                }
                presenterMuaHang.muahang(khachHang, sanPham,AddCartActivity.this);
            }
        }

    }

    @Override
    public void muaThanhCong(String message) {
        xuLyDataBase.XoaTable();
        sendSinglePush();
        Constants.DELETETB=true;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void sendSinglePush(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoints.URL_SEND_SINGLE_PUSH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("title", "Thông báo có khách hàng mua hàng");
                params.put("message", name);
                params.put("email", "phamvantruong@gmail.com");
                return params;
            }
        };

        MyVolley.getInstance(this).addToRequestQueue(stringRequest);

    }
    @Override
    public void muaKhongThanhCong(String message) {
        txtDongBo.setVisibility(View.GONE);
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(Constants.DELETETB){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            overridePendingTransition(0,R.anim.slide_down);
        }else {
            Intent intent=new Intent(this,Activity_AddCart.class);
            startActivity(intent);
            overridePendingTransition(0,R.anim.slide_down);
        }
    }

    public void onBackPress(View view) {
        onBackPressed();
    }

    public  boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Pattern.compile("^[^.][a-zA-Z0-9_.+-]+[^.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+[^.]").matcher(target).matches();
    }
}
