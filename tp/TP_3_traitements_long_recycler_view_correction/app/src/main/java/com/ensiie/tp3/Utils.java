package com.ensiie.tp3;

import android.content.Context;
import android.os.SystemClock;
import android.support.annotation.WorkerThread;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Adrian on 19/11/2016.
 */
public class Utils {
    private static final String TAG = "com.ensiie.tp3.Utils";

    @WorkerThread
    public static void workTodo() {
        SystemClock.sleep(10000);
        Log.i(TAG, "Work is done !");
    }

    @WorkerThread
    public static String workToDo(int time) {
        SystemClock.sleep(time * 1000);
        return "Work is done !";
    }

    @WorkerThread
    public static void smallWorkToDo() {
        SystemClock.sleep(1000);
        Log.i(TAG, "Small work done !");
    }

    /**
     * Permette de convertir une Date en String
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE);
        return dateFormat.format(date);
    }

    public static void showMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
