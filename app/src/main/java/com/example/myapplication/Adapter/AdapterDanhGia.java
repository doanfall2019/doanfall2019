package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.ObjectClass.DanhGia;
import com.example.myapplication.R;

import java.util.List;

public class AdapterDanhGia extends RecyclerView.Adapter<AdapterDanhGia.ViewHolderDanhGia> {
    List<DanhGia>danhGiaList;
    int limit;
    Context context;
    public AdapterDanhGia(Context context,List<DanhGia>danhGiaList, int limit){
        this.danhGiaList = danhGiaList;
        this.limit = limit;
        this.context = context;

    }
    public class ViewHolderDanhGia extends RecyclerView.ViewHolder{
        TextView txtTieuDeDanhGia,txtNoiDungDanhGia,txtDuocDanhGiaBoi;
        RatingBar rbDanhGia;

        public ViewHolderDanhGia(@NonNull View itemView) {

            super(itemView);
            txtDuocDanhGiaBoi = itemView.findViewById(R.id.txtDuocDangBoi);
            txtNoiDungDanhGia = itemView.findViewById(R.id.txtNoiDungDanhGia);
            txtTieuDeDanhGia = itemView.findViewById(R.id.txtTieuDeDanhGia);
            rbDanhGia = itemView.findViewById(R.id.rbDanhGia);
        }
    }

    @Override
    public ViewHolderDanhGia onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_recycler_danhgia_chitiet,parent,false);


        ViewHolderDanhGia viewHolderDanhGia = new ViewHolderDanhGia(view);
        return viewHolderDanhGia;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDanhGia holder, int position) {
                DanhGia danhGia = danhGiaList.get(position);
                holder.txtTieuDeDanhGia.setText(danhGia.getTIEUDE());
                holder.txtNoiDungDanhGia.setText(danhGia.getNOIDUNG());
                holder.txtDuocDanhGiaBoi.setText("duoc dang Boi"+danhGia.getTENTHIETBI()+ "ngay"+danhGia.getNGAYDANHGIA());
                holder.rbDanhGia.setRating(danhGia.getSOSAO());
    }

    @Override
    public int getItemCount() {
        int dong = 0;
        if (danhGiaList.size() < limit){
            dong = danhGiaList.size();
    }else{
        if(limit !=0 ){
            dong = limit;
        }else{
            dong = danhGiaList.size();
        }

    }


        return dong;
    }


}
