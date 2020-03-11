package com.example.app4g.features.users.registrasi;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
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
import com.example.app4g.features.petani.registrasi.RegistPetaniActivity;
import com.example.app4g.server.App;
import com.example.app4g.server.Config_URL;
import com.example.app4g.R;

import com.example.app4g.features.users.login.Login;
import com.example.app4g.features.users.registrasi.presenter.IRegisterPresenter;
import com.example.app4g.features.users.registrasi.presenter.RegisterPresenter;
import com.example.app4g.features.users.registrasi.view.IRegisterView;
import com.example.app4g.ui.TopSnakbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

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

    private VerticalStepperItemView[] mSteppers = new VerticalStepperItemView[3];
    private Button mNextBtn0, mNextBtn1, mPrevBtn1, mNextBtn2, mPrevBtn2;

    @BindView(R.id.edNik)
    EditText edNik;
    @BindView(R.id.edTlp)
    EditText edTlp;
    @BindView(R.id.edPass)
    EditText edPass;
    @BindView(R.id.edRePass)
    TextView edRepass;

    @BindView(R.id.btnRegistrasi)
    Button btnRegistrasi;

    @BindView(R.id.txtDaftar)
    TextView txtDaftar;
    @BindView(R.id.LayoutDaftar)
    CardView LayoutDaftar;

    @BindView(R.id.txtPoktan)
    TextView txtPoktan;
    @BindView(R.id.txtNama)
    TextView txtNama;
    @BindView(R.id.txtAlamat)
    TextView txtAlamat;
    @BindView(R.id.txtJenisKelamin)
    TextView txtJenisKelmain;

    @BindView(R.id.dataPetani)
    LinearLayout dataPetani;

    @BindView(R.id.MySignUp)
    CoordinatorLayout layout;

    private int[] mImages = new int[]{R.drawable.slide_login_1, R.drawable.slide_login_2, R.drawable.slide_login_3};
    CarouselView carouselView;

//    @BindView(R.id.titik_1)
//    TextView titik1;
//    @BindView(R.id.titik_2)
//    TextView titik2;
//    @BindView(R.id.titik_3)
//    TextView titik3;
//    @BindView(R.id.titik_4)
//    TextView titik4;


    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    RetryPolicy policy = new DefaultRetryPolicy(5000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    private final int GALLERY_KK = 1;
    private final int CameraR_KK = 111;
    private final int GALLERY_KTP = 11;
    private final int CameraR_KTP = 1111;

//    @BindView(R.id.imgKtp)
//    ImageView imgKtp;
//    @BindView(R.id.imgKK)
//    ImageView imgKk;

    Dialog myDialog;

    String mCurrentPhotoPath;

    IRegisterPresenter iRegisterPresenter;
    @BindView(R.id.progress_login)
    ProgressBar prgBar;

    Bitmap bitmapKk, bitmapktp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        layout = findViewById(R.id.MySignUp);


//        mSteppers[0] = findViewById(R.id.stepper_0);
//        mSteppers[1] = findViewById(R.id.stepper_1);
//        mSteppers[2] = findViewById(R.id.stepper_2);
//
//        VerticalStepperItemView.bindSteppers(mSteppers);
//
//        mNextBtn0 = findViewById(R.id.button_next_0);
//        mPrevBtn1 = findViewById(R.id.button_prev_1);
//        mPrevBtn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mSteppers[1].prevStep();
//            }
//        });
//
//        mNextBtn1 = findViewById(R.id.button_next_1);
//        mNextBtn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mSteppers[1].nextStep();
//            }
//        });
//
//        mPrevBtn2 = findViewById(R.id.button_prev_2);
//        mPrevBtn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mSteppers[2].prevStep();
//            }
//        });

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

//        mNextBtn0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String nik      = edNik.getText().toString();
//                String nama     = txtNama.getText().toString();
//                String poktan   = txtNama.getText().toString();
//                String telp     = edTlp.getText().toString();
//                String pass     = edPass.getText().toString();
//                String repass   = edRepass.getText().toString();
//                if(nik.isEmpty()){
//                    snacBars("Nik tidak boleh kosong");
//                }else if(nama.isEmpty()){
//                    snacBars("Tidak ada data");
//                }else if(poktan.isEmpty()){
//                    snacBars("Tidak ada data");
//                }else if(telp.isEmpty()){
//                    snacBars("Nomor telp tidak boleh kosong");
//                }else if(pass.isEmpty()){
//                    snacBars("Password tidak boleh kosong");
//                }else if(repass.isEmpty()){
//                    snacBars("Ulangi password");
//                }else if(!pass.equals(repass)){
//                    snacBars("Password tidak sesuai");
//                }else {
//                    mSteppers[0].nextStep();
//                }
//            }
//        });

        carouselView = findViewById(R.id.carousel);
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Regist.this, Login.class);
        startActivity(a);
        finish();
    }

//    @OnClick(R.id.tglan)
//    void tglan(){
//        showDateDialog();
//    }

    private void showDateDialog() {
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                //edTanggal.setText(dateFormatter.format(newDate.getTime()));
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
//                Log.d("msg", "Data petani: " + CommonRespon.toString());

                try {
                    final JSONObject jObj = new JSONObject(response);
                    final boolean status = jObj.getBoolean("status");
                    if (status) {
                        String msg = jObj.getString("message");
                        String res = jObj.getString("result");
                        String nama = jObj.getJSONObject("result").getString("nama");
                        String alamat = jObj.getJSONObject("result").getString("address");
                        String poktan = jObj.getJSONObject("result").getJSONObject("poktan").getString("name");
                        btnRegistrasi.setVisibility(View.VISIBLE);
                        txtNama.setText(" " + nama);
                        txtPoktan.setText(" " + poktan);
                        txtAlamat.setText("Alamat " + alamat);
                        dataPetani.setVisibility(View.VISIBLE);
                        snacBarsGreen(msg);
                    } else {
                        dataPetani.setVisibility(View.GONE);
                        LayoutDaftar.setVisibility(View.VISIBLE);
                        txtDaftar.setText(Html.fromHtml(String.format("Nik anda tidak terdaftar, Silahkan klik  <b><u>tautan ini</u></b>")));
                        txtDaftar.setTextColor(Color.RED);
                        txtDaftar.setOnClickListener(view -> goToDaftar());
                        txtNama.setText(null);
                        txtPoktan.setText(null);
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

//    @OnClick(R.id.uploadKtp)
//    void uploadKtp(){
//        addPermission();
//        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
//            // Create the File where the photo should go
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                // Error occurred while creating the File
//                Log.i("Tags", "IOException");
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
//                startActivityForResult(cameraIntent, CameraR_KTP);
//            }
//        }
//    }
//
//    @OnClick(R.id.uploadKk)
//    void uploadKk(){
//        addPermission();
//
//        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
//            // Create the File where the photo should go
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                // Error occurred while creating the File
//                Log.i("Tags", "IOException");
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
//                startActivityForResult(cameraIntent, CameraR_KK);
//            }
//        }
//    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  // prefix
                ".jpg",         // suffix
                storageDir      // directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Regist.this.RESULT_CANCELED) {
//            return;
//        }
//        if (requestCode == CameraR_KTP) {
//                try {
//
//                     bitmapktp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(mCurrentPhotoPath));
//                    // imageView.setImageBitmap(bitmap);
//                    //getFileDataFromDrawable(bitmap);
//
//                    imgKtp.setVisibility(View.VISIBLE);
//                    imgKtp.setImageBitmap(bitmapktp);
//                    //uploadImage(bitmap);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
//                }
//        }
//
//        if (requestCode == CameraR_KK) {
//                try {
//                     bitmapKk = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(mCurrentPhotoPath));
//                    // imageView.setImageBitmap(bitmap);
//                    //getFileDataFromDrawable(bitmap);
//                    imgKk.setVisibility(View.VISIBLE);
//                    imgKk.setImageBitmap(bitmapKk);
//                    //uploadImage(bitmap);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
//                }
//        }
//    }


    public void snacBars(String text) {
        Snackbar snackbar = Snackbar.make(layout, text, Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        view.setBackgroundColor(layout.getResources().getColor(R.color.red));
        TextView tv = view.findViewById(android.support.design.R.id.snackbar_text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        } else {
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
        }
        snackbar.show();
    }

    public void snacBarsGreen(String text) {
        Snackbar snackbar = Snackbar.make(layout, text, Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        view.setBackgroundColor(layout.getResources().getColor(R.color.bg_screen3));
        TextView tv = view.findViewById(android.support.design.R.id.snackbar_text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        } else {
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
        }
        snackbar.show();
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
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            //Toast.makeText(getActivity(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings

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
        String telp = edTlp.getText().toString();
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
        if (result) {
            Intent a = new Intent(Regist.this, Login.class);
            startActivity(a);
            finish();
            Toast.makeText(getApplicationContext(), "Berhasil Melakukan Registrasi , Silahkan Login ", Toast.LENGTH_LONG).show();
        } else {
            TopSnakbar.showWarning(this, msg);
        }
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        prgBar.setVisibility(visibility);
    }
}
