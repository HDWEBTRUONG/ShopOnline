package com.example.pctruong.appbanhang.view.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pctruong.ManagerSharedPreference;
import com.example.pctruong.appbanhang.R;
import com.example.pctruong.appbanhang.presenter.PresenterUser.PresenterUser;
import com.example.pctruong.appbanhang.view.Views.ViewLogin;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ViewLogin {
    @BindView(R.id.txtDangKi)
    TextView txtDangKi;

    @BindView(R.id.edtMatKhau)
    EditText edtMatKhau;

    @BindView(R.id.edEmail)
    EditText edEmail;

    @BindView(R.id.btnDangNhap)
    Button btnDangNhap;
    PresenterUser presenterUser;

    ManagerSharedPreference managerSharedPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         managerSharedPreference=new ManagerSharedPreference(this);
        if(managerSharedPreference.isLoggedIn()){
            Intent itmain = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(itmain);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        presenterUser=new PresenterUser(this,this);
        btnDangNhap.setOnClickListener(v->{
            if(edEmail.getText().toString().trim().isEmpty()||edtMatKhau.getText().toString().trim().isEmpty()){
                Toast.makeText(this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }
            presenterUser.loginUser(edEmail.getText().toString().trim(),edtMatKhau.getText().toString().trim());
        });

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.txtDangKi)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.txtDangKi:
                Intent intent=new Intent(this,SignUpActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;
        }
    }

    @Override
    public void loginThanhCong(String message) {
        managerSharedPreference.setLogin(true);
        Intent itmain = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(itmain);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }

    @Override
    public void loginKhongThanhCong(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
