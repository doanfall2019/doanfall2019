package com.example.myapplication.Presenter.GioHang;

import android.content.Context;

import com.example.myapplication.Model.GioHang.ModelGioHang;
import com.example.myapplication.Model.ObjectClass.SanPham;
import com.example.myapplication.View.GioHang.GioHangActivity;
import com.example.myapplication.View.GioHang.ViewGioHang;

import java.util.List;

public class PresenterLogicGioHang implements   IPresenterGioHang{
    ModelGioHang modelGioHang;
    ViewGioHang viewGioHang;
    public PresenterLogicGioHang(ViewGioHang viewGioHang){
        modelGioHang = new ModelGioHang();
        this.viewGioHang =viewGioHang;
    }
    @Override
    public void LayDanhSachSanPhamTrongGioHang(Context context) {
        modelGioHang.MoKetNoiSQL(context);
        List<SanPham> sanPhamList =modelGioHang.LayDanhSachSPTrongGH();
        if (sanPhamList.size() >0){
            viewGioHang.HienThiDanhSachSanPhamTrongGioHang(sanPhamList);
        }

    }



}
