package com.example.pctruong.appbanhang.view.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.example.pctruong.appbanhang.R;
import com.example.pctruong.appbanhang.model.object.SanPham;
import com.example.pctruong.appbanhang.presenter.PresenterSanPham.PresenterDSSanPham;
import com.example.pctruong.appbanhang.view.Adapter.AdapterDSSanPham;
import com.example.pctruong.appbanhang.view.Adapter.RecyclerItemClickListener;
import com.example.pctruong.appbanhang.view.Views.ViewDSSanPham;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ListProductActivity extends BaseActivity implements ViewDSSanPham, AdapterDSSanPham.ISendData {
    @BindView(R.id.imgSearch)
    ImageView imgSearch;
    @BindView(R.id.rvListProduct)
    RecyclerView rvListProduct;
    @BindView(R.id.ln_menu)
    LinearLayout ln_menu;


    PresenterDSSanPham presenterDSSanPham;
    AdapterDSSanPham adapterDSSanPham;
    List<SanPham> sanPhamList=new ArrayList<>();
    public static int mathuonghieu;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenterDSSanPham=new PresenterDSSanPham(this);
        layoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(this.getResources().getDrawable(R.drawable.sk_line_divider));
        rvListProduct.addItemDecoration(dividerItemDecoration);


    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_list_product;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent=getIntent();
        mathuonghieu=intent.getIntExtra("mathuonghieu",0);
        presenterDSSanPham.getDSSanPham(mathuonghieu,"desc");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick({R.id.ln_menu,R.id.imgSearch})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.ln_menu:
                onBackPressed();
            break;
            case R.id.imgSearch :
                Intent intent=new Intent(this,ActivitySearch.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0,R.anim.slide_down);
        finish();
    }

    @Override
    public void getDSSanPham(List<SanPham> list) {
        sanPhamList=list;
        adapterDSSanPham=new AdapterDSSanPham(this,sanPhamList,this);
        rvListProduct.setLayoutManager(layoutManager);
        rvListProduct.setAdapter(adapterDSSanPham);
        adapterDSSanPham.notifyDataSetChanged();
    }

    @Override
    public void getError(String s) {

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
}
