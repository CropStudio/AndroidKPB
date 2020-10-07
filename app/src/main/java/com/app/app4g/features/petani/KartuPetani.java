package com.app.app4g.features.petani;

import android.content.Intent;
import android.graphics.Bitmap;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.app4g.R;
import com.app.app4g.Utils.GsonHelper;
import com.app.app4g.features.users.login.model.LoginResponse;
import com.app.app4g.server.App;
import com.app.app4g.session.Prefs;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class KartuPetani extends AppCompatActivity {

    private Bitmap qRBit;
    String nik , nama , namaBank ;
    @BindView(R.id.baseLayout)
    LinearLayout baseLaouyout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kartu_petani);
        ButterKnife.bind(this);
        LoginResponse mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        nama = (mProfile.getResult().getProfile().getNama().contains(" "))
                ? mProfile.getResult().getProfile().getNama() : mProfile.getResult().getProfile().getNama();
        if (!mProfile.getResult().getProfile().getNomorRekening().equals("")) {
            namaBank = (mProfile.getResult().getProfile().getBank().contains(" "))
                    ? mProfile.getResult().getProfile().getBank() : mProfile.getResult().getProfile().getBank();
            if(namaBank.equals("Bank LAMPUNG")){
                baseLaouyout.setBackgroundResource(R.drawable.bank_lampung);
            }else if(namaBank.equals("Bank BNI")){
                baseLaouyout.setBackgroundResource(R.drawable.bank_bni);
            }else if(namaBank.equals("Bank MANDIRI")){
                baseLaouyout.setBackgroundResource(R.drawable.bank_mandiri);
            }else if(namaBank.equals("Bank BRI")){
                baseLaouyout.setBackgroundResource(R.drawable.bank_bri);
            }else {
                baseLaouyout.setBackgroundResource(R.drawable.default_kartu);
            }

        }
        Log.d("namabank" , namaBank);
        qRBit = printQRCode("http://aplikasi.kartupetaniberjaya.com/#/profil/"+nik);
//        qRBit = getIntent().getParcelableExtra("bitmap");
        Log.v("QR = ", String.valueOf(qRBit));
        ImageView image = findViewById(R.id.imageView);
        image.setImageBitmap(qRBit);

        TextView txtNama = findViewById(R.id.nameTag);
        TextView txtNik = findViewById(R.id.nikTag);
        txtNama.setText(nama);
        txtNik.setText(nik);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

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



    @Override
    public void onBackPressed() { //ini untuk tombol fisik kembali
        Intent a = new Intent(KartuPetani.this, MenuUtama.class);
        startActivity(a);
        finish();
    }
}
