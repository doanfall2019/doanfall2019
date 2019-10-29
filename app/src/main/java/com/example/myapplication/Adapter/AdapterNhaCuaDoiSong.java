package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.ObjectClass.NhaCuaDoiSong;
import com.example.myapplication.R;

import java.util.List;

public class AdapterNhaCuaDoiSong extends RecyclerView.Adapter<AdapterNhaCuaDoiSong.ViewHolderNhaCuaDoiSong> {

    Context context;
    List<NhaCuaDoiSong> nhaCuaDoiSongList;
    int[] imHinhKhuyenMai = {R.drawable.banner_dogiadung,R.drawable.banner_dogiadung,R.drawable.banner_thethao};

    public AdapterNhaCuaDoiSong(Context context, List<NhaCuaDoiSong> nhaCuaDoiSongList) {
        this.context = context;
        this.nhaCuaDoiSongList = nhaCuaDoiSongList;
    }

    public class ViewHolderNhaCuaDoiSong extends RecyclerView.ViewHolder {
        ImageView imKhuyenMaiDienTu;
        RecyclerView recyclerViewThuongHieuLon, recyclerViewTopSanPham;
        TextView txtTieuDeSanPhamNoiBat, txtTopSanPhamNoiBat;

        public ViewHolderNhaCuaDoiSong(@NonNull View itemView) {
            super(itemView);

            imKhuyenMaiDienTu = itemView.findViewById(R.id.imKhuyenMaiDienTu);
            recyclerViewThuongHieuLon = itemView.findViewById(R.id.recyclerThuongHieuLon);
            recyclerViewTopSanPham = itemView.findViewById(R.id.recyclerTopDienThoaiMayTinhBang);
            txtTieuDeSanPhamNoiBat = itemView.findViewById(R.id.txtTenSanPhamNoiBat);
            txtTopSanPhamNoiBat = itemView.findViewById(R.id.txtTenTopSanPhamNoiBat);
        }
    }

    @NonNull
    @Override
    public ViewHolderNhaCuaDoiSong onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recyclerview_nhacuadoisong, parent, false);

        ViewHolderNhaCuaDoiSong viewHolderNhaCuaDoiSong = new ViewHolderNhaCuaDoiSong(view);

        return viewHolderNhaCuaDoiSong;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNhaCuaDoiSong holder, int position) {
        NhaCuaDoiSong nhaCuaDoiSong = nhaCuaDoiSongList.get(position);

        holder.imKhuyenMaiDienTu.setImageResource(imHinhKhuyenMai[position]);
        holder.txtTieuDeSanPhamNoiBat.setText(nhaCuaDoiSong.getTennoibat().toString());
        holder.txtTopSanPhamNoiBat.setText(nhaCuaDoiSong.getTentopnoibat().toString());

        // xử lý hiển thị danh sách thuong hiệu lớn
        AdapterThuongHieuLon adapterThuongHieuLon = new AdapterThuongHieuLon(context, nhaCuaDoiSong.getThuongHieus(), nhaCuaDoiSong.getThuonghieu());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3, GridLayoutManager.HORIZONTAL, false);

        holder.recyclerViewThuongHieuLon.setLayoutManager(layoutManager);
        holder.recyclerViewThuongHieuLon.setAdapter(adapterThuongHieuLon);
        adapterThuongHieuLon.notifyDataSetChanged();

        // xử lý hiển thị danh sách TOP sản phẩm
        AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(context, R.layout.custom_layout_topdienthoaivamaytinhbang, nhaCuaDoiSong.getSanPhams());

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        holder.recyclerViewTopSanPham.setLayoutManager(linearLayoutManager);
        holder.recyclerViewTopSanPham.setAdapter(adapterTopDienThoaiDienTu);
    }

    @Override
    public int getItemCount() {
        return nhaCuaDoiSongList.size();
    }


}
