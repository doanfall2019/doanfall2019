package com.example.myapplication.Presenter.HienThiSanPhamTheoDanhMuc;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.Model.HienThiSanPhamTheoDanhMuc.ModelHienThiSanPhamTheoDanhMuc;
import com.example.myapplication.Model.ObjectClass.SanPham;
import com.example.myapplication.View.TrangChu.ViewHienThiSanPhamTheoDanhMuc;

import java.util.ArrayList;
import java.util.List;

public class PresenterLogicHienThiSanPhamTheoDanhMuc implements IPresenterHienThiSanPhamTheoDanhMuc {

    ViewHienThiSanPhamTheoDanhMuc viewHienThiSanPhamTheoDanhMuc;
    ModelHienThiSanPhamTheoDanhMuc modelHienThiSanPhamTheoDanhMuc;

    public PresenterLogicHienThiSanPhamTheoDanhMuc(ViewHienThiSanPhamTheoDanhMuc viewHienThiSanPhamTheoDanhMuc) {
        this.viewHienThiSanPhamTheoDanhMuc = viewHienThiSanPhamTheoDanhMuc;
        modelHienThiSanPhamTheoDanhMuc = new ModelHienThiSanPhamTheoDanhMuc();
    }

    @Override
    public void layDanhSachSanPhamTheoMaLoai(int masp, boolean kiemtra) {
        List<SanPham> sanPhamList = new ArrayList<>();
        if (kiemtra) {
            sanPhamList = modelHienThiSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp, "DANHSACHSANPHAM", "LayDanhSachSanPhamTheoMaThuongHieu", 0);
        } else {
            sanPhamList = modelHienThiSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp, "DANHSACHSANPHAM", "LayDanhSachSanPhamTheoMaLoaiDanhMuc", 0);
        }

        if (sanPhamList.size() > 0) {
            viewHienThiSanPhamTheoDanhMuc.HienThiDanhSachSanPham(sanPhamList);
        } else {
            viewHienThiSanPhamTheoDanhMuc.LoiHienThiDanhSachSanPham();
        }
    }

    public void layDanhSachSanPhamTheoGiaTang(int masp) {
        List<SanPham> sanPhamList = new ArrayList<>();
        sanPhamList = modelHienThiSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoGiaTang(masp, "DANHSACHSANPHAM", "LayDanhSachSanPhamTheoGiaTang", 0);
        viewHienThiSanPhamTheoDanhMuc.HienThiDanhSachSanPham(sanPhamList);

    }

    public List<SanPham> layDanhSachSanPhamTheoMaLoaiLoadMore(int masp, boolean kiemtra, int limit, ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
        List<SanPham> sanPhamList = new ArrayList<>();
        if (kiemtra) {
            sanPhamList = modelHienThiSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp, "DANHSACHSANPHAM", "LayDanhSachSanPhamTheoMaThuongHieu", limit);
        } else {
            sanPhamList = modelHienThiSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp, "DANHSACHSANPHAM", "LayDanhSachSanPhamTheoMaLoaiDanhMuc", limit);
        }

        if (sanPhamList.size() != 0 ){
            progressBar.setVisibility(View.GONE);
        }
        return sanPhamList;
    }
}
