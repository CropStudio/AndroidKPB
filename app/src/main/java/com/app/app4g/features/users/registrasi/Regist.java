package com.app.app4g.features.users.registrasi;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;

import androidx.appcompat.app.AlertDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.app.app4g.ui.SweetDialogs;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.app.app4g.features.petani.registrasi.RegistPetaniActivity;
import com.app.app4g.server.App;
import com.app.app4g.server.Config_URL;
import com.app.app4g.R;

import com.app.app4g.features.users.login.Login;
import com.app.app4g.features.users.registrasi.presenter.IRegisterPresenter;
import com.app.app4g.features.users.registrasi.presenter.RegisterPresenter;
import com.app.app4g.features.users.registrasi.view.IRegisterView;
import com.app.app4g.ui.TopSnakbar;
import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moe.feng.common.stepperview.VerticalStepperItemView;


public class Regist extends AppCompatActivity implements IRegisterView, View.OnClickListener {

//    private VerticalStepperItemView[] mSteppers = new VerticalStepperItemView[3];
    private Button mNextBtn0, mNextBtn1, mPrevBtn1, mNextBtn2, mPrevBtn2;

    @BindView(R.id.edNik)
    EditText edNik;
    @BindView(R.id.edPass)
    EditText edPass;
    @BindView(R.id.edRePass)
    TextView edRepass;
    @BindView(R.id.btnRegistrasi)
    Button btnRegistrasi;
    @BindView(R.id.txtNama)
    TextView txtNama;
    @BindView(R.id.txtAlamat)
    TextView txtAlamat;
    @BindView(R.id.txtJenisKelamin)
    TextView txtJenisKelmain;
    @BindView(R.id.imgView)
    ImageView imgView;
    @BindView(R.id.dataPetani)
    LinearLayout dataPetani;
    @BindView(R.id.buttonHide)
    LinearLayout btnRegistrasiLayout;
    @BindView(R.id.passwordHide)
    LinearLayout PasswordLayout;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    RetryPolicy policy = new DefaultRetryPolicy(5000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    private final int GALLERY_KK = 1;
    private final int CameraR_KK = 111;
    private final int GALLERY_KTP = 11;
    private final int CameraR_KTP = 1111;

    Dialog myDialog;
    String mCurrentPhotoPath;
    IRegisterPresenter iRegisterPresenter;
    Bitmap bitmapKk, bitmapktp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        iRegisterPresenter = new RegisterPresenter(this, this);
        iRegisterPresenter.setProgressBarVisiblity(View.GONE);

        myDialog = new Dialog(Regist.this);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Regist.this, Login.class));
        Animatoo.animateSlideDown(Regist.this);
    }

    private void showDateDialog() {
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @OnClick(R.id.btnCari)
    void cariPetani() {
        String nikPetani = edNik.getText().toString();
        if (nikPetani.isEmpty()) {
            TopSnakbar.showWarning(this, "NIK TIDAK BOLEH KOSONG");
        } else {
            cekPetani(nikPetani);
        }
    }

    public void cekPetani(String nikPetani) {
        String tag_string_req = "req_login";
        StringRequest strReq = new StringRequest(Request.Method.GET,
                Config_URL.cekPetani + nikPetani, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    final JSONObject jObj = new JSONObject(response);
                    final boolean status = jObj.getBoolean("status");
                    if (status) {
                        String msg = jObj.getString("message");
                        String res = jObj.getString("result");
                        String nama = jObj.getJSONObject("result").getString("nama");
                        String alamat = jObj.getJSONObject("result").getString("address");
                        String poktan = jObj.getJSONObject("result").getJSONObject("poktan").getString("name");
                        imgView.setVisibility(View.GONE);
                        btnRegistrasiLayout.setVisibility(View.VISIBLE);
                        dataPetani.setVisibility(View.VISIBLE);
                        PasswordLayout.setVisibility(View.VISIBLE);
                        txtNama.setText(nama);
                        txtAlamat.setText(alamat);
                    } else {
//                        imgView.setVisibility(View.VISIBLE);
//                        dataPetani.setVisibility(View.GONE);
//                        PasswordLayout.setVisibility(View.GONE);
//                        btnRegistrasiLayout.setVisibility(View.GONE);
//                        txtNama.setText(null);
                        SweetDialogs.commonWarningWithIntent(Regist.this, "Nik Anda tidak terdaftar di Rdkk"  , "silahkan registrasi ", string -> {
                            goToDaftar();
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("msg", "Login Error : " + error.getMessage());
                error.printStackTrace();
                TopSnakbar.showWarning(Regist.this, "UPS !!! SERVER TIDAK MERESPON");
            }
        });
        strReq.setRetryPolicy(policy);
        App.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public void goToDaftar() {
        startActivity(new Intent(this, RegistPetaniActivity.class));
        finish();
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public void addPermission() {
        Dexter.withActivity(Regist.this)
                .withPermissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                        }
                        if (report.isAnyPermissionPermanentlyDenied()) {
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(Regist.this, "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    @OnClick(R.id.btnRegistrasi)
    void regis() {
        String nik = edNik.getText().toString();
        String nama = txtNama.getText().toString();
        String poktan = txtNama.getText().toString();
        String pass = edPass.getText().toString();
        String repass = edRepass.getText().toString();
        if (nik.isEmpty()) {
            TopSnakbar.showWarning(this, "NIK TIDAK BOLEH KOSONG");
        } else if (nama.isEmpty()) {
            TopSnakbar.showWarning(this, "TIDAK ADA DATA");
        } else if (poktan.isEmpty()) {
            TopSnakbar.showWarning(this, "TIDAK ADA DATA");
        } else if (!nik.equals(nik)) {
            TopSnakbar.showWarning(this, "NIK TIDAK SESUAI");
        } else if (pass.isEmpty()) {
            TopSnakbar.showWarning(this, "PASSWORD TIDAK BOLEH KOSONG");
        } else if (repass.isEmpty()) {
            TopSnakbar.showWarning(this, "ULANGI PASSWORD");
        } else if (!pass.equals(repass)) {
            TopSnakbar.showWarning(this, "PASSWORD TIDAK SESUAI");
        } else {
            iRegisterPresenter.setProgressBarVisiblity(View.VISIBLE);
            iRegisterPresenter.doRegistrasi(nik, nama , "petani", repass);
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onClearText() {

    }

    @Override
    public void onRegisterResult(Boolean result, String msg) {
        iRegisterPresenter.setProgressBarVisiblity(View.GONE);
        new AlertDialog.Builder(this)
                .setTitle("SYARAT DAN KETENTUAN")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (result) {
                            Intent a = new Intent(Regist.this, Login.class);
                            startActivity(a);
                            finish();
                            Toast.makeText(getApplicationContext(), "Berhasil Melakukan Registrasi , Silahkan Login ", Toast.LENGTH_LONG).show();
                        } else {
                            TopSnakbar.showWarning(Regist.this, msg);
                        }
                    }
                })
                .setNegativeButton("No", null)
                .setMessage(Html.fromHtml(String.format(App.getApplication().getString(R.string.termCondition))))
                .show();

    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {

    }
}
