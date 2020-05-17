package com.app.app4g.features.rut.aset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.app.app4g.R;
import com.app.app4g.Utils.GsonHelper;
import com.app.app4g.features.cart.CartAdapter;
import com.app.app4g.features.petani.MenuUtama;
import com.app.app4g.features.petani.profile.model.AsetPetani;
import com.app.app4g.features.rut.RutActivity;
import com.app.app4g.features.rut.aset.createaset.CreateAset;
import com.app.app4g.features.rut.createmt.CreateMt;
import com.app.app4g.features.users.login.model.LoginResponse;
import com.app.app4g.server.App;
import com.app.app4g.session.Prefs;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONException;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aset);
        ButterKnife.bind(this);
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
        if (aset.getDataPermt().size() > 0) {
            Intent i = new Intent(this, RutActivity.class);
            i.putExtra("data", (Serializable) aset.getDataPermt());
            i.putExtra("_id", aset.get_id());
            startActivity(i);
            finish();
//            Toast.makeText(this, "ada", Toast.LENGTH_SHORT).show();
        }else{
            Intent i = new Intent(this, CreateMt.class);
            i.putExtra("subsektor", aset.getNamaAset());
            i.putExtra("idSubsektor", aset.getIdSubsektor());
            i.putExtra("totalAset", aset.getTotalAset());
            i.putExtra("_id", aset.get_id());
            startActivity(i);
            finish();
        }
    }
}
