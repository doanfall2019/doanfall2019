package com.example.myapplication.View.ChiTietSanPham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.myapplication.Adapter.AdapterViewPagerSlider;
import com.example.myapplication.Model.ObjectClass.ChiTietSanPham;
import com.example.myapplication.Model.ObjectClass.SanPham;
import com.example.myapplication.Presenter.ChiTietSanPham.FragmentSliderChiTietSanPham;
import com.example.myapplication.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.myapplication.R;
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
    TextView txtTenSanPham,txtGiaTien,txtTenCHDongGoi,txtThongTinChiTiet;//1
    Toolbar toolbar;
    ImageView imXemThemChiTiet;
    LinearLayout lnThongSoKyThuat;
    List<Fragment> fragmentList;
    TextView txtVietDanhGia;
    int masp;
    boolean kiemtraxochitiet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        viewPager = findViewById(R.id.viewpagerSlider);
        layoutDots = findViewById(R.id.layoutDots);
        txtTenSanPham =findViewById(R.id.txtTenSanPham);//2
        txtGiaTien =findViewById(R.id.txtGiaTien);
        txtTenCHDongGoi =findViewById(R.id.txtTenCHDongGoi);
        txtThongTinChiTiet = findViewById(R.id.txtThongTinChiTiet);
        imXemThemChiTiet =findViewById(R.id.imXemThemChiTiet);
        lnThongSoKyThuat =findViewById(R.id.lnThongSoKyThuat);
        txtVietDanhGia = findViewById(R.id.txtVietDanhGia);
        toolbar =findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        int masp = getIntent().getIntExtra("masp",0);

        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        presenterLogicChiTietSanPham.LayChiTietSanPham(masp);

        txtVietDanhGia.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu,menu);//khoi tao menu
        return true;
    }

    @Override
    public void HienThiChiTietSanPham(final SanPham sanPham) {
        masp = sanPham.getMASP();


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
        //duyet chitiet san pham


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
