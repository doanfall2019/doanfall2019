package com.example.myapplication.View.ThanhToan;

import com.example.myapplication.Model.ObjectClass.SanPham;

import java.util.List;

public interface ViewThanhToan {
    void DatHangThanhCong();
    void DatHangThatBai ();
    void LayDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList);
}
