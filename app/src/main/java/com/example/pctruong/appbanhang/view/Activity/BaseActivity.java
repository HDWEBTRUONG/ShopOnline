package com.example.pctruong.appbanhang.view.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.pctruong.appbanhang.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by PCTruong on 07/06/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {
    Unbinder unbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        unbinder= ButterKnife.bind(this);
    }
    protected abstract int getLayoutResourceId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0,R.anim.slide_down);
        finish();
    }
}
