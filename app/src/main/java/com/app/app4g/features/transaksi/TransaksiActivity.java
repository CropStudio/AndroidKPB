package com.app.app4g.features.transaksi;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.app.app4g.NewMessageNotification;
import com.app.app4g.R;
import com.app.app4g.Utils.GsonHelper;
import com.app.app4g.features.petani.MenuUtama;
import com.app.app4g.features.rut.RutAdapter;
import com.app.app4g.features.rut.model.BiayaTanam;
import com.app.app4g.features.rut.model.JadwalUsahaTani;
import com.app.app4g.features.rut.model.KebutuhanSaprotan;
import com.app.app4g.features.rut.model.Result;
import com.app.app4g.features.transaksi.fragment.FragmentTransaksiList;
import com.app.app4g.features.transaksi.fragment.TransaksiListAdapter;
import com.app.app4g.features.transaksi.model.DetailTransaksi;
import com.app.app4g.features.transaksi.model.Transaksi;
import com.app.app4g.features.users.login.model.LoginResponse;
import com.app.app4g.server.App;
import com.app.app4g.server.rmq.ReceiveRmq;
import com.app.app4g.server.rmq.SendRmq;
import com.app.app4g.session.Prefs;
import com.app.app4g.ui.AnimationHelper;
import com.app.app4g.ui.CustomDrawable;
import com.app.app4g.ui.SweetDialogs;
import com.google.gson.Gson;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransaksiActivity extends AppCompatActivity implements ITransaksiView ,TransaksiAdapter.onTransaksitSelected {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.list_viewpager)
    ViewPager mListViewPager;
    @BindView(R.id.collapse_image)
    ImageView mCollapseImage;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.DetailListLayout)
    LinearLayout DetailListLayout;
    NewMessageNotification notif;
    ReceiveRmq receiveRmq;
    SendRmq sendRmq;
    public static String[] stringArray = {"String1"};
    SweetAlertDialog sweetAlertDialog;
    TransaksiPresenter presenter ;
    String nik , token ;
    public TransaksiAdapter adapter;
    List<FragmentTransaksiList> mTransaksiFragments = new ArrayList<>();
    Animation slideUp, slideDown;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Transaksi");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.initView();
        LoginResponse mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        token = (mProfile.getResult().getToken().contains(" "))
                ? mProfile.getResult().getToken() : mProfile.getResult().getToken();
        presenter = new TransaksiPresenter(this);
        presenter.getTransaksi(nik , token);

    }

    @Override
    public void initView() {
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.clearFocus();
        mCollapseImage.setOnClickListener(view -> this.hideDetailList());
    }

    @Override
    public void clearLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            activity.getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onDataReady(List<Transaksi> result) {
        System.out.println(new Gson().toJson(result));
        adapter = new TransaksiAdapter(result, this, this);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

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
    public void onNetworkError(String cause) {
        Log.d("Error", cause);
        SweetDialogs.endpointError(this);
    }

//    @Override
//    public void initDetailList() {
////        mCollapseImage.setOnClickListener(this);
//        final TransaksiPageAdapter adapter = new TransaksiPageAdapter(getSupportFragmentManager());
//        for (int i = 0; i < mTitles.size(); i++) {
//            FragmentTrackerList fragmentTracker = new FragmentTrackerList();
//            List<Result> models = new ArrayList<>();
//            for (int j = 0; j < trackers.size(); j++) {
//                String trackerName = trackers.get(j).getName().split("\\|")[0];
//                if (mTitles.get(i).equals(trackerName)) {
//                    models.add(trackers.get(j));
//                }
//            }
//            fragmentTracker.setData(models, markerColors.get(i), myLocation);
//            adapter.addFragment(fragmentTracker, mTitles.get(i));
//            mTrackerFragments.add(fragmentTracker);
//        }
//        mListViewPager.setAdapter(adapter);
//        mListTabs.setupWithViewPager(mListViewPager);
//
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showNotif() {
        String NOTIFICATION_CHANNEL_ID = "channel_androidnotif";
        Context context = this.getApplicationContext();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String channelName = "Android Notif Channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel mChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        Intent mIntent = new Intent(this, TransaksiActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("fromnotif", "notif");
        mIntent.putExtras(bundle);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.iconkpb)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.iconkpb))
                .setTicker("notif starting")
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setLights(Color.RED, 3000, 3000)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentTitle("Notifikasi KPB")
                .setContentText("by imamfarisiwww.com");
        Random random = new Random();
//        int m = random.nextInt(9999 - 1000) + 1000;
        int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(m, builder.build());
    }

    @Override
    public void showList(List<DetailTransaksi> result) {
        final TransaksiPageAdapter adapter = new TransaksiPageAdapter(getSupportFragmentManager());
        FragmentTransaksiList fragmentTracker = new FragmentTransaksiList();
        fragmentTracker.setData(result);
        adapter.addFragment(fragmentTracker);
        mTransaksiFragments.add(fragmentTracker);
        mListViewPager.setAdapter(adapter);
        if (slideUp == null) {
            slideUp = AnimationHelper.getAnimation(this, R.anim.slide_up, anim -> {
                mRecyclerView.setVisibility(View.GONE);
                DetailListLayout.setVisibility(View.VISIBLE);
                mCollapseImage.setVisibility(View.VISIBLE);
            });
        }
        mCollapseImage.setImageDrawable(CustomDrawable.googleMaterialDrawable(
                this, R.color.color_default_green, 12, GoogleMaterial.Icon.gmd_keyboard_arrow_down
        ));

        DetailListLayout.startAnimation(slideUp);

    }

    @Override
    public void hideDetailList() {
        if (slideDown == null) {
            slideDown = AnimationHelper.getAnimation(this, R.anim.slide_down, anim -> {
//                DetailListLayout.setVisibility(View.GONE);
//                mRecyclerView.setVisibility(View.VISIBLE);
                mCollapseImage.setVisibility(View.GONE);
                this.refresh();
            });
        }
        DetailListLayout.startAnimation(slideDown);

    }

    @Override
    public void onBackPressed() {
        // ...
        if(DetailListLayout.getVisibility() == View.VISIBLE)
            hideDetailList();
        else {
            this.goToDashboard();
            super.onBackPressed();
        }
    }


    @Override
    public void goToDashboard() {
        Intent i = new Intent(this, MenuUtama.class);
        startActivity(i);
        finish();
    }

    @Override
    public void refresh() {
        Intent i = new Intent(this, TransaksiActivity.class);
        startActivity(i);
        finish();
    }
}
