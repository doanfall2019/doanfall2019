package com.example.myapplication.View.GioHang;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.AdapterGioHang;
import com.example.myapplication.Model.ObjectClass.SanPham;
import com.example.myapplication.Presenter.GioHang.PresenterLogicGioHang;
import com.example.myapplication.R;
import com.example.myapplication.View.ThanhToan.ThanhToanActivity;

import java.util.List;

public class GioHangActivity extends AppCompatActivity implements ViewGioHang, View.OnClickListener {

    RecyclerView recyclerViewGioHang;
    PresenterLogicGioHang presenterLogicGioHang;
    Toolbar toolbar;
    Button btnMuaNgay;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);

        toolbar = findViewById(R.id.toolbar);
        btnMuaNgay = findViewById(R.id.btnMuaNgay);
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

        btnMuaNgay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case  R.id.btnMuaNgay:
                Intent a = new Intent(GioHangActivity.this, ThanhToanActivity.class);
                startActivity(a);
                break;
        }
    }
}
