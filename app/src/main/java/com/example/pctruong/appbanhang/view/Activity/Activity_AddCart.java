package com.example.pctruong.appbanhang.view.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pctruong.Constants;
import com.example.pctruong.appbanhang.R;
import com.example.pctruong.appbanhang.model.data.XuLyDataBase;
import com.example.pctruong.appbanhang.model.object.SanPham;
import com.example.pctruong.appbanhang.view.Adapter.AdapterCart;
import com.example.pctruong.appbanhang.view.Adapter.RecyclerItemClickListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_AddCart extends BaseActivity implements AdapterCart.IUpdateProduct {
    @BindView(R.id.rvCart)
    RecyclerView rvCart;
    @BindView(R.id.txtTongTien)
    TextView txtTongTien;
    @BindView(R.id.rlMuahang)
    RelativeLayout rlMuahang;
    @BindView(R.id.txtGioHang)
    TextView txtGioHang;
    @BindView(R.id.ln_menu)
    LinearLayout ln_menu;
    List<SanPham> sanPhamList=new ArrayList<>();
    AdapterCart adapterCart;
    int sum=0;
    XuLyDataBase xuLyDataBase;
    @Override
    public void capnhatGioHang() {
        Intent intent=getIntent();
        int love;
        love=intent.getIntExtra("key",0);
        if(love==0){
            sanPhamList=xuLyDataBase.LaySanPhamMuaHang();
            adapterCart=new AdapterCart(this,sanPhamList,this ,0);
        }
        else
        {
            sanPhamList = xuLyDataBase.LaySanPhamYeuThich();
            if(sanPhamList.size()>0) {
                adapterCart = new AdapterCart(this, sanPhamList, this, 1);
            }else {
                txtGioHang.setVisibility(View.VISIBLE);
                txtGioHang.setText("Bạn chưa có sản phẩm yêu thích !");
            }
            rlMuahang.setVisibility(View.GONE);
        }
        if(sanPhamList.size()>0) {
            for (int i = 0; i < sanPhamList.size(); i++) {
                 sum += sanPhamList.get(i).tongtien;

            }
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            txtTongTien.setText(decimalFormat.format(sum));
        }else {
            rlMuahang.setVisibility(View.GONE);
            txtGioHang.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        xuLyDataBase=new XuLyDataBase(this);
        capnhatGioHang();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(this.getResources().getDrawable(R.drawable.sk_line_divider));
        rvCart.addItemDecoration(dividerItemDecoration);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvCart.setLayoutManager(layoutManager);
        rvCart.setAdapter(adapterCart);


    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity__add_cart;
    }

    @Override
    public void capnhatdulieu() {
        sanPhamList=xuLyDataBase.LaySanPhamMuaHang();
        int sum=0;
        if(sanPhamList.size()>0) {
            for (int i = 0; i < sanPhamList.size(); i++) {
                sum += sanPhamList.get(i).tongtien;
            }
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            txtTongTien.setText(decimalFormat.format(sum));
        }else {
            rlMuahang.setVisibility(View.GONE);
            txtGioHang.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.ln_menu ,R.id.rlMuahang})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ln_menu :
                onBackPressed();
                break;
            case R.id.rlMuahang:
                Intent intent=new Intent(this,AddCartActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Constants.DELETETB){
            rlMuahang.setVisibility(View.GONE);
            txtGioHang.setVisibility(View.VISIBLE);
            sanPhamList.clear();
            xuLyDataBase.XoaTable();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0,R.anim.slide_down);
    }
}
