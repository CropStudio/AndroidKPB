package com.app.app4g.features.petani.dashboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.app4g.R;
import com.app.app4g.Utils.GsonHelper;
import com.app.app4g.features.petani.AdapterSliderBanner;
import com.app.app4g.features.petani.KartuPetani;
import com.app.app4g.features.petani.ModelSliderBanner;
import com.app.app4g.features.petani.jatah.ListDataPupuk;
import com.app.app4g.features.rut.aset.AsetActivity;
import com.app.app4g.features.users.login.Login;
import com.app.app4g.features.users.login.model.LoginResponse;
import com.app.app4g.server.App;
import com.app.app4g.session.Prefs;
import com.app.app4g.features.webview.InfoBeasiswa;
import com.app.app4g.features.webview.InfoKur;
import com.app.app4g.features.webview.PortalInformasi;
import com.app.app4g.ui.DrawerHeader;
import com.app.app4g.ui.DrawerMenuItem;
import com.app.app4g.ui.SweetDialogs;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.mindorks.placeholderview.PlaceHolderView;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Dashboard extends Fragment implements IDashboardView {

    @BindView(R.id.drawerView)
    PlaceHolderView mDrawerView;
    @BindView(R.id.mainMenu)
    ImageButton mainMenuDashboard;
    @BindView(R.id.mCardInfoRek)
    CardView mCardInfoRek;
    @BindView(R.id.cardPasarTani)
    CardView cardRdkk;
    @BindView(R.id.mCardTransaksi)
    CardView mCardTransaksi;

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

    DashboardPresenter presenter ;
    SweetAlertDialog sweetAlertDialog;
    private LoginResponse mProfile;

//    static {
//        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
//    }

    int asetPetani ;

    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dashboard, container, false);
        ButterKnife.bind(this, view);
        presenter = new DashboardPresenter(this);
        this.setupDrawer();
        this.initViews();

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
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarMain);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

//        menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawer.openDrawer(navigation);
//            }
//        });
//        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                if (id == R.id.profile){
//
//                }else if (id == R.id.kolaborator){
//
//                }else if (id == R.id.about){
//
//                }else if (id == R.id.term){
//
//                }else if (id == R.id.exit){
//                    poupExit();
//                }
//                return true;
//            }
//        });

//        carouselView = view.findViewById(R.id.carousel);
//        carouselView.setPageCount(mImages.length);
//        carouselView.setImageListener(new ImageListener() {
//            @Override
//            public void setImageForPosition(int position, ImageView imageView) {
//                imageView.setImageResource(mImages[position]);
//            }
//        });

        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        if(mProfile.getResult().getProfile().getAsetPetani() !=null)
            asetPetani  = mProfile.getResult().getProfile().getAsetPetani().size();
        else
            asetPetani = 0 ;
        Log.d("Result", new Gson().toJson(mProfile.getResult().getProfile()));
        return view;
    }

    void poupExit(){
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
        presenter.cekAppVersion(App.getApplication().getString(R.string.app_id));
    }

    @Override
    public void goUpdateVersion(String rm) {
        SweetDialogs.commonWarningWithIntent(getActivity(),"Mohon perbaharui aplikasi", rm, string -> {
            this.goUpdateApps();
        });
    }

    @Override
    public void goUpdateApps(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.app.app4g"));
        startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.mCardTransaksi)
    void tambah_saldo() {
        Toast.makeText(getActivity(), "Maaf menu ini belum tersedia !", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.mCardInfoRek)
    void info_rek() {
        Toast.makeText(getActivity(), "Maaf menu ini belum tersedia !", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.cardPasarTani)
    void goToRdkk() {
        Toast.makeText(getActivity(), "Maaf menu ini belum tersedia !", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.cardPupuk)
    void infoPupuk() {
        Intent i = new Intent(getActivity(), ListDataPupuk.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.mCardRut)
    void goToRut() {
        this.gotoAset();
    }

    void gotoAset(){
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

    private void setupDrawer() {
        mDrawerView
                .addView(new DrawerHeader(getActivity()))
                .addView(new DrawerMenuItem(getActivity(), DrawerMenuItem.DRAWER_MENU_ITEM_PROFILE))
                .addView(new DrawerMenuItem(getActivity(), DrawerMenuItem.DRAWER_MENU_ITEM_KOLABORATOR))
                .addView(new DrawerMenuItem(getActivity(), DrawerMenuItem.DRAWER_MENU_ITEM_ABOUT))
                .addView(new DrawerMenuItem(getActivity(), DrawerMenuItem.DRAWER_MENU_ITEM_TERMCONDITION))
                .addView(new DrawerMenuItem(getActivity(), DrawerMenuItem.DRAWER_MENU_ITEM_LOGOUT));

//        menu.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("WrongConstant")
//            @Override
//            public void onClick(View v) {
//                if (!drawer.isDrawerOpen(Gravity.END)) drawer.openDrawer(Gravity.END);
//                else drawer.closeDrawer(Gravity.START);
//            }
//        });
        mainMenuDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!drawer.isDrawerOpen(Gravity.END)) drawer.openDrawer(Gravity.END);
                else drawer.closeDrawer(Gravity.START);
            }
        });
    }
}
