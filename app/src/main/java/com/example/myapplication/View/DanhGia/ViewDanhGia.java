package com.example.myapplication.View.DanhGia;

import com.example.myapplication.Model.ObjectClass.DanhGia;

import java.util.List;

public interface ViewDanhGia {
    void DanhGiaThanhCong();
    void DanhGiaThatBai();
    void HienThiDanhSachDanhGiaTheoSanPham(List<DanhGia>danhGiaList);
}
