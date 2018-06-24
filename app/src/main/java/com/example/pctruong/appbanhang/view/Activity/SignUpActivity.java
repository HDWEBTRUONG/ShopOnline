package com.example.pctruong.appbanhang.view.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pctruong.ManagerSharedPreference;
import com.example.pctruong.appbanhang.R;
import com.example.pctruong.appbanhang.presenter.PresenterUser.PresenterUser;
import com.example.pctruong.appbanhang.view.Views.ViewLogin;

import butterknife.BindView;

public class SignUpActivity extends BaseActivity implements ViewLogin{
    @BindView(R.id.edName)
    EditText edName;
    @BindView(R.id.edEmail)
    EditText edEmail;
    @BindView(R.id.edPassword)
    EditText edPassword;
    @BindView(R.id.btnDangKi)
    Button btnDangKi;
    @BindView(R.id.btnLinkToLoginScreen)
    Button btnLinkToLoginScreen;

    PresenterUser presenterUser;
    ManagerSharedPreference managerSharedPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        managerSharedPreference=new ManagerSharedPreference(this);
        presenterUser=new PresenterUser(this,this);
        btnDangKi.setOnClickListener(v->{
            if(edName.getText().toString().isEmpty()||edEmail.getText().toString().isEmpty()||edPassword.getText().toString().isEmpty()){
                Toast.makeText(this, "Làm ơn điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }
            presenterUser.registerUser(edEmail.getText().toString().trim(),edPassword.getText().toString().trim(),edName.getText().toString().trim());
        });
        btnLinkToLoginScreen.setOnClickListener(v->{
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            finish();
        });

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_sign_up;
    }

    @Override
    public void loginThanhCong(String message) {
        Toast.makeText(this,"Đăng kí thành công", Toast.LENGTH_SHORT).show();
        managerSharedPreference.setLogin(true);
        Intent itmain = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(itmain);
    }

    @Override
    public void loginKhongThanhCong(String message) {
       // Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
