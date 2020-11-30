package com.app.kpb2.features.petani;

import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.app.kpb2.R;
import com.app.kpb2.Utils.GsonHelper;
import com.app.kpb2.features.petani.dashboard.Dashboard;
import com.app.kpb2.features.petani.profile.createprofile.CreateProfile;
import com.app.kpb2.features.users.login.model.LoginResponse;
import com.app.kpb2.server.App;
import com.app.kpb2.session.Prefs;

import butterknife.ButterKnife;

public class MenuUtama extends AppCompatActivity implements ComponentCallbacks2 {
    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;
    boolean BackPress = false;
    LoginResponse mProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
        ButterKnife.bind(this);
//        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
//        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Dashboard()).commit();
        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

//        String noKk = (mProfile.getResult().getNoKk().contains(" "))
//                ? mProfile.getResult().getNoKk() : mProfile.getResult().getNoKk();
//        if(noKk.equals("")){
//            SweetDialogs.commonWarningWithIntent(this, "Anda harus melengkapi data terlebih dahulu !" , string -> {
//                this.goToUpdateProfile();
//            });
//        }
    }

    public void goToUpdateProfile() {
        startActivity(new Intent(this, CreateProfile.class));
        finish();
    }


    @Override
    public void onBackPressed() {
        if (BackPress) {
            finishAffinity();
            return;
        }
        this.BackPress = true;
        Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                BackPress = false;
            }
        }, 2000);
    }

}