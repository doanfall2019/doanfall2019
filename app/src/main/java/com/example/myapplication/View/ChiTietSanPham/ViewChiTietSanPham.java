package com.example.myapplication.View.ChiTietSanPham;

import com.example.myapplication.Model.ObjectClass.DanhGia;
import com.example.myapplication.Model.ObjectClass.SanPham;

import java.util.List;

public interface ViewChiTietSanPham {
    void HienThiChiTietSanPham(SanPham sanPham);
    void HienSliderSanPham(String[] linkhinhsanpham);
    void  ThemGioHangThanhCong();
    void  ThemGioHangThatBai();
    void HienThiDanhGia(List<DanhGia>danhGiaList);

}
