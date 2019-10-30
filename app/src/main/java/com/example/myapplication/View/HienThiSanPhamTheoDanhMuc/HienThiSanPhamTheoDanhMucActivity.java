package com.example.myapplication.View.HienThiSanPhamTheoDanhMuc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.Adapter.AdapterTopDienThoaiDienTu;
import com.example.myapplication.Model.ObjectClass.ILoadMore;
import com.example.myapplication.Model.ObjectClass.LoadMoreScroll;
import com.example.myapplication.Model.ObjectClass.SanPham;
import com.example.myapplication.Presenter.HienThiSanPhamTheoDanhMuc.PresenterLogicHienThiSanPhamTheoDanhMuc;
import com.example.myapplication.R;
import com.example.myapplication.View.DangNhap_DangKy.DangNhapActivity;
import com.example.myapplication.View.TrangChu.TrangChuActivity;
import com.example.myapplication.View.TrangChu.ViewHienThiSanPhamTheoDanhMuc;

import java.util.List;

public class HienThiSanPhamTheoDanhMucActivity extends AppCompatActivity implements ViewHienThiSanPhamTheoDanhMuc, View.OnClickListener, ILoadMore {
    RecyclerView recyclerView;
    Button btnThayDoiTrangThaiRecycler, btnGiaTang, btnGiaGiam;
    boolean dangGrid = true;
    RecyclerView.LayoutManager layoutManager;
    PresenterLogicHienThiSanPhamTheoDanhMuc sanPhamTheoDanhMuc;
    int masp;
    boolean kiemtra;
    AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu;
    Toolbar toolbar;
    List<SanPham> sanPhamList1;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_san_pham_theo_danh_muc);

        recyclerView = findViewById(R.id.recyclerViewHienThiSanPhamTheoDanhMuc);
        btnThayDoiTrangThaiRecycler = findViewById(R.id.btnThayDoiTrangThaiRecycler);
        toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.progressBar);
        btnGiaTang = findViewById(R.id.btnGiaTang);
        btnGiaGiam = findViewById(R.id.btnGiaGiam);

        Intent intent = getIntent();
        masp = intent.getIntExtra("MALOAI", 0);
        String tensanpham = intent.getStringExtra("TENLOAI");
        kiemtra = intent.getBooleanExtra("KIEMTRA", false);

        sanPhamTheoDanhMuc = new PresenterLogicHienThiSanPhamTheoDanhMuc(this);
        sanPhamTheoDanhMuc.layDanhSachSanPhamTheoMaLoai(masp, kiemtra);

        btnThayDoiTrangThaiRecycler.setOnClickListener(this);
        btnGiaTang.setOnClickListener(this);
        btnGiaGiam.setOnClickListener(this);

        toolbar.setTitle(tensanpham);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iTrangChu = new Intent(HienThiSanPhamTheoDanhMucActivity.this, TrangChuActivity.class);
                startActivity(iTrangChu);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        return true;
    }

    @Override
    public void HienThiDanhSachSanPham(List<SanPham> sanPhamList) {
        sanPhamList1 = sanPhamList;

        if (dangGrid) {
            layoutManager = new GridLayoutManager(HienThiSanPhamTheoDanhMucActivity.this, 2);
            adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(HienThiSanPhamTheoDanhMucActivity.this, R.layout.custom_layout_topdienthoaivamaytinhbang, sanPhamList1);
        } else {
            layoutManager = new LinearLayoutManager(HienThiSanPhamTheoDanhMucActivity.this);
            adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(HienThiSanPhamTheoDanhMucActivity.this, R.layout.custom_layout_list_topdienthoaivamaytinhbang, sanPhamList1);
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTopDienThoaiDienTu);
        recyclerView.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
        adapterTopDienThoaiDienTu.notifyDataSetChanged();

    }

    @Override
    public void LoiHienThiDanhSachSanPham() {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnThayDoiTrangThaiRecycler:
                dangGrid = !dangGrid;
                sanPhamTheoDanhMuc.layDanhSachSanPhamTheoMaLoai(masp, kiemtra);
                break;
            case R.id.btnGiaGiam:
                sanPhamTheoDanhMuc.layDanhSachSanPhamTheoMaLoai(masp,kiemtra);
                break;

            case R.id.btnGiaTang:
                sanPhamTheoDanhMuc.layDanhSachSanPhamTheoGiaTang(masp);
                break;
        }
    }

    @Override
    public void LoadMore(int tongitem) {
        List<SanPham> ListsanPhamsLoadMore = sanPhamTheoDanhMuc.layDanhSachSanPhamTheoMaLoaiLoadMore(masp, kiemtra, tongitem, progressBar);
        sanPhamList1.addAll(ListsanPhamsLoadMore);

        adapterTopDienThoaiDienTu.notifyDataSetChanged();
    }
}

