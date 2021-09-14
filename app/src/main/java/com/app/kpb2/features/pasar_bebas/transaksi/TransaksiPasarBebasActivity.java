package com.app.kpb2.features.pasar_bebas.transaksi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.kpb2.NewMessageNotification;
import com.app.kpb2.R;
import com.app.kpb2.Utils.GsonHelper;
import com.app.kpb2.Utils.Utils;
import com.app.kpb2.features.pasar_bebas.PasarBebasActivity;
import com.app.kpb2.features.pasar_bebas.PasarBebasPresenter;
import com.app.kpb2.features.pasar_bebas.transaksi.model.TransaksiPasarBebas;
import com.app.kpb2.features.pasar_bebas.transaksi.model.TransaksiPasarBebasResponse;
import com.app.kpb2.features.pasar_bebas.transaksi.model.Transaksis;
import com.app.kpb2.features.petani.MenuUtama;
import com.app.kpb2.features.transaksi.TransaksiActivity;
import com.app.kpb2.features.transaksi.TransaksiAdapter;
import com.app.kpb2.features.transaksi.TransaksiPageAdapter;
import com.app.kpb2.features.transaksi.TransaksiPresenter;
import com.app.kpb2.features.transaksi.fragment.FragmentTransaksiList;
import com.app.kpb2.features.transaksi.model.DetailTransaksi;
import com.app.kpb2.features.transaksi.model.Transaksi;
import com.app.kpb2.features.users.login.model.LoginResponse;
import com.app.kpb2.server.App;
import com.app.kpb2.server.rmq.ReceiveRmq;
import com.app.kpb2.server.rmq.SendRmq;
import com.app.kpb2.session.Prefs;
import com.app.kpb2.ui.AnimationHelper;
import com.app.kpb2.ui.CustomDrawable;
import com.app.kpb2.ui.SweetDialogs;
import com.google.gson.Gson;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransaksiPasarBebasActivity extends AppCompatActivity implements ITransaksiPasarBebasView, TransaksiPasarBebasAdapter.onTransaksitSelected {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.list_viewpager)
    ViewPager mListViewPager;
    @BindView(R.id.collapse_image)
    ImageView mCollapseImage;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.DetailListLayout)
    LinearLayout DetailListLayout;
    Dialog dialog;
    ImageButton closePopup;
    NewMessageNotification notif;
    ReceiveRmq receiveRmq;
    SendRmq sendRmq;
    public static String[] stringArray = {"String1"};
    SweetAlertDialog sweetAlertDialog;
    TransaksiPasarBebasPresenter presenter;
    String nik, token, idUser;
    public TransaksiPasarBebasAdapter adapter;
    List<FragmentTransaksiList> mTransaksiFragments = new ArrayList<>();
    Animation slideUp, slideDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi_pasar_bebas);
        ButterKnife.bind(this);
        presenter = new TransaksiPasarBebasPresenter(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Transaksi Pasar Bebas");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.initView();

    }

    @Override
    public void initView() {

        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.clearFocus();
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
        Log.d("IDUSERNYA" , idUser) ;
        presenter.beforeView(nik, token, idUser);
//        presenter.getTransaksi(nik, token, idUser);
//        mCollapseImage.setOnClickListener(view -> this.hideDetailList());
    }

    @Override
    public void clearLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            activity.getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onDataReady(List<Transaksis> result) {
        Log.d("TransaksiPasarBebas", new Gson().toJson(result));
        adapter = new TransaksiPasarBebasAdapter(result, this, this);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void beforeRequest(TransaksiPasarBebasResponse result) {
        Log.d("beforeRequest" , new Gson().toJson(result));
        presenter.getTransaksi(nik, token, idUser);
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
    public void onRequestFailed(String code , String message) {
        if (Integer.parseInt(code) == 401)
            SweetDialogs.commonInvalidToken(this, "Gagal Memuat Permintaan",
                    "Sesi anda telah berakhir , silahkan login kembali!");
        else
            SweetDialogs.commonWarning(this,"Gagal Memuat Permintaan" , message, false);
    }

    @Override
    public void onNetworkError(String cause) {
        Log.d("Error", cause);
        SweetDialogs.endpointError(this);
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
    public void onDetailTransaksi(Transaksis result) {
        presenter.getDetailTransaksi(nik, token, result, idUser);
    }

    @Override
    public void showList(List<TransaksiPasarBebas> result, Transaksis transaksis) {
        dialog = new Dialog(this, R.style.AppDialogTheme);
        dialog.getWindow().getAttributes().windowAnimations = R.style.AppDialogTheme;
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_detailtfpasarbebas);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.85);
        dialog.getWindow().setLayout(width, height);

        TextView mTotal = dialog.findViewById(R.id.mTotal);
        TextView mOrderId = dialog.findViewById(R.id.mOrderId);
        TextView mBtnTutup = dialog.findViewById(R.id.mBtnTutup);
        TextView mStatus = dialog.findViewById(R.id.Status);

        LinearLayout layoutDetailProduk = dialog.findViewById(R.id.mLayoutProduk);
        for (TransaksiPasarBebas val : result) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView = inflater.inflate(R.layout.data_produk_field, null);
            final TextView mNamaProduk = rowView.findViewById(R.id.mNamaProduk);
            final TextView mHarga = rowView.findViewById(R.id.mHarga);
            final TextView mJumlahBeli = rowView.findViewById(R.id.mJmlhBeli);
            final TextView mTotalHarga = rowView.findViewById(R.id.mTotal);

            mNamaProduk.setText(val.getNamaBarang());
            mJumlahBeli.setText(val.getJumlahBeli()+ " Barang");
            long TotalHarga = Integer.parseInt(val.getJumlahBeli())*Long.parseLong(val.getHargaBarang());
            mHarga.setText(Utils.convertRupiahBigDecimal(String.valueOf(val.getHargaBarang())));
            mTotalHarga.setText("Total: "+Utils.convertRupiahBigDecimal(String.valueOf(TotalHarga)));

            layoutDetailProduk.addView(rowView, 0);
        }
        long totalHarga = Long.parseLong(transaksis.getGross_amount()) - Integer.parseInt(transaksis.getIdUnique());
        mTotal.setText(Utils.convertRupiahBigDecimal(String.valueOf(totalHarga)));
        mOrderId.setText(transaksis.getOrder_id());
        mOrderId.setText(transaksis.getOrder_id());
        int statusBarang = transaksis.getStatusBarang().getStatus();
        String status ;
        if(statusBarang == 0)
            status = "Belum Dikirim" ;
        else if(statusBarang == 1)
            status = "Sudah Dikirim" ;
        else if(statusBarang == 2)
            status = "Sudah Dikios" ;
        else
            status = "Barang Sudah Diterima" ;

        mStatus.setText(status);
//        mJenis.setText("Jenis : " +result.getJenis());
//        mHargaBarang.setText("Harga Barang : " +Utils.convertRupiah(String.valueOf(result.getHargaBarang())));;
//        mJumlahBeli.setText("Jumlah Beli : " +result.getJumlahBeli());
//        mSubtotal.setText("Total Bayar : " + Utils.convertRupiah(String.valueOf(result.getSubTotal())));
//        mJenisPembayaran.setText("Jenis Pembayaran : " +result.getJenisPembayaran());
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
        closePopup = dialog.findViewById(R.id.closePopKeuntungan);
        closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        mBtnTutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

//    @Override
//    public void hideDetailList() {
//        if (slideDown == null) {
//            slideDown = AnimationHelper.getAnimation(this, R.anim.slide_down, anim -> {
////                DetailListLayout.setVisibility(View.GONE);
////                mRecyclerView.setVisibility(View.VISIBLE);
//                mCollapseImage.setVisibility(View.GONE);
//                this.refresh();
//            });
//        }
//        DetailListLayout.startAnimation(slideDown);
//
//    }

    @Override
    public void onBackPressed() {
        // ...
//        if(DetailListLayout.getVisibility() == View.VISIBLE)
//            hideDetailList();
//        else {
        this.goToDashboard();
        super.onBackPressed();
//        }
    }


    @Override
    public void goToDashboard() {
        Intent i = new Intent(this, PasarBebasActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void refresh() {
        Intent i = new Intent(this, TransaksiActivity.class);
        startActivity(i);
        finish();
    }
}
