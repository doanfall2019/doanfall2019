package com.example.myapplication.Presenter.ThanhToan;

import android.content.Context;

import com.example.myapplication.Model.GioHang.ModelGioHang;
import com.example.myapplication.Model.HoaDon.HoaDon;
import com.example.myapplication.Model.ObjectClass.SanPham;
import com.example.myapplication.Model.ThanhToan.ModeThanhToan;
import com.example.myapplication.View.ThanhToan.ViewThanhToan;

import java.util.List;

public class PresenterLogicThanhToan implements IPresenterThanhToan {
    ViewThanhToan viewThanhToan;
    ModeThanhToan modeThanhToan;
    ModelGioHang modelGioHang;
    List<SanPham> sanPhamList;

    public PresenterLogicThanhToan(ViewThanhToan viewThanhToan, Context context){
        this.viewThanhToan = viewThanhToan;
        modeThanhToan = new ModeThanhToan();
        modelGioHang =new ModelGioHang();
        modelGioHang.MoKetNoiSQL(context);

    }

    @Override
    public void ThemHoaDon(HoaDon hoaDon) {
        boolean kiemtra = modeThanhToan.ThemHoaDon(hoaDon);
        if (kiemtra ==true){
            viewThanhToan.DatHangThanhCong();

            int dem = sanPhamList.size();
            for(int i =0; i<dem;i++){
                modelGioHang.XoaSanPhamTrongGioHang(sanPhamList.get(i).getMASP());
            }
        }else {
            viewThanhToan.DatHangThatBai();
        }
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang() {

        sanPhamList =modelGioHang.LayDanhSachSPTrongGH();
        viewThanhToan.LayDanhSachSanPhamTrongGioHang(sanPhamList);
    }
}
