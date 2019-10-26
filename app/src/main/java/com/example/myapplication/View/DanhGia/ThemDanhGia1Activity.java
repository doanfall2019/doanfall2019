package com.example.myapplication.View.DanhGia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.myapplication.Model.ObjectClass.DanhGia;
import com.example.myapplication.Presenter.DanhGia.PresenterLogicDanhGia;
import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class ThemDanhGia1Activity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener, ViewDanhGia, View.OnClickListener {


    TextInputLayout input_edTieuDe,input_edNoiDung;
    EditText edTieuDe,edNoiDung;
    RatingBar rbDanhGia;
    int masp = 0;
    int sosao = 0;
    Button btnDongYDanhGia;
    PresenterLogicDanhGia presenterLogicDanhGia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_danh_gia1);
        input_edNoiDung = findViewById(R.id.input_edNoiDungDanhGia);
        input_edTieuDe = findViewById(R.id.input_edTieuDeDanhGia);
        edTieuDe = findViewById(R.id.edTieuDe);
        edNoiDung =findViewById(R.id.edNoiDung);
        rbDanhGia = findViewById(R.id.rbDanhGia);
        btnDongYDanhGia = findViewById(R.id.btnDongYDanhGia);

        masp =getIntent().getIntExtra("masp",0);//truyen tu ma chi tiet
        presenterLogicDanhGia = new PresenterLogicDanhGia(this);
        rbDanhGia.setOnRatingBarChangeListener(this);
        btnDongYDanhGia.setOnClickListener(this);
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
        sosao = (int)v;
    }

    @Override
    public void DanhGiaThanhCong() {
        Toast.makeText(this,"danh gia thanh cong",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DanhGiaThatBai() {
            Toast.makeText(this,"Ban Khong the Danh Gia san Pham nay",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void HienThiDanhSachDanhGiaTheoSanPham(List<DanhGia> danhGiaList) {

    }

    @Override
    public void onClick(View view) {


        String madg = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            String tenthietbi = Build.MODEL;
            String tieude = edTieuDe.getText().toString();
            String noidung = edNoiDung.getText().toString();


            if (tieude.trim().length() > 0){
                    input_edTieuDe.setErrorEnabled(false);
                    input_edTieuDe.setError("");
            }else {
                input_edTieuDe.setErrorEnabled(true);
                input_edTieuDe.setError("Ban chua nhap tieu de");
            }
            if(noidung.trim().length()> 0){
                input_edNoiDung.setError("");
                input_edNoiDung.setErrorEnabled(false);
            }else {
                input_edNoiDung.setErrorEnabled(true);
                input_edNoiDung.setError("Ban chua nhap noi dung");
            }

            if(!input_edNoiDung.isErrorEnabled() && !input_edTieuDe.isErrorEnabled()){
                DanhGia danhGia = new DanhGia();
                danhGia.setMASP(masp);
                danhGia.setMADG(madg);
                danhGia.setTIEUDE(tieude);
                danhGia.setNOIDUNG(noidung);
                danhGia.setSOSAO(sosao);
                danhGia.setTENTHIETBI(tenthietbi);
                presenterLogicDanhGia.ThemDangGia(danhGia);
            }
    }
}
