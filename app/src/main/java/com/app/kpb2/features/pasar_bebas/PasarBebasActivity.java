package com.app.kpb2.features.pasar_bebas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.kpb2.R;
import com.app.kpb2.Utils.CircleAnimationUtil;
import com.app.kpb2.Utils.GsonHelper;
import com.app.kpb2.features.cart.CartActivity;
import com.app.kpb2.features.cart.model.Cart;
import com.app.kpb2.features.e_commerce.EcommerceActivity;
import com.app.kpb2.features.e_commerce.EcommerceAdapter;
import com.app.kpb2.features.e_commerce.model.Item;
import com.app.kpb2.features.e_commerce.model.RutResponse;
import com.app.kpb2.features.pasar_bebas.model.Items;
import com.app.kpb2.features.pasar_bebas.model.PasarBebasResponse;
import com.app.kpb2.features.pasar_bebas.transaksi.TransaksiPasarBebasActivity;
import com.app.kpb2.features.pasar_bebas.transaksi.TransaksiPasarBebasAdapter;
import com.app.kpb2.features.petani.MenuUtama;
import com.app.kpb2.features.users.login.model.LoginResponse;
import com.app.kpb2.server.App;
import com.app.kpb2.session.Prefs;
import com.app.kpb2.ui.SweetDialogs;
import com.app.kpb2.ui.TopSnakbar;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.nex3z.notificationbadge.NotificationBadge;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PasarBebasActivity extends AppCompatActivity implements IPasarBebasView, PasarBebasAdapter.onCartSelected {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.bottomNavViewBar)
    TabLayout tabLayout;
    @BindView(R.id.mSearch)
    SearchView mSearch;
    @BindView(R.id.mCart)
    RelativeLayout mCart;
    @BindView(R.id.mTransaksi)
    LinearLayout mTransaksi;
    @BindView(R.id.badge)
    NotificationBadge mBadge;
    SweetAlertDialog sweetAlertDialog;
    PasarBebasAdapter adapter;
    PasarBebasPresenter presenter;
    List<Items> product = null;
    String nik, token, idUser;
    int totalKeranjang  = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasar_bebas);
        ButterKnife.bind(this);
        presenter = new PasarBebasPresenter(this);
        this.initViews();
        presenter.showProduct(nik, token);



    }

    @Override
    public void initViews() {
        LoginResponse mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        token = (mProfile.getResult().getToken().contains(" "))
                ? mProfile.getResult().getToken() : mProfile.getResult().getToken();
        idUser = (mProfile.getResult().get_id().contains(" "))
                ? mProfile.getResult().get_id() : mProfile.getResult().get_id();
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Loading ...");
        RecyclerView.LayoutManager recyclerViewLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(recyclerViewLayoutManager);
        mRecyclerView.clearFocus();
        mCart.setOnClickListener(view -> this.goToCart());
        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String nextText) {
                //Data akan berubah saat user menginputkan text/kata kunci pada SearchView
                nextText = nextText.toLowerCase();
                ArrayList<Items> dataFilter = new ArrayList<>();
                for (Items data : product) {
                    String nama = data.getNamaBarang().toLowerCase();
                    if (nama.contains(nextText)) {
                        dataFilter.add(data);
                    }
                }
                adapter.setFilter(dataFilter);
                tabLayout.getTabAt(0).select();
                return true;
            }
        });

        tabLayout.addTab(tabLayout.newTab().setText("Semua"));
        tabLayout.addTab(tabLayout.newTab().setText("Pupuk"));
        tabLayout.addTab(tabLayout.newTab().setText("Alat tani"));
        tabLayout.addTab(tabLayout.newTab().setText("Obat-Obatan"));
        tabLayout.addTab(tabLayout.newTab().setText("Saprotan"));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                List<Items> filters = new ArrayList<>();
                if (tab.getPosition() == 1) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        filters = product.stream()
                                .filter(kategori -> kategori.getJenis().equals("Pupuk"))
                                .collect(Collectors.toList());
                    }
                } else if (tab.getPosition() == 2) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        filters = product.stream()
                                .filter(kategori -> kategori.getJenis().equals("Alat Tani"))
                                .collect(Collectors.toList());
                    }

                } else if (tab.getPosition() == 3) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        filters = product.stream()
                                .filter(kategori -> kategori.getJenis().equals("Obat-Obatan"))
                                .collect(Collectors.toList());
                    }

                } else if (tab.getPosition() == 4) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        filters = product.stream()
                                .filter(kategori -> kategori.getJenis().equals("Saprotan"))
                                .collect(Collectors.toList());
                    }

                } else {
                    filters= product ;
                }

                adapter = new PasarBebasAdapter(filters, PasarBebasActivity.this, PasarBebasActivity.this);
                mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                //tab.getText().setColorFilter(Color.DKGRAY,PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }

        });

        mTransaksi.setOnClickListener(view ->this.goToTransaksi());
    }

    @Override
    public void clearLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            activity.getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onDataReady(PasarBebasResponse items) {
        if (!items.getmResult().isEmpty()) {
            product = items.getmResult();

        }
        tabLayout.getTabAt(0).select();
        presenter.getCart(nik , token, idUser);
        adapter = new PasarBebasAdapter(product, PasarBebasActivity.this, PasarBebasActivity.this);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

//        adapter = new PasarBebasAdapter(product, this, this);
//        mRecyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

    }

    @Override
    public void onNetworkError(String cause) {
        Log.e("errornya", cause);
        SweetDialogs.endpointError(this);
    }

    @Override
    public void onRequestFailed(int Code) {
        if (Code == 401)
            SweetDialogs.commonInvalidToken(this, "Gagal Memuat Permintaan",
                    "Sesi anda telah berakhir , silahkan login kembali!");
        else
            TopSnakbar.showWarning(this, "Barang ini Sudah masuk kekeranjang");
    }

    @Override
    public void showLoadingIndicator() {
        sweetAlertDialog.show();
    }

    @Override
    public void hideLoadingIndicator() {

        sweetAlertDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToDashboard();
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            goToDashboard();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void goToDashboard() {
        Intent i = new Intent(this, MenuUtama.class);
        startActivity(i);
        finish();
    }


    @Override
    public void onItemSelect(Items item, ImageView image) {
        presenter.createCart(nik, token, idUser, item, image);
    }

    @Override
    public void createCartSukses(ImageView image) {
        totalKeranjang += 1  ;
        addItemToCart(image);
    }

    public void addItemToCart(ImageView targetView) {
        new CircleAnimationUtil().attachActivity(this).setTargetView(targetView).setMoveDuration(500).setDestView(mCart).setAnimationListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
//                presenter.getSaldo(nik, token);
//                NotificationBadge textView = findViewById(R.id.badge);
//                textView.setNumber(totalCart);
                //Toast.makeText(EcommerceActivity.this, "Continue Shopping...", Toast.LENGTH_SHORT).show();
                TopSnakbar.showSuccess(PasarBebasActivity.this, "Barang berhasil ditambahkan");
                mBadge.setNumber(totalKeranjang);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).startAnimation();


    }

    @Override
    public void goToCart() {
        Intent i = new Intent(this, CartActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void goToTransaksi() {
        Intent i = new Intent(this, TransaksiPasarBebasActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onDataCartReady(List<Cart> result) {
        totalKeranjang = result.size() ;
        mBadge.setNumber(totalKeranjang);

    }
}
