package com.app.kpb2.features.petani.dashboard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.cardview.widget.CardView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.kpb2.R;
import com.app.kpb2.Utils.GsonHelper;
import com.app.kpb2.Utils.Utils;
import com.app.kpb2.features.data_produksi.DataProduksiActivity;
import com.app.kpb2.features.e_commerce.EcommerceActivity;
import com.app.kpb2.features.pasar_tani.PasarTaniActivity;
import com.app.kpb2.features.petani.KartuPetani;
import com.app.kpb2.features.petani.dokter_hewan.DokterHewanActivity;
import com.app.kpb2.features.petani.keuangan.KeuanganActivity;
import com.app.kpb2.features.petani.noRekening.model.Balance;
import com.app.kpb2.features.petani.noRekening.model.BalanceResponse;
import com.app.kpb2.features.petani.program_bantuan.ProgramBantuanActivity;
import com.app.kpb2.features.petani.transaksi_non_tunai.TransaksiNonTunaiActivity;
import com.app.kpb2.features.rut.aset.AsetActivity;
import com.app.kpb2.features.transaksi.TransaksiActivity;
import com.app.kpb2.features.users.login.Login;
import com.app.kpb2.features.users.login.model.LoginResponse;
import com.app.kpb2.features.webview.PasarOnlineNasionalTernak;
import com.app.kpb2.server.App;
import com.app.kpb2.session.Prefs;
import com.app.kpb2.features.webview.InfoBeasiswa;
import com.app.kpb2.features.webview.InfoKur;
import com.app.kpb2.features.webview.PortalInformasi;
import com.app.kpb2.ui.DrawerHeader;
import com.app.kpb2.ui.DrawerMenuItem;
import com.app.kpb2.ui.SweetDialogs;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.appupdate.testing.FakeAppUpdateManager;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.google.gson.Gson;
import com.mindorks.placeholderview.PlaceHolderView;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE;
import static com.mikepenz.iconics.Iconics.TAG;

public class Dashboard extends Fragment implements IDashboardView {

    private static final int MY_REQUEST_CODE = 199;
    private static final int IMMEDIATE_APP_UPDATE_REQ_CODE = 80008;
    @BindView(R.id.drawerView)
    PlaceHolderView mDrawerView;
    @BindView(R.id.mainMenu)
    ImageButton mainMenuDashboard;
    @BindView(R.id.mCardKeuangan)
    CardView mCardInfoRek;
    @BindView(R.id.cardPasarTani)
    CardView cardPasarTani;
    @BindView(R.id.mCardTransaksi)
    CardView mCardTransaksi;
    @BindView(R.id.cardDokterHewan)
    CardView cardDokterHewan;
    @BindView(R.id.mCardTransaksiNonTunai)
    CardView mCardTransaksiNonTunai;

    @BindView(R.id.mSaldo)
    TextView mSaldo;

    CardView headerScroller;

    ImageView banners;
    NavigationView navigation;
    ImageButton menu;
    Toolbar toolbarMain;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle mTogle;
    ViewPager viewPager;
    LinearLayout indicatorDot;
    AdapterSliderBanner adapter;
    List<ModelSliderBanner> models;
    private int dotsCount;
    private ImageView[] dots;

    DashboardPresenter presenter;
    SweetAlertDialog sweetAlertDialog;
    private LoginResponse mProfile;
    AppUpdateManager appUpdateManager;
//    Task<AppUpdateInfo> appUpdateInfoTask;
//    static {
//        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
//    }

    int asetPetani;

    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dashboard, container, false);
        ButterKnife.bind(this, view);
        presenter = new DashboardPresenter(this);
        this.setupDrawer();
        this.initViews();

        headerScroller = view.findViewById(R.id.cardInfoBeasiswa);
        headerScroller.setPreventCornerOverlap(false);

        indicatorDot = view.findViewById(R.id.bannerDot);
        viewPager = view.findViewById(R.id.viewPager);
        drawer = view.findViewById(R.id.dashboard);
        toolbarMain = view.findViewById(R.id.toolbar);
//        menu = view.findViewById(R.id.mainMenu);
//        navigation = view.findViewById(R.id.navigation);
        banners = view.findViewById(R.id.banner);

        models = new ArrayList<>();
        models.add(new ModelSliderBanner(R.drawable.banner_a));
        models.add(new ModelSliderBanner(R.drawable.banner_b));
        models.add(new ModelSliderBanner(R.drawable.banner_c));
        models.add(new ModelSliderBanner(R.drawable.banner_d));
        models.add(new ModelSliderBanner(R.drawable.banner_e));
        models.add(new ModelSliderBanner(R.drawable.banner_f));

        adapter = new AdapterSliderBanner(models, getActivity());
        dotsCount = adapter.getCount();
        dots = new ImageView[dotsCount];

        getActivity().getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.nonactive_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            indicatorDot.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.active_dot));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.nonactive_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);

        viewPager.setAdapter(adapter);
        viewPager.setPadding(55, 0, 55, 0);

        mTogle = new ActionBarDrawerToggle(getActivity(), drawer, R.string.open, R.string.close);
        drawer.setDrawerListener(mTogle);
        mTogle.syncState();
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbarMain);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        if (mProfile.getResult().getProfile().getAsetPetani() != null)
            asetPetani = mProfile.getResult().getProfile().getAsetPetani().size();
        else
            asetPetani = 0;
        Log.d("Result", new Gson().toJson(mProfile.getResult().getProfile()));
        return view;
    }

    void poupExit() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View v = getLayoutInflater().inflate(R.layout.custom_dialog_exit, null);

        final Button exit = v.findViewById(R.id.exitacc);
        final Button stay = v.findViewById(R.id.stayacc);

        builder.setView(v);

        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);

        stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getPref().clear();
                startActivity(new Intent(getActivity(), Login.class));
                Animatoo.animateSlideUp(getActivity());
            }
        });

        alertDialog.show();
    }

    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (viewPager.getCurrentItem() == 0) {
                                viewPager.setCurrentItem(1);
                            } else if (viewPager.getCurrentItem() == 1) {
                                viewPager.setCurrentItem(2);
                            } else if (viewPager.getCurrentItem() == 2) {
                                viewPager.setCurrentItem(3);
                            } else if (viewPager.getCurrentItem() == 3) {
                                viewPager.setCurrentItem(4);
                            } else {
                                viewPager.setCurrentItem(0);
                            }
                        } catch (Exception e) {

                        }
                    }
                });
            }
        }
    }

    @Override
    public void onNetworkError(String cause) {
        Log.e("errornya", cause);
        SweetDialogs.endpointError(getActivity());
    }

    @Override
    public void showLoadingIndicator() {
        sweetAlertDialog.show();
    }

    @Override
    public void hideLoadingIndicator() {
        sweetAlertDialog.dismiss();
    }

    @Override
    public void initViews() {

        sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );

        String nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        String token = (mProfile.getResult().getToken().contains(" "))
                ? mProfile.getResult().getToken() : mProfile.getResult().getToken();

        String nomorrekening = (mProfile.getResult().getProfile().getNomorRekening().contains(""))
                ? mProfile.getResult().getProfile().getNomorRekening() : mProfile.getResult().getProfile().getNomorRekening();

        if (!nomorrekening.equals("") && nomorrekening.equals("Bank LAMPUNG"))
            presenter.getSaldo(nik, token, nomorrekening);

//        Toast.makeText(getActivity(), "yang lama", Toast.LENGTH_SHORT).show();

//        presenter.getSaldo();
//        this.forceUpdate();
//        presenter.cekAppVersion(App.getApplication().getString(R.string.app_id));

    }

    @Override
    public void goUpdateVersion(String rm) {
        SweetDialogs.commonWarningWithIntent(getActivity(), "Mohon perbaharui aplikasi", rm, string -> {
            this.goUpdateApps();
        });


    }

    @Override
    public void onDataReady(Balance result) {

        mSaldo.setText(Utils.convertRupiahBigDecimal(result.getAvailable_balance()));

//        DecimalFormat formatter = new DecimalFormat("###.###.###,00");
//        mSaldo.setText(formatter.format(Double.parseDouble(result.getAvailable_balance())));


    }

    @Override
    public void onGetSaldoFailed(BalanceResponse result) {

        if (result.getRc().equals(Prefs.DEFAULT_INVALID_TOKEN))
            SweetDialogs.commonInvalidToken(getActivity(), "Gagal Memuat Permintaan",
                    result.getRm());
        else
            mSaldo.setText("0");

    }

    InstallStateUpdatedListener installStateUpdatedListener = new InstallStateUpdatedListener() {
        @Override
        public void onStateUpdate(InstallState state) {
            if (state.installStatus() == InstallStatus.DOWNLOADED){
                //CHECK THIS if AppUpdateType.FLEXIBLE, otherwise you can skip
                popupSnackbarForCompleteUpdate();
            } else if (state.installStatus() == InstallStatus.INSTALLED){
                if (appUpdateManager != null){
                    appUpdateManager.unregisterListener(installStateUpdatedListener);
                }

            } else {
                Log.i(TAG, "InstallStateUpdatedListener: state: " + state.installStatus());
            }
        }
    };

    @Override
    public void onStart() {
        super.onStart();
//        this.forceUpdate();
    }

    private void forceUpdate() {
//        Toast.makeText(getActivity(), "memeriksa pembaharuan", Toast.LENGTH_SHORT).show();
        appUpdateManager = AppUpdateManagerFactory.create(getActivity());
        appUpdateManager.registerListener(installStateUpdatedListener);
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

        appUpdateInfoTask.addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo appUpdateInfo) {
                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                        // For a flexible update, use AppUpdateType.FLEXIBLE
                        && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                    try {
                        appUpdateManager.startUpdateFlowForResult(
                                // Pass the intent that is returned by 'getAppUpdateInfo()'.
                                appUpdateInfo,
                                // Or 'AppUpdateType.FLEXIBLE' for flexible updates.
                                AppUpdateType.IMMEDIATE,
                                // The current activity making the update request.
                                getActivity(),
                                // Include a request code to later monitor this update request.
                                MY_REQUEST_CODE);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                }else if(appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                        // For a flexible update, use AppUpdateType.FLEXIBLE
                        && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)){

                    try {
                        appUpdateManager.startUpdateFlowForResult(
                                // Pass the intent that is returned by 'getAppUpdateInfo()'.
                                appUpdateInfo,
                                // Or 'AppUpdateType.FLEXIBLE' for flexible updates.
                                AppUpdateType.FLEXIBLE,
                                // The current activity making the update request.
                                getActivity(),
                                // Include a request code to later monitor this update request.
                                MY_REQUEST_CODE);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }

                }

                else if(appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS
                        // For a flexible update, use AppUpdateType.FLEXIBLE
                        && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)){

                    try {
                        appUpdateManager.startUpdateFlowForResult(
                                // Pass the intent that is returned by 'getAppUpdateInfo()'.
                                appUpdateInfo,
                                // Or 'AppUpdateType.FLEXIBLE' for flexible updates.
                                AppUpdateType.IMMEDIATE,
                                // The current activity making the update request.
                                getActivity(),
                                // Include a request code to later monitor this update request.
                                MY_REQUEST_CODE);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

    }



    private void popupSnackbarForCompleteUpdate() {

        Snackbar snackbar =
                Snackbar.make(
                        getActivity().findViewById(R.id.layout),
                        "Aplikasi Berhasil diperbaharui !",
                        Snackbar.LENGTH_INDEFINITE);

        snackbar.setAction("Install", view -> {
            if (appUpdateManager != null){
                appUpdateManager.completeUpdate();
            }
        });


        snackbar.setActionTextColor(getResources().getColor(R.color.success_stroke_color));
        snackbar.show();
    }

//        FakeAppUpdateManager fakeAppUpdateManager = new FakeAppUpdateManager(getActivity());
//        fakeAppUpdateManager.setUpdateAvailable(35); // add app version code greater than current version.
//        fakeAppUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
//            @Override
//            public void onSuccess(AppUpdateInfo appUpdateInfo) {
//                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
//                        && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
//                    Log.d("updateannya" , " ada");
//                    startUpdateFlow(appUpdateInfo);
//                }
//            }
//        });



//    private void startUpdateFlow(AppUpdateInfo appUpdateInfo) {
//        try {
//            appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.IMMEDIATE, getActivity(), IMMEDIATE_APP_UPDATE_REQ_CODE);
//        } catch (IntentSender.SendIntentException e) {
//            e.printStackTrace();
//            Log.d("updatean error", e.getLocalizedMessage());
//        }
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE) {
            if (resultCode == RESULT_CANCELED) {
                forceUpdate();
            } else if (resultCode == RESULT_OK) {
                Toast.makeText(getActivity(), "Pembaharuan aplikasi berhasil", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), "Pembaharuan gagal , mohon coba kembali", Toast.LENGTH_LONG).show();
                forceUpdate();
            }
        }
    }



//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
////        if (requestCode == MY_REQUEST_CODE) {
////            InstallStateUpdatedListener listener = state -> {
////                // (Optional) Provide a download progress bar.
////                if (state.installStatus() == InstallStatus.DOWNLOADING) {
//////                    popupSnackbarForCompleteUpdate();
////                    // Implement progress bar.
////                    onStateUpdate(state);
////                }
////
////                // Log state or install the update.
////            };
////
////// Before starting an update, register a listener for updates.
////            appUpdateManager.registerListener(listener);
////
////// Start an update.
////
////// When status updates are no longer needed, unregister the listener.
////            appUpdateManager.unregisterListener(listener);
////            if (resultCode != RESULT_OK) {
////                Log.d("Update flow failed! Result code: " + resultCode, "");
////                // If the update is cancelled or fails,
////                // you can request to start the update again.
////            }
////        }
//
//        if (requestCode == IMMEDIATE_APP_UPDATE_REQ_CODE) {
//            if (resultCode == RESULT_CANCELED) {
//                Toast.makeText(getActivity(), "Update canceled by user! Result Code: " + resultCode, Toast.LENGTH_LONG).show();
//            } else if (resultCode == RESULT_OK) {
//                Toast.makeText(getActivity(), "Update success! Result Code: " + resultCode, Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(getActivity(), "Update Failed! Result Code: " + resultCode, Toast.LENGTH_LONG).show();
//                forceUpdate();
//            }
//        }
//    }


    @Override
    public void onResume() {
        super.onResume();
//        appUpdateManager
//                .getAppUpdateInfo()
//                .addOnSuccessListener(
//                        appUpdateInfo -> {
//                            if (appUpdateInfo.updateAvailability()
//                                    == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
//                                // If an in-app update is already running, resume the update.
//                                try {
//                                    appUpdateManager.startUpdateFlowForResult(
//                                            appUpdateInfo,
//                                            IMMEDIATE,
//                                            (IntentSenderForResultStarter) this,
//                                            MY_REQUEST_CODE);
//                                } catch (IntentSender.SendIntentException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
    }

    @Override
    public void onStop() {
        super.onStop();
//        if (appUpdateManager != null) {
//            appUpdateManager.unregisterListener(installStateUpdatedListener);
//        }
    }

    @Override
    public void goUpdateApps() {
        App.getPref().clear();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://play.google.com/store/apps/details?id=com.app.kpb2"));
        startActivity(intent);
        getActivity().finishAffinity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.mCardTransaksi)
    void goToTransaksi() {
        Intent i = new Intent(getActivity(), TransaksiActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.mCardKeuangan)
    void info_rek() {
        Intent i = new Intent(getActivity(), KeuanganActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.mCardDataProduksi)
    void goToDataProduksi() {
        Intent i = new Intent(getActivity(), DataProduksiActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.cardPasarTani)
    void goToPasarTani() {
        Intent i = new Intent(getActivity(), PasarTaniActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.cardPupuk)
    void infoPupuk() {
        Intent i = new Intent(getActivity(), ProgramBantuanActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.mCardRut)
    void goToRut() {
        this.gotoAset();
//        Toast.makeText(getActivity(), "Menu ini sedang dalam Pengembangan", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.mCardTransaksiNonTunai)
    void goToTransaksiNonTunai() {
        Intent i = new Intent(getActivity(), TransaksiNonTunaiActivity.class);
        startActivity(i);
        getActivity().finish();
    }


    void gotoAset() {
        Intent i = new Intent(getActivity(), AsetActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.cardInfoBeasiswa)
    void infoBea() {
        Intent i = new Intent(getActivity(), InfoBeasiswa.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.cardInfoKur)
    void infoKur() {
        Intent i = new Intent(getActivity(), InfoKur.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.cardPortalInformasi)
    void portalInformasi() {
        Intent i = new Intent(getActivity(), PortalInformasi.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.cardPortalPeternakan)
    void portalPeternakan() {
        Intent i = new Intent(getActivity(), PasarOnlineNasionalTernak.class);
        startActivity(i);
        getActivity().finish();
    }

//    @OnClick(R.id.cardKatam)
//    void katam() {
//        Toast.makeText(getActivity(), "Maaf menu ini belum tersedia !", Toast.LENGTH_SHORT).show();
//    }

//    @OnClick(R.id.cardECommerce)
//    void eCommerce() {
//        Intent i = new Intent(getActivity(), EcommerceActivity.class);
//        startActivity(i);
//        getActivity().finish();
//    }

    @OnClick(R.id.cardKartuPetani)
    void kartuPetani() {
        Intent i = new Intent(getActivity(), KartuPetani.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.cardDokterHewan)
    void cardDokterHewan() {
        Intent i = new Intent(getActivity(), DokterHewanActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    private void setupDrawer() {
        mDrawerView
                .addView(new DrawerHeader(getActivity()))
                .addView(new DrawerMenuItem(getActivity(), DrawerMenuItem.DRAWER_MENU_ITEM_PROFILE))
//                .addView(new DrawerMenuItem(getActivity(), DrawerMenuItem.DRAWER_MENU_ITEM_KOLABORATOR))
//                .addView(new DrawerMenuItem(getActivity(), DrawerMenuItem.DRAWER_MENU_ITEM_ABOUT))
//                .addView(new DrawerMenuItem(getActivity(), DrawerMenuItem.DRAWER_MENU_ITEM_TERMCONDITION))
//                .addView(new DrawerMenuItem(getActivity(), DrawerMenuItem.DRAWER_MENU_ITEM_SURATKUASA))
                .addView(new DrawerMenuItem(getActivity(), DrawerMenuItem.DRAWER_MENU_ITEM_RESETPASSWORD))
                .addView(new DrawerMenuItem(getActivity(), DrawerMenuItem.DRAWER_MENU_ITEM_LOGOUT));


        mainMenuDashboard.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                if (!drawer.isDrawerOpen(Gravity.END)) drawer.openDrawer(Gravity.END);
                else drawer.closeDrawer(Gravity.START);
            }
        });
    }
}
