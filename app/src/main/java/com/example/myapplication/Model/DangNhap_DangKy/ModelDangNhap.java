package com.example.myapplication.Model.DangNhap_DangKy;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ConnectInternet.DownloadJSON;
import com.example.myapplication.View.TrangChu.TrangChuActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDangNhap {
    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;

    public String LayCachedDangNhap(Context context){

        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        String tennguoidung = cachedDangNhap.getString("tennguoidung","");

        return tennguoidung;

    }

    public void CapNhatCachedDangNhap(Context context, String tennguoidung){
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cachedDangNhap.edit();
        editor.putString("tennguoidung", tennguoidung);
        editor.commit();
    }

    public boolean KiemTraDangNhap(Context context, String tendangnhap, String matkhau){
        boolean kiemtra = false;
        String duongdan = TrangChuActivity.SERVER_NAME;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "KiemTraDangNhap");

        HashMap<String, String> hsTenDangNhap = new HashMap<>();
        hsTenDangNhap.put("tendangnhap", tendangnhap);

        HashMap<String, String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau", matkhau);

        attrs.add(hsHam);
        attrs.add(hsTenDangNhap);
        attrs.add(hsMatKhau);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();

        try {
            String dulieu = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieu);
            String jsonKetQua = jsonObject.getString("ketqua");
            if (jsonKetQua.equals("true")){
                kiemtra = true;
                String tennguoidung = jsonObject.getString("tennguoidung");

                CapNhatCachedDangNhap(context, tennguoidung);

            } else {
                kiemtra = false;
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return kiemtra;

    }

    public AccessToken LayTokenFacebookHienTai(){

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };

        accessToken = AccessToken.getCurrentAccessToken();
        return  accessToken;
    }

    public GoogleApiClient LayGoogleApiClient(Context context, GoogleApiClient.OnConnectionFailedListener failedListener){
        GoogleApiClient mGoogleApiClient;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .enableAutoManage((AppCompatActivity)context,failedListener)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        return  mGoogleApiClient;
    }

    public GoogleSignInResult LayThongTinDangNhapGoogle(GoogleApiClient googleApiClient){
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()){
            return  opr.get();
        } else {
            return null;
        }
    }

    public void HuyTokenTracker(){
        accessTokenTracker.stopTracking();
    }
}
