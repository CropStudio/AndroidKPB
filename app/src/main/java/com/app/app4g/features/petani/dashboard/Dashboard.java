package com.app.app4g.features.petani.dashboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.app4g.R;
import com.app.app4g.Utils.GsonHelper;
import com.app.app4g.features.petani.KartuPetani;
import com.app.app4g.features.petani.jatah.ListDataPupuk;
import com.app.app4g.features.e_commerce.EcommerceActivity;
import com.app.app4g.features.petani.profile.createaset.CreateAset;
import com.app.app4g.features.users.login.model.LoginResponse;
import com.app.app4g.server.App;
import com.app.app4g.session.Prefs;
import com.app.app4g.ui.DrawerHeader;
import com.app.app4g.ui.DrawerMenuItem;
import com.app.app4g.features.webview.InfoBeasiswa;
import com.app.app4g.features.webview.InfoKur;
import com.app.app4g.features.webview.PortalInformasi;
import com.app.app4g.ui.SweetDialogs;
import com.google.gson.Gson;
import com.mindorks.placeholderview.PlaceHolderView;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Dashboard extends Fragment implements IDashboardView {


    private int[] mImages = new int[]{R.drawable.slide_1, R.drawable.slide_2, R.drawable.slide_3, R.drawable.slide_4};
    // private int[] mImages = new int[]{getR.drawable.slide_1, R.drawable.slide_2, R.drawable.slide_3, R.drawable.slide_4};
    CarouselView carouselView;
    @BindView(R.id.list_viewpager)
    ViewPager mListViewPager;
    @BindView(R.id.drawerView)
    PlaceHolderView mDrawerView;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawer;
    @BindView(R.id.mainMenuDashboard)
    ImageButton mainMenuDashboard;
    //    @BindView(R.id.mIcon)
//    ImageView mIcon;
    @BindView(R.id.mCardInfoRek)
    CardView mCardInfoRek;
    @BindView(R.id.cardPasarTani)
    CardView cardRdkk;
    @BindView(R.id.mCardTransaksi)
    CardView mCardTransaksi;
    DashboardPresenter presenter ;
    SweetAlertDialog sweetAlertDialog;
    private LoginResponse mProfile;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    int asetPetani ;
    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_dashboard, container, false);
        ButterKnife.bind(this, view);
        presenter = new DashboardPresenter(this);
        this.setupDrawer();
        this.initViews();
        carouselView = view.findViewById(R.id.carousel);
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);
//                imageView.setImageDrawable(VectorDrawableCompat.create(getResources(), R.drawable.slide_1, null));
//                imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), mImages));
            }
        });

        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        if(mProfile.getResult().getProfile().getAsetPetani() != null)
            asetPetani  = mProfile.getResult().getProfile().getAsetPetani().size();
        else
            asetPetani = 0 ;
        Log.d("Result", new Gson().toJson(mProfile.getResult().getProfile()));
        return view;
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
        //setContentView(R.layout.activity_dashboard);
    }

    @OnClick(R.id.mCardTransaksi)
    void tambah_saldo() {
        Toast.makeText(getActivity(), "Maaf menu ini belum tersedia !", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(getActivity(), TransaksiActivity.class));
//        getActivity().finish();
    }

    @OnClick(R.id.mCardInfoRek)
    void info_rek() {
        Toast.makeText(getActivity(), "Maaf menu ini belum tersedia !", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.cardPasarTani)
    void goToRdkk() {
//        startActivity(new Intent(getActivity(), PupukSubsidiActivity.class) );
//        getActivity().finish();
        Toast.makeText(getActivity(), "Maaf menu ini belum tersedia !", Toast.LENGTH_SHORT).show();
    }

//    @OnClick(R.id.mIcon)
//    void icon(){
////        Intent i = new Intent(getActivity(), CreateProfile.class);
////        startActivity(i);
////        getActivity().finish();
//    }

    @OnClick(R.id.cardPupuk)
    void infoPupuk() {
        Intent i = new Intent(getActivity(), ListDataPupuk.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.mCardRut)
    void goToRut() {

//        if(asetPetani <= 0){
//            SweetDialogs.commonWarningWithIntent(getActivity(), "Data anda belum lengkap","mohon untuk mengisi data aset anda terlebih dahulu , Silahkan tekan ok untuk mengisi data", string -> {
//               goToCreateAset();
//            });
//
//        }else{
//            Intent i = new Intent(getActivity(), RutActivity.class);
//            startActivity(i);
//            getActivity().finish();
//        }

        this.goToCreateAset();

    }

    void goToCreateAset(){
        Intent i = new Intent(getActivity(), CreateAset.class);
        startActivity(i);
        getActivity().finish();
    }

    //OnClick function
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

    @OnClick(R.id.cardKatam)
    void katam() {
//        Intent i = new Intent(getActivity(), BiayaTanam.class);
//        startActivity(i);
//        getActivity().finish();
        Toast.makeText(getActivity(), "Maaf menu ini belum tersedia !", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.cardECommerce)
    void eCommerce() {
//        Toast.makeText(getActivity(), "Maaf menu ini belum tersedia !", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getActivity(), EcommerceActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.cardKartuPetani)
    void kartuPetani() {

        Intent i = new Intent(getActivity(), KartuPetani.class);
        startActivity(i);
        getActivity().finish();
    }

    private void setupDrawer() {
        mDrawerView
                .addView(new DrawerHeader(getActivity()))
//                .addView(new DrawerMenuItem(getActivity(), DrawerMenuItem.DRAWER_MENU_ITEM_PROFILE))
                .addView(new DrawerMenuItem(getActivity(), DrawerMenuItem.DRAWER_MENU_ITEM_KOLABORATOR))
//                .addView(new DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_CONTACTUS))
                .addView(new DrawerMenuItem(getActivity(), DrawerMenuItem.DRAWER_MENU_ITEM_LOGOUT));


        mainMenuDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mDrawer.isDrawerOpen(Gravity.END)) mDrawer.openDrawer(Gravity.END);
                else mDrawer.closeDrawer(Gravity.START);
            }
        });
    }
}
