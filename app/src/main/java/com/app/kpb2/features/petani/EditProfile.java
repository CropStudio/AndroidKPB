package com.app.kpb2.features.petani;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.app.kpb2.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditProfile extends AppCompatActivity {


    @BindView(R.id.edNik)
    EditText edtNik;
    @BindView(R.id.namaLengkap)
    EditText edtNamaLengkap;
    @BindView(R.id.phone)
    EditText edtPhone;

    @BindView(R.id.switch1)
    Switch switchData;
    @BindView(R.id.linearPassword)
    LinearLayout passwordLinear;
    @BindView(R.id.editPassword)
    Button edtPassword;

    @BindView(R.id.password)
    EditText edPassword;

    @BindView(R.id.progress)
    ProgressBar prgBar;

    RetryPolicy policy = new DefaultRetryPolicy(5000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    Handler handler;

    String poktan, alamatPoktan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        //getSupportActionBar().hide();


//        handler=new Handler();
//        prgBar.setVisibility(View.GONE);
//
//        edtNik.setText(strNik);
//        edtNamaLengkap.setText(strNama);
//        edtPhone.setText(strNotelp);
//
//        switchData.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//                    passwordLinear.setVisibility(View.VISIBLE);
//                    edtPassword.setVisibility(View.VISIBLE);
//                }else {
//                    passwordLinear.setVisibility(View.GONE);
//                    edtPassword.setVisibility(View.GONE);
//                }
//            }
//        });
//
//        Intent i     = getIntent();
//        poktan       = i.getStringExtra("namapoktan");
//        alamatPoktan = i.getStringExtra("alamat");

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

    }

    @OnClick(R.id.ediPoktan)
    void editPoktan(){
        Intent i = new Intent(EditProfile.this, EditPoktan.class);
        i.putExtra("namapoktan", poktan);
        i.putExtra("alamat", alamatPoktan);
        startActivity(i);
        finish();
    }


    @Override
    public void onBackPressed(){
        Intent a = getIntent();
        String hehe = a.getStringExtra("datanya");
        Intent i = new Intent(EditProfile.this, MenuUtama.class);
        i.putExtra("datanya", hehe);
        startActivity(i);

    }


}
