package com.example.myapplication.Presenter.ChiTietSanPham;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.Model.ChiTietSanPham.ModelChiTietSanPham;
import com.example.myapplication.Model.GioHang.ModelGioHang;
import com.example.myapplication.Model.ObjectClass.DanhGia;
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

        if (sanPham.getMASP() > 0){
            String[] linkhinhanh = sanPham.getANHNHO().split(",");
            viewChiTietSanPham.HienSliderSanPham(linkhinhanh);

            //hien thi phuong thuc chi tiet san pham
            viewChiTietSanPham.HienThiChiTietSanPham(sanPham);

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

    @Override
    public void LayDanhSachDanhGiaTheoCuaSanPham(int masp, int limit) {
        List<DanhGia> danhGias = modelChiTietSanPham.LayDanhSachDanhGiaCuaSanPham(masp,limit);
        if(danhGias.size()>0){
            viewChiTietSanPham.HienThiDanhGia(danhGias);
        }
    }

    public int DemSanPhamTrongGioHang(Context context){
        modelGioHang.MoKetNoiSQL(context);
        List<SanPham> sanPhamList=modelGioHang.LayDanhSachSPTrongGH();
        int dem =sanPhamList.size();
        return  dem;
    }

}
