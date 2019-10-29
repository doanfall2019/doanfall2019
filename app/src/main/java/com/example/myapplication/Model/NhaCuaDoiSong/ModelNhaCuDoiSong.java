package com.example.myapplication.Model.NhaCuaDoiSong;

import com.example.myapplication.ConnectInternet.DownloadJSON;
import com.example.myapplication.Model.ObjectClass.SanPham;
import com.example.myapplication.Model.ObjectClass.ThuongHieu;
import com.example.myapplication.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelNhaCuDoiSong {

    public List<SanPham> LayDanhSachSanPhamTOP(String tenham, String tenmang){
        List<SanPham> sanPhams  = new ArrayList<>();

        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan =  TrangChuActivity.SERVER_NAME ;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", tenham);

        attrs.add(hsHam);
        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        // End phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPham = jsonObject.getJSONArray(tenmang);

            int dem = jsonArrayDanhSachSanPham.length();
            for(int i = 0; i < dem; i++){
                SanPham sanPham = new SanPham();
                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);

                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setANHLON(object.getString("HINHSANPHAM"));

                sanPhams.add(sanPham);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sanPhams;
    }

    public List<ThuongHieu> LayDanhSachThuongHieuLon(String tenham, String tenmang){
        List<ThuongHieu> thuongHieuList  = new ArrayList<>();

        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan =  TrangChuActivity.SERVER_NAME ;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", tenham);

        attrs.add(hsHam);
        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        // End phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachThuongHieu = jsonObject.getJSONArray(tenmang);

            int dem = jsonArrayDanhSachThuongHieu.length();
            for(int i = 0; i < dem; i++){
                ThuongHieu thuongHieu = new ThuongHieu();
                JSONObject object = jsonArrayDanhSachThuongHieu.getJSONObject(i);

                thuongHieu.setMATHUONGHIEU(object.getInt("MASP"));
                thuongHieu.setTENTHUONHIEU(object.getString("TENSP"));
                thuongHieu.setHINHTHUONGHIEU(object.getString("HINHSANPHAM"));

                thuongHieuList.add(thuongHieu);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return thuongHieuList;
    }
}
