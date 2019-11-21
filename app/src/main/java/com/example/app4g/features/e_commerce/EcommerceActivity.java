package com.example.app4g.features.e_commerce;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.app4g.R;
import com.example.app4g.Utils.CircleAnimationUtil;
import com.example.app4g.Utils.GsonHelper;
import com.example.app4g.Utils.Utils;
import com.example.app4g.features.cart.CartActivity;
import com.example.app4g.features.e_commerce.model.Product;
import com.example.app4g.features.petani.MenuUtama;
import com.example.app4g.features.e_commerce.model.Item;
import com.example.app4g.features.e_commerce.model.RutResponse;
import com.example.app4g.features.e_commerce.model.Saldo;
import com.example.app4g.features.users.login.model.LoginResponse;
import com.example.app4g.server.App;
import com.example.app4g.session.Prefs;
import com.example.app4g.ui.SweetDialogs;
import com.example.app4g.ui.TopSnakbar;
import com.nex3z.notificationbadge.NotificationBadge;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EcommerceActivity extends AppCompatActivity implements IEcommerceView, EcommerceAdapter.OnItemSelected, EcommerceAdapter.onCartSelected {
    @BindView(R.id.bottomNavViewBar)
    TabLayout tabLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.loading_layout)
    RelativeLayout mLoadingIndicator;
    @BindView(R.id.mSaldo)
    TextView mSaldo;
    @BindView(R.id.mCart)
    RelativeLayout mCart;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.badge)
    NotificationBadge mBadge;
    @BindView(R.id.mSearch)
    SearchView mSearch;
    EcommercePresenter presenter;
    List<Item> product = null;
    int totalCart;
    SweetAlertDialog sweetAlertDialog;
    EcommerceAdapter adapter;
    String nik ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecommerce);
        ButterKnife.bind(this);
        this.initViews();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LoginResponse mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        presenter = new EcommercePresenter(this);
        presenter.showProduct();
        presenter.getSaldo(nik);

    }

    @Override
    public void initViews() {
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
                ArrayList<Item> dataFilter = new ArrayList<>();
                for(Item data : product){
                    String nama = data.getNamaItem().toLowerCase();
                    if(nama.contains(nextText)){
                        dataFilter.add(data);
                    }
                }
                adapter.setFilter(dataFilter);
                tabLayout.getTabAt(0).select();
                return true;
            }
        });
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Loading ...");
        mCart.setOnClickListener(view -> this.goToCart());
        RecyclerView.LayoutManager recyclerViewLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(recyclerViewLayoutManager);
        mRecyclerView.clearFocus();

        tabLayout.addTab(tabLayout.newTab().setText("Semua"));
        tabLayout.addTab(tabLayout.newTab().setText("Pupuk"));
        tabLayout.addTab(tabLayout.newTab().setText("Alat tani"));
        tabLayout.addTab(tabLayout.newTab().setText("Subsidi"));
        tabLayout.addTab(tabLayout.newTab().setText("Benih"));


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                List<Item> filters = null;
                if (tab.getPosition() == 1) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        filters = product.stream()
                                .filter(kategori -> kategori.getKategori().equals("Pupuk"))
                                .collect(Collectors.toList());
                    }
                } else if (tab.getPosition() == 2) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        filters = product.stream()
                                .filter(kategori -> kategori.getKategori().equals("Alat Tani"))
                                .collect(Collectors.toList());
                    }
                } else if (tab.getPosition() == 3) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        filters = product.stream()
                                .filter(kategori -> kategori.getKategori().equals("Subsidi"))
                                .collect(Collectors.toList());
                    }
                } else if (tab.getPosition() == 4) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        filters = product.stream()
                                .filter(kategori -> kategori.getKategori().equals("Benih"))
                                .collect(Collectors.toList());
                    }
                } else {
                    filters = product;
                }

                adapter = new EcommerceAdapter(filters, EcommerceActivity.this, EcommerceActivity.this);
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
    }

    @Override
    public void goToCart() {
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
        product = ruts.getResult();
        tabLayout.getTabAt(1).select();

    }

    @Override
    public void onDataSaldo(Saldo saldo) {
        //Log.d("Totalcart" , String.valueOf(saldo.getTotcart()));
        totalCart = saldo.getTotcart();
        mBadge.setNumber(totalCart);
        mSaldo.setText(Utils.convertRupiah(String.valueOf(saldo.getJumlah())));

    }

    @Override
    public void onNetworkError(String cause) {
        Log.e("errornya", cause);
        SweetDialogs.endpointError(this);
    }

    @Override
    public void onAddTocartSuccess(RutResponse ruts, ImageView img) {
        addItemToCart(img);

    }
    private void addItemToCart(ImageView targetView) {
        new CircleAnimationUtil().attachActivity(this).setTargetView(targetView).setMoveDuration(500).setDestView(mCart).setAnimationListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                presenter.getSaldo(nik);
                NotificationBadge textView = (NotificationBadge) findViewById(R.id.badge);
                textView.setNumber(totalCart);
                //Toast.makeText(EcommerceActivity.this, "Continue Shopping...", Toast.LENGTH_SHORT).show();
                TopSnakbar.showSuccess(EcommerceActivity.this,"Barang berhasil ditambahkan");
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
        Intent i = new Intent(EcommerceActivity.this, MenuUtama.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onCartSelect(Item rut, ImageView img) {
        presenter.createCart(nik, rut , img);
    }

    @Override
    public void onSelect(Product product) {
//        Intent i = new Intent(this, RutDetailActivity.class);;
//        i.putExtra("item", (Serializable) product.getmItem());
//        startActivity(i);
//        finish();
    }
}
