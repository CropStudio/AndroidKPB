package com.example.app4g.petani;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app4g.R;
import com.example.app4g.session.SessionManager;
import com.example.app4g.users.login.Login;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class KartuPetani extends AppCompatActivity {

    private Bitmap qRBit;
    public SharedPreferences prefs;
    public SessionManager session;

    String strId, strNik, strNotelp, strNama, strRole, strToken, strKtp, strKk, strPotoPropil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kartu_petani);

        prefs = getSharedPreferences("UserDetails",
                Context.MODE_PRIVATE);

        isLogin();
    }

    private Bitmap printQRCode(String textToQR){
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(textToQR, BarcodeFormat.QR_CODE,300,300);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void isLogin(){
        // Session manager
        session = new SessionManager(this);
        //Session Login
        if(session.isLoggedIn()){
            strId       = prefs.getString("id","");
            strNik      = prefs.getString("nik","");
            strNotelp   = prefs.getString("notelp", "");
            strNama     = prefs.getString("nama", "");
            strRole     = prefs.getString("role", "");
            strToken    = prefs.getString("token", "");
            strKtp      = prefs.getString("ktp", "");
            strKk       = prefs.getString("kk","");
            strPotoPropil=prefs.getString("pp","");

            qRBit = printQRCode(strNik);
//        qRBit = getIntent().getParcelableExtra("bitmap");
            Log.v("QR = ", String.valueOf(qRBit));
            ImageView image = (ImageView) findViewById(R.id.imageView);
            image.setImageBitmap(qRBit);

            TextView txtNama = (TextView) findViewById(R.id.nameTag);
            txtNama.setText(strNama);

        }else{
            Intent a = new Intent(getApplicationContext(), Login.class);
            startActivity(a);
            finish();
        }
    }

    @Override
    public void onBackPressed() { //ini untuk tombol fisik kembali
        Intent a = new Intent(KartuPetani.this, MenuUtama.class);
        startActivity(a);
        finish();
    }
}
