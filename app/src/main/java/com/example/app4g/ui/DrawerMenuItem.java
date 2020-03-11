package com.example.app4g.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app4g.R;
import com.example.app4g.features.petani.profile.detailProfile.DetailProfile;
import com.example.app4g.features.users.login.Login;
import com.example.app4g.server.App;
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
    public static final int DRAWER_MENU_ITEM_CONTACTUS = 3;
    public static final int DRAWER_MENU_ITEM_MESSAGE = 4;
    public static final int DRAWER_MENU_ITEM_NOTIFICATIONS = 5;
    public static final int DRAWER_MENU_ITEM_SETTINGS = 6;
    public static final int DRAWER_MENU_ITEM_TERMS = 7;
    public static final int DRAWER_MENU_ITEM_LOGOUT = 8;

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
//            case DRAWER_MENU_ITEM_PROFILE:
//                itemNameTxt.setText("Profil");
////                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_account_circle_black_24dp));
//                itemIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_account_circle_black_24dp));
//                break;
            case DRAWER_MENU_ITEM_KOLABORATOR:
                itemNameTxt.setText("Kolaborator");
//                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_group_work_black_24dp));
                itemIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_group_work_black_24dp));
                break;
//            case DRAWER_MENU_ITEM_CONTACTUS:
//                itemNameTxt.setText("Contact us");
//                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_contact_phone_black_24dp));
//                break;
            case DRAWER_MENU_ITEM_LOGOUT:
//                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_exit_to_app_black_24dp));
                itemIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_exit_to_app_black_24dp));
                itemNameTxt.setText("Keluar");
                break;
        }
    }

    @Click(R.id.mainView)
    private void onMenuItemClick(){

        switch (mMenuPosition){
//            case DRAWER_MENU_ITEM_PROFILE:
//
//                goToProfile();
//                if(mCallBack != null)mCallBack.onProfileMenuSelected();
//                break;
            case DRAWER_MENU_ITEM_KOLABORATOR:
                Toast.makeText(mContext, "Maaf, fitur ini belum tersedia", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onRequestMenuSelected();
                break;
            case DRAWER_MENU_ITEM_CONTACTUS:
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
        mContext.startActivity(new Intent(mContext, DetailProfile.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        ((Activity)mContext).finish();
    }
    public void goToKontak(){
//        mContext.startActivity(new Intent(mContext, ContactActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//        ((Activity)mContext).finish();
    }

}