package com.example.pctruong.appbanhang.view.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pctruong.Constants;
import com.example.pctruong.appbanhang.R;
import com.example.pctruong.appbanhang.model.data.XuLyDataBase;
import com.example.pctruong.appbanhang.model.object.BinhLuan;
import com.example.pctruong.appbanhang.model.object.DanhSachSoSao;
import com.example.pctruong.appbanhang.model.object.Love;
import com.example.pctruong.appbanhang.model.object.SanPham;
import com.example.pctruong.appbanhang.presenter.PresenterBinhLuan.PresenterBinhLuan;
import com.example.pctruong.appbanhang.presenter.PresenterLove.PresenterLove;
import com.example.pctruong.appbanhang.view.Adapter.AdapterCommentDetail;
import com.example.pctruong.appbanhang.view.Views.ViewBinhLuan;
import com.example.pctruong.appbanhang.view.Views.ViewLove;
import com.example.pctruong.appbanhang.view.Views.ViewSoSao;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityDetail extends BaseActivity implements ViewBinhLuan, ViewLove, ViewSoSao {
    @BindView(R.id.rvComment)
    RecyclerView rvComment;
    @BindView(R.id.imgDetailProduct)
    ImageView imgDetailProduct;
    @BindView(R.id.txtDetailProduct)
    TextView txtDetailProduct;
    @BindView(R.id.txtTenSP)
    TextView txtTenSP;
    @BindView(R.id.txtGiaSP)
    TextView txtGiaSP;

    @BindView(R.id.txtXemTatCa)
    TextView txtXemTatCa;
    @BindView(R.id.llDanhGia)
    LinearLayout llDanhGia;

    @BindView(R.id.ll_DanhGia)
    LinearLayout ll_DanhGia;
    @BindView(R.id.viewRV)
    View viewRV;

    @BindView(R.id.txtAddCart)
    TextView txtAddCart;
    @BindView(R.id.txtShopping)
    TextView txtShopping;

    @BindView(R.id.txtCart)
    TextView txtCart;

    @BindView(R.id.flCart)
    FrameLayout flCart;

    @BindView(R.id.imgLove)
    ImageView imgLove;

    @BindView(R.id.txtSoLuongLove)
    TextView txtSoLuongLove;

    @BindView(R.id.txtSLDanhGia)
    TextView txtSLDanhGia;

   @BindView(R.id.txtmot)
   TextView txtMot;

    @BindView(R.id.txthai)
    TextView txtHai;

    @BindView(R.id.txtba)
    TextView txtBa;

    @BindView(R.id.txtbon)
    TextView txtBon;

    @BindView(R.id.txtnam)
    TextView txtNam;

    @BindView(R.id.pbmot)
    ProgressBar pbMot;

    @BindView(R.id.pbhai)
    ProgressBar pbHai;

    @BindView(R.id.pbba)
    ProgressBar pbBa;

    @BindView(R.id.pbbon)
    ProgressBar pbBon;

    @BindView(R.id.pbnam)
    ProgressBar pbNam;

    @BindView(R.id.rbDanhGia)
    RatingBar rbDanhGia;

    @BindView(R.id.rbComment)
    RatingBar rbComment;

    @BindView(R.id.txtDanhGiaTB)
    TextView    txtDanhGiaTB;

    @BindView(R.id.txtSoLuongDanhGia)
    TextView   txtSoLuongDanhGia;

    @BindView(R.id.ln_menu)
    LinearLayout ln_menu;

    SanPham sanPham;
    AdapterCommentDetail adapterCommentDetail;
    PresenterBinhLuan presnterBinhLuan;
    PresenterLove presenterLove;
    List<BinhLuan> binhLuanList = new ArrayList<>();

    XuLyDataBase xuLyDataBase;
    List<SanPham> sanPhamList;

    int count;
    int countLove;
    int sumsao = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");

        sanPham = (SanPham) bundle.getSerializable("data");

        txtDetailProduct.setText(sanPham.thongtin);
        Picasso.with(this).load(sanPham.hinhanh).into(imgDetailProduct);
        txtTenSP.setText(sanPham.tensp);


        int gia = sanPham.gia;
        countLove = sanPham.soluongyeuthich;

        txtSoLuongLove.setText("("+countLove+")");


        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGiaSP.setText(decimalFormat.format(gia));
        if (sanPham.soluongyeuthich > 0) {
            imgLove.setImageResource(R.drawable.icn_heart_on);
        }

        presnterBinhLuan = new PresenterBinhLuan(this,this);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void onStart() {
        super.onStart();
        presnterBinhLuan.layBinhLuanTheoMa(sanPham.masp);
        presnterBinhLuan.layDanhSachSoSao(sanPham.masp);
    }

    @Override
    protected void onResume() {
        super.onResume();
        xuLyDataBase = new XuLyDataBase(this);
        sanPhamList = xuLyDataBase.LaySanPhamMuaHang();
        count = sanPhamList.size();
        txtCart.setText(count + "");

        if(Constants.DELETETB){
            xuLyDataBase.XoaTable();
        }
    }



    @Override
    public void binhluanThanhCong(String message) {

    }

    @Override
    public void binhluankThanhCong(String message) {

    }

    @Override
    public void layBinhLuan(List<BinhLuan> binhLuanList) {
        this.binhLuanList = binhLuanList;
        for (int i = 0; i < binhLuanList.size(); i++) {
            sumsao += binhLuanList.get(i).sosao;
        }
        float kq = sumsao / binhLuanList.size();

        rbComment.setRating(Math.round(kq));

        txtSLDanhGia.setText("(" + binhLuanList.size() + ")");
        txtDanhGiaTB.setText(binhLuanList.size()+"");
        txtSoLuongDanhGia.setText(binhLuanList.size()+" Đánh giá");
        if (binhLuanList.size() > 0) {
            viewRV.setVisibility(View.VISIBLE);
            llDanhGia.setVisibility(View.GONE);
            txtXemTatCa.setVisibility(View.VISIBLE);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                    LinearLayoutManager.VERTICAL);
            dividerItemDecoration.setDrawable(this.getResources().getDrawable(R.drawable.sk_line_divider));
            rvComment.addItemDecoration(dividerItemDecoration);
            adapterCommentDetail = new AdapterCommentDetail(this, binhLuanList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            rvComment.setLayoutManager(layoutManager);
            rvComment.setAdapter(adapterCommentDetail);
        }

    }


    @OnClick({R.id.txtAddCart, R.id.txtShopping, R.id.llDanhGia, R.id.ll_DanhGia, R.id.flCart, R.id.imgLove,R.id.ln_menu})
    public void onClick(View view) {
        Intent intentDG = new Intent(this, ActivityBinhLuan.class);
        intentDG.putExtra("masp", sanPham.masp);
        switch (view.getId()) {
            case R.id.txtAddCart:

                boolean check_add = xuLyDataBase.ThemSanPham(sanPham);
                if (check_add) {
                    count++;
                    txtCart.setText(count + "");
                    Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Sản phẩm đã tồn tại", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.txtShopping:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;

            case R.id.ll_DanhGia:
                startActivity(intentDG);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;

            case R.id.llDanhGia:
                startActivity(intentDG);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;

            case R.id.flCart:
                Intent itCart = new Intent(this, Activity_AddCart.class);
                startActivity(itCart);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;

            case R.id.imgLove:
                presenterLove = new PresenterLove(this);
                Love love = new Love();
                love.masp = sanPham.masp;
                imgLove.setSelected(!imgLove.isSelected());
                if (imgLove.isSelected()) {
                    countLove--;
                    if(countLove==-1){
                        countLove=0;
                    }
                    txtSoLuongLove.setText("("+countLove+")");
                    imgLove.setImageResource(R.drawable.icn_heart);
                    presenterLove.deleteLove(7);
                } else {
                    countLove++;
                    txtSoLuongLove.setText("("+countLove+")");
                    imgLove.setImageResource(R.drawable.icn_heart_on);
                    love.love = "true";
                    presenterLove.addLove(love);
                }

                break;

            case R.id.ln_menu:
                onBackPressed();
                break;

        }
    }

    @Override
    public void themLoveThanhCong(String message) {
        // Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void themLoveKhongThanhCong(String message) {
        // Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void layDanhSachSao(List<DanhSachSoSao> list) {

       for(int i=0;i<list.size();i++){
           if(list.get(i).sosao.equals("1")){
                txtMot.setText(list.get(i).tongsosao);
                pbMot.setProgress(Integer.parseInt(list.get(i).so_sao));
           }

           if(list.get(i).sosao.equals("2")){
               txtHai.setText(list.get(i).tongsosao);
               pbHai.setProgress(Integer.parseInt(list.get(i).so_sao));
           }

           if(list.get(i).sosao.equals("3")){
               txtBa.setText(list.get(i).tongsosao);
               pbBa.setProgress(Integer.parseInt(list.get(i).so_sao));
           }

           if(list.get(i).sosao.equals("4")){
               txtBon.setText(list.get(i).tongsosao);
               pbBon.setProgress(Integer.parseInt(list.get(i).so_sao));
           }

           if(list.get(i).sosao.equals("5")){
               txtNam.setText(list.get(i).tongsosao);
               pbNam.setProgress(Integer.parseInt(list.get(i).so_sao));
           }
       }
    }

    @Override
    public void layDanhSachSaoLoi(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
