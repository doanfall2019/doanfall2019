package com.example.myapplication.Presenter.ChiTietSanPham;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.Model.ChiTietSanPham.ModelChiTietSanPham;
import com.example.myapplication.Model.GioHang.ModelGioHang;
import com.example.myapplication.Model.ObjectClass.SanPham;
import com.example.myapplication.View.ChiTietSanPham.ViewChiTietSanPham;

import java.util.List;

public class PresenterLogicChiTietSanPham implements  IPresenterChiTietSanPham {
    ViewChiTietSanPham viewChiTietSanPham;
    ModelChiTietSanPham modelChiTietSanPham;
    ModelGioHang modelGioHang;

    public  PresenterLogicChiTietSanPham(){
        modelGioHang =new ModelGioHang();
    }
    public PresenterLogicChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham){
        this.viewChiTietSanPham = viewChiTietSanPham;
        modelChiTietSanPham = new ModelChiTietSanPham();
        modelGioHang =new ModelGioHang();
    }

    @Override
    public void LayChiTietSanPham(int masp) {
        SanPham sanPham = modelChiTietSanPham.LayChiTietSanPham("LaySanPhamVsChitietTheoMaSP","CHITIETSANPHAM",masp);
        Log.d("MASP-Presenter", masp+"");
        if (sanPham.getMASP() > 0){
            String[] linkhinhanh = sanPham.getANHNHO().split(",");
            viewChiTietSanPham.HienSliderSanPham(linkhinhanh);
            viewChiTietSanPham.HienThiChiTietSanPham(sanPham);
        }
    }

    @Override
    public void ThemGioHang(SanPham sanPham, Context context) {
        modelGioHang.MoKetNoiSQL(context);
        boolean kiemtra = modelGioHang.ThemGioHang(sanPham);
        if (kiemtra){
            viewChiTietSanPham.ThemGioHangThanhCong();
        }else {
            viewChiTietSanPham.ThemGioHangThatBai();
        }
    }
    public int DemSanPhamTrongGioHang(Context context){
        modelGioHang.MoKetNoiSQL(context);
        List<SanPham> sanPhamList=modelGioHang.LayDanhSachSPTrongGH();
        int dem =sanPhamList.size();
        return  dem;
    }

}
