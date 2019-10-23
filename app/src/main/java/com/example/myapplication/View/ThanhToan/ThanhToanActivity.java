package com.example.myapplication.View.ThanhToan;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.Model.HoaDon.ChiTietHoaDon;
import com.example.myapplication.Model.HoaDon.HoaDon;
import com.example.myapplication.Model.ObjectClass.SanPham;
import com.example.myapplication.Presenter.ThanhToan.PresenterLogicThanhToan;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ThanhToanActivity extends AppCompatActivity implements View.OnClickListener, ViewThanhToan{
    Toolbar toolbar;
    EditText edTenNguoiNhan, edDiaChi, edSoDT;
    ImageButton imThanhToanVisa, imThanhToanKhiGiaoHang;
    Button btnThanhToan;
    CheckBox cbThoaThuan;
    PresenterLogicThanhToan presenterLogicThanhToan;
    List<ChiTietHoaDon> chiTietHoaDons=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thanhtoan);

        edTenNguoiNhan=findViewById(R.id.edTenNguoiNhan);
        edDiaChi = findViewById(R.id.edDiaChi);
        edSoDT = findViewById(R.id.edSoDT);
        cbThoaThuan =findViewById(R.id.cbThoaThuan);

        imThanhToanKhiGiaoHang= findViewById(R.id.imNhanTienKhiGiaoHang);
        imThanhToanVisa =findViewById(R.id.imThanhToanVisa);
        btnThanhToan=findViewById(R.id.btnThanhToan);

        presenterLogicThanhToan=new PresenterLogicThanhToan(this,this);
        presenterLogicThanhToan.LayDanhSachSanPhamTrongGioHang();
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        btnThanhToan.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.btnThanhToan:
                String tennguoinhan= edTenNguoiNhan.getText().toString();
                String sodt = edSoDT.getText().toString();
                String diachi =edDiaChi.getText().toString();

                if ((tennguoinhan.trim().length() > 0 && sodt.trim().length()>0 && diachi.trim().length() >0)){
                    if (cbThoaThuan.isChecked()){
                        HoaDon hoaDon=new HoaDon();
                        hoaDon.setTenNguoiNhan(tennguoinhan);
                        hoaDon.setSoDT(sodt);
                        hoaDon.setDiaChi(diachi);

                        hoaDon.setChiTietHoaDonList(chiTietHoaDons);
                        presenterLogicThanhToan.ThemHoaDon(hoaDon);

                    }else {
                        Toast.makeText(this, "Vui lòng nhập đủ", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "Vui lòng nhập đủ", Toast.LENGTH_SHORT).show();

                }
            break;
        }
    }

    @Override
    public void DatHangThanhCong() {
        Toast.makeText(this, "ThanhCOng", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DatHangThatBai() {
        Toast.makeText(this, "ThatBai", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList) {

        for (int i =0; i<sanPhamList.size();i++){
            ChiTietHoaDon chiTietHoaDon =new ChiTietHoaDon();
            chiTietHoaDon.setMaSP(sanPhamList.get(1).getMASP());
            chiTietHoaDon.setSoLuong(sanPhamList.get(1).getSOLUONG());

            chiTietHoaDons.add(chiTietHoaDon);

        }
    }
}
