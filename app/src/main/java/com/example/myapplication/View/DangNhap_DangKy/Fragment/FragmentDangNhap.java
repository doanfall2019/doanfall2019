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

public class FragmentDangNhap extends Fragment implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    Button btnDangNhap;
    ModelDangNhap modelDangNhap;
    EditText edTenDangNhap, edMatKhau;

    Button btnDangNhapFacebook, btnDangNhapGoogle;
    CallbackManager callbackManager;
    GoogleApiClient mGoogleApiClient;
    public static int SIGN_IN_GOOGLE = 111;
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dang_nhap, container, false);

        ModelDangNhap modelDangNhap = new ModelDangNhap();
        mGoogleApiClient = modelDangNhap.LayGoogleApiClient(getContext(),this);

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
        btnDangNhapGoogle = view.findViewById(R.id.btnDangNhapGoogle);


        btnDangNhap.setOnClickListener(this);
        btnDangNhapFacebook.setOnClickListener(this);
        btnDangNhapGoogle.setOnClickListener(this);


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

            case R.id.btnDangNhapGoogle:
                Intent iGoogle = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(iGoogle, SIGN_IN_GOOGLE);
                ShowProgressDialog();
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
        if (requestCode == SIGN_IN_GOOGLE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            Log.d("google", result.getSignInAccount().getEmail());
            if (result.isSuccess()){
                progressDialog.cancel();
                Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(iTrangChu);
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        progressDialog.cancel();

    }
}
