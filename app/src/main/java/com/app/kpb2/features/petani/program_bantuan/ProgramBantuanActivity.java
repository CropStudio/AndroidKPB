package com.app.kpb2.features.petani.program_bantuan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.app.kpb2.R;
import com.app.kpb2.features.petani.MenuUtama;
import com.app.kpb2.features.petani.program_bantuan.alokasi.AlokasiPupukActivity;
import com.app.kpb2.features.petani.program_bantuan.autp.AutpActivity;
import com.app.kpb2.features.petani.program_bantuan.bantuan.BantuanActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProgramBantuanActivity extends AppCompatActivity {
    @BindView(R.id.mCardAlokasi)
    CardView mCardAlokasi;
    @BindView(R.id.mCardAutp)
    CardView mCardAutp;
    @BindView(R.id.mCardBantuan)
    CardView mCardBantuan;
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_bantuan);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Program Bantuan");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        this.initViews();

    }

    public void initViews(){
        mCardAlokasi.setOnClickListener(view -> this.goToAlokasi());
        mCardAutp.setOnClickListener(view -> this.goToAutp());
        mCardBantuan.setOnClickListener(view -> this.goToBantuan());
    }

    public void goToAlokasi(){
        startActivity(new Intent(this, AlokasiPupukActivity.class));
        finish();
    }

    public void goToAutp(){
        startActivity(new Intent(this, AutpActivity.class));
        finish();
    }

    public void goToBantuan(){
        startActivity(new Intent(this, BantuanActivity.class));
        finish();
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

    public void goToDashboard() {
        Intent a = new Intent(this, MenuUtama.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed() {
        // ...
        super.onBackPressed();
        this.goToDashboard();
    }


}
