package com.example.myapplication.View.TrangChu.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.AdapterDienTu;
import com.example.myapplication.Adapter.AdapterNhaCuaDoiSong;
import com.example.myapplication.Model.ObjectClass.DienTu;
import com.example.myapplication.Model.ObjectClass.NhaCuaDoiSong;
import com.example.myapplication.Presenter.NhaCuaDoiSong.PresenterLogicNhaCuaDoiSong;
import com.example.myapplication.Presenter.TrangChu_DienTu.PresenterLogicDienTu;
import com.example.myapplication.R;
import com.example.myapplication.View.TrangChu.ViewNhaCuaDoiSong;

import java.util.ArrayList;
import java.util.List;

public class FragmentNhaCuaVaDoiSong extends Fragment implements ViewNhaCuaDoiSong {

    RecyclerView recyclerView;
    List<NhaCuaDoiSong> nhaCuaDoiSongList;
    PresenterLogicNhaCuaDoiSong presenterLogicNhaCuaDoiSong;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nhacuavacuocsong, container, false);

        recyclerView = view.findViewById(R.id.recyclerNhaCuaCuocSong);

        presenterLogicNhaCuaDoiSong = new PresenterLogicNhaCuaDoiSong(this);

        nhaCuaDoiSongList = new ArrayList<>();

        presenterLogicNhaCuaDoiSong.LayDanhSachNhaCuaDoiSong();

        return view;
    }

    @Override
    public void HienThiDanhSach(List<NhaCuaDoiSong> nhaCuaDoiSongs) {
        nhaCuaDoiSongList = nhaCuaDoiSongs;

        AdapterNhaCuaDoiSong adapterNhaCuaDoiSong = new AdapterNhaCuaDoiSong(getContext(), nhaCuaDoiSongList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterNhaCuaDoiSong);

        adapterNhaCuaDoiSong.notifyDataSetChanged();
    }
}
