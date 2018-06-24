package com.example.pctruong.appbanhang.view.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.pctruong.appbanhang.R;
import com.example.pctruong.appbanhang.model.object.BinhLuan;

import java.util.List;

/**
 * Created by PCTruong on 07/06/2018.
 */

public class AdapterComment extends RecyclerView.Adapter<AdapterComment.ViewHolder> {
    Context context;
    List<BinhLuan> binhLuanList;

    public AdapterComment(Context context, List<BinhLuan> binhLuanList) {
        this.context = context;
        this.binhLuanList = binhLuanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.item_comment,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      holder.rb_comment.setRating(binhLuanList.get(position).sosao);
      holder.txtUserName.setText(binhLuanList.get(position).username);
      holder.txtNoidung.setText(binhLuanList.get(position).noidung);
    }

    @Override
    public int getItemCount() {
        return binhLuanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtUserName,txtNoidung;
        RatingBar rb_comment;
        public ViewHolder(View itemView) {
            super(itemView);
            txtNoidung=itemView.findViewById(R.id.txtNoidung);
            txtUserName=itemView.findViewById(R.id.txtUserName);
            rb_comment=itemView.findViewById(R.id.rbComment);
        }
    }
}
