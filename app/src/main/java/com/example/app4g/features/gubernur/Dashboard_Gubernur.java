package com.example.app4g.features.gubernur;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;



import com.example.app4g.R;
import com.example.app4g.ui.DrawerHeader;
import com.example.app4g.ui.DrawerMenuItem;
import com.mindorks.placeholderview.PlaceHolderView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Dashboard_Gubernur extends AppCompatActivity {

    WebView webView;
    ProgressBar bar;
    private int[] mImages = new int[]{R.drawable.slide_1, R.drawable.slide_2, R.drawable.slide_3, R.drawable.slide_4};
    CarouselView carouselView;
    @BindView(R.id.list_viewpager)
    ViewPager mListViewPager;
    @BindView(R.id.drawerView)
    PlaceHolderView mDrawerView;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawer;
//    @BindView(R.id.profile)
//    ImageView mainMenu_profile;
    @BindView(R.id.Home_Dashboard)
    ImageView Home;
    @SuppressLint("RestrictedApi")
//    @BindView(R.id.mainMenuDashboard)
//    ImageButton Profile;
//    @BindView(R.id.ivHome) ImageView home;
//    LoginResponse mProfile;
//
//    @BindView(R.id.kabupaten)
//    Spinner spinnerKabupaten;
//    @BindView(R.id.kecamatan)
//    Spinner spinnerKecamatan;
//    Context mContext;
//    NetworkService mApiService;
//    NetworkService BasepiService;
//    public String Kabupaten;

    //    String api NetworkService;
//    Retrofit mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard__gubernur);
//        setupDrawer();
        webView = (WebView) findViewById(R.id.Gubernur_Dashboard);
        bar = (ProgressBar) findViewById(R.id.progressBar2);
        webView.setWebViewClient(new myWebclient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://192.168.1.17:8080/#/dashboardpimpinan");
        carouselView =findViewById(R.id.carousel);
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);
            }
        });
        ImageView mainMenu_profile=(ImageView) findViewById(R.id.profile);
        mainMenu_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Dashboard_Gubernur.this,ProfileFragmentGubernur.class);
                startActivity(intent);
            }
        });
//        ImageView Home=(ImageView) findViewById(R.id.Home_Dashboard);
//        Home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(Dashboard_Gubernur.this,Dashboard_Gubernur.class);
//                startActivity(intent);
//            }
//        });

    }


    public class myWebclient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            bar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

//    @OnClick(R.id.profile)
//    public void submit_pindahprofle(){
////        if(!mDrawer.isDrawerOpen(Gravity.END)) mDrawer.openDrawer(Gravity.END);
////        else mDrawer.closeDrawer(Gravity.START);
//        Intent intent=new Intent(Dashboard_Gubernur.this,ProfileFragmentGubernur.class);
//        startActivity(intent);
//
//    }
//    @OnClick(R.id.Home_Dashboard)
//    public void tampildashboard(){
////        if(!mDrawer.isDrawerOpen(Gravity.END)) mDrawer.openDrawer(Gravity.END);
////        else mDrawer.closeDrawer(Gravity.START);
//        Intent intent=new Intent(Dashboard_Gubernur.this,Dashboard_Gubernur.class);
//        startActivity(intent);
//
//    }
}