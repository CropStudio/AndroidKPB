package com.example.app4g.features.rut;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app4g.R;
import com.example.app4g.Utils.GsonHelper;
import com.example.app4g.Utils.Utils;
import com.example.app4g.features.petani.MenuUtama;
import com.example.app4g.features.rut.detailRut.MainDetailFragment;
import com.example.app4g.features.rut.model.BiayaTanam;
import com.example.app4g.features.rut.model.EstimasiPanen;
import com.example.app4g.features.rut.model.HasilPascaPanen;
import com.example.app4g.features.rut.model.KalenderTanam;
import com.example.app4g.features.rut.model.KebutuhanSaprotan;
import com.example.app4g.features.rut.model.Rut;
import com.example.app4g.features.users.login.model.LoginResponse;
import com.example.app4g.server.App;
import com.example.app4g.session.Prefs;
import com.example.app4g.ui.SweetDialogs;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.delight.android.webview.AdvancedWebView;

public class RutActivity extends AppCompatActivity implements IRutView, RutAdapter.onRutSelected {
    //    String TEST_PAGE_URL = "http://192.168.1.14:8080/rut/";
    String TEST_PAGE_URL = "http://kpb.lampungprov.go.id/#/rut/";
    private AdvancedWebView mWebView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.list_viewpager)
    ViewPager mListViewPager;
    @BindView(R.id.list_layout)
    LinearLayout list_layout;
    @BindView(R.id.layoutCheckout)
    RelativeLayout layoutCheckout;
    @BindView(R.id.mBtnTutup)
    Button mBtnTutup;
    @BindView(R.id.mSubtotal)
    TextView mSubtotal;
    @BindView(R.id.txtSubtotal)
    TextView txtSubtotal;
    @BindView(R.id.mCheckout)
    Button mCheckout;
    @BindView(R.id.Layouttoolbar)
    RelativeLayout Layouttoolbar;

    long Subtotal ;
    ProgressDialog pDialog;
    LoginResponse mProfile;
    public RutAdapter adapter;
    RutPresenter presenter;
    String idKec, nik, token;
    SweetAlertDialog sweetAlertDialog;
    boolean LayoutStat = false;

    List<KebutuhanSaprotan> models ;
    public List<Rut> items = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rut);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("RUT (Rencana Usaha Tani)");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.c_black));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new RutPresenter(this);
        this.initView();
        LoginResponse mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        token = (mProfile.getResult().getToken().contains(" "))
                ? mProfile.getResult().getToken() : mProfile.getResult().getToken();
        idKec = (mProfile.getResult().getIdKecamatan().contains(" "))
                ? mProfile.getResult().getIdKecamatan() : mProfile.getResult().getIdKecamatan();
        presenter.getRut(nik, token, idKec);

    }

    @Override
    public void initView() {
        mCheckout.setOnClickListener(view-> Toast.makeText(this, "Maaf menu ini masih dalam masa pengembangan ", Toast.LENGTH_SHORT).show());
        mCheckout.setEnabled(false);
        mCheckout.setBackgroundColor(getResources().getColor(R.color.grey));
        mBtnTutup.setOnClickListener(view -> this.HideDetailKebutuhan());
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Loading ...");
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
    public void showLoadingIndicator() {
        sweetAlertDialog.show();
    }

    @Override
    public void hideLoadingIndicator() {

        sweetAlertDialog.dismiss();
    }

    @Override
    public void onDataReady(List<Rut> ruts) {
        this.items = ruts ;
        adapter = new RutAdapter(items, this, this);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestFailed(String rc, String rm) {
        if (rc.equals(Prefs.DEFAULT_INVALID_TOKEN))
            SweetDialogs.commonInvalidToken(this, "Gagal Memuat Permintaan",
                    rm);
        else
            SweetDialogs.commonError(this, "Gagal Memuat Permintaan", rm, string -> {
                this.goToDashboard();
            });
    }

    @Override
    public void onNetworkError(String cause) {
        Toast.makeText(this, cause, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void goToDashboard() {
        Intent a = new Intent(this, MenuUtama.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed() {
        // ...
        Intent a = new Intent(this, MenuUtama.class);
        startActivity(a);
        finish();
        super.onBackPressed();
    }

    @Override
    public void onDetailData(List<KebutuhanSaprotan> kebutuhanSaprotans, List<BiayaTanam> biayaTanams, KalenderTanam kalenderTanams, EstimasiPanen estimasiPanen, HasilPascaPanen hasilPascaPanen) {
        Layouttoolbar.setVisibility(View.GONE);
        list_layout.setVisibility(View.VISIBLE);
        mBtnTutup.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
        layoutCheckout.setVisibility(View.GONE);
        final RutPageAdapter adapter = new RutPageAdapter(getSupportFragmentManager());
        MainDetailFragment fragmentMainDetail = new MainDetailFragment();
        fragmentMainDetail.setData(kebutuhanSaprotans, biayaTanams,kalenderTanams,estimasiPanen,hasilPascaPanen);
        adapter.addFragment(fragmentMainDetail);
        mListViewPager.setAdapter(adapter);
        LayoutStat = true;
    }

    @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCheckBox(int position) {
        mSubtotal.setText(Utils.convertRupiah(String.valueOf(getSubtotal(position))));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private long getSubtotal(int position) {
//        for (Item x : adapter.ruts) {
//            if (x.isSelected()) {
//                hargaPerItem = x.getHarga() * x.getQty();
//                subTotal += hargaPerItem;
//            }
//
//        }
        long subTotal = adapter.ruts.stream().filter(word -> word.isSelected == true).mapToLong(i -> i.getKebutuhanSaprotan().get(0).getSubTotal() + i.getKebutuhanSaprotan().get(1).getSubTotal() + i.getKebutuhanSaprotan().get(2).getSubTotal()).sum();
        if (subTotal != 0) {
            mCheckout.setEnabled(true);
            mCheckout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        } else {
            mCheckout.setEnabled(false);
            mCheckout.setBackgroundColor(getResources().getColor(R.color.grey));
        }
        return subTotal;
    }

//    @Override
//    public void onDataDetailReady(List<KebutuhanSaprotan> kebutuhanSaprotans) {
//
////            Toast.makeText(context, kebutuhanSaprotans.get(0).getNama(), Toast.LENGTH_SHORT).show();
//        list_layout.setVisibility(View.VISIBLE);
//        mBtnTutup.setVisibility(View.VISIBLE);
//        mRecyclerView.setVisibility(View.GONE);
//        layoutCheckout.setVisibility(View.GONE);
////        final RutPageAdapter adapter = new RutPageAdapter(getSupportFragmentManager());
////        KebutuhanSaprotanFragment fragmentTracker = new KebutuhanSaprotanFragment();
////        models = new ArrayList<>();
////        for (int j = 0; j < kebutuhanSaprotans.size(); j++) {
////
////            models.add(kebutuhanSaprotans.get(j));
////
////        }
////        fragmentTracker.setData(models, JenisTanaman);
////        adapter.addFragment(fragmentTracker);
////        mListViewPager.setAdapter(adapter);
//        final PageFramentAdapter adapter = new PageFramentAdapter(getSupportFragmentManager());
//        MainDetailFragment fragmentMainDetail = new MainDetailFragment();
//        fragmentMainDetail.setData(kebutuhanSaprotans);
//        adapter.addFragment(fragmentMainDetail);
//        mListViewPager.setAdapter(adapter);
//        LayoutStat = true;
//    }

    @Override
    public void HideDetailKebutuhan() {
//        list_layout.setVisibility(View.GONE);
//        mBtnTutup.setVisibility(View.GONE);
//        mRecyclerView.setVisibility(View.VISIBLE);
//        LayoutStat = false;
        startActivity(new Intent(this,RutActivity.class));
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (LayoutStat)
                HideDetailKebutuhan();
            else
                this.goToDashboard();
        }

        return false;
        // Disable back button..............
    }
}
