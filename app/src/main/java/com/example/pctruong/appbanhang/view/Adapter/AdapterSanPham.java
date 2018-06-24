package com.example.pctruong.appbanhang.view.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.pctruong.appbanhang.R;
import com.example.pctruong.appbanhang.model.object.SanPham;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PCTruong on 04/06/2018.
 */

public class AdapterSanPham extends RecyclerView.Adapter<AdapterSanPham.ViewHolderSanPham> {
    Context mContext;
    List<SanPham> list;

    public AdapterSanPham(Context mContext, List<SanPham> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolderSanPham onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.item_sanpham,parent,false);
        return new ViewHolderSanPham(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSanPham holder, int position) {
        Picasso.with(mContext).load(list.get(position).hinhanh).into(holder.imgSanPham);
        holder.txtTenSP.setText(list.get(position).tensp);
        holder.txtGiaSP.setText(list.get(position).gia+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderSanPham extends RecyclerView.ViewHolder {
        @BindView(R.id.imgSanPham)
        ImageView imgSanPham;
        @BindView(R.id.txtTenSP)
        TextView txtTenSP;
        @BindView(R.id.txtGiaSP)
        TextView txtGiaSP;
        public ViewHolderSanPham(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
