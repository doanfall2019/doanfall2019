package com.example.myapplication.Model.ObjectClass;

import java.util.List;

public class SanPham {
    int MASP, GIA, SOLUONG,MALOAISP,MANGUOIDUNG, LUOTMUA, MATHUONGHIEU;
    String ANHLON, ANHNHO, THONGTIN;
    String TENSP;
    String TENNGUOIDUNG;
    List<ChiTietSanPham> chiTietSanPhamList;
    public String getTENNGUOIDUNG() {
        return TENNGUOIDUNG;
    }

    public void setTENNGUOIDUNG(String TENNGUOIDUNG) {
        this.TENNGUOIDUNG = TENNGUOIDUNG;
    }




    ChiTietKhuyenMai chiTietKhuyenMai;

    public ChiTietKhuyenMai getChiTietKhuyenMai() {
        return chiTietKhuyenMai;
    }

    public void setChiTietKhuyenMai(ChiTietKhuyenMai chiTietKhuyenMai) {
        this.chiTietKhuyenMai = chiTietKhuyenMai;
    }


    public int getSOLUONGTONKHO() {
        return SOLUONGTONKHO;
    }

    public void setSOLUONGTONKHO(int SOLUONGTONKHO) {
        this.SOLUONGTONKHO = SOLUONGTONKHO;
    }

    int SOLUONGTONKHO;

    public byte[] getHinhGioHang() {
        return HinhGioHang;
    }

    public void setHinhGioHang(byte[] hinhGioHang) {
        HinhGioHang = hinhGioHang;
    }

    byte [] HinhGioHang;


    public int getMATHUONGHIEU() {
        return MATHUONGHIEU;
    }

    public void setMATHUONGHIEU(int MATHUONGHIEU) {
        this.MATHUONGHIEU = MATHUONGHIEU;
    }



    public List<ChiTietSanPham> getChiTietSanPhamList() {
        return chiTietSanPhamList;
    }

    public void setChiTietSanPhamList(List<ChiTietSanPham> chiTietSanPhamList) {
        this.chiTietSanPhamList = chiTietSanPhamList;
    }

    public String getTENSP() {
        return TENSP;
    }

    public void setTENSP(String TENSP) {
        this.TENSP = TENSP;
    }

    public int getMASP() {
        return MASP;
    }

    public void setMASP(int MASP) {
        this.MASP = MASP;
    }

    public int getGIA() {
        return GIA;
    }

    public void setGIA(int GIA) {
        this.GIA = GIA;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public int getMALOAISP() {
        return MALOAISP;
    }

    public void setMALOAISP(int MALOAISP) {
        this.MALOAISP = MALOAISP;
    }

    public int getMANGUOIDUNG() {
        return MANGUOIDUNG;
    }

    public void setMANGUOIDUNG(int MANGUOIDUNG) {
        this.MANGUOIDUNG = MANGUOIDUNG;
    }

    public int getLUOTMUA() {
        return LUOTMUA;
    }

    public void setLUOTMUA(int LUOTMUA) {
        this.LUOTMUA = LUOTMUA;
    }

    public String getANHLON() {
        return ANHLON;
    }

    public void setANHLON(String ANHLON) {
        this.ANHLON = ANHLON;
    }

    public String getANHNHO() {
        return ANHNHO;
    }

    public void setANHNHO(String ANHNHO) {
        this.ANHNHO = ANHNHO;
    }

    public String getTHONGTIN() {
        return THONGTIN;
    }

    public void setTHONGTIN(String THONGTIN) {
        this.THONGTIN = THONGTIN;
    }
}
