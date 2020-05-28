package com.app.app4g.features.users.reset_password;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.app4g.R;
import com.app.app4g.Utils.GsonHelper;
import com.app.app4g.features.petani.MenuUtama;
import com.app.app4g.features.rut.aset.AsetAdapter;
import com.app.app4g.features.rut.aset.AsetPresenter;
import com.app.app4g.features.users.login.Login;
import com.app.app4g.features.users.login.model.LoginResponse;
import com.app.app4g.server.App;
import com.app.app4g.session.Prefs;
import com.app.app4g.ui.SweetDialogs;
import com.app.app4g.ui.TopSnakbar;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResetPasswordActivity extends AppCompatActivity implements IResetPasswordView{
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    @BindView(R.id.mSubmit)
    Button mSubmit;
    @BindView(R.id.mPassword)
    EditText mPassword;
    @BindView(R.id.mRePassword)
    EditText mRePassword;
    ResetPasswordPresenter presenter ;
    SweetAlertDialog sweetAlertDialog;
    LoginResponse mProfile;
    String nik , token ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        presenter  = new ResetPasswordPresenter(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Ubah Sandi");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        this.initViews();

    }

    @Override
    public void initViews() {
        mSubmit.setOnClickListener(view -> this.onResetPassword());
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);

        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        token = (mProfile.getResult().getToken().contains(" "))
                ? mProfile.getResult().getToken() : mProfile.getResult().getToken();

    }

    @Override
    public void onResetPassword(){
        String password = mPassword.getText().toString() ;
        String rePassword = mRePassword.getText().toString() ;
//        System.out.println(password.length());
        if(!password.equals("")) {
            if(password.length() > 5) {
                if (!rePassword.equals(password))
                    TopSnakbar.showWarning(this, "Kata sandi tidak sesuai !");
                else
                    presenter.onResetPassword(nik, token, password);
            }else
                TopSnakbar.showWarning(this, "Kata sandi minimal 6 karakter");
        }else
            TopSnakbar.showWarning(this, "Kata sandi tidak boleh kosong !");

    }

    @Override
    public void onBackPressed() {
        // ...
        this.goToDashboard();
        super.onBackPressed();
    }

    @Override
    public void onResetSuccess(LoginResponse response){
        SweetDialogs.commonSuccessWithIntent(this , "untuk keamanan data silahkan anda masuk kembali ",string -> this.onLogout());
    }

    @Override
    public void onLogout(){
        App.getPref().clear();
        startActivity(new Intent(this , Login.class));
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.goToDashboard();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void goToDashboard() {
        Intent i = new Intent(this, MenuUtama.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onNetworkError(String cause) {
        Log.d("Error", cause);
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
}
