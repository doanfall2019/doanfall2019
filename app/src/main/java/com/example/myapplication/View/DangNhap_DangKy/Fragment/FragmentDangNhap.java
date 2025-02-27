package com.example.myapplication.View.DangNhap_DangKy.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Model.DangNhap_DangKy.ModelDangNhap;
import com.example.myapplication.R;
import com.example.myapplication.View.TrangChu.TrangChuActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.reflect.Array;
import java.util.Arrays;

public class FragmentDangNhap extends Fragment implements View.OnClickListener {

    Button btnDangNhap;
    ModelDangNhap modelDangNhap;
    EditText edTenDangNhap, edMatKhau;

    Button btnDangNhapFacebook;
    CallbackManager callbackManager;
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dang_nhap, container, false);

        modelDangNhap = new ModelDangNhap();

        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(iTrangChu);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });



        btnDangNhap = view.findViewById(R.id.btnDangNhap);
        edTenDangNhap = view.findViewById(R.id.edDiaChiEmailDN);
        edMatKhau = view.findViewById(R.id.edMatKhauDN);

        btnDangNhapFacebook = view.findViewById(R.id.btnDangNhapFacebook);


        btnDangNhap.setOnClickListener(this);
        btnDangNhapFacebook.setOnClickListener(this);



        return view;
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {
            case R.id.btnDangNhap:
                String tendangnhap = edTenDangNhap.getText().toString();
                String matkhau = edMatKhau.getText().toString();
                boolean kiemtra = modelDangNhap.KiemTraDangNhap(getActivity(), tendangnhap, matkhau);
                if (kiemtra == true) {
                    Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                    startActivity(iTrangChu);
                } else {
                    Toast.makeText(getActivity(), "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnDangNhapFacebook:
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this, Arrays.asList("public_profile"));
                break;

        }

    }

    private void ShowProgressDialog(){
        if (progressDialog ==null){
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);


                progressDialog.cancel();
                Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(iTrangChu);


    }

}
