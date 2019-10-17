package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.ObjectClass.KhuyenMai;
import com.example.myapplication.R;
import com.example.myapplication.View.TrangChu.ViewKhuyenMai;

import java.util.List;

public class AdapterDanhSachKhuyenMai extends RecyclerView.Adapter<AdapterDanhSachKhuyenMai.ViewHolderKhuyenMai> {

    Context context;
    List<KhuyenMai> khuyenMaiList;

    public AdapterDanhSachKhuyenMai(Context context, List<KhuyenMai> khuyenMaiList){
        this.context = context;
        this.khuyenMaiList = khuyenMaiList;
    }

    public class ViewHolderKhuyenMai extends RecyclerView.ViewHolder {

        RecyclerView recyclerViewKhuyenMai;
        TextView txtTieuDeKhuyenMai;
        public ViewHolderKhuyenMai(@NonNull View itemView) {
            super(itemView);

            recyclerViewKhuyenMai = itemView.findViewById(R.id.recyclerItemKhuyenMai);
            txtTieuDeKhuyenMai = itemView.findViewById(R.id.txtTieuDeKhuyenMai);

        }
    }

    @NonNull
    @Override
    public ViewHolderKhuyenMai onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_itemkhuyenmai, parent, false);

        ViewHolderKhuyenMai viewHolderKhuyenMai = new ViewHolderKhuyenMai(view);

        return viewHolderKhuyenMai;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderKhuyenMai holder, int position) {

        KhuyenMai khuyenMai = khuyenMaiList.get(position);

        holder.txtTieuDeKhuyenMai.setText(khuyenMai.getTENLOAISP());

        AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(context,R.layout.custom_layout_topdienthoaivamaytinhbang, khuyenMai.getDanhSachSanPhamKhuyenMai());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.recyclerViewKhuyenMai.setLayoutManager(layoutManager);
        holder.recyclerViewKhuyenMai.setAdapter(adapterTopDienThoaiDienTu);
    }

    @Override
    public int getItemCount() {
        return khuyenMaiList.size();
    }


}
