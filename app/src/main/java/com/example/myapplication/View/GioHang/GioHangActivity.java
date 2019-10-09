package com.example.myapplication.View.GioHang;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.AdapterGioHang;
import com.example.myapplication.Model.ObjectClass.SanPham;
import com.example.myapplication.Presenter.GioHang.PresenterLogicGioHang;
import com.example.myapplication.R;

import java.util.List;

public class GioHangActivity extends AppCompatActivity implements ViewGioHang{

    RecyclerView recyclerViewGioHang;
    PresenterLogicGioHang presenterLogicGioHang;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);

        recyclerViewGioHang =findViewById(R.id.recyclerGioHang);
        presenterLogicGioHang = new PresenterLogicGioHang(this);
        presenterLogicGioHang.LayDanhSachSanPhamTrongGioHang(this);

    }

    @Override
    public void HienThiDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList) {
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(this);
        AdapterGioHang adapterGioHang = new AdapterGioHang(this,sanPhamList);
        recyclerViewGioHang.setLayoutManager(layoutManager);
        recyclerViewGioHang.setAdapter(adapterGioHang);
    }
}
