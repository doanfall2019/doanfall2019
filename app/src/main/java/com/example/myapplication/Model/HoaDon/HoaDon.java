package com.example.myapplication.Model.HoaDon;

import java.util.List;

public class HoaDon {
    int MaHD, ChuyenKhoan;
    String NgayMua;
    String TrangThai;
    String TenNguoiNhan;
    String SoDT;
    String DiaChi;
    String MaChuyenKhoan;
    List<ChiTietHoaDon> chiTietHoaDonList;

    public void setMaHD(int maHD) {
        MaHD = maHD;
    }

    public void setChuyenKhoan(int chuyenKhoan) {
        ChuyenKhoan = chuyenKhoan;
    }

    public void setNgayMua(String ngayMua) {
        NgayMua = ngayMua;
    }

    public void setNgayGiao(String ngayGiao) {
        NgayGiao = ngayGiao;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        TenNguoiNhan = tenNguoiNhan;
    }

    public void setSoDT(String soDT) {
        SoDT = soDT;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public void setMaChuyenKhoan(String maChuyenKhoan) {
        MaChuyenKhoan = maChuyenKhoan;
    }

    public void setChiTietHoaDonList(List<ChiTietHoaDon> chiTietHoaDonList) {
        this.chiTietHoaDonList = chiTietHoaDonList;
    }

    String NgayGiao;

    public int getMaHD() {
        return MaHD;
    }

    public int getChuyenKhoan() {
        return ChuyenKhoan;
    }

    public String getNgayMua() {
        return NgayMua;
    }

    public String getNgayGiao() {
        return NgayGiao;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public String getTenNguoiNhan() {
        return TenNguoiNhan;
    }

    public String getSoDT() {
        return SoDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public String getMaChuyenKhoan() {
        return MaChuyenKhoan;
    }

    public List<ChiTietHoaDon> getChiTietHoaDonList() {
        return chiTietHoaDonList;
    }



}
