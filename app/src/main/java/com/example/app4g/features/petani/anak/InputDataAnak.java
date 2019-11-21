package com.example.app4g.features.petani.anak;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.app4g.R;
import com.example.app4g.features.petani.anak.presenter.IAnakPresenter;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InputDataAnak extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @BindView(R.id.edTgl)
    TextView txtTgl;
    @BindView(R.id.edNamaAnak)
    EditText edNamaAnak;

    @BindView(R.id.rg_gender)
    RadioGroup radioGroupGender;
    @BindView(R.id.rb_laki)
    RadioButton radioButtonLaki;
    @BindView(R.id.rb_perempuan)
    RadioButton radioButtonPerempuan;

    IAnakPresenter iAnakPresenter;
    @BindView(R.id.progress_login)
    ProgressBar prgBar;

    @BindView(R.id.linears)
    LinearLayout layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_anak);
        ButterKnife.bind(this);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        iAnakPresenter.setProgressBarVisiblity(View.GONE);
     }

//     @OnClick(R.id.submitAnak)
//     void sumbitAnak(){
//         int selectedId = radioGroupGender.getCheckedRadioButtonId();
//         String namaAnak = edNamaAnak.getText().toString();
//         if(namaAnak.isEmpty()){
//             snacBars("Nama anak dibutuhkan");
//         } else if (selectedId <= 0){
//             snacBars("Jenis kelamin anak dibutuhkan");
//         }else {
//             radioButtonLaki = (RadioButton) radioGroupGender.findViewById(selectedId);
//             String genders = (String) radioButtonLaki.getText().toString();
//             String tglLahir = txtTgl.getText().toString();
//             Log.v("Data input", namaAnak+" "+genders+" "+tglLahir+" "+strId);
//             if(tglLahir.equals("yyyy-MM-dd")){
//                 snacBars("Tanggal lahir anak dibutuhkan");
//             }else {
//                 iAnakPresenter.setProgressBarVisiblity(View.VISIBLE);
//                 iAnakPresenter.doAnak(namaAnak,tglLahir,genders,strId);
//             }
//         }
//
//     }
//
//    @Override
//    public void onBackPressed() {
//        Intent a = new Intent(InputDataAnak.this, ListDataAnak.class);
//        startActivity(a);
//        finish();
//    }
//
//    @OnClick(R.id.tglan)
//    void tglan(){
//        showDateDialog();
//    }
//
//
//    private void showDateDialog(){
//        Calendar newCalendar = Calendar.getInstance();
//        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//
//                Calendar newDate = Calendar.getInstance();
//                newDate.set(year, monthOfYear, dayOfMonth);
//                txtTgl.setText(dateFormatter.format(newDate.getTime()));
//            }
//
//        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//        datePickerDialog.show();
//    }
//
//    @Override
//    public void onClearText() {
//
//    }
//
//    @Override
//    public void onInputResult(Boolean result, String msg) {
//        iAnakPresenter.setProgressBarVisiblity(View.GONE);
//        if (result){
//            snacBarsGreen(msg);
//            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//                    this);
//            alertDialogBuilder.setTitle(msg);
//            alertDialogBuilder
//                    .setMessage("Ingin menambah data anak lagi ?")
//                    .setIcon(R.drawable.lampung_coa)
//                    .setCancelable(false)
//                    .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog,int id) {
//                            Intent a = new Intent(InputDataAnak.this, InputDataAnak.class);
//                            startActivity(a);
//                            finish();
//                        }
//                    })
//                    .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            // jika tombol ini diklik, akan menutup dialog
//                            // dan tidak terjadi apa2
//                            Intent a = new Intent(InputDataAnak.this, ListDataAnak.class);
//                            startActivity(a);
//                            finish();
//                        }
//                    });
//            AlertDialog alertDialog = alertDialogBuilder.create();
//            alertDialog.show();
//        }else {
//            snacBars(msg);
//        }
//    }
//
//    @Override
//    public void onSetProgressBarVisibility(int visibility) {
//        prgBar.setVisibility(visibility);
//    }
//
//    public void snacBars(String text){
//        Snackbar snackbar = Snackbar.make(layout, text, Snackbar.LENGTH_LONG);
//        View view = snackbar.getView();
//        FrameLayout.LayoutParams params=(FrameLayout.LayoutParams)view.getLayoutParams();
//        params.gravity = Gravity.TOP;
//        view.setBackgroundColor(layout.getResources().getColor(R.color.red));
//        view.setLayoutParams(params);
//        snackbar.show();
//    }
//
//    public void snacBarsGreen(String text){
//        Snackbar snackbar = Snackbar.make(layout, text, Snackbar.LENGTH_LONG);
//        View view = snackbar.getView();
//        FrameLayout.LayoutParams params=(FrameLayout.LayoutParams)view.getLayoutParams();
//        params.gravity = Gravity.TOP;
//        view.setBackgroundColor(layout.getResources().getColor(R.color.bg_screen3));
//        view.setLayoutParams(params);
//        snackbar.show();
//    }
//
//    public void isLogin(){
//        // Session manager
//        session = new SessionManager(this);
//        //Session Login
//        if(session.isLoggedIn()){
//            strId       = prefs.getString("id","");
//            strNik      = prefs.getString("nik","");
//            strNotelp   = prefs.getString("notelp", "");
//            strNama     = prefs.getString("nama", "");
//            strRole     = prefs.getString("role", "");
//            strToken    = prefs.getString("token", "");
//            strKtp      = prefs.getString("ktp", "");
//            strKk       = prefs.getString("kk","");
//            strPotoPropil=prefs.getString("pp","");
//
//        }else{
//            Intent a = new Intent(getApplicationContext(), Login.class);
//            startActivity(a);
//            finish();
//        }
//    }
}
