package com.example.pctruong.appbanhang.view.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.pctruong.appbanhang.R;
import com.example.pctruong.appbanhang.model.object.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by PCTruong on 04/06/2018.
 */

public class AdapterDSSanPham extends RecyclerView.Adapter<AdapterDSSanPham.ViewHolder> {
    Context mContext;
    List<SanPham> list;
    ISendData iSendData;

    public AdapterDSSanPham(Context mContext, List<SanPham> list, ISendData iSendData) {
        this.mContext = mContext;
        this.list = list;
        this.iSendData = iSendData;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.with(mContext).load(list.get(position).hinhanh).into(holder.imgSanPham);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtTenSP.setText(list.get(position).tensp);
        holder.txtGiaSP.setText(decimalFormat.format(list.get(position).gia));
        holder.txtCountComment.setText(list.get(position).soluongdanhgia + "");

        if (list.get(position).soluongdanhgia > 0 && list.get(position).soluongsao > 0) {
            float rating = list.get(position).sosao();
            if (rating > 0) {
                holder.rbComment.setRating(rating);
            }
        }


        int demlove = list.get(position).soluongyeuthich;
        if (demlove > 0) {
            holder.txtCountLove.setText(demlove + "");
            holder.imgLove.setImageResource(R.drawable.icn_heart_on);
        }

        holder.itemView.setOnClickListener(v -> {
            SanPham sanPham = list.get(position);
            iSendData.sendData(sanPham);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSanPham, imgLove, imgComment;

        TextView txtTenSP, txtCountLove;

        TextView txtGiaSP, txtCountComment;
        RatingBar rbComment;

        public ViewHolder(View itemView) {
            super(itemView);
            this.setIsRecyclable(false);
            ButterKnife.bind(itemView);
            imgSanPham = itemView.findViewById(R.id.imgSanPham);
            txtTenSP = itemView.findViewById(R.id.txtTenSP);
            txtGiaSP = itemView.findViewById(R.id.txtGiaSP);
            imgComment = itemView.findViewById(R.id.imgComment);
            imgLove = itemView.findViewById(R.id.imgLove);
            txtCountComment = itemView.findViewById(R.id.txtCountComment);
            txtCountLove = itemView.findViewById(R.id.txtCountLove);
            rbComment = itemView.findViewById(R.id.rbComment);
        }
    }

    public interface ISendData {
        void sendData(SanPham sanPham);
    }
}
