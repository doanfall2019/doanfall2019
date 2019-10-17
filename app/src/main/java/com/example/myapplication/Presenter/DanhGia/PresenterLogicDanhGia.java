package com.example.myapplication.Presenter.DanhGia;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.Model.DanhGia.ModelDanhGia;
import com.example.myapplication.Model.ObjectClass.DanhGia;
import com.example.myapplication.View.DanhGia.ViewDanhGia;

import java.util.List;

public class PresenterLogicDanhGia implements IPresenterDanhGia {
    ViewDanhGia viewDanhGia;
    ModelDanhGia modelDanhGia;


    public PresenterLogicDanhGia(ViewDanhGia viewDanhGia) {
        this.viewDanhGia = viewDanhGia;
        modelDanhGia = new ModelDanhGia();
    }

    @Override
    public void ThemDangGia(DanhGia danhGia) {
        boolean kiemtra = modelDanhGia.ThemDanhGia(danhGia);
        if (kiemtra) {
            viewDanhGia.DanhGiaThanhCong();


        } else {
            viewDanhGia.DanhGiaThatBai();
        }
    }

    @Override
    public void LayDanhSachDanhGiaCuaSanPham(int masp, int limit, ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
        List<DanhGia> danhGiaList = modelDanhGia.LayDanhSachDanhGiaCuaSanPham(masp,limit);

        if(danhGiaList.size()>0){
            progressBar.setVisibility(View.GONE);
            viewDanhGia.HienThiDanhSachDanhGiaTheoSanPham(danhGiaList);
        }
    }
}
