package com.example.myapplication.Presenter.ChiTietSanPham;

import android.content.Context;

import com.example.myapplication.Model.ObjectClass.SanPham;

public interface IPresenterChiTietSanPham {
    void LayChiTietSanPham(int masp);
    void ThemGioHang(SanPham sanPham, Context context);


}
