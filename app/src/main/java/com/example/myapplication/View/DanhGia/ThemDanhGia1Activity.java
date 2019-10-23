package com.example.myapplication.View.DanhGia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputLayout;

public class ThemDanhGia1Activity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener {


    TextInputLayout input_edTieuDe,input_edNoiDung;
    EditText edTieuDe,edNoiDung;
    RatingBar rbDanhGia;
    int masp = 0;
    int sosao = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_danh_gia1);
        input_edNoiDung = findViewById(R.id.input_edNoiDungDanhGia);
        input_edTieuDe = findViewById(R.id.input_edTieuDeDanhGia);
        edTieuDe = findViewById(R.id.edTieuDe);
        edNoiDung =findViewById(R.id.edNoiDung);
        rbDanhGia = findViewById(R.id.rbDanhGia);

        masp =getIntent().getIntExtra("masp",0);//truyen tu ma chi tiet
        rbDanhGia.setOnRatingBarChangeListener(this);
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
        sosao = (int)v;
    }
}
