package com.app.kpb2.features.pupuksubsidi;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;

import com.app.kpb2.R;
import com.app.kpb2.features.petani.MenuUtama;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PupukSubsidiActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rdkk);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("Rencana Definitif Kelompok Tani");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

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

    public void goToDashboard() {
        Intent i = new Intent(this, MenuUtama.class);
        startActivity(i);
        finish();
    }
}
