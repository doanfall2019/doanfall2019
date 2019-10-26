package com.example.myapplication.View.DanhGia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.myapplication.Adapter.AdapterDanhGia;
import com.example.myapplication.Model.ObjectClass.DanhGia;
import com.example.myapplication.Model.ObjectClass.ILoadMore;
import com.example.myapplication.Model.ObjectClass.LoadMoreScroll;
import com.example.myapplication.Presenter.DanhGia.PresenterLogicDanhGia;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class DanhSachDanhGiaActivity extends AppCompatActivity implements ViewDanhGia, ILoadMore {
        RecyclerView recyclerDanhSachDanhGia;
        ProgressBar progressBar;
        int masp = 0;
        PresenterLogicDanhGia presenterLogicDanhGia;
        List<DanhGia> tatcaDanhGia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_danh_gia);
        recyclerDanhSachDanhGia = findViewById(R.id.recyclerDanhSachDanhGia);
        progressBar =findViewById(R.id.progress_bar);

        masp = getIntent().getIntExtra("masp",0);
        tatcaDanhGia = new ArrayList<>();
        presenterLogicDanhGia = new PresenterLogicDanhGia(this);
        presenterLogicDanhGia.LayDanhSachDanhGiaCuaSanPham(masp,0,progressBar);

    }

    @Override
    public void DanhGiaThanhCong() {

    }

    @Override
    public void DanhGiaThatBai() {

    }

    @Override
    public void HienThiDanhSachDanhGiaTheoSanPham(List<DanhGia> danhGiaList) {
        tatcaDanhGia.addAll(danhGiaList);
        AdapterDanhGia adapterDanhGia = new AdapterDanhGia(this,tatcaDanhGia,0);
        RecyclerView.LayoutManager layoutManager = new  LinearLayoutManager(this);
        recyclerDanhSachDanhGia.setLayoutManager(layoutManager);
        recyclerDanhSachDanhGia.setAdapter(adapterDanhGia);
        recyclerDanhSachDanhGia.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
        adapterDanhGia.notifyDataSetChanged();

    }

    @Override
    public void LoadMore(int tongitem) {
            presenterLogicDanhGia.LayDanhSachDanhGiaCuaSanPham(masp,tongitem,progressBar);
    }
}
