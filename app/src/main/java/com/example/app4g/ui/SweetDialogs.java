package com.example.app4g.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;

import com.example.app4g.R;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;


public class SweetDialogs {

    public interface onDialogClosed{
        void onClosed(String string);
    }


    public static void commonWarning(Activity context, String title, String content, boolean close) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
        dialog.setCancelable(true);
        dialog.setTitleText(title);
        dialog.setContentText(content);
        dialog.setConfirmText("OK");
        dialog.setConfirmClickListener(sweetAlertDialog -> {
            sweetAlertDialog.dismissWithAnimation();
            if(close)
                context.finish();
        });
        dialog.show();
    }

    public static void locationDisabledWarning(Activity context) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
        dialog.setCancelable(false);
        dialog.setTitleText("Lokasi Tidak aktif");
        dialog.setContentText("Lokasi Anda tidak aktif, aktifkan lokasi terlebih dahulu untuk melanjutkan");
        dialog.setConfirmText("Settings");
        dialog.setCustomImage(CustomDrawable.googleMaterialDrawable(
                context, R.color.colorPrimaryDark, 36,
                GoogleMaterial.Icon.gmd_gps_off
        ));
        dialog.setConfirmClickListener(sweetAlertDialog -> {
            sweetAlertDialog.dismissWithAnimation();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivity(intent);
            context.finish();
        });
        dialog.show();
    }

    public static void commonError(Activity context, String content, boolean close) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
        dialog.setCancelable(false);
        dialog.setTitleText("Gagal memuat permintaan");
        dialog.setContentText(content);
        dialog.setConfirmText("OK");
        dialog.setConfirmClickListener(sweetAlertDialog -> {
            sweetAlertDialog.dismissWithAnimation();
            if(close)
                context.finish();
        });
        dialog.show();
    }


    public static void endpointError(Activity context) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
        dialog.setCancelable(false);
        dialog.setTitleText("Oops!");
        dialog.setContentText("Koneksi internet Anda sedang tidak stabil atau server mengalami gangguan, silahkan coba beberapa saat lagi");
        dialog.setConfirmText("OK");
        dialog.setConfirmClickListener(sweetAlertDialog -> {
            sweetAlertDialog.dismissWithAnimation();
            context.finish();
        });
        try {
            dialog.show();
        }catch (Exception e){
            e.printStackTrace();

        }
    }
    public static void commonError(Activity context, String title, String content, onDialogClosed listener) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
        dialog.setCancelable(false);
        dialog.setTitleText(title);
        dialog.setContentText(content);
        dialog.setConfirmText("OK");
        dialog.setConfirmClickListener(sweetAlertDialog -> {
            sweetAlertDialog.dismissWithAnimation();
            listener.onClosed("closed");
        });
        dialog.show();
    }

    public static void commonLogout(Activity context, String title, String content, onDialogClosed listener) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
        dialog.setCancelable(false);
        dialog.setTitleText(title);
        dialog.setContentText(content);
        dialog.setConfirmText("OK");
        dialog.setConfirmClickListener(sweetAlertDialog -> {
            sweetAlertDialog.dismissWithAnimation();
            listener.onClosed("closed");
        });
        dialog.show();
    }



    public static void commonSuccess(Activity context, String body, boolean close) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
        dialog.setCancelable(false);
        dialog.setTitleText("Berhasil Memuat permintaan");
        dialog.setContentText(body);

        dialog.setConfirmText("OK");
        dialog.setConfirmClickListener(sweetAlertDialog -> {
            
            if(close)
                sweetAlertDialog.dismissWithAnimation();
        });
        dialog.show();
    }

    public static void commonSuccessWithIntent(Activity context, String body, onDialogClosed listener) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
        dialog.setCancelable(false);
        dialog.setTitleText("Berhasil Memuat permintaan");
        dialog.setContentText(body);
        dialog.setConfirmText("OK");
        dialog.setConfirmClickListener(sweetAlertDialog -> {
                sweetAlertDialog.dismissWithAnimation();
                listener.onClosed("Sukses");
        });
        dialog.show();
    }

    public static void Loading(Activity context, String body) {
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
    }
    public static void Loading(Activity context) {
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.dismiss();
    }

    public static void confirmDialog(Activity context, String Title , String body, String suksesBody, onDialogClosed listener) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
        dialog.setTitleText(Title);
        dialog.setContentText(body);
        dialog.setCancelText("No");
        dialog.setConfirmText("Yes");
        dialog.showCancelButton(true);
        dialog.setCancelable(false);
        dialog.setConfirmClickListener(sweetAlertDialog -> {
            sweetAlertDialog.dismissWithAnimation();
            listener.onClosed(suksesBody);
        });
        dialog.show();
    }
}
