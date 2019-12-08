package com.example.app4g.features.petani;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.support.v7.widget.VectorEnabledTintResources;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.app4g.R;
import com.example.app4g.features.petani.jatah.ListDataPupuk;
import com.example.app4g.features.e_commerce.EcommerceActivity;
import com.example.app4g.features.rut.RutActivity;
import com.example.app4g.ui.DrawerHeader;
import com.example.app4g.ui.DrawerMenuItem;
import com.example.app4g.features.webview.InfoBeasiswa;
import com.example.app4g.features.webview.InfoKur;
import com.example.app4g.features.webview.PortalInformasi;
import com.mindorks.placeholderview.PlaceHolderView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Dashboard extends Fragment  {


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
    @BindView(R.id.mCardTambahSaldo)
    CardView mCardTambahSaldo;
    static
    {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_dashboard, container, false);
        ButterKnife.bind(this, view);

            VectorEnabledTintResources.shouldBeUsed();
        this.setupDrawer();
        carouselView = view.findViewById(R.id.carousel);
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
//                imageView.setImageResource(getActivity(),mImages[position]);
//                imageView.setImageDrawable(VectorDrawableCompat.create(getResources(), R.drawable.slide_1, null));
                imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.slide_1));
            }
        });
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_dashboard);
    }

    @OnClick(R.id.mCardTambahSaldo)
    void tambah_saldo(){
        Toast.makeText(getActivity(), "Maaf menu ini belum tersedia !", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.mCardInfoRek)
    void info_rek(){
        Toast.makeText(getActivity(), "Maaf menu ini belum tersedia !", Toast.LENGTH_SHORT).show();
    }

//    @OnClick(R.id.mIcon)
//    void icon(){
////        Intent i = new Intent(getActivity(), CreateProfile.class);
////        startActivity(i);
////        getActivity().finish();
//    }

    @OnClick(R.id.cardPupuk)
    void infoPupuk(){
        Intent i = new Intent(getActivity(), ListDataPupuk.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.mCardRut)
    void goToRut(){
        Intent i = new Intent(getActivity(), RutActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    //OnClick function
    @OnClick(R.id.cardInfoBeasiswa)
    void infoBea(){
        Intent i = new Intent(getActivity(), InfoBeasiswa.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.cardInfoKur)
    void infoKur(){
        Intent i = new Intent(getActivity(), InfoKur.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.cardPortalInformasi)
    void portalInformasi(){
        Intent i = new Intent(getActivity(), PortalInformasi.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.cardKatam)
    void katam(){
//        Intent i = new Intent(getActivity(), KalenderTanam.class);
//        startActivity(i);
//        getActivity().finish();
        Toast.makeText(getActivity(), "Maaf menu ini belum tersedia !", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.cardECommerce)
    void eCommerce(){
        Intent i = new Intent(getActivity(), EcommerceActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.cardKartuPetani)
    void kartuPetani(){
        Intent i = new Intent(getActivity(), KartuPetani.class);
        startActivity(i);
        getActivity().finish();
    }

    private void setupDrawer() {
        mDrawerView
                .addView(new DrawerHeader(getActivity()))
                .addView(new DrawerMenuItem(getActivity(), DrawerMenuItem.DRAWER_MENU_ITEM_PROFILE))
                .addView(new DrawerMenuItem(getActivity(), DrawerMenuItem.DRAWER_MENU_ITEM_KOLABORATOR))
//                .addView(new DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_CONTACTUS))
                .addView(new DrawerMenuItem(getActivity(), DrawerMenuItem.DRAWER_MENU_ITEM_LOGOUT));


        mainMenuDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mDrawer.isDrawerOpen(Gravity.END)) mDrawer.openDrawer(Gravity.END);
                else mDrawer.closeDrawer(Gravity.START);
            }
        });
    }
}
