package com.example.app4g.features.users.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.example.app4g.R;
import com.example.app4g.features.petani.MenuUtama;
import com.example.app4g.features.users.login.model.LoginResponse;
import com.example.app4g.server.App;
import com.example.app4g.session.Prefs;
import com.example.app4g.features.users.registrasi.Regist;
import com.example.app4g.ui.SweetDialogs;
import com.glide.slider.library.Animations.DescriptionAnimation;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderTypes.BaseSliderView;
import com.glide.slider.library.SliderTypes.TextSliderView;
import com.glide.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Login extends AppCompatActivity implements BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener, ILoginView {

    @BindView(R.id.slider)
    SliderLayout mDemoSlider;

    @BindView(R.id.nik)
    EditText edNik;
    @BindView(R.id.password)
    EditText edPassword;

    com.example.app4g.features.users.login.LoginPresenter presenter;
    SweetAlertDialog sweetAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);
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

    }

    @Override
    public void initViews() {
        ArrayList<String> listUrl = new ArrayList<>();
        ArrayList<String> listName = new ArrayList<>();

        listUrl.add("http://kartupetaniberjaya.com/wp-content/uploads/2019/07/fffghgf.jpg");
        listName.add("Pemodalan Usaha Tani");

        listUrl.add("http://kartupetaniberjaya.com/wp-content/uploads/2019/07/cropped-PicsArt_03-29-09.36.17-1.jpg");
        listName.add("Kepastian Pupuk");

        listUrl.add("http://kartupetaniberjaya.com/wp-content/uploads/2019/07/044465100_1539706125-PADI_ORGANIK-Muhamad_Ridlo.jpg");
        listName.add("Kemudahan Benih");

        listUrl.add("http://kartupetaniberjaya.com/wp-content/uploads/2019/07/cropped-Marketplace-Pertanian-01-Petani-Indonesia-Finansialku-1.jpg");
        listName.add("Kesejahteraan Petani");

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
            mDemoSlider.addSlider(sliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);

        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Loading ...");
    }

    @OnClick(R.id.signup)
    void signUp() {
        Intent a = new Intent(Login.this, Regist.class);
        startActivity(a);
        finish();
    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
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
    public void onBackPressed() {
        System.exit(0);
    }


    @Override
    public void onSigninSuccess(LoginResponse response) {
        presenter.storeProfile(new Gson().toJson(response));
        presenter.storeAccessToken(response.getResult().getToken());
        App.getPref().put(Prefs.PREF_ROLE, response.getResult().getRole());
        if (presenter.isLoggedIn()) {
            if (response.getResult().getRole().equals("petani")) {
                this.goToDashboardPetani();
            } else if (response.getResult().getRole().equals("gubernur")) {
                this.goToDashboardGubernur();
            }
        }
    }

    @Override
    public void onSigninFailed(String rm) {

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
        startActivity(new Intent(this, MenuUtama.class));
        finish();
    }

    @Override
    public void goToDashboardGubernur() {
//        startActivity(new Intent(this, TrackerActivity.class));
//        finish();
        Toast.makeText(this, "Gubernur", Toast.LENGTH_SHORT).show();
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

}
