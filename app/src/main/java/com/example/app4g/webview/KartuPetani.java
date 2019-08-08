package com.example.app4g.webview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.app4g.R;
import com.example.app4g.petani.MenuUtama;
import com.example.app4g.petani.anak.ListDataAnak;

public class KartuPetani extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kartu_petani);
    }

    @Override
    public void onBackPressed() { //ini untuk tombol fisik kembali
        Intent a = new Intent(KartuPetani.this, MenuUtama.class);
        startActivity(a);
        finish();
    }
}
