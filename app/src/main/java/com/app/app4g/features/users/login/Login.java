package com.app.app4g.features.users.login;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.request.RequestOptions;
import com.app.app4g.R;
import com.app.app4g.features.gubernur.Dashboard_Gubernur;
import com.app.app4g.features.petani.MenuUtama;
import com.app.app4g.features.petani.registrasi.RegistPetaniActivity;
import com.app.app4g.features.users.login.model.LoginResponse;
import com.app.app4g.server.App;
import com.app.app4g.session.Prefs;
import com.app.app4g.features.users.registrasi.Regist;
import com.app.app4g.ui.SweetDialogs;
import com.glide.slider.library.SliderTypes.BaseSliderView;
import com.glide.slider.library.SliderTypes.TextSliderView;
import com.glide.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Login extends AppCompatActivity implements BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener, ILoginView {

    boolean doubleBackToExitPressedOnce = false;

    @BindView(R.id.nik)
    EditText edNik;
    @BindView(R.id.password)
    EditText edPassword;

    @BindView(R.id.mBtnRegisPetani)
    Button signup;
    @BindView(R.id.signin)
    Button signin;

    ImageButton closePopup;
    @BindView(R.id.popupKeuntungan)
    TextView keuntungan;
    @BindView(R.id.popupSyarat)
    TextView syarat;

    Dialog dialog;

    com.app.app4g.features.users.login.LoginPresenter presenter;
    SweetAlertDialog sweetAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        if (presenter.isLoggedIn()) {
            if (App.getPref().getString(Prefs.PREF_ROLE, "").equals("petani")) {
                this.goToDashboardPetani();
            }
            else if (App.getPref().getString(Prefs.PREF_ROLE, "").equals("gubernur")) {
                this.goToDashboardGubernur();
            }
        } else {
            this.initViews();
        }

        dialog = new Dialog(this);
        keuntungan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showpoupkeuntungan();
            }
        });

        syarat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showpoupsyarat();
            }
        });

    }

    @Override
    public void initViews() {
        signup.setText((Html.fromHtml("Belum punya akun ? <b><font fgcolor='#3EB2FF'> Daftar Sekarang ! </font></b>")));
        ArrayList<String> listUrl = new ArrayList<>();
        ArrayList<String> listName = new ArrayList<>();

        RequestOptions requestOptions = new RequestOptions();
        for (int i = 0; i < listUrl.size(); i++) {
            TextSliderView sliderView = new TextSliderView(this);
            sliderView
                    .image(listUrl.get(i))
                    .description(listName.get(i))
                    .setRequestOption(requestOptions)
                    .setProgressBarVisible(true)
                    .setOnSliderClickListener(this);
            sliderView.bundle(new Bundle());
            sliderView.getBundle().putString("extra", "");
        }

        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Loading ...");

    }

    public void gotoRegisPetani(){
        startActivity(new Intent(Login.this, RegistPetaniActivity.class));
        Animatoo.animateSlideUp(Login.this);
    }

    @OnClick(R.id.mBtnRegisPetani)
    void signUp() {
        startActivity(new Intent(Login.this, Regist.class));
        Animatoo.animateSlideUp(Login.this);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSigninSuccess(LoginResponse response) {
        presenter.storeProfile(new Gson().toJson(response));
        presenter.storeAccessToken(response.getResult().getToken());
        App.getPref().put(Prefs.PREF_FIRST_TIME, true);
        App.getPref().put(Prefs.PREF_ROLE, response.getResult().getRole());
        if (presenter.isLoggedIn()) {
            if (response.getResult().getRole().equals("petani")) {
                presenter.storeNoKK(response.getResult().getProfile().getNoKk());
                presenter.storeNoRek(response.getResult().getNomorRekening());
                this.goToDashboardPetani();
            } else if (response.getResult().getRole().equals("gubernur")) {
                this.goToDashboardGubernur();
            }
        }
    }

    @Override
    public void onSigninFailed(String rm) {
        Toast.makeText(this, rm, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNetworkError(String cause) {
        Log.e("errornya", cause);
        SweetDialogs.endpointError(this);
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
    public void goToDashboardPetani() {
//        final LoadingDialog loadingDialog = new LoadingDialog(Login.this);
//        loadingDialog.startLoadingDialog();
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                loadingDialog.dismissDialog();
//                startActivity(new Intent(Login.this, MenuUtama.class));
//                Animatoo.animateZoom(Login.this);
//            }
//        }, 3000);

        startActivity(new Intent(Login.this, MenuUtama.class));
        Animatoo.animateZoom(Login.this);
    }

    @Override
    public void goToDashboardGubernur() {
        startActivity(new Intent(this, Dashboard_Gubernur.class));
        finish();
    }

    @OnClick(R.id.signin)
    void login() {
        String nik = edNik.getText().toString();
        String pass = edPassword.getText().toString();
        if (nik.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Nik tidak boleh kosong", Toast.LENGTH_LONG).show();
        } else if (pass.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Password tidak boleh kosong", Toast.LENGTH_LONG).show();
        } else {
            presenter.login(nik, pass);
        }
    }

    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private void showpoupkeuntungan() {
        dialog.setContentView(R.layout.popup_keuntungan);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        closePopup = dialog.findViewById(R.id.closePopKeuntungan);
        closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void showpoupsyarat() {
        dialog.setContentView(R.layout.popup_syarat);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        closePopup = dialog.findViewById(R.id.closePopKeuntungan);
        closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan sekali lagi untuk keluar aplikasi", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

}
