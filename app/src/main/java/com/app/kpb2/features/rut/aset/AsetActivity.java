package com.app.kpb2.features.rut.aset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;

import com.app.kpb2.R;
import com.app.kpb2.Utils.GsonHelper;
import com.app.kpb2.features.petani.MenuUtama;
import com.app.kpb2.features.petani.profile.model.AsetPetani;
import com.app.kpb2.features.rut.RutActivity;
import com.app.kpb2.features.rut.aset.createaset.CreateAset;
import com.app.kpb2.features.rut.createmt.CreateMt;
import com.app.kpb2.features.users.login.model.LoginResponse;
import com.app.kpb2.server.App;
import com.app.kpb2.session.Prefs;
import com.app.kpb2.ui.SweetDialogs;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AsetActivity extends AppCompatActivity implements IAsetView, AsetAdapter.OnItemSelected {
    LoginResponse mProfile;
    String nik, token , idDesa;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.btnAddAset)
    Button btnAddAset;
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    private List<AsetPetani> asetPetani;
    AsetAdapter adapter;
    AsetPresenter presenter ;
    SweetAlertDialog sweetAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aset);
        ButterKnife.bind(this);
        presenter = new AsetPresenter(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Subsektor");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        this.initViews();
    }

    @Override
    public void initViews() {

        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);
        btnAddAset.setOnClickListener(view -> this.goToAddAset());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.clearFocus();
        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        token = (mProfile.getResult().getToken().contains(" "))
                ? mProfile.getResult().getToken() : mProfile.getResult().getToken();
        idDesa = (mProfile.getResult().getProfile().getArea().getSub_district_code().contains(" "))
                ? mProfile.getResult().getProfile().getArea().getSub_district_code() : mProfile.getResult().getProfile().getArea().getSub_district_code();
        if (mProfile.getResult().getProfile().getAsetPetani() != null) {
            asetPetani = (mProfile.getResult().getProfile().getAsetPetani().contains(" "))
                    ? mProfile.getResult().getProfile().getAsetPetani() : mProfile.getResult().getProfile().getAsetPetani();
            if (asetPetani.size() > 0) {
                adapter = new AsetAdapter(asetPetani, this, this);
                mRecyclerView.setAdapter(adapter);
            }

        }

    }

    @Override
    public void goToAddAset() {
        startActivity(new Intent(this, CreateAset.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        // ...
        this.goToDashboard();
        super.onBackPressed();
    }


    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.goToDashboard();
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
    public void onSelect(AsetPetani aset) {
        JSONObject data = new JSONObject();
        Log.d("datadariaset" , new Gson().toJson(aset.getDataPermt()));
        if (aset.getDataPermt().size() > 0) {
            Intent i = new Intent(this, RutActivity.class);
            i.putExtra("data", (Serializable) aset.getDataPermt());
            i.putExtra("_id", aset.get_id());
            startActivity(i);
            finish();
//            Toast.makeText(this, "ada", Toast.LENGTH_SHORT).show();
        }else{
           SweetDialogs.commonWarningWithIntent(this,"Data Anda belum lengkap" , "Anda belum mengatur Masa Tanam / Usia Tanam, Silahkan tekan 'OK' untuk melengkapi data",string -> {
               this.goToCreateMt(aset);
           });
        }
    }

    @Override
    public void goToCreateMt(AsetPetani aset){
        Intent i = new Intent(this, CreateMt.class);
        i.putExtra("subsektor", aset.getNamaAset());
        i.putExtra("idSubsektor", aset.getIdSubsektor());
        i.putExtra("totalAset", aset.getTotalAset());
        i.putExtra("_id", aset.get_id());
        startActivity(i);
        finish();
    }

    @Override
    public void onHapus(AsetPetani aset) {
       presenter.deleteAset(nik , token , aset.get_id());

    }

    @Override
    public void onDeleteSuccess(LoginResponse response) {
        presenter.storeProfile(response);
        this.recreate();

    }

    @Override
    public void onRequestFailed(LoginResponse response) {
        SweetDialogs.commonError(this,"Subsektor ini tidak dapat dihapus , karna anda telah melakukan transaksi",false);
    }

    @Override
    public void onNetworkError(String cause) {
        Log.d("Error", cause);
        SweetDialogs.endpointError(this);
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
