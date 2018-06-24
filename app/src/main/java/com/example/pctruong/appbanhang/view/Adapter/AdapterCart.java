package com.example.pctruong.appbanhang.view.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.example.pctruong.appbanhang.R;
import com.example.pctruong.appbanhang.model.data.XuLyDataBase;
import com.example.pctruong.appbanhang.model.object.SanPham;
import com.example.pctruong.appbanhang.view.Activity.ActivityDetail;
import com.example.pctruong.appbanhang.view.Activity.Activity_AddCart;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by PCTruong on 13/06/2018.
 */

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.ViewHolder> {

    List<SanPham> sanPhamList;
    Context context;
    XuLyDataBase xuLyDataBase;
    IUpdateProduct iUpdateProduct;
    public ImageView img_Love;
    int check;
    public AdapterCart(Context context, List<SanPham> sanPhamList, IUpdateProduct iUpdateProduct ,int check) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.iUpdateProduct = iUpdateProduct;
        xuLyDataBase = new XuLyDataBase(context);
        this.check=check;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = null;
        if(check==0){
            view = inflater.inflate(R.layout.layout_custom_giohang, parent, false);
        }else {
            view = inflater.inflate(R.layout.layout_customgiohang, parent, false);
        }
        return new ViewHolder(view);
    }

    int soluong;

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(check==1){
            holder.gone();
        }
        holder.txtSoLuong.setText(sanPhamList.get(position).soluong + "");
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGiaSP.setText(decimalFormat.format(sanPhamList.get(position).gia));
        holder.txtTenSP.setText(sanPhamList.get(position).tensp);
        holder.lnTang.setOnClickListener(v -> {
            int tongsoluong = sanPhamList.get(position).tongsoluong;
            soluong = Integer.parseInt(holder.txtSoLuong.getText().toString());
            if (soluong < tongsoluong) {
                soluong++;
                holder.txtSoLuong.setText(soluong + "");
                xuLyDataBase.CapNhatLaiDuLieu(sanPhamList.get(position).masp, soluong, sanPhamList.get(position).gia);
                iUpdateProduct.capnhatdulieu();

            }
        });

        holder.lnGiam.setOnClickListener(v -> {
            int soluongtxt = Integer.parseInt(holder.txtSoLuong.getText().toString());
            if (soluongtxt > 0) {
                soluongtxt--;
                if (soluongtxt == 0) {
                    soluongtxt = 1;
                    holder.txtSoLuong.setText(soluongtxt + "");
                } else {
                    holder.txtSoLuong.setText(soluongtxt + "");
                }
                xuLyDataBase.CapNhatLaiDuLieu(sanPhamList.get(position).masp, soluongtxt, sanPhamList.get(position).gia);
                iUpdateProduct.capnhatdulieu();
            }
        });

        Picasso.with(context).load(sanPhamList.get(position).hinhanh).into(holder.imgHinhAnh);
        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.layout_right);

        holder.imgOpen.setOnClickListener(v -> {
            holder.swipeLayout.open(true);
        });

        holder.imgLove.setOnClickListener(v -> {

        });
        holder.imgDelete.setOnClickListener((View v) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Thông báo");
            builder.setMessage("Bạn có thực sự muốn xóa sản phẩm này ?");
            builder.setCancelable(false);
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(check==0) {
                        xuLyDataBase.XoaSanPhamTheoMa(sanPhamList.get(position).masp);
                    }else {
                        xuLyDataBase.XoaSanPhamYeuThichTheoMa(sanPhamList.get(position).masp);
                    }
                    sanPhamList.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    iUpdateProduct.capnhatGioHang();
                    dialog.dismiss();
                }
            });

            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
           builder.create().show();

        });

        holder.imgLove.setOnClickListener(v -> {
            SanPham sanPham = sanPhamList.get(position);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Chuyển đến danh sách yêu thích");
            builder.setCancelable(false);
            builder.setMessage("Bạn có chắc bạn muốn chuyển đến những sản phẩm yêu thích ?");
            builder.setPositiveButton("Di chuyển", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    xuLyDataBase.ThemSanPhamYeuThich(sanPham);
                    xuLyDataBase.XoaSanPhamTheoMa(sanPhamList.get(position).masp);
                    sanPhamList.remove(position);
                    notifyDataSetChanged();
                    iUpdateProduct.capnhatGioHang();
                }
            });


            builder.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.create().show();


        });

        holder.imgHinhAnh.setOnClickListener(v->{
            Intent intent=new Intent(context,ActivityDetail.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("data",sanPhamList.get(position));
            intent.putExtra("data",bundle);
            context.startActivity(intent);
            ((Activity_AddCart)context).overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        });

    }



    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhAnh, imgDelete, imgLove;
        TextView txtSoLuong, txtGiaSP, txtTenSP;
        ImageView imgOpen;
        SwipeLayout swipeLayout;
        ConstraintLayout layout_right;
        LinearLayout lnTang, lnGiam ,ln;

        public ViewHolder(View itemView) {
            super(itemView);
            imgHinhAnh = itemView.findViewById(R.id.imgHinhSanPham);
            txtGiaSP = itemView.findViewById(R.id.txtGiaSP);
            txtTenSP = itemView.findViewById(R.id.txtTenSP);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuongGioHang);
            lnGiam = itemView.findViewById(R.id.lnGiam);
            lnTang = itemView.findViewById(R.id.lnTang);
            swipeLayout = itemView.findViewById(R.id.swipe);
            layout_right = itemView.findViewById(R.id.ct_righ_cart);
            imgOpen = itemView.findViewById(R.id.imgOpen);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            imgLove = itemView.findViewById(R.id.imgLove);
            ln=itemView.findViewById(R.id.ll);
        }

        public void gone(){
            ln.setVisibility(View.GONE);
            imgOpen.setVisibility(View.GONE);
        }


    }

    public interface IUpdateProduct {
        void capnhatdulieu();

        void capnhatGioHang();
    }
}
