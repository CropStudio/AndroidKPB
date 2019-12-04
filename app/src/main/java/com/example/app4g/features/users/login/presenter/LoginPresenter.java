package com.example.app4g.features.users.login.presenter;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.app4g.server.App;
import com.example.app4g.server.Config_URL;
import com.example.app4g.features.users.login.view.ILoginview;
import com.example.app4g.features.users.model.IUserLogin;
import com.example.app4g.features.users.model.LoginModel;
import com.example.app4g.session.Prefs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginPresenter implements ILoginPresenter{

    ILoginview iLoginView;
    IUserLogin user;
    Handler handler;

    RetryPolicy policy = new DefaultRetryPolicy(5000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    public LoginPresenter(ILoginview iLoginView) {
       this.iLoginView = iLoginView;
       handler = new Handler(Looper.getMainLooper());
    }
    @Override
    public boolean isLoggedIn(){
        return App.getPref().getBoolean(Prefs.PREF_IS_LOGEDIN, false);
    }
    @Override
    public void storeAccessToken(String token){
        App.getPref().put(Prefs.PREF_ACCESS_TOKEN, token);
    }
    @Override
    public void storeProfile(String data){
        App.getPref().put(Prefs.PREF_STORE_PROFILE, data);
        App.getPref().put(Prefs.PREF_IS_LOGEDIN, true);
    }

    @Override
    public void clear() {
        iLoginView.onClearText();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void doLogin(String nik, String passwd) {
        //if(email.isEmpty() && passwd.isEmpty()){
            //iLoginView.onLoginResult(false, "Lengkapi data");
        //}else {
        checkLogin(nik, passwd);
        //}
    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {
        iLoginView.onSetProgressBarVisibility(visiblity);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void checkLogin(final String nik, final String password){
        user   = new LoginModel(nik, password);
        String encoded = nik + ":" + password;
//        final String BasicBase64format
//                = Base64.getEncoder()
//                .encodeToString(encoded.getBytes());

        String tag_string_req = "req_login";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config_URL.login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("msg", "Login Response: " + response.toString());

                try {
                    final JSONObject jObj = new JSONObject(response);
                    final boolean status = jObj.getBoolean("status");
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(status == true){

                                String msg = null;
                                String api_token = null;
                                String res = null;
                                try {
                                    msg         = jObj.getString("message");
                                    res         = jObj.getString("result");
                                    JSONObject object = new JSONObject(res);
                                    api_token   = object.getString("token");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                iLoginView.onLoginResult(status, msg + "/"+ res + "/" + api_token );
                            }else {
                                String msg = null;
                                try {
                                    msg = jObj.getString("message");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                iLoginView.onLoginResult(status, msg + "");
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
                iLoginView.onLoginResult(false, "Maaf server tidak meresponse atau periksa koneksi internet anda");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
//                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("Authorization", "Basic " + android.util.Base64.encodeToString(encoded.getBytes(), android.util.Base64.NO_WRAP));
                return params;
            }

//            @Override
//            protected Map<String, String> getParams()
//            {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("Content-Type", "application/json");
//                params.put("nik", nik);
//                params.put("password", password);
//                return params;
//            }
        };
        strReq.setRetryPolicy(policy);
        App.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}
