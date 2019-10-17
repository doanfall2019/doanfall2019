package com.example.myapplication.View.TrangChu.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.AdapterDanhSachKhuyenMai;
import com.example.myapplication.Model.ObjectClass.KhuyenMai;
import com.example.myapplication.Presenter.KhuyenMai.PresenterLogicKhuyenMai;
import com.example.myapplication.R;
import com.example.myapplication.View.TrangChu.ViewKhuyenMai;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FragmentChuongTrinhKhuyenMai extends Fragment implements ViewKhuyenMai {

    LinearLayout lnHinhKhuyenMai;
    RecyclerView recyclerViewDanhSachKhuyenMai;
    PresenterLogicKhuyenMai presenterLogicKhuyenMai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chuongtrinhkhuyenmai, container, false);

        lnHinhKhuyenMai = view.findViewById(R.id.lnHinhKhuyenMai);
        recyclerViewDanhSachKhuyenMai = view.findViewById(R.id.recyclerDanhSachKhuyenMai);

        presenterLogicKhuyenMai =  new PresenterLogicKhuyenMai(this);
        presenterLogicKhuyenMai.LayDanhSachKhuyenMai();

        return view;
    }

    @Override
    public void HienThiDanhSachKhuyenMai(List<KhuyenMai> khuyenMaiList) {
        int demhinh = khuyenMaiList.size();
        if (demhinh > 5){
            demhinh = 5;
        } else {
            demhinh = khuyenMaiList.size();
        }

        lnHinhKhuyenMai.removeAllViews();
        for (int i=0;i<demhinh;i++){
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0,10,0,10);
            imageView.setLayoutParams(layoutParams);

            Picasso.with(getContext()).load(khuyenMaiList.get(i).getHINHKHUYENMAI()).resize(1200,300).centerCrop().into(imageView);

            lnHinhKhuyenMai.addView(imageView);
        }

        AdapterDanhSachKhuyenMai adapterDanhSachKhuyenMai= new AdapterDanhSachKhuyenMai(getContext(),khuyenMaiList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerViewDanhSachKhuyenMai.setLayoutManager(layoutManager);
        recyclerViewDanhSachKhuyenMai.setAdapter(adapterDanhSachKhuyenMai);
        adapterDanhSachKhuyenMai.notifyDataSetChanged();

    }
}
