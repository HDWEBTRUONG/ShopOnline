package com.example.pctruong.appbanhang.view.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.pctruong.ManagerSharedPreference;
import com.example.pctruong.appbanhang.R;
import com.example.pctruong.appbanhang.model.object.BinhLuan;
import com.example.pctruong.appbanhang.model.object.DanhSachSoSao;
import com.example.pctruong.appbanhang.presenter.PresenterBinhLuan.PresenterBinhLuan;
import com.example.pctruong.appbanhang.view.Adapter.AdapterComment;
import com.example.pctruong.appbanhang.view.Views.ViewBinhLuan;
import com.example.pctruong.appbanhang.view.Views.ViewSoSao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityBinhLuan extends BaseActivity implements ViewBinhLuan, ViewSoSao {

    @BindView(R.id.edt_comment)
    EditText edt_comment;
    @BindView(R.id.img_send)
    ImageView img_send;
    @BindView(R.id.rbComment)
    RatingBar rbComment;
    @BindView(R.id.rvComment)
    RecyclerView rvComment;
    int count;
    PresenterBinhLuan presnterBinhLuan;
    AdapterComment adapterComment;
    List<BinhLuan> binhluanList=new ArrayList<>();
    String noidung="";
    int masp;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presnterBinhLuan=new PresenterBinhLuan(this,this);

        masp=getIntent().getIntExtra("masp",0);
        presnterBinhLuan.layBinhLuanTheoMa(masp);

        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvComment.setLayoutManager(layoutManager);


        rbComment.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                count= (int) rating;
            }
        });
        img_send.setOnClickListener(v->{


            noidung=edt_comment.getText().toString().trim();
            if(noidung.isEmpty()){
                Toast.makeText(this, "Mời nhập nội dung bình luận", Toast.LENGTH_SHORT).show();
                return;
            }

            if(count==0){
                Toast.makeText(this, "Phải có ít nhất 1 sao", Toast.LENGTH_SHORT).show();
                return;
            }

            BinhLuan binhLuan=new BinhLuan();
            String name= ManagerSharedPreference.getName(this,ManagerSharedPreference.KEY_NAME);
            binhLuan.username=name;
            noidung=edt_comment.getText().toString();
            binhLuan.noidung=noidung;
            binhLuan.masp=masp;
            binhLuan.sosao=count;
            presnterBinhLuan.themBinhLuan(binhLuan);

            if(binhluanList.size()>0) {
                binhluanList.add(0, binhLuan);
                adapterComment.notifyItemInserted(0);
                rbComment.setRating(0);
                edt_comment.setText("");
                rvComment.smoothScrollToPosition(0);
            }else {
                rbComment.setRating(0);
                edt_comment.setText("");
                binhluanList.add(binhLuan);
                layBinhLuan(binhluanList);
            }


        });



    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_binhluan;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void binhluanThanhCong(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void binhluankThanhCong(String message) {

    }

    @Override
    public void layBinhLuan(List<BinhLuan> binhLuanList) {
        binhluanList=binhLuanList;
        adapterComment=new AdapterComment(this,binhluanList);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(this.getResources().getDrawable(R.drawable.sk_line_divider));
        rvComment.addItemDecoration(dividerItemDecoration);
        rvComment.setAdapter(adapterComment);
        adapterComment.notifyDataSetChanged();
    }

    @Override
    public void layDanhSachSao(List<DanhSachSoSao> list) {

    }

    @Override
    public void layDanhSachSaoLoi(String message) {

    }
}
