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

import butterknife.ButterKnife;

import static com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE;

public class MenuUtama extends AppCompatActivity implements ComponentCallbacks2 {
    private static final int TIME_INTERVAL = 2000;
    private static final int MY_REQUEST_CODE = 999 ;
    private long mBackPressed;
    boolean BackPress = false;
    LoginResponse mProfile;
    AppUpdateManager appUpdateManager ;
    private static final int IMMEDIATE_APP_UPDATE_REQ_CODE = 124;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
        ButterKnife.bind(this);
        appUpdateManager = AppUpdateManagerFactory.create(getApplicationContext());
        forceUpdate();
//        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
//        bottomNav.setOnNavigationItemSelectedListener(navListener);
//        appUpdateManager = AppUpdateManagerFactory.create(this);
//
//// Returns an intent object that you use to check for an update.
//        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
//        Log.d("appUpdateInfo" , String.valueOf(appUpdateManager));
// Checks that the platform will allow the specified type of update.
//        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
//            Log.d("appUpdateInfo" , String.valueOf(appUpdateInfo));
//            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
//                    // For a flexible update, use AppUpdateType.FLEXIBLE
//                    && appUpdateInfo.isUpdateTypeAllowed(IMMEDIATE)) {
//                try {
//                    appUpdateManager.startUpdateFlowForResult(
//                            // Pass the intent that is returned by 'getAppUpdateInfo()'.
//                            appUpdateInfo,
//                            // Or 'AppUpdateType.FLEXIBLE' for flexible updates.
//                            IMMEDIATE,
//                            // The current activity making the update request.
//                            this,
//                            // Include a request code to later monitor this update request.
//                            MY_REQUEST_CODE);
//                } catch (IntentSender.SendIntentException e) {
//                    e.printStackTrace();
//                }
//
//            }else{
//                Log.d("status update" ,"tidak ada ") ;
//            }
//        });


//        String noKk = (mProfile.getResult().getNoKk().contains(" "))
//                ? mProfile.getResult().getNoKk() : mProfile.getResult().getNoKk();
//        if(noKk.equals("")){
//            SweetDialogs.commonWarningWithIntent(this, "Anda harus melengkapi data terlebih dahulu !" , string -> {
//                this.goToUpdateProfile();
//            });
//        }


    }

    public void forceUpdate(){
//        Toast.makeText(this, "memeriksa pembaharuan", Toast.LENGTH_SHORT).show();
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
        Log.d("appUpdateInfoTask" , String.valueOf(appUpdateInfoTask));
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            Log.d("appUpdateInfo" , String.valueOf(appUpdateInfo));
            Log.d("appUpdateInfo" , String.valueOf(UpdateAvailability.UPDATE_AVAILABLE));
            Log.d("appUpdateInfo" , String.valueOf(AppUpdateType.IMMEDIATE));
            Log.d("appUpdateInfo" , String.valueOf(UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS));
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                startUpdateFlow(appUpdateInfo);
            } else if  (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS){
                startUpdateFlow(appUpdateInfo);
            }else{
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Dashboard()).commit();

                getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            }
        });


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Dashboard()).commit();

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
    }

    private void startUpdateFlow(AppUpdateInfo appUpdateInfo) {
        try {
            appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.IMMEDIATE, this,IMMEDIATE_APP_UPDATE_REQ_CODE);
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