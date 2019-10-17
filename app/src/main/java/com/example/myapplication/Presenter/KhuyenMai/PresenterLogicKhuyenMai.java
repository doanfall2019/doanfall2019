package com.example.myapplication.Presenter.KhuyenMai;

import com.example.myapplication.Model.KhuyenMai.ModelKhuyenMai;
import com.example.myapplication.Model.ObjectClass.KhuyenMai;
import com.example.myapplication.View.TrangChu.ViewKhuyenMai;

import java.util.List;

public class PresenterLogicKhuyenMai implements IPresenterKhuyenMai {
    ViewKhuyenMai viewKhuyenMai;
    ModelKhuyenMai modelKhuyenMai;

    public PresenterLogicKhuyenMai(ViewKhuyenMai viewKhuyenMai){
        this.viewKhuyenMai = viewKhuyenMai;
        modelKhuyenMai = new ModelKhuyenMai();
    }

    @Override
    public void LayDanhSachKhuyenMai() {
        List<KhuyenMai> khuyenMaiList = modelKhuyenMai.LayDanhSachSanPhamTheoMaLoai("DANHSACHKHUYENMAI","LayDanhSachKhuyenMai");
        if (khuyenMaiList.size() >0){
            viewKhuyenMai.HienThiDanhSachKhuyenMai(khuyenMaiList);
        }
    }
}
