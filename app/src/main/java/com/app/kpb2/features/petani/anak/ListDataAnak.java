//package com.example.app4g.features.petani.anak;
//
//import android.app.DatePickerDialog;
//import android.app.Dialog;
//import android.app.ProgressDialog;
//import android.os.Handler;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.widget.ListView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import butterknife.BindView;
//
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.RetryPolicy;
//import com.example.app4g.R;
//import com.example.app4g.adapter.AdapterAnak;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Locale;
//
//import butterknife.ButterKnife;
//
//
//public class ListDataAnak extends AppCompatActivity {
//    @BindView(R.id.main_list)
//    ListView list;
//
//    ProgressDialog pDialog;
//
//    String strId, strNik, strNotelp, strNama, strRole, strToken, strKtp, strKk, strPotoPropil;
////    ArrayList<DataAnak> newsList = new ArrayList<DataAnak>();
//    AdapterAnak adapter;
//
//    Dialog myDialog;
//    DatePickerDialog datePickerDialog;
//    SimpleDateFormat dateFormatter;
//    TextView txtTgl;
//
//    @BindView(R.id.layout)
//    RelativeLayout layout;
//
//    Handler handler;
//
//    RetryPolicy policy = new DefaultRetryPolicy(5000,
//            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_data_anak);
//        ButterKnife.bind(this);
//
//        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
//        pDialog = new ProgressDialog(this);
//        myDialog = new Dialog(this);
//        pDialog.setCancelable(false);
//        newsList.clear();
//        adapter = new AdapterAnak(this, newsList);
//        list.setAdapter(adapter);
//        list.setEmptyView(findViewById(R.id.textNodata));
//
////        prefs = getSharedPreferences(
////                "UserDetails",
////                Context.MODE_PRIVATE);
////        isLogin();
////        getNamaAnak();
////        updateData();
//    }
//
////    public void tanggalan(){
////        Calendar newCalendar = Calendar.getInstance();
////        datePickerDialog = new DatePickerDialog(myDialog.getContext(), new DatePickerDialog.OnDateSetListener() {
////            @Override
////            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
////
////                Calendar newDate = Calendar.getInstance();
////                newDate.set(year, monthOfYear, dayOfMonth);
////                txtTgl.setText(dateFormatter.format(newDate.getTime()));
////            }
////
////        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
////        datePickerDialog.show();
////    }
////    // Fungsi get JSON Mahasiswa
////    private void getNamaAnak() {
////
////        String tag_string_req = "req";
////
////        pDialog.setMessage("Loading.....");
////        showDialog();
////
////        String tag_json_obj = "json_obj_req";
////        StringRequest strReq = new StringRequest(Request.Method.GET,
////                Config_URL.dataAnak+strId,
////                new CommonRespon.Listener<String>() {
////
////                    @Override
////                    public void onResponse(String CommonRespon) {
////                        Log.e("CommonRespon: ", CommonRespon.toString());
////                        hideDialog();
////
////                        try {
////                            JSONObject jObj = new JSONObject(CommonRespon);
////                            boolean status = jObj.getBoolean("status");
////
////                            if(status == true){
////
////                                String getObject = jObj.getString("message");
////                                JSONArray jsonArray = new JSONArray(getObject);
////                                for (int i = 0; i < jsonArray.length(); i++) {
////                                    final JSONObject jsonObject = jsonArray.getJSONObject(i);
////                                    DataAnak anak = new DataAnak();
////                                    anak.setNama(jsonObject.getString("nama"));
////                                    anak.setJenisKelami(jsonObject.getString("jenis_kelamin"));
////                                    anak.setTglLahir(jsonObject.getString("tanggal_lahir"));
////                                    anak.setIdAnak(jsonObject.getString("id"));
////                                    newsList.add(anak);
////                                }
////                            }
////
////                        } catch (JSONException e) {
////                            // JSON error
////                            e.printStackTrace();
////                        }
////                        adapter.notifyDataSetChanged();
////                    }
////                }, new CommonRespon.ErrorListener(){
////
////            @Override
////            public void onErrorResponse(VolleyError error){
////                Log.e(String.valueOf(getApplication()), "Error : " + error.getMessage());
////                error.printStackTrace();
//////                ImageView image = new ImageView(ListPembayaran.this);
//////                image.setImageResource(R.drawable.no_internet);
//////                AlertDialog.Builder builder = new AlertDialog.Builder(ListPembayaran.this);
//////                builder.setTitle(Html.fromHtml("<font color='#2980B9'><b></b></font>"))
//////                        .setCancelable(false)
//////                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//////                            public void onClick(DialogInterface dialog, int id) {
//////                                Intent a = new Intent(ListPembayaran.this, ListPembayaran.class);
//////                                startActivity(a);
//////                                finish();
//////                            }
//////                        }).setView(image)
//////                        .show();
////                hideDialog();
////            }
////        });
//////        {
//////            @Override
//////            public Map<String, String> getHeaders() throws AuthFailureError {
//////                Map<String, String> headers = new HashMap<>();
//////                headers.put("Authorization", "Bearer " + main.token);
//////                return headers;
//////            }
//////        };
////
////        App.getInstance().addToRequestQueue(strReq, tag_json_obj);
////    }
////
////    @Override
////    public void onBackPressed() { //ini untuk tombol fisik kembali
////        Intent a = new Intent(ListDataAnak.this, MenuUtama.class);
////        startActivity(a);
////        finish();
////     }
////
////    @OnClick(R.id.fabTambahData)
////    void btnTambahDataAnak() {
////        Intent a = new Intent(ListDataAnak.this, InputDataAnak.class);
////        startActivity(a);
////        finish();
////    }
////
////    public void isLogin(){
////        // Session manager
////        session = new SessionManager(this);
////        //Session Login
////        if(session.isLoggedIn()){
////            strId       = prefs.getString("id","");
////            strNik      = prefs.getString("nik","");
////            strNotelp   = prefs.getString("notelp", "");
////            strNama     = prefs.getString("nama", "");
////            strRole     = prefs.getString("role", "");
////            strToken    = prefs.getString("token", "");
////            strKtp      = prefs.getString("ktp", "");
////            strKk       = prefs.getString("kk","");
////            strPotoPropil=prefs.getString("pp","");
////
////        }else{
////            Intent a = new Intent(getApplicationContext(), Login.class);
////            startActivity(a);
////            finish();
////        }
////    }
////
////    private void showDialog() {
////        if (!pDialog.isShowing())
////            pDialog.show();
////    }
////
////    private void hideDialog() {
////        if (pDialog.isShowing())
////            pDialog.dismiss();
////    }
////
////    public void updateData(){
////        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
////                // TODO Auto-generated method stub
////                //Toast.makeText(getApplicationContext(), newsList.get(position).getNama(), Toast.LENGTH_LONG).show();
////                myDialog.setContentView(R.layout.update_data_anak);
////                handler=new Handler();
////                final EditText edAnak = (EditText) myDialog.findViewById(R.id.edNamaAnak);
////                final RadioButton[] radioLaki = {(RadioButton) myDialog.findViewById(R.id.rb_laki)};
////                RadioButton radioPerempuan = (RadioButton) myDialog.findViewById(R.id.rb_perempuan);
////                final RadioGroup radioGroupGender = (RadioGroup) myDialog.findViewById(R.id.rg_gender);
////                LinearLayout tglan = (LinearLayout) myDialog.findViewById(R.id.tglan);
////                Button btnSubmit = (Button) myDialog.findViewById(R.id.submitAnak);
////                Button btnHapus = (Button) myDialog.findViewById(R.id.hapusAnak);
////                LinearLayout closed = (LinearLayout) myDialog.findViewById(R.id.close);
////
////                final ProgressBar prgBar = (ProgressBar) myDialog.findViewById(R.id.progress_login);
////                prgBar.setVisibility(View.GONE);
////
////                txtTgl = (TextView) myDialog.findViewById(R.id.edTgl);
////                txtTgl.setText(newsList.get(position).getTglLahir());
////                if(newsList.get(position).getJenisKelami().equals("Laki-Laki")){
////                    radioLaki[0].setChecked(true);
////                }else {
////                    radioPerempuan.setChecked(true);
////                }
////                //ButterKnife.bind(myDialog);
////                edAnak.setText(newsList.get(position).getNama());
////
////                tglan.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        tanggalan();
////                    }
////                });
////
////                //update function
////                btnSubmit.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        int selectedId = radioGroupGender.getCheckedRadioButtonId();
////                        String namaAnak = edAnak.getText().toString();
////                        if(namaAnak.isEmpty()){
////                            snacBars("Nama anak dibutuhkan");
////                        } else if (selectedId <= 0){
////                            snacBars("Jenis kelamin anak dibutuhkan");
////                        }else {
////                            prgBar.setVisibility(View.VISIBLE);
////                            radioLaki[0] = (RadioButton) radioGroupGender.findViewById(selectedId);
////                            final String genders = (String) radioLaki[0].getText().toString();
////                            String tglLahir = txtTgl.getText().toString();
////                            Log.v("Data input", namaAnak+" "+genders+" "+tglLahir+" "+strId);
////                            if(tglLahir.equals("yyyy-MM-dd")){
////                                snacBars("Tanggal lahir anak dibutuhkan");
////                            }else {
////                                String tag_string_req = "req";
////                                StringRequest strReq = new StringRequest(Request.Method.PUT,
////                                        Config_URL.anak+"/"+newsList.get(position).getIdAnak(), new CommonRespon.Listener<String>() {
////                                    @Override
////                                    public void onResponse(String CommonRespon) {
////                                        Log.d("msg", "CommonRespon: " + CommonRespon.toString());
////                                        prgBar.setVisibility(View.GONE);
////                                        try {
////                                            final JSONObject jObj = new JSONObject(CommonRespon);
////                                            final boolean status = jObj.getBoolean("status");
////
////                                            handler.postDelayed(new Runnable() {
////                                                @Override
////                                                public void run() {
////                                                    if(status == true){
////                                                        String msg = null;
////                                                        try {
////                                                            msg = jObj.getString("message");
////                                                            Intent a = new Intent(ListDataAnak.this, ListDataAnak.class);
////                                                            startActivity(a);
////                                                            finish();
////                                                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
////                                                        } catch (JSONException e) {
////                                                            e.printStackTrace();
////                                                        }
////                                                    }else {
////                                                        String msg = null;
////                                                        try {
////                                                            msg = jObj.getString("message");
////                                                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
////                                                        } catch (JSONException e) {
////                                                            e.printStackTrace();
////                                                        }
////                                                        //iInputAnakView.onInputResult(status, msg);
////                                                    }
////                                                }
////                                            }, 1000);
////
////                                        }catch (JSONException e){
////                                            //JSON error
////                                            e.printStackTrace();
////                                        }
////                                    }
////                                }, new CommonRespon.ErrorListener(){
////
////                                    @Override
////                                    public void onErrorResponse(VolleyError error){
////                                        Log.e("msg", "Login Error : " + error.getMessage());
////                                        error.printStackTrace();
////                                        //iInputAnakView.onInputResult(false, "Maaf server tidak meresponse atau periksa koneksi internet anda");
////                                        prgBar.setVisibility(View.GONE);
////                                    }
////                                }){
////
////                                    @Override
////                                    protected Map<String, String> getParams(){
////                                        Map<String, String> params = new HashMap<String, String>();
////                                        params.put("nama", edAnak.getText().toString());
////                                        params.put("tanggal_lahir", txtTgl.getText().toString());
////                                        params.put("jenis_kelamin", genders);
////                                        params.put("id_user", strId);
////                                        return params;
////                                    }
////                                };
////                                strReq.setRetryPolicy(policy);
////                                App.getInstance().addToRequestQueue(strReq, tag_string_req);
////
////                            }
////                        }
////                    }
////                });
////
////                //hapus function
////                btnHapus.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        final int selectedId = radioGroupGender.getCheckedRadioButtonId();
////                        final String namaAnak = edAnak.getText().toString();
////                        if(namaAnak.isEmpty()){
////                            snacBars("Nama anak dibutuhkan");
////                        } else if (selectedId <= 0){
////                            snacBars("Jenis kelamin anak dibutuhkan");
////                        }else {
////                            final AlertDialog.Builder builder = new AlertDialog.Builder(myDialog.getContext());
////                            builder.setTitle(Html.fromHtml("<font color='#F44336'><b>Yakin ingin menghapus data anak ?</b></font>"))
////                                    .setIcon(R.drawable.lampung_coa)
////                                    .setCancelable(false)
////                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
////                                        public void onClick(DialogInterface dialog, int id) {
////                                            prgBar.setVisibility(View.VISIBLE);
////                                            radioLaki[0] = (RadioButton) radioGroupGender.findViewById(selectedId);
////                                            final String genders = (String) radioLaki[0].getText().toString();
////                                            String tglLahir = txtTgl.getText().toString();
////                                            Log.v("Data input", namaAnak+" "+genders+" "+tglLahir+" "+strId);
////                                            if(tglLahir.equals("yyyy-MM-dd")){
////                                                snacBars("Tanggal lahir anak dibutuhkan");
////                                            }else {
////                                                String tag_string_req = "req";
////                                                StringRequest strReq = new StringRequest(Request.Method.DELETE,
////                                                        Config_URL.anak+"/"+newsList.get(position).getIdAnak(), new CommonRespon.Listener<String>() {
////                                                    @Override
////                                                    public void onResponse(String CommonRespon) {
////                                                        Log.d("msg", "CommonRespon: " + CommonRespon.toString());
////                                                        prgBar.setVisibility(View.GONE);
////                                                        try {
////                                                            final JSONObject jObj = new JSONObject(CommonRespon);
////                                                            final boolean status = jObj.getBoolean("status");
////
////                                                            handler.postDelayed(new Runnable() {
////                                                                @Override
////                                                                public void run() {
////                                                                    if(status == true){
////                                                                        String msg = null;
////                                                                        try {
////                                                                            msg = jObj.getString("message");
////                                                                            Intent a = new Intent(ListDataAnak.this, ListDataAnak.class);
////                                                                            startActivity(a);
////                                                                            finish();
////                                                                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
////                                                                        } catch (JSONException e) {
////                                                                            e.printStackTrace();
////                                                                        }
////                                                                    }else {
////                                                                        String msg = null;
////                                                                        try {
////                                                                            msg = jObj.getString("message");
////                                                                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
////                                                                        } catch (JSONException e) {
////                                                                            e.printStackTrace();
////                                                                        }
////                                                                        //iInputAnakView.onInputResult(status, msg);
////                                                                    }
////                                                                }
////                                                            }, 1000);
////
////                                                        }catch (JSONException e){
////                                                            //JSON error
////                                                            e.printStackTrace();
////                                                        }
////                                                    }
////                                                }, new CommonRespon.ErrorListener(){
////
////                                                    @Override
////                                                    public void onErrorResponse(VolleyError error){
////                                                        Log.e("msg", "Login Error : " + error.getMessage());
////                                                        error.printStackTrace();
////                                                        //iInputAnakView.onInputResult(false, "Maaf server tidak meresponse atau periksa koneksi internet anda");
////                                                        prgBar.setVisibility(View.GONE);
////                                                    }
////                                                });
////                                                strReq.setRetryPolicy(policy);
////                                                App.getInstance().addToRequestQueue(strReq, tag_string_req);
////
////                                            }
////
////                                        }
////                                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
////                                @SuppressLint("RestrictedApi")
////                                @Override
////                                public void onClick(DialogInterface dialog, int which) {
////                                    builder.setCancelable(true);
////                                }
////                            })
////                                    .show();
////                        }
////                    }
////                });
////                //delete function
////                closed.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        myDialog.dismiss();
////                    }
////                });
////
////                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
////                myDialog.show();
////            }
////        });
////    }
////
////    public void snacBars(String text){
////        Snackbar snackbar = Snackbar.make(layout, text, Snackbar.LENGTH_LONG);
////        View view = snackbar.getView();
////        FrameLayout.LayoutParams params=(FrameLayout.LayoutParams)view.getLayoutParams();
////        params.gravity = Gravity.TOP;
////        view.setBackgroundColor(layout.getResources().getColor(R.color.red));
////        view.setLayoutParams(params);
////        snackbar.show();
////    }
////
////    public void snacBarsGreen(String text){
////        Snackbar snackbar = Snackbar.make(layout, text, Snackbar.LENGTH_LONG);
////        View view = snackbar.getView();
////        FrameLayout.LayoutParams params= (FrameLayout.LayoutParams)view.getLayoutParams();
////        params.gravity = Gravity.TOP;
////        view.setBackgroundColor(layout.getResources().getColor(R.color.bg_screen3));
////        view.setLayoutParams(params);
////        snackbar.show();
////    }
//}
