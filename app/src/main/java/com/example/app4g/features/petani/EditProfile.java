package com.example.app4g.petani;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.app4g.R;
import com.example.app4g.server.AppController;
import com.example.app4g.server.Config_URL;
import com.example.app4g.session.SessionManager;
import com.example.app4g.users.login.Login;
import com.example.app4g.users.model.LoginModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditProfile extends AppCompatActivity {

    public SharedPreferences prefs;
    public SessionManager session;

    String strId, strNik, strNotelp, strNama, strRole, strToken, strKtp, strKk, strPotoPropil;

    @BindView(R.id.edNik)
    EditText edtNik;
    @BindView(R.id.namaLengkap)
    EditText edtNamaLengkap;
    @BindView(R.id.phone)
    EditText edtPhone;

    @BindView(R.id.switch1)
    Switch switchData;
    @BindView(R.id.linearPassword)
    LinearLayout passwordLinear;
    @BindView(R.id.editPassword)
    Button edtPassword;

    @BindView(R.id.password)
    EditText edPassword;

    @BindView(R.id.progress)
    ProgressBar prgBar;

    RetryPolicy policy = new DefaultRetryPolicy(5000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    Handler handler;

    String poktan, alamatPoktan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        //getSupportActionBar().hide();
        isLogin();

        handler=new Handler();
        prgBar.setVisibility(View.GONE);

        edtNik.setText(strNik);
        edtNamaLengkap.setText(strNama);
        edtPhone.setText(strNotelp);

        switchData.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    passwordLinear.setVisibility(View.VISIBLE);
                    edtPassword.setVisibility(View.VISIBLE);
                }else {
                    passwordLinear.setVisibility(View.GONE);
                    edtPassword.setVisibility(View.GONE);
                }
            }
        });

        Intent i     = getIntent();
        poktan       = i.getStringExtra("namapoktan");
        alamatPoktan = i.getStringExtra("alamat");
    }

    @OnClick(R.id.ediPoktan)
    void editPoktan(){
        Intent i = new Intent(EditProfile.this, EditPoktan.class);
        i.putExtra("namapoktan", poktan);
        i.putExtra("alamat", alamatPoktan);
        startActivity(i);
        finish();
    }

    public void isLogin(){

        prefs = getSharedPreferences("UserDetails",
                Context.MODE_PRIVATE);
        // Session manager
        session = new SessionManager(this);
        //Session Login
        if(session.isLoggedIn()){
            strId       = prefs.getString("id","");
            strNik      = prefs.getString("nik","");
            strNotelp   = prefs.getString("notelp", "");
            strNama     = prefs.getString("nama", "");
            strRole     = prefs.getString("role", "");
            strToken    = prefs.getString("token", "");
            strKtp      = prefs.getString("ktp", "");
            strKk       = prefs.getString("kk","");
            strPotoPropil=prefs.getString("pp","");

        }else{
            Intent a = new Intent(getApplicationContext(), Login.class);
            startActivity(a);
            finish();
        }
    }

    @Override
    public void onBackPressed(){
        Intent a = getIntent();
        String hehe = a.getStringExtra("datanya");
        Intent i = new Intent(EditProfile.this, MenuUtama.class);
        i.putExtra("datanya", hehe);
        startActivity(i);

    }

    @OnClick(R.id.ediProfile)
    void editProfile(){
        editProfile(edtNamaLengkap.getText().toString(), edtPhone.getText().toString());
    }

    @OnClick(R.id.editPassword)
    void editPassword(){
        if(edPassword.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else {
            editPassword(edPassword.getText().toString());
        }
    }

    public void editProfile(final String nama, final String noTelp){

            String tag_string_req = "req_edit";
            prgBar.setVisibility(View.VISIBLE);
            StringRequest strReq = new StringRequest(Request.Method.PUT,
                    Config_URL.crudUser+"/"+strId, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("msg", "Login Response: " + response.toString());
                    prgBar.setVisibility(View.GONE);
                    try {
                        final JSONObject jObj = new JSONObject(response);
                        final boolean status = jObj.getBoolean("status");

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(status == true){
                                    String msg = null;
                                    String api_token = null;
                                    try {
                                        msg         = jObj.getString("message");
                                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                                        storeRegIdinSharedPref(getApplicationContext(), strId, edtNik.getText().toString(),
                                                edtPhone.getText().toString(), edtNamaLengkap.getText().toString());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }else {
                                    String msg = null;
                                    try {
                                        msg = jObj.getString("message");
                                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }, 1000);

                    }catch (JSONException e){
                        //JSON error
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error){
                    Log.e("msg", "Login Error : " + error.getMessage());
                    error.printStackTrace();
                    prgBar.setVisibility(View.GONE);
                }
            }){

                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/json");
                    params.put("nama", nama);
                    params.put("no_hp", noTelp);
                    return params;
                }
            };
            strReq.setRetryPolicy(policy);
            AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public void editPassword(final String password){

        String tag_string_req = "req_edit";
        prgBar.setVisibility(View.VISIBLE);
        StringRequest strReq = new StringRequest(Request.Method.PUT,
                Config_URL.crudUser+"/"+strId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("msg", "Login Response: " + response.toString());
                prgBar.setVisibility(View.GONE);
                try {
                    final JSONObject jObj = new JSONObject(response);
                    final boolean status = jObj.getBoolean("status");

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(status == true){
                                String msg = null;
                                String api_token = null;
                                try {
                                    msg         = jObj.getString("message");
                                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                                    storeRegIdinSharedPref(getApplicationContext(), strId, edtNik.getText().toString(),
                                            edtPhone.getText().toString(), edtNamaLengkap.getText().toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }else {
                                String msg = null;
                                try {
                                    msg = jObj.getString("message");
                                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }, 1000);

                }catch (JSONException e){
                    //JSON error
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error){
                Log.e("msg", "Login Error : " + error.getMessage());
                error.printStackTrace();
                prgBar.setVisibility(View.GONE);
            }
        }){

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("password", password);
                return params;
            }
        };
        strReq.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void storeRegIdinSharedPref(Context context, String strId, String strNik, String strNotelp, String strNama) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("id", strId);
        editor.putString("nik", strNik);
        editor.putString("notelp", strNotelp);
        editor.putString("nama", strNama);
        editor.commit();
    }
}
