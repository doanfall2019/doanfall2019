package com.example.myapplication.View.ChiTietSanPham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.AdapterViewPagerSlider;
import com.example.myapplication.Model.ObjectClass.SanPham;
import com.example.myapplication.Presenter.ChiTietSanPham.FragmentSliderChiTietSanPham;
import com.example.myapplication.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.myapplication.R;
import com.example.myapplication.View.GioHang.GioHangActivity;
import com.example.myapplication.View.TrangChu.TrangChuActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham, ViewPager.OnPageChangeListener {

    ViewPager viewPager;
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    TextView[] txtDots;
    LinearLayout layoutDots;
    ImageView imThemGioHang;
    SanPham sanPhamGioHang;
    TextView txtGioHang;
    int soluongtonkho;
    List<Fragment> fragmentList;
     Menu menu;
     boolean onPause =false;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        viewPager = findViewById(R.id.viewpagerSlider);
        layoutDots = findViewById(R.id.layoutDots);
        imThemGioHang = findViewById(R.id.imThemGioHang);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        int masp = getIntent().getIntExtra("masp",0);

        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        presenterLogicChiTietSanPham.LayChiTietSanPham(masp);

        imThemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment =fragmentList.get(0);
                View view = fragment.getView();
                ImageView imageView = (ImageView) view.findViewById(R.id.imHinhSlider);
                Bitmap bitmap =((BitmapDrawable)imageView.getDrawable()).getBitmap();

                //imThemGioHang.setImageBitmap(bitmap);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] hinhsanphamgiohang = byteArrayOutputStream.toByteArray();
                sanPhamGioHang.setHinhGioHang(hinhsanphamgiohang);
                sanPhamGioHang.setSOLUONG(1);
                presenterLogicChiTietSanPham.ThemGioHang(sanPhamGioHang,ChiTietSanPhamActivity.this);

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


        return true;
    }

    @Override
    public void HienThiChiTietSanPham(SanPham sanPham) {

        sanPhamGioHang = sanPham;
        sanPhamGioHang.setSOLUONGTONKHO(sanPham.getSOLUONG());
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
}
