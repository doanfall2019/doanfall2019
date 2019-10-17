package com.example.myapplication.Presenter.DanhGia;

import android.widget.ProgressBar;

import com.example.myapplication.Model.ObjectClass.DanhGia;

public interface IPresenterDanhGia {
    void ThemDangGia(DanhGia danhGia);
    void LayDanhSachDanhGiaCuaSanPham(int masp, int limit, ProgressBar progressBar);

}
