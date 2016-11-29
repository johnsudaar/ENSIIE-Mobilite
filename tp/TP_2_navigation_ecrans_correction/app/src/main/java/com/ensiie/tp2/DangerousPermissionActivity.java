package com.ensiie.tp2;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.ensiie.tp2.helpers.Utils;

import static com.ensiie.tp2.helpers.Utils.PERMISSIONS_REQUEST_SEND_SMS;
import static com.ensiie.tp2.helpers.Utils.sendSms;

/**
 * Created by Adrian on 18/11/2016.
 */
public class DangerousPermissionActivity extends Activity {

    private final static String TAG = "Activity_4";
    private EditText phoneNumber;
    private EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangerous_permission_activity);

        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        message = (EditText) findViewById(R.id.message);

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (hasSendSmsPermission()) {
                sendSms(phoneNumber.getText().toString(), message.getText().toString());
            } else {
                askSendSmsPermission();
            }
        }
    };

    /**
     * Test si l'utilisateur a la permission SEND_SMS.
     *
     * @return
     */
    public boolean hasSendSmsPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Demande de permission à l'utilisateur.
     * Lorsque l'utilisateur a fait un choix, la méthode {@link #onRequestPermissionsResult} est appelée avec le résultat.
     */
    public void askSendSmsPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSIONS_REQUEST_SEND_SMS);
    }

    /**
     * Appelé quand l'utilisateur a fait un choix.
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult");

        switch (requestCode) {
            case PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // La permission a été acceptée
                    Log.d(TAG, "permission granted");
                    sendSms(phoneNumber.getText().toString(), message.getText().toString());
                    Utils.showMessage(this, getString(R.string.sms_sent));
                } else {
                    // La permission a été refusée
                    Log.d(TAG, "permission refused");
                    Utils.showMessage(this, getString(R.string.permission_failed));
                }
            }
        }
    }
}
