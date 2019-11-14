package com.example.app4g.petani;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.app4g.R;
import com.example.app4g.petani.anak.ListDataAnak;
import com.example.app4g.session.SessionManager;
import com.example.app4g.users.login.Login;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuUtama extends AppCompatActivity {

    public SharedPreferences prefs;
    public SessionManager session;

    String strId, strNik, strNotelp, strNama, strRole, strToken, strKtp, strKk, strPotoPropil;

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    @BindView(R.id.appBar)
//    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
        ButterKnife.bind(this);
        //getSupportActionBar().hide();

//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        Intent a = getIntent();
        String hehe = a.getStringExtra("datanya");

        //I added this if statement to keep the selected fragment when rotating the device
        if (hehe != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ProfileFragment()).commit();
            bottomNav.getMenu().findItem(R.id.profile).setChecked(true);
//            toolbar.setVisibility(View.GONE);
//            appBarLayout.setVisibility(View.GONE);
        }else {
//            toolbar.setVisibility(View.VISIBLE);
//            appBarLayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Dashboard()).commit();
        }

        prefs = getSharedPreferences("UserDetails",
                Context.MODE_PRIVATE);

        isLogin();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.home:
//                            toolbar.setVisibility(View.VISIBLE);
//                            appBarLayout.setVisibility(View.VISIBLE);
                            selectedFragment = new Dashboard();
                            break;
                        case R.id.profile:
//                            toolbar.setVisibility(View.GONE);
//                            appBarLayout.setVisibility(View.GONE);
                            selectedFragment = new ProfileFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_notification1) {
            return true;
        }

        if (id == R.id.dataAnak) {
            Intent intent = new Intent(MenuUtama.this, ListDataAnak.class);
            startActivity(intent);
            finish();
            return true;
        }else if (id == R.id.exit) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(MenuUtama.this);
            builder.setTitle(Html.fromHtml("<font color='#009688'><b>Yakin ingin keluar ?</b></font>")).
                setIcon(R.drawable.lampung_coa)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
//                        keluar();
                        session.setLogin(false);
                        session.setSkip(false);
                        session.setSessid(0);
                        Intent intent = new Intent(MenuUtama.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.setCancelable(true);
            }
        })
                .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
