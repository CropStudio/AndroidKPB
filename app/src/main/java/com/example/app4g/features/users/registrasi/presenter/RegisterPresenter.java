package com.example.app4g.features.users.registrasi.presenter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.app4g.server.App;
import com.example.app4g.server.Config_URL;
import com.example.app4g.features.users.model.IUserRegister;
import com.example.app4g.features.users.model.RegisterModel;
import com.example.app4g.features.users.registrasi.view.IRegisterView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class RegisterPresenter implements IRegisterPresenter {

    IRegisterView iRegisterView;
    IUserRegister user;
    Handler handler;
    Activity activity;

    RetryPolicy policy = new DefaultRetryPolicy(5000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    private RequestQueue rQueue;

    public RegisterPresenter(IRegisterView iRegisterView, Activity activity){
        this.iRegisterView = iRegisterView;
        handler = new android.os.Handler(Looper.getMainLooper());
        this.activity = activity;
    }


    @Override
    public void clear() {
        iRegisterView.onClearText();
    }

    @Override
    public void doRegistrasi(String nik, String nama, String role, String password) {
        user   = new RegisterModel(nik, nama, role, password);
        registrasi(nik, nama, role, password);
    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {
        iRegisterView.onSetProgressBarVisibility(visiblity);
    }

    public void registrasi(final String nik, final String nama, final String role, final String password){
        String tag_string_req = "req";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config_URL.registrasi, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("msg", "CommonRespon: " + response);

                try {
                    final JSONObject jObj = new JSONObject(response);
                    final boolean status = jObj.getBoolean("status");


                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(status == true){
                                String msg = null;
                                try {
                                    msg = jObj.getString("message");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                iRegisterView.onRegisterResult(status, msg);
                            }else {
                                String msg = null;
                                try {
                                    msg = jObj.getString("message");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                iRegisterView.onRegisterResult(status, msg);
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
                iRegisterView.onRegisterResult(false, "Maaf server tidak meresponse atau periksa koneksi internet anda");
            }
        }){

            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("nik", nik);
                params.put("nama", nama);
                params.put("no_hp", "");
                params.put("password", password);
                params.put("role", role);
                return params;
            }
        };
        strReq.setRetryPolicy(policy);
        App.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
