package com.example.myapplication.Model.KhuyenMai;

import com.example.myapplication.ConnectInternet.DownloadJSON;
import com.example.myapplication.Model.ObjectClass.ChiTietKhuyenMai;
import com.example.myapplication.Model.ObjectClass.KhuyenMai;
import com.example.myapplication.Model.ObjectClass.SanPham;
import com.example.myapplication.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelKhuyenMai {

    public List<KhuyenMai> LayDanhSachSanPhamTheoMaLoai(String tenmang, String tenham) {

        List<KhuyenMai> khuyenMais = new ArrayList<>();

        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", tenham);

        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        // End phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachKhuyenMai = jsonObject.getJSONArray(tenmang);

            int dem = jsonArrayDanhSachKhuyenMai.length();
            for (int i = 0; i < dem; i++) {
                JSONObject object = jsonArrayDanhSachKhuyenMai.getJSONObject(i);
                KhuyenMai khuyenMai = new KhuyenMai();
                khuyenMai.setMAKM(object.getInt("MAKM"));
                khuyenMai.setTENKM(object.getString("TENKM"));
                khuyenMai.setTENLOAISP(object.getString("TENLOAISP"));
                khuyenMai.setHINHKHUYENMAI(TrangChuActivity.SERVER + object.getString("HINHKHUYENMAI"));

                List<SanPham> sanPhamList = new ArrayList<>();
                JSONArray arrayDanhSachSanPham = object.getJSONArray("DANHSACHSANPHAM");
                int demsanpham = arrayDanhSachSanPham.length();
                for (int j=0; j< demsanpham;j++){
                    JSONObject objectSanPham = arrayDanhSachSanPham.getJSONObject(j);
                    SanPham sanPham = new SanPham();
                    sanPham.setMASP(objectSanPham.getInt("MASP"));
                    sanPham.setTENSP(objectSanPham.getString("TENSP"));
                    sanPham.setGIA(objectSanPham.getInt("GIA"));
                    sanPham.setANHLON(TrangChuActivity.SERVER + objectSanPham.getString("HINHLON"));
                    sanPham.setANHNHO(TrangChuActivity.SERVER + objectSanPham.getString("HINHNHO"));

                    ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                    chiTietKhuyenMai.setPHANTRAMKM(objectSanPham.getInt("PHANTRAMKM"));

                    sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);

                    sanPhamList.add(sanPham);
                }

                khuyenMai.setDanhSachSanPhamKhuyenMai(sanPhamList);
                khuyenMais.add(khuyenMai);

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return khuyenMais;
    }
}
