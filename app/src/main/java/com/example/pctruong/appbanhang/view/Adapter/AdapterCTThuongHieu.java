package com.example.pctruong.appbanhang.view.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.pctruong.appbanhang.R;
import com.example.pctruong.appbanhang.model.object.ChiTietThuongHieu;
import com.example.pctruong.appbanhang.view.Activity.ListProductActivity;
import com.example.pctruong.appbanhang.view.Activity.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by PCTruong on 02/06/2018.
 */

public class AdapterCTThuongHieu extends RecyclerView.Adapter<AdapterCTThuongHieu.ViewHolder> {

    List<ChiTietThuongHieu> list;
    Context mContextc;

    public AdapterCTThuongHieu(List<ChiTietThuongHieu> list, Context mContextc) {
        this.list = list;
        this.mContextc = mContextc;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContextc.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_ctthuonghieu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTH.setText(list.get(position).tenthuonghieu);
        Picasso.with(mContextc).load(list.get(position).hinhthuonghieu).into(holder.imgTH);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTH;
        ImageView imgTH;
        public ViewHolder(View itemView) {
            super(itemView);
            txtTH=itemView.findViewById(R.id.txtThuongHieu);
            imgTH=itemView.findViewById(R.id.imgThuongHieu);
        }
    }
}
