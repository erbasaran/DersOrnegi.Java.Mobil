package com.hr200012.bekir_berk_dundar_final.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.hr200012.bekir_berk_dundar_final.R;

public class DialogHelper {
    public static void showInternetConnectionFaliedDialog(Context context) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(context.getApplicationContext().getString(R.string.internet_connection_error_title));
        alertDialog.setMessage(context.getApplicationContext().getString(R.string.internet_connection_error_description));
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, context.getApplicationContext().getString(R.string.close),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dilaogu kapat
                        dialog.dismiss();

                        //uygulamayı tamamen kapatır
                        System.exit(0);
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, context.getApplicationContext().getString(R.string.open_internet),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dilaogu kapat
                        dialog.dismiss();

                        //telefon internet ayarlarını aç
                        Intent intent = new Intent("android.settings.NETWORK_OPERATOR_SETTINGS");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.getApplicationContext().startActivity(intent);
                    }
                });
        alertDialog.show();
    }

    public static void areYouSureExit(Context context) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(context.getApplicationContext().getString(R.string.warning));
        alertDialog.setMessage(context.getApplicationContext().getString(R.string.are_you_sure_you_want_to_exit));
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, context.getApplicationContext().getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dilaogu kapat
                        dialog.dismiss();

                        //uygulamayı tamamen kapatır
                        System.exit(0);
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, context.getApplicationContext().getString(R.string.no),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dilaogu kapat
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}