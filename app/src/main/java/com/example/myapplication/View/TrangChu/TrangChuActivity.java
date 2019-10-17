package com.example.myapplication.View.TrangChu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.ExpandAdapter;
import com.example.myapplication.Adapter.ViewPagerAdapter;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Model.DangNhap_DangKy.ModelDangNhap;
import com.example.myapplication.Model.ObjectClass.LoaiSanPham;
import com.example.myapplication.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.myapplication.Presenter.TrangChu.XuLyMenu.PresenterLogicXuLyMenu;
import com.example.myapplication.R;
import com.example.myapplication.View.DangNhap_DangKy.DangNhapActivity;
import com.example.myapplication.View.GioHang.GioHangActivity;
import com.example.myapplication.View.TimKiem.TimKiemActivity;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class TrangChuActivity extends AppCompatActivity implements ViewXuLyMenu, AppBarLayout.OnOffsetChangedListener, View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    public static final String SERVER_NAME = "http://192.168.43.254/webservice/apiserver.php";
    public static final String SERVER = "http://192.168.43.254/webservice";
    
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ExpandableListView expandableListView;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Menu menu;
    TextView txtGioHang;
    boolean onPause = false;
    ModelDangNhap modelDangNhap;
    Button btnSearch;
    ImageButton im_btn_Search;
    PresenterLogicXuLyMenu logicXuLyMenu;
    String tennguoidung = "";
    AccessToken accessToken;
    MenuItem itemDangNhap;
    MenuItem menuItemDangXuat;
    GoogleApiClient mGoogleApiClient;
    GoogleSignInResult googleSignInResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_trang_chu);

        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
        drawerLayout = findViewById(R.id.drawerLayout);
        appBarLayout = findViewById(R.id.appbar);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        expandableListView = findViewById(R.id.epMenu);
        btnSearch = findViewById(R.id.btnSearch);
        im_btn_Search = findViewById(R.id.im_btn_Search);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        logicXuLyMenu = new PresenterLogicXuLyMenu(this);
        modelDangNhap = new ModelDangNhap();
        logicXuLyMenu.LayDanhSachMenu();

        logicXuLyMenu.LayTenNguoiDungFaceBook();

        mGoogleApiClient = modelDangNhap.LayGoogleApiClient(this, this);


        btnSearch.setOnClickListener(this);
        im_btn_Search.setOnClickListener(this);

        appBarLayout.addOnOffsetChangedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        this.menu = menu;

        itemDangNhap = menu.findItem(R.id.itDangNhap);
        menuItemDangXuat = menu.findItem(R.id.itDangXuat);

        accessToken = logicXuLyMenu.LayTenNguoiDungFaceBook();
        googleSignInResult = modelDangNhap.LayThongTinDangNhapGoogle(mGoogleApiClient);
        if (accessToken != null) {
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        tennguoidung = object.getString("name");

                        itemDangNhap.setTitle(tennguoidung);
//                        Log.d("token", tennguoidung);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            Bundle parameter = new Bundle();
            parameter.putString("fields", "name");

            graphRequest.setParameters(parameter);
            graphRequest.executeAsync();
        }

        if (googleSignInResult != null) {
            itemDangNhap.setTitle(googleSignInResult.getSignInAccount().getDisplayName());
        }


        if (accessToken != null || googleSignInResult != null) {
            menuItemDangXuat.setVisible(true);
        }


        MenuItem iGioHang = this.menu.findItem(R.id.itGioHang);
        View giaoDienCustomGioHang = MenuItemCompat.getActionView(iGioHang);
        txtGioHang = giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPham);

        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(TrangChuActivity.this, GioHangActivity.class);
                startActivity(a);
            }
        });

        PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();

        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();
        switch (id) {
            case R.id.itDangNhap:
                if (accessToken == null && googleSignInResult == null) {
                    Intent iDangNhap = new Intent(this, DangNhapActivity.class);
                    startActivity(iDangNhap);
                }
                break;

            case R.id.itDangXuat:
                if (accessToken != null) {
                    LoginManager.getInstance().logOut();
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }
                if (googleSignInResult != null) {
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }
                break;


            case R.id.itSearch:
                Intent iTimkiem = new Intent(this, TimKiemActivity.class);
                startActivity(iTimkiem);
                break;

        }
        return true;
    }

    @Override
    public void HienThiDanhSachMenu(List<LoaiSanPham> loaiSanPhamList) {
//        Log.d("kiem tra drawerMenu", loaiSanPhamList.get(0).getTENLOAISP());
        ExpandAdapter expandAdapter = new ExpandAdapter(this, loaiSanPhamList);
        expandableListView.setAdapter(expandAdapter);
        expandAdapter.notifyDataSetChanged();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        // Kiểm tra chiều cao của AppBarLayout
//        Log.d("Kiem tra",collapsingToolbarLayout.getHeight() +" - " + i + " - " +  ViewCompat.getMinimumHeight(collapsingToolbarLayout));
        if (collapsingToolbarLayout.getHeight() + i <= 1.5 * ViewCompat.getMinimumHeight(collapsingToolbarLayout)) {
            LinearLayout linearLayout = findViewById(R.id.lnSearch);
            linearLayout.animate().alpha(0).setDuration(200);

            MenuItem itSearch = menu.findItem(R.id.itSearch);
            itSearch.setVisible(true);

        } else {
            LinearLayout linearLayout = findViewById(R.id.lnSearch);
            linearLayout.animate().alpha(1).setDuration(200);
            try {
                MenuItem itSearch = menu.findItem(R.id.itSearch);
                itSearch.setVisible(false);
            } catch (Exception e) {

            }

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (onPause == true) {
            PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
            txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));
        }
    }


    @Override
    protected void onPause() {
        super.onPause();

        onPause = true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.im_btn_Search:
                Intent iTimkiem = new Intent(this, TimKiemActivity.class);
                startActivity(iTimkiem);
                ;
                break;


        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
