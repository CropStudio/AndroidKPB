package com.app.kpb2.features.petani.dokter_hewan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.app.kpb2.R;
import com.app.kpb2.Utils.GsonHelper;
import com.app.kpb2.features.petani.MenuUtama;
import com.app.kpb2.features.petani.dokter_hewan.model.DokterHewans;
import com.app.kpb2.features.petani.keuangan.KeuanganAdapter;
import com.app.kpb2.features.petani.keuangan.KeuanganPresenter;
import com.app.kpb2.features.rut.model.Result;
import com.app.kpb2.features.users.login.model.LoginResponse;
import com.app.kpb2.server.App;
import com.app.kpb2.session.Prefs;
import com.app.kpb2.ui.SweetDialogs;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.w3c.dom.Text;

import java.util.List;

public class DokterHewanActivity extends AppCompatActivity implements IDokterHewanView {
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    @BindView(R.id.mNama)
    TextView mNama;
    @BindView(R.id.mNoTlp)
    TextView mNoTlp;
    @BindView(R.id.mBtnHub)
    Button mBtnHub;
    SweetAlertDialog sweetAlertDialog;
    DokterHewanPresenter presenter ;
    String nik, idKab, token ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter_hewan);

        presenter= new DokterHewanPresenter(this);
        ButterKnife.bind(this);
        presenter = new DokterHewanPresenter(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Dokter Hewan");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        LoginResponse mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        token = (mProfile.getResult().getToken().contains(" "))
                ? mProfile.getResult().getToken() : mProfile.getResult().getToken();
        idKab = (mProfile.getResult().getProfile().getArea().getCity_code().contains(" "))
                ? mProfile.getResult().getProfile().getArea().getCity_code() : mProfile.getResult().getProfile().getArea().getCity_code();
        this.initView();
    }


    @Override
    public void initView() {
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);
        presenter.getDokterHewan(nik,token,idKab);

    }

    @Override
    public void onDataReady(DokterHewans result) {
        String noHp = result.getNomorTelp();
        mNama.setText(result.getNamaDokterHewan());
        mNoTlp.setText(result.getNomorTelp());
        if(!noHp.equals(""))
            mBtnHub.setOnClickListener(view -> this.onHubWa(noHp));
    }

    @Override
    public void onHubWa(String noHp){
        String url = "https://api.whatsapp.com/send?phone="+noHp;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    @Override
    public void onRequestFailed(String rm) {
        Log.d("requestFail", rm);
        SweetDialogs.commonError(this, "Gagal Memuat Permintaan", rm, string -> {
            this.goToDashboard();
        });
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
    public void goToDashboard() {
        Intent a = new Intent(this, MenuUtama.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed() {
        // ...
        this.goToDashboard();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.goToDashboard();
        }
        return false;
        // Disable back button..............
    }

    @Override
    public void showLoadingIndicator() {
        sweetAlertDialog.show();
    }

    @Override
    public void hideLoadingIndicator() {
        sweetAlertDialog.dismiss();
    }
}
