package com.example.myapplication.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.myapplication.Model.GioHang.ModelGioHang;
import com.example.myapplication.Model.ObjectClass.SanPham;
import com.example.myapplication.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.ViewHolderGioHang> {
  Context context;
    List<SanPham> sanPhamList;
    public  AdapterGioHang(Context context, List<SanPham> sanPhamList){
      this.context =context;
      this.sanPhamList =sanPhamList;
    }


    @NonNull
    @Override
    public ViewHolderGioHang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_giohang,parent,false);
        ViewHolderGioHang viewHolderGioHang =new ViewHolderGioHang(view);

        return viewHolderGioHang;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderGioHang holder, final int position) {
        final SanPham sanPham = sanPhamList.get(position);

        holder.txtTenSanPhamGioHang.setText(sanPham.getTENSP());

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA()).toString();
        holder.txtGiaTienGioHang.setText(gia+ " VND");

        byte [] hinhsanpham = sanPham.getHinhGioHang();
        Bitmap bitmapHinhGioHang= BitmapFactory.decodeByteArray(sanPham.getHinhGioHang(),0,hinhsanpham.length);
        holder.imHinhGioHang.setImageBitmap(bitmapHinhGioHang);

        holder.imXoaSanPhamGioHang.setTag(sanPham.getMASP());

        holder.imXoaSanPhamGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("kiemtra",(int)v.getTag() + "");
                ModelGioHang modelGioHang =new ModelGioHang();
                modelGioHang.MoKetNoiSQL(context);
                modelGioHang.XoaSanPhamTrongGioHang((int)v.getTag());
                sanPhamList.get(position);
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class ViewHolderGioHang extends RecyclerView.ViewHolder {
        TextView txtTenSanPhamGioHang, txtGiaTienGioHang;
        ImageView imHinhGioHang, imXoaSanPhamGioHang;
        public ViewHolderGioHang(@NonNull View itemView) {
            super(itemView);
            txtGiaTienGioHang = itemView.findViewById(R.id.txtGiaGioHang);
            txtTenSanPhamGioHang = itemView.findViewById(R.id.txtTieuDeGioHang);

            imHinhGioHang = itemView.findViewById(R.id.imHinhGioHang);
            imXoaSanPhamGioHang = itemView.findViewById(R.id.imXoaSanPham);
        }
    }
}
