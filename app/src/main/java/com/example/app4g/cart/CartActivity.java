package com.example.app4g.cart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.app4g.R;
import com.example.app4g.cart.model.Cart;
import com.example.app4g.petani.MenuUtama;
import com.example.app4g.rut.RutActivity;
import com.example.app4g.rut.RutAdapter;
import com.example.app4g.rut.model.Item;
import com.example.app4g.session.SessionManager;
import com.example.app4g.ui.SweetDialogs;
import com.example.app4g.users.login.Login;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity implements ICartView {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.loading_layout)
    RelativeLayout mLoadingIndicator;
    CartPresenter presenter;
    public SharedPreferences prefs;
    public SessionManager session;
    String strId, strNik, strNotelp, strNama, strRole, strToken, strKtp, strKk, strPotoPropil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        prefs = getSharedPreferences("UserDetails",
                Context.MODE_PRIVATE);
        isLogin();
        presenter = new CartPresenter(this);
        presenter.getCart(strNik);
        this.initViews();
    }
    public void isLogin(){
        // Session manager
        session = new SessionManager(this);
        //Session Login
        if(session.isLoggedIn()){
            strId       = prefs.getString("id","");
            strNik      = prefs.getString("nik","");
            strNotelp   = prefs.getString("notelp", "");
            strNama     = prefs.getString("nama", "");
            strRole     = prefs.getString("role", "");
            strToken    = prefs.getString("token", "");
            strKtp      = prefs.getString("ktp", "");
            strKk       = prefs.getString("kk","");
            strPotoPropil=prefs.getString("pp","");

        }else{
            Intent a = new Intent(getApplicationContext(), Login.class);
            startActivity(a);
            finish();
        }
    }


    @Override
    public void initViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.clearFocus();
    }

    @Override
    public void clearLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            activity.getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onDataReady(Cart carts) {
        if(carts != null) {
            CartAdapter adapter = new CartAdapter(carts.getItem(), this);
            mRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else{
            Toast.makeText(this, "Anda belum ada barang di keranjang", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNetworkError(String cause) {
        Log.e("errornya", cause);
        SweetDialogs.endpointError(this);
    }

    @Override
    public void showLoadingIndicator() {
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        mLoadingIndicator.setVisibility(View.GONE);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToDashboard();
    }

    @Override
    public void goToDashboard(){
        Intent i = new Intent(this, RutActivity.class);
        startActivity(i);
        finish();
    }

}
