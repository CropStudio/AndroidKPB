package com.app.app4g.features.petani.detailpupuk;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.app4g.R;
import com.app.app4g.features.petani.jatah.ListDataPupuk;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailPupukActivity extends AppCompatActivity {

    @BindView(R.id.jumlahPupukUrea1)
    TextView jumlahPupukUrea1;
    @BindView(R.id.jumlahPupukUrea2)
    TextView jumlahPupukUrea2;
    @BindView(R.id.jumlahPupukUrea3)
    TextView jumlahPupukUrea3;
    @BindView(R.id.jumlahPupukSp361)
    TextView jumlahPupukSp361;
    @BindView(R.id.jumlahPupukSp362)
    TextView jumlahPupukSp362;
    @BindView(R.id.jumlahPupukSp363)
    TextView jumlahPupukSp363;
    @BindView(R.id.jumlahPupukZA1)
    TextView jumlahPupukZA1;
    @BindView(R.id.jumlahPupukZA2)
    TextView jumlahPupukZA2;
    @BindView(R.id.jumlahPupukZA3)
    TextView jumlahPupukZA3;
    @BindView(R.id.jumlahPupukNpk1)
    TextView jumlahPupukNpk1;
    @BindView(R.id.jumlahPupukNpk2)
    TextView jumlahPupukNpk2;
    @BindView(R.id.jumlahPupukNpk3)
    TextView jumlahPupukNpk3;
    @BindView(R.id.jumlahPupukOrganik1)
    TextView jumlahPupukOrganik1;
    @BindView(R.id.jumlahPupukOrganik2)
    TextView jumlahPupukOrganik2;
    @BindView(R.id.jumlahPupukOrganik3)
    TextView jumlahPupukOrganik3;
    @BindView(R.id.komoditas1)
    TextView komoditas1;
    @BindView(R.id.komoditas2)
    TextView komoditas2;
    @BindView(R.id.komoditas3)
    TextView komoditas3;
    @BindView(R.id.total1)
    TextView total1;
    @BindView(R.id.total2)
    TextView total2;
    @BindView(R.id.total3)
    TextView total3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pupuk);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        String urea1  = intent.getStringExtra("urea1");
        String urea2  = intent.getStringExtra("urea2");
        String urea3  = intent.getStringExtra("urea3");
        String sp361  = intent.getStringExtra("sp361");
        String sp362  = intent.getStringExtra("sp362");
        String sp363  = intent.getStringExtra("sp363");
        String za1  = intent.getStringExtra("za1");
        String za2  = intent.getStringExtra("za2");
        String za3  = intent.getStringExtra("za3");
        String npk1  = intent.getStringExtra("npk1");
        String npk2  = intent.getStringExtra("npk2");
        String npk3  = intent.getStringExtra("npk3");
        String organik1  = intent.getStringExtra("organik1");
        String organik2  = intent.getStringExtra("organik2");
        String organik3  = intent.getStringExtra("organik3");
        String kom1  = intent.getStringExtra("komoditas1");
        String kom2  = intent.getStringExtra("komoditas2");
        String kom3  = intent.getStringExtra("komoditas3");

        jumlahPupukUrea1.setText(urea1 + " Kg");
        jumlahPupukUrea2.setText(urea2 + " Kg");
        jumlahPupukUrea3.setText(urea3 + " Kg");
        jumlahPupukSp361.setText(sp361 + " Kg");
        jumlahPupukSp362.setText(sp362 + " Kg");
        jumlahPupukSp363.setText(sp363 + " Kg");
        jumlahPupukZA1.setText(za1 + " Kg");
        jumlahPupukZA2.setText(za2 + " Kg");
        jumlahPupukZA3.setText(za3 + " Kg");
        jumlahPupukNpk1.setText(npk1 + " Kg");
        jumlahPupukNpk2.setText(npk2 + " Kg");
        jumlahPupukNpk3.setText(npk3 + " Kg");
        jumlahPupukOrganik1.setText(organik1 + " Kg");
        jumlahPupukOrganik2.setText(organik2 + " Kg");
        jumlahPupukOrganik3.setText(organik3 + " Kg");
        komoditas1.setText(kom1);
        komoditas2.setText(kom2);
        komoditas3.setText(kom3);

        double tot1 = Double.parseDouble(urea1) + Double.parseDouble(sp361) + Double.parseDouble(za1) +
                Double.parseDouble(npk1) + Double.parseDouble(organik1);

        double tot2 = Double.parseDouble(urea2) + Double.parseDouble(sp362) + Double.parseDouble(za2) +
                Double.parseDouble(npk2) + Double.parseDouble(organik2);

        double tot3 = Double.parseDouble(urea3) + Double.parseDouble(sp363) + Double.parseDouble(za3) +
                Double.parseDouble(npk3) + Double.parseDouble(organik3);

        total1.setText(tot1 + " Kg");
        total2.setText(tot2 + " Kg");
        total3.setText(tot3 + " Kg");


    }

    @Override
    public void onBackPressed() {
        goToListPupuk();
    }

    public void onBack(View view) {
        goToListPupuk();
    }

    public void goToListPupuk(){
        startActivity(new Intent(DetailPupukActivity.this, ListDataPupuk.class));
        finish();
    }
}
