package com.example.pctruong.appbanhang.view.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pctruong.appbanhang.R;
import com.example.pctruong.appbanhang.model.object.SanPham;
import com.example.pctruong.appbanhang.presenter.PresenterTimKiem.PresenterTimKiem;
import com.example.pctruong.appbanhang.view.Adapter.AdapterDSSanPham;
import com.example.pctruong.appbanhang.view.CustomView.SearchTextWatcher;
import com.example.pctruong.appbanhang.view.Views.ViewTimKiem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivitySearch extends BaseActivity implements ViewTimKiem, AdapterDSSanPham.ISendData {
    @BindView(R.id.ln_menu)
    LinearLayout ln_menu;
    @BindView(R.id.search_edt_search)
    EditText edSearch;
    @BindView(R.id.rvSearch)
    RecyclerView rvSearch;
    PresenterTimKiem presenterTimKiem;
    List<SanPham> listSP=new ArrayList<>();
    AdapterDSSanPham adapterDSSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenterTimKiem = new PresenterTimKiem(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(this.getResources().getDrawable(R.drawable.sk_line_divider));
        rvSearch.addItemDecoration(dividerItemDecoration);
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    return true;
                }
                return false;
            }
        });

        edSearch.addTextChangedListener(new SearchTextWatcher() {
            @Override
            public void onSearch(String key) {

                if(!edSearch.getText().toString().trim().isEmpty()) {
                    presenterTimKiem.timKiemSanPham(edSearch.getText().toString());
                }else {
                    listSP.clear();
                }

            }
        });
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_search;
    }

    @OnClick(R.id.ln_menu)
    public void onClick(View view){
         onBackPressed();
    }
    @Override
    public void layDSTimKiem(List<SanPham> list) {
        listSP=list;
        adapterDSSanPham = new AdapterDSSanPham(this, list, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvSearch.setLayoutManager(layoutManager);
        rvSearch.setAdapter(adapterDSSanPham);
        adapterDSSanPham.notifyDataSetChanged();
    }

    @Override
    public void sendData(SanPham sanPham) {
        Intent intent=new Intent(getApplicationContext(),ActivityDetail.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("data",sanPham);
        intent.putExtra("data",bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0,R.anim.slide_down);
    }
}
