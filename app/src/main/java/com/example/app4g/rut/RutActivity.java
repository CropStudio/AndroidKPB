package com.example.app4g.rut;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.app4g.R;
import com.example.app4g.cart.CartActivity;
import com.example.app4g.petani.MenuUtama;
import com.example.app4g.rut.model.Item;
import com.example.app4g.rut.model.Rut;
import com.example.app4g.rut.model.RutResponse;
import com.example.app4g.rutDetail.RutDetailActivity;
import com.example.app4g.session.SessionManager;
import com.example.app4g.ui.SweetDialogs;
import com.example.app4g.users.login.Login;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RutActivity extends AppCompatActivity implements IRutView , RutAdapter.OnItemSelected ,RutAdapter.onCartSelected {
    @BindView (R.id.bottomNavViewBar)
    TabLayout tabLayout ;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.loading_layout)
    RelativeLayout mLoadingIndicator;
    @BindView(R.id.mCart)
    ImageView mCart;
    RutPresenter presenter;
    public SharedPreferences prefs;
    public SessionManager session;
    String strId, strNik, strNotelp, strNama, strRole, strToken, strKtp, strKk, strPotoPropil;
//    private LoginResponse mProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rut);
        ButterKnife.bind(this);
        prefs = getSharedPreferences("UserDetails",
                Context.MODE_PRIVATE);
        isLogin();
        presenter = new RutPresenter(this);
        presenter.getSkema(strKtp,strToken);
        this.initViews();
    }

    @Override
    public void initViews() {
        mCart.setOnClickListener(view -> this.goToCart());
        RecyclerView.LayoutManager recyclerViewLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(recyclerViewLayoutManager);
        mRecyclerView.clearFocus();

        tabLayout.addTab(tabLayout.newTab().setText("Semua"));
        tabLayout.addTab(tabLayout.newTab().setText("Pupuk"));
        tabLayout.addTab(tabLayout.newTab().setText("Alat tani"));
        tabLayout.addTab(tabLayout.newTab().setText("Subsidi"));


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                //tab.getText().setColorFilter(getResources().getColor(R.color.light_green),PorterDuff.Mode.SRC_IN);
//                if(tab.getText().equals("Subsidi")){
//                    Toast.makeText(RutActivity.this, "Subsidi", Toast.LENGTH_SHORT).show();
//                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                //tab.getText().setColorFilter(Color.DKGRAY,PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }

        });
    }

    @Override
    public void goToCart(){
        Intent i = new Intent(this, CartActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void clearLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            activity.getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onDataReady(RutResponse ruts) {
        // Toast.makeText(this, presensi.get(0).getPertemuanke(), Toast.LENGTH_SHORT).show();
        RutAdapter adapter = new RutAdapter(ruts.getResult(),this,this);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onNetworkError(String cause) {
        Log.e("errornya", cause);
        SweetDialogs.endpointError(this);
    }

    @Override
    public void onCreateSuccess(RutResponse ruts) {
        // Toast.makeText(this, presensi.get(0).getPertemuanke(), Toast.LENGTH_SHORT).show();
//        RutAdapter adapter = new RutAdapter(ruts.getResult(),this,this);
//        mRecyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
        SweetDialogs.commonSuccess(this, "Barang berhasil ditambahkan" , true);
    }

    @Override
    public void showLoadingIndicator() {
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        mLoadingIndicator.setVisibility(View.GONE);
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
    public void onBackPressed() {
        super.onBackPressed();
        goToDashboard();
    }

    @Override
    public void goToDashboard(){
        Intent i = new Intent(RutActivity.this, MenuUtama.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onCartSelect(Item rut) {
        presenter.createCart(strNik, rut);
    }

    @Override
    public void onSelect(Rut rut) {
//        Intent i = new Intent(this, RutDetailActivity.class);;
//        i.putExtra("item", (Serializable) rut.getmItem());
//        startActivity(i);
//        finish();
    }
}
