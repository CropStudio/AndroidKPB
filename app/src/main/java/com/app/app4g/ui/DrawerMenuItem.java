package com.app.app4g.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.core.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.app4g.R;
import com.app.app4g.features.petani.profile.Profile;
import com.app.app4g.features.petani.profile.ProfileFragment;
import com.app.app4g.features.petani.profile.detailProfile.DetailProfile;
import com.app.app4g.features.users.login.Login;
import com.app.app4g.server.App;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;


/**
 * Created by github.com/adip28 on 7/31/2018.
 */
@Layout(R.layout.drawer_item)
public class DrawerMenuItem {

    public static final int DRAWER_MENU_ITEM_PROFILE = 1;
    public static final int DRAWER_MENU_ITEM_KOLABORATOR = 2;
    public static final int DRAWER_MENU_ITEM_ABOUT = 3;
    public static final int DRAWER_MENU_ITEM_TERMCONDITION = 4;
    public static final int DRAWER_MENU_ITEM_LOGOUT = 5;

    private int mMenuPosition;
    private Context mContext;
    private DrawerCallBack mCallBack;

    @View(R.id.itemNameTxt)
    private TextView itemNameTxt;

    @View(R.id.itemIcon)
    private ImageView itemIcon;

    public DrawerMenuItem(Context context, int menuPosition) {
        mContext = context;
        mMenuPosition = menuPosition;

    }

    @Resolve

    private void onResolved() {
        switch (mMenuPosition){
            case DRAWER_MENU_ITEM_PROFILE:
                itemNameTxt.setText("Profil");
//                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_account_circle_black_24dp));
                itemIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_userz));
                break;
            case DRAWER_MENU_ITEM_KOLABORATOR:
                itemNameTxt.setText("Kolaborator");
//                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_group_work_black_24dp));
                itemIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_kolaborator));
                break;
            case DRAWER_MENU_ITEM_ABOUT:
                itemNameTxt.setText("Tentang Kami");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_about));
                break;
            case DRAWER_MENU_ITEM_TERMCONDITION:
                itemNameTxt.setText("Syarat dan Ketentuan");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_term));
                break;
            case DRAWER_MENU_ITEM_LOGOUT:
//                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_exit_to_app_black_24dp));
                itemIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_keluar));
                itemNameTxt.setText("Keluar");
                break;
        }
    }

    @Click(R.id.mainView)
    private void onMenuItemClick(){

        switch (mMenuPosition){
            case DRAWER_MENU_ITEM_PROFILE:
                goToProfile();
                if(mCallBack != null)mCallBack.onProfileMenuSelected();
                break;
            case DRAWER_MENU_ITEM_KOLABORATOR:
                Toast.makeText(mContext, "Maaf, fitur ini belum tersedia", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onRequestMenuSelected();
                break;
            case DRAWER_MENU_ITEM_ABOUT:
                //goToKontak();
                Toast.makeText(mContext, "Maaf, fitur ini belum tersedia", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onRequestMenuSelected();
                break;
            case DRAWER_MENU_ITEM_TERMCONDITION:
                //goToKontak();
                Toast.makeText(mContext, "Maaf, fitur ini belum tersedia", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onRequestMenuSelected();
                break;
            case DRAWER_MENU_ITEM_LOGOUT:
                SweetDialogs.commonLogout((Activity) mContext, "SIGNOUT",
                        "Apakah Anda yakin akan signout dari aplikasi?",
                        string ->logout());
                if(mCallBack != null)mCallBack.onLogoutMenuSelected();
                break;
        }
    }

    public void setDrawerCallBack(DrawerCallBack callBack) {
        mCallBack = callBack;
    }

    public interface DrawerCallBack{
        void onProfileMenuSelected();
        void onRequestMenuSelected();
        void onGroupsMenuSelected();
        void onMessagesMenuSelected();
        void onNotificationsMenuSelected();
        void onSettingsMenuSelected();
        void onTermsMenuSelected();
        void onLogoutMenuSelected();
    }

    public void logout(){
        App.getPref().clear();
        mContext.startActivity(new Intent(mContext, Login.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        ((Activity)mContext).finish();
        Toast.makeText(mContext, "Signout berhasil", Toast.LENGTH_LONG).show();
    }

    public void goToProfile(){
         mContext.startActivity(new Intent(mContext, Profile.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        ((Activity)mContext).finish();
    }
    public void goToKontak(){
//        mContext.startActivity(new Intent(mContext, ContactActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//        ((Activity)mContext).finish();
    }

}