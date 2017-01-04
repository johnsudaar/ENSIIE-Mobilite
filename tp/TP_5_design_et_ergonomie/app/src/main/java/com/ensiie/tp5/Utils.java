package com.ensiie.tp5;

import android.content.Context;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class Utils {
    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, LENGTH_SHORT).show();
    }
}
