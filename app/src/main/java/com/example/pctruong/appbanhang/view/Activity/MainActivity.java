package com.example.pctruong.appbanhang.view.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pctruong.Constants;
import com.example.pctruong.ManagerSharedPreference;
import com.example.pctruong.appbanhang.R;
import com.example.pctruong.appbanhang.model.data.CreateTable;
import com.example.pctruong.appbanhang.model.data.XuLyDataBase;
import com.example.pctruong.appbanhang.model.object.ChiTietThuongHieu;
import com.example.pctruong.appbanhang.model.object.SanPham;
import com.example.pctruong.appbanhang.model.object.ThuongHieu;
import com.example.pctruong.appbanhang.presenter.PresenterThuongHieu.PresenterThuongHieu;
import com.example.pctruong.appbanhang.push_fcm.ActivitySendPushNotification;
import com.example.pctruong.appbanhang.push_fcm.EndPoints;
import com.example.pctruong.appbanhang.push_fcm.SharedPrefManager;
import com.example.pctruong.appbanhang.view.Adapter.AdapterCTThuongHieu;
import com.example.pctruong.appbanhang.view.Adapter.RecyclerItemClickListener;
import com.example.pctruong.appbanhang.view.Views.ViewThuongHieu;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, ViewThuongHieu {

    PresenterThuongHieu presenterThuongHieu;
    @BindView(R.id.rvCTThuongHieu)
    RecyclerView rvChiTietThuongHieu;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txtGioHang)
    TextView txtGioHang;
    @BindView(R.id.txtSanPhamYeuThich)
    TextView txtSanPhamYeuThich;
    @BindView(R.id.txtDangXuat)
    TextView txtDangXuat;
    @BindView(R.id.txtAdmin)
    TextView txtAdmin;

    TextView  txtSoLuong;

    AdapterCTThuongHieu adapterThuongHieu;
    List<ThuongHieu> listThuongHieu;
    List<SanPham> sanPhamList;
    XuLyDataBase xuLyDataBase;
    ManagerSharedPreference managerSharedPreference;
    String token;
    String email;
    String status="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);


        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        listThuongHieu = new ArrayList<>();

        presenterThuongHieu = new PresenterThuongHieu(this);
        presenterThuongHieu.getChiTietThuongHieu();

        managerSharedPreference=new ManagerSharedPreference(this);
        token = SharedPrefManager.getInstance(this).getDeviceToken();



        xuLyDataBase=new XuLyDataBase(this);
        HashMap<String,String> user=xuLyDataBase.getUserDetails();
        String name=user.get(CreateTable.TB_NAME);
        String uid=user.get(CreateTable.TB_UID);
        email=user.get(CreateTable.TB_EMAIL);
        if(!status.isEmpty()&&status!=null){
            txtAdmin.setVisibility(View.VISIBLE);
        }
        sendTokenToServer();



    }
    private void sendTokenToServer() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoints.URL_REGISTER_DEVICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
                params.put("email", email);
                params.put("token",token);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_giohang, menu);
        MenuItem menuItem = menu.findItem(R.id.itemgiohang);
        View viewGioHang = MenuItemCompat.getActionView(menuItem);
        txtSoLuong = viewGioHang.findViewById(R.id.txtSLSanPhamGioHang);
        txtSoLuong.setText(sanPhamList.size()+"");
        viewGioHang.setOnClickListener(v->{
            Intent itCart = new Intent(this, Activity_AddCart.class);
            startActivity(itCart);
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        });
        return true;
    }
    @OnClick({R.id.txtGioHang,R.id.txtSanPhamYeuThich,R.id.txtDangXuat,R.id.txtAdmin})
     public void onClick(View view){
        switch (view.getId()){
            case R.id.txtGioHang:
                Intent itCart = new Intent(this, Activity_AddCart.class);
                itCart.putExtra("key",0);
                startActivity(itCart);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

                break;
            case R.id.txtSanPhamYeuThich :
                Intent intent=new Intent(this,Activity_AddCart.class);
                intent.putExtra("key",1);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;
            case R.id.txtDangXuat :
                ManagerSharedPreference.deleteByKey();
                Intent itDangXuat=new Intent(this,LoginActivity.class);
                startActivity(itDangXuat);
                finish();
                break;

            case R.id.txtAdmin:
                Intent itAdmin=new Intent(getApplicationContext(), ActivitySendPushNotification.class);
                startActivity(itAdmin);
                break;
        }
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
    boolean check;
    @Override
    protected void onPause() {
        super.onPause();
        check=true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        sanPhamList=xuLyDataBase.LaySanPhamMuaHang();
        if(check&&sanPhamList.size()>0){
            txtSoLuong.setText(sanPhamList.size()+"");
        }

        if(Constants.DELETETB){
            xuLyDataBase.XoaTable();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent=new Intent(this,ActivitySearch.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void getChiTietThuongHieu(List<ChiTietThuongHieu> listThuongHieu) {
        adapterThuongHieu = new AdapterCTThuongHieu(listThuongHieu, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rvChiTietThuongHieu.setLayoutManager(gridLayoutManager);
        rvChiTietThuongHieu.setAdapter(adapterThuongHieu);
        rvChiTietThuongHieu.addOnItemTouchListener(new RecyclerItemClickListener(this, rvChiTietThuongHieu, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(MainActivity.this,ListProductActivity.class);
                intent.putExtra("mathuonghieu",listThuongHieu.get(position).mathuonghieu);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));


    }

    @Override
    public void getErrorThuongHieu(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}
