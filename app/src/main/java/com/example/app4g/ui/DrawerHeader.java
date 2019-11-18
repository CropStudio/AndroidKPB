package com.example.app4g.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app4g.R;
import com.example.app4g.features.users.login.Login;
import com.example.app4g.session.SessionManager;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;


/**
 * Created by github.com/adip28 on 7/31/2018.
 */
@NonReusable
@Layout(R.layout.drawer_header)
public class DrawerHeader {
    public SharedPreferences prefs;
    public SessionManager session;
    private Context mContext;
    String strId, strNik, strNotelp, strNama, strRole, strToken, strPotoPropil, namaPoktan, alamat, mt1, mt2, mt3, kecamatan, kabupaten, kota, provinsi;
//    private LoginResponse mProfile;

    @View(R.id.profileImageView)
    private ImageView profileImage;

    @View(R.id.nameTxt)
    private TextView nameTxt;

    @View(R.id.emailTxt)
    private TextView emailTxt;

    public DrawerHeader(Context context) {
        mContext = context;

    }

    @Resolve
    private void onResolved() {
        prefs = mContext.getSharedPreferences("UserDetails",
                Context.MODE_PRIVATE);
        session = new SessionManager(mContext);
        //Session Login
        if (session.isLoggedIn()) {
            strId = prefs.getString("id", "");
            strNik = prefs.getString("nik", "");
            strNotelp = prefs.getString("notelp", "");
            strNama = prefs.getString("nama", "");
            strRole = prefs.getString("role", "");
            strToken = prefs.getString("token", "");
            strPotoPropil = prefs.getString("pp", "");
        }
        nameTxt.setText(strNama);


    }
}