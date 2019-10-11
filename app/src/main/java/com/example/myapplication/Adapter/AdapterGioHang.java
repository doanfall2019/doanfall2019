package com.example.myapplication.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
    ModelGioHang modelGioHang;
    public  AdapterGioHang(Context context, List<SanPham> sanPhamList){
      this.context =context;
      this.sanPhamList =sanPhamList;
      modelGioHang = new ModelGioHang();
      modelGioHang.MoKetNoiSQL(context);
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
    public void onBindViewHolder(@NonNull final ViewHolderGioHang holder, final int position) {
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
                sanPhamList.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.txtSoLuongSanPham.setText(String.valueOf(sanPham.getSOLUONG()));
        holder.imTangSoLuongSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(holder.txtSoLuongSanPham.getText().toString());
                int soluongtonkho =sanPham.getSOLUONGTONKHO();
                if (soluong < soluongtonkho){
                    soluong++;
                }
                modelGioHang.CapNhatSoLuongSanPhamGioHang(sanPham.getMASP(),soluong);

                holder.txtSoLuongSanPham.setText(String.valueOf(soluong));

            }
        });
        holder.imGiamSoLuongSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(holder.txtSoLuongSanPham.getText().toString());
                if(soluong > 1){
                    soluong--;
                }
                holder.txtSoLuongSanPham.setText(String.valueOf(soluong));
                modelGioHang.CapNhatSoLuongSanPhamGioHang(sanPham.getMASP(),soluong);


            }
        });

    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class ViewHolderGioHang extends RecyclerView.ViewHolder {
        TextView txtTenSanPhamGioHang, txtGiaTienGioHang, txtSoLuongSanPham;
        ImageView imHinhGioHang, imXoaSanPhamGioHang;
        ImageButton imTangSoLuongSanPham, imGiamSoLuongSanPham;
        public ViewHolderGioHang(@NonNull View itemView) {
            super(itemView);
            txtGiaTienGioHang = itemView.findViewById(R.id.txtGiaGioHang);
            txtTenSanPhamGioHang = itemView.findViewById(R.id.txtTieuDeGioHang);

            imHinhGioHang = itemView.findViewById(R.id.imHinhGioHang);
            imXoaSanPhamGioHang = itemView.findViewById(R.id.imXoaSanPham);

            txtSoLuongSanPham = itemView.findViewById(R.id.txtSoLuongSanPham);
            imGiamSoLuongSanPham= itemView.findViewById(R.id.imGiamSoLuongSPTrongGioHang);
            imTangSoLuongSanPham = itemView.findViewById(R.id.imTangSoLuongSPTrongGioHang);



        }
    }
}
