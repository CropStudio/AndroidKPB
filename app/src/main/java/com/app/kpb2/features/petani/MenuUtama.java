package com.app.kpb2.features.petani;

import android.annotation.SuppressLint;
import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.app.kpb2.BuildConfig;
import com.app.kpb2.R;
import com.app.kpb2.Utils.GsonHelper;
import com.app.kpb2.features.petani.dashboard.Dashboard;
import com.app.kpb2.features.petani.profile.createprofile.CreateProfile;
import com.app.kpb2.features.users.login.model.LoginResponse;
import com.app.kpb2.server.App;
import com.app.kpb2.session.Prefs;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.sanojpunchihewa.updatemanager.UpdateManager;
import com.sanojpunchihewa.updatemanager.UpdateManager.UpdateInfoListener;
import com.sanojpunchihewa.updatemanager.UpdateManagerConstant;

import butterknife.ButterKnife;

import static com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE;

public class MenuUtama extends AppCompatActivity implements ComponentCallbacks2 {
    private static final int TIME_INTERVAL = 2000;
    private static final int MY_REQUEST_CODE = 999;
    private long mBackPressed;
    boolean BackPress = false;
    LoginResponse mProfile;
    AppUpdateManager appUpdateManager;
    private static final int IMMEDIATE_APP_UPDATE_REQ_CODE = 124;
    UpdateManager mUpdateManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
        ButterKnife.bind(this);
//        Toast.makeText(MenuUtama.this, String.valueOf(BuildConfig.VERSION_CODE ), Toast.LENGTH_SHORT).show();
        mUpdateManager = UpdateManager.Builder(this).mode(UpdateManagerConstant.IMMEDIATE);
        mUpdateManager.start();

        mUpdateManager.addUpdateInfoListener(new UpdateInfoListener() {
            @Override
            public void onReceiveVersionCode(final int code) {
                Toast.makeText(MenuUtama.this, String.valueOf(code), Toast.LENGTH_SHORT).show();
                if (code != BuildConfig.VERSION_CODE) {
                    mUpdateManager.mode(UpdateManagerConstant.IMMEDIATE).start();
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new Dashboard()).commit();

                    getWindow().setFlags(
                            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
                }
            }

            @Override
            public void onReceiveStalenessDays(final int days) {

            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Dashboard()).commit();

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
    }

    public void forceUpdate() {
//        Toast.makeText(this, "memeriksa pembaharuan", Toast.LENGTH_SHORT).show();
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
        Log.d("appUpdateInfoTask", String.valueOf(appUpdateInfoTask));
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                startUpdateFlow(appUpdateInfo);
            } else if (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                startUpdateFlow(appUpdateInfo);
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Dashboard()).commit();

                getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            }
        });


//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                new Dashboard()).commit();
//
//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
//                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
    }

    private void startUpdateFlow(AppUpdateInfo appUpdateInfo) {
        try {
            appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.IMMEDIATE, this, IMMEDIATE_APP_UPDATE_REQ_CODE);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMMEDIATE_APP_UPDATE_REQ_CODE) {
            if (resultCode == RESULT_CANCELED) {
                forceUpdate();
            } else if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Pembaharuan aplikasi berhasil", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Pembaharuan gagal , mohon coba kembali", Toast.LENGTH_LONG).show();
                forceUpdate();
            }
        }
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