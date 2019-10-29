package com.example.myapplication.Presenter.NhaCuaDoiSong;

import com.example.myapplication.Model.NhaCuaDoiSong.ModelNhaCuDoiSong;
import com.example.myapplication.Model.ObjectClass.NhaCuaDoiSong;
import com.example.myapplication.Model.ObjectClass.SanPham;
import com.example.myapplication.Model.ObjectClass.ThuongHieu;
import com.example.myapplication.View.TrangChu.ViewNhaCuaDoiSong;

import java.util.ArrayList;
import java.util.List;

public class PresenterLogicNhaCuaDoiSong implements IPresenterNhaCuaDoiSong {

    ViewNhaCuaDoiSong viewNhaCuaDoiSong;
    ModelNhaCuDoiSong modelNhaCuDoiSong;

    public PresenterLogicNhaCuaDoiSong(ViewNhaCuaDoiSong viewNhaCuaDoiSong){
        this.viewNhaCuaDoiSong = viewNhaCuaDoiSong;
        modelNhaCuDoiSong = new ModelNhaCuDoiSong();
    }

    @Override
    public void LayDanhSachNhaCuaDoiSong() {
        List<NhaCuaDoiSong> nhaCuaDoiSongList = new ArrayList<>();

        List<ThuongHieu> thuongHieuList =  modelNhaCuDoiSong.LayDanhSachThuongHieuLon("LayDanhSachCacThuongHieuLon","DANHSACHTHUONGHIEU");
        List<SanPham> sanPhamList = modelNhaCuDoiSong.LayDanhSachSanPhamTOP("LayDanhSachTopDienThoaiMayTinhBang","TOPDIENTHOAIVAMAYTINHBANG");

        NhaCuaDoiSong nhaCuaDoiSong1 = new NhaCuaDoiSong();
        nhaCuaDoiSong1.setThuongHieus(thuongHieuList);
        nhaCuaDoiSong1.setSanPhams(sanPhamList);
        nhaCuaDoiSong1.setTennoibat("Dụng cụ gia đình");
        nhaCuaDoiSong1.setTentopnoibat("Top các sản phẩm bán chạy");
        nhaCuaDoiSong1.setThuonghieu(true);
        nhaCuaDoiSongList.add(nhaCuaDoiSong1);


        List<ThuongHieu> phukienList =  modelNhaCuDoiSong.LayDanhSachThuongHieuLon("LayDanhSachPhuKien","DANHSACHPHUKIEN");
        List<SanPham> topphukienList = modelNhaCuDoiSong.LayDanhSachSanPhamTOP("LayDanhSachTopPhuKien","TOPPHUKIEN");

        NhaCuaDoiSong nhaCuaDoiSong2 = new NhaCuaDoiSong();
        nhaCuaDoiSong2.setThuongHieus(phukienList);
        nhaCuaDoiSong2.setSanPhams(topphukienList);
        nhaCuaDoiSong2.setTennoibat("Thời trang mẹ & bé");
        nhaCuaDoiSong2.setTentopnoibat("Top quần áo hot trend");
        nhaCuaDoiSong2.setThuonghieu(false);
        nhaCuaDoiSongList.add(nhaCuaDoiSong2);

        List<ThuongHieu> tienichList =  modelNhaCuDoiSong.LayDanhSachThuongHieuLon("LayDanhSachTienIch","DANHSACHTIENICH");
        List<SanPham> toptienichList = modelNhaCuDoiSong.LayDanhSachSanPhamTOP("LayTopTienIch","TOPTIENICH");

        NhaCuaDoiSong nhaCuaDoiSong3 = new NhaCuaDoiSong();
        nhaCuaDoiSong3.setThuongHieus(tienichList);
        nhaCuaDoiSong3.setSanPhams(toptienichList);
        nhaCuaDoiSong3.setTennoibat("Sức khỏe đời sống");
        nhaCuaDoiSong3.setTentopnoibat("Dụng cụ tập luyện");
        nhaCuaDoiSong3.setThuonghieu(false);
        nhaCuaDoiSongList.add(nhaCuaDoiSong3);

        if (thuongHieuList.size() > 0 ){
            viewNhaCuaDoiSong.HienThiDanhSach(nhaCuaDoiSongList);
        }
    }
}
