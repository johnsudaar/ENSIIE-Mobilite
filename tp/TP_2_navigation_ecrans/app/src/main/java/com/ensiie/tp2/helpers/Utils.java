package com.ensiie.tp2.helpers;

import android.content.Context;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.widget.Toast;

public class Utils {
    public static final int PERMISSIONS_REQUEST_SEND_SMS = 1;

    /**
     * Fait vibrer le téléphone.
     */
    public static void vibratePhone(Context context) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);
    }

    /**
     * Envoi d'un SMS.
     */
    public static void sendSms(String phoneNumber, String text) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, text, null, null);
    }

    /**
     * Affichage d'un message.
     */
    public static void showMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


}
