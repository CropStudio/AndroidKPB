package com.app.app4g.ui;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.app4g.R;
import com.app.app4g.Utils.GsonHelper;
import com.app.app4g.features.users.login.model.LoginResponse;
import com.app.app4g.server.App;
import com.app.app4g.session.Prefs;
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
    private LoginResponse mProfile;
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
        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
//        String user_photo = (mProfile.getResult().getUser_photo().contains(" "))
//                ? mProfile.getResult().getUser_photo() : mProfile.getResult().getUser_photo();
        String nama = (mProfile.getResult().getNama().contains(" "))
                ? mProfile.getResult().getNama() : mProfile.getResult().getNama();
        String nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        nameTxt.setText(nama);
        emailTxt.setText(nik);
//        if(!user_photo.equals(""))
//            Glide.with(mContext)
//                    .load(App.getApplication().getString(R.string.img_end_point))
//                    .into(profileImage);
//        else Glide.with(mContext)
//                .load(R.drawable.user)
//                .into(profileImage);


    }
}