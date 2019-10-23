package com.example.myapplication.View.ChiTietSanPham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import android.graphics.drawable.Drawable;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;

import android.view.MenuItem;
import android.view.View;

import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication.Adapter.AdapterViewPagerSlider;
import com.example.myapplication.Model.ObjectClass.ChiTietSanPham;
import com.example.myapplication.Model.ObjectClass.SanPham;
import com.example.myapplication.Presenter.ChiTietSanPham.FragmentSliderChiTietSanPham;
import com.example.myapplication.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.myapplication.R;

import com.example.myapplication.View.GioHang.GioHangActivity;
import com.example.myapplication.View.ThanhToan.ThanhToanActivity;
import com.example.myapplication.View.TrangChu.TrangChuActivity;

import java.io.ByteArrayOutputStream;

import com.example.myapplication.View.DanhGia.ThemDanhGia1Activity;
import com.example.myapplication.View.TrangChu.TrangChuActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham, ViewPager.OnPageChangeListener , View.OnClickListener {

    ViewPager viewPager;
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    TextView[] txtDots;
    LinearLayout layoutDots;
    Button btnMuaNgay;
    ImageView imThemGioHang;
        SanPham sanPhamGioHang;
    TextView txtGioHang;
    int soluongtonkho;
    List<Fragment> fragmentList;
    Menu menu;
    boolean onPause = false;
    Toolbar toolbar;
    TextView txtTenSanPham, txtGiaTien, txtTenCHDongGoi, txtThongTinChiTiet;//1
    ImageView imXemThemChiTiet;
    LinearLayout lnThongSoKyThuat;
    TextView txtVietDanhGia;
    int masp;
    boolean kiemtraxochitiet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        viewPager = findViewById(R.id.viewpagerSlider);
        layoutDots = findViewById(R.id.layoutDots);

        imThemGioHang = findViewById(R.id.imThemGioHang);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");

        txtTenSanPham = findViewById(R.id.txtTenSanPham);//2
        txtGiaTien = findViewById(R.id.txtGiaTien);
        txtTenCHDongGoi = findViewById(R.id.txtTenCHDongGoi);
        txtThongTinChiTiet = findViewById(R.id.txtThongTinChiTiet);
        imXemThemChiTiet = findViewById(R.id.imXemThemChiTiet);
        lnThongSoKyThuat = findViewById(R.id.lnThongSoKyThuat);
        txtVietDanhGia = findViewById(R.id.txtVietDanhGia);
        btnMuaNgay =findViewById(R.id.btnMuaNgay);
        toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

        int masp = getIntent().getIntExtra("masp", 0);

        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        presenterLogicChiTietSanPham.LayChiTietSanPham(masp);


        imThemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = fragmentList.get(0);
                View view = fragment.getView();
                ImageView imageView = view.findViewById(R.id.imHinhSlider);
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

                //imThemGioHang.setImageBitmap(bitmap);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] hinhsanphamgiohang = byteArrayOutputStream.toByteArray();
                sanPhamGioHang.setHinhGioHang(hinhsanphamgiohang);
                sanPhamGioHang.setSOLUONG(1);
                presenterLogicChiTietSanPham.ThemGioHang(sanPhamGioHang, ChiTietSanPhamActivity.this);

            }
        });
        btnMuaNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = fragmentList.get(0);
                View view = fragment.getView();
                ImageView imageView = view.findViewById(R.id.imHinhSlider);
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

                //imThemGioHang.setImageBitmap(bitmap);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] hinhsanphamgiohang = byteArrayOutputStream.toByteArray();
                sanPhamGioHang.setHinhGioHang(hinhsanphamgiohang);
                sanPhamGioHang.setSOLUONG(1);
                presenterLogicChiTietSanPham.ThemGioHang(sanPhamGioHang, ChiTietSanPhamActivity.this);
                Intent ithanhtoan = new Intent(ChiTietSanPhamActivity.this, ThanhToanActivity.class);
                startActivity(ithanhtoan);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        this.menu = menu;
        MenuItem iGioHang = menu.findItem(R.id.itGioHang);
        View giaoDienCustomGioHang = MenuItemCompat.getActionView(iGioHang);
        txtGioHang = giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPham);
        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(a);
            }
        });

        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));
        txtVietDanhGia.setOnClickListener(this);
        return  true;
    }







    @Override
    public void HienThiChiTietSanPham(final SanPham sanPham) {
        masp = sanPham.getMASP();
        sanPhamGioHang = sanPham;
        sanPhamGioHang.setSOLUONGTONKHO(sanPham.getSOLUONG());
            txtTenSanPham.setText(sanPham.getTENSP());//3
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA());
        txtGiaTien.setText(gia + "VND") ;
        txtTenCHDongGoi.setText(sanPham.getTENNGUOIDUNG());//lay ten nguoi dung
        txtThongTinChiTiet.setText(sanPham.getTHONGTIN().substring(0,100));
        if(sanPham.getTHONGTIN().length()<100){
            imXemThemChiTiet.setVisibility(View.GONE);
        }else {
            imXemThemChiTiet.setVisibility(View.VISIBLE);
            imXemThemChiTiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    kiemtraxochitiet =!kiemtraxochitiet;
                    if(kiemtraxochitiet){
                        // sau khi xo chi tiet
                        txtThongTinChiTiet.setText(sanPham.getTHONGTIN());

                        imXemThemChiTiet.setImageDrawable(getHinhChiTiet(R.drawable.ic_remove_black_24dp));
                            lnThongSoKyThuat.setVisibility(View.VISIBLE);// cho no hien thi len
                        HienThiThongSoKythuat(sanPham);

                    }else {
                        // sau khi dong chi tiet
                        txtThongTinChiTiet.setText(sanPham.getTHONGTIN().substring(0,100));
                        imXemThemChiTiet.setImageDrawable(getHinhChiTiet(R.drawable.ic_add_black_24dp));
                        lnThongSoKyThuat.setVisibility(View.GONE);//an no di
                    }
                }
            });
        }
    }
    private void HienThiThongSoKythuat(SanPham sanPham){

        List<ChiTietSanPham> chiTietSanPhams =sanPham.getChiTietSanPhamList();
        lnThongSoKyThuat.removeAllViews();
        TextView txtTieuDeThongSoKyThuat = new TextView(this);
        txtTieuDeThongSoKyThuat.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        lnThongSoKyThuat.addView(txtTieuDeThongSoKyThuat);
        for (int i = 0; i<chiTietSanPhams.size();i++){
            LinearLayout lnChiTiet = new LinearLayout(this);
            lnChiTiet.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lnChiTiet.setOrientation(LinearLayout.HORIZONTAL);


            TextView txtTenThongSo = new TextView(this);
            txtTenThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
            txtTenThongSo.setText(chiTietSanPhams.get(i).getTENCHITIET());
            TextView txtGiaTriThongSo = new TextView(this);
            txtGiaTriThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
            txtGiaTriThongSo.setText(chiTietSanPhams.get(i).getGIATRI());
            lnChiTiet.addView(txtTenThongSo);
            lnChiTiet.addView(txtGiaTriThongSo);
            lnThongSoKyThuat.addView(lnChiTiet);
        }

    }

    @Override
    public void HienSliderSanPham(String[] linkhinhsanpham) {
        fragmentList = new ArrayList<>();

        for (int i=0; i<linkhinhsanpham.length; i++){
            FragmentSliderChiTietSanPham fragmentSliderChiTietSanPham = new FragmentSliderChiTietSanPham();
            Bundle bundle = new Bundle();
            bundle.putString("linkhinh",TrangChuActivity.SERVER+linkhinhsanpham[i]);
            fragmentSliderChiTietSanPham.setArguments(bundle);

            fragmentList.add(fragmentSliderChiTietSanPham);
        }

        AdapterViewPagerSlider adapterViewPagerSlider = new AdapterViewPagerSlider(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapterViewPagerSlider);
        adapterViewPagerSlider.notifyDataSetChanged();

        ThemDotSlider(0);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void ThemGioHangThanhCong() {
        Toast.makeText(this, "thanh cong", Toast.LENGTH_SHORT).show();
        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));
    }

    @Override
    public void ThemGioHangThatBai() {
        Toast.makeText(this, "That bai", Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onResume() {
        super.onResume();
        if (onPause == true){
            PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
            txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));
        }
    }


    @Override
    protected void onPause() {
        super.onPause();

        onPause = true;
    }


    private void ThemDotSlider(int vitrihientai){
        txtDots = new TextView[fragmentList.size()];

        layoutDots.removeAllViews();
        for (int i=0;i<fragmentList.size();i++){
            txtDots[i] = new TextView(this);
            txtDots[i].setText(Html.fromHtml("&#8226;"));
            txtDots[i].setTextSize(40);
            txtDots[i].setTextColor(getIdColor(R.color.colorSliderInactive));

            layoutDots.addView(txtDots[i]);
        }
        txtDots[vitrihientai].setTextColor(getIdColor(R.color.bgToolbar));
    }
    private Drawable getHinhChiTiet(int idDrawable){
            Drawable drawable;
            if (Build.VERSION.SDK_INT >21){
                drawable=  ContextCompat.getDrawable(this, idDrawable);

            }else {
                drawable = getResources().getDrawable(idDrawable);
    }
        return drawable;
    }

    private int getIdColor(int idcolor){
        int color = 0;
        if (Build.VERSION.SDK_INT >21){
            color = ContextCompat.getColor(this, idcolor);
        }else {
            color = getResources().getColor(idcolor);
        }
        return color;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ThemDotSlider(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.txtVietDanhGia:
                Intent iThemDanhGia = new Intent(this, ThemDanhGia1Activity.class);
                iThemDanhGia.putExtra("masp",masp);
                startActivity(iThemDanhGia);
                ;break;
        }
    }
}
