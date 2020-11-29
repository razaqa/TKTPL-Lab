package id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab;

import android.Manifest;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;



public class MainActivity extends AppCompatActivity {

    private Button buttonScan;
    private ListView listViewWifi;

    private WifiManager wifiManager;
    private WifiReceiver wifiReceiver;

    private final int PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewWifi = findViewById(R.id.ListViewWiFi);
        buttonScan = findViewById(R.id.ButtonScan);
        buttonSend = findViewById(R.id.ButtonSend);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        setOnButtonScanClicked();
    }

    protected void setOnButtonScanClicked() {
        buttonScan.setOnClickListener(v -> {

            if (isPermissionsNotGranted()) {
                requestPermissions();
            } else {
                wifiManager.startScan();
                sendWifiListToServer();
            }
        });
    }


    protected boolean isPermissionsNotGranted() {
        return ActivityCompat.checkSelfPermission(
                        MainActivity.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(
                        MainActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(
                        MainActivity.this,
                        Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(
                        MainActivity.this,
                        Manifest.permission.CHANGE_WIFI_STATE) != PackageManager.PERMISSION_GRANTED;
    }

    protected void requestPermissions() {
        ActivityCompat.requestPermissions(
                MainActivity.this,
                new String[] {
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.CHANGE_WIFI_STATE
                },
                PERMISSION_CODE);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        wifiReceiver = new WifiReceiver(wifiManager, listViewWifi);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);

        registerReceiver(wifiReceiver, intentFilter);
        scanWifi();
    }

    private void scanWifi() {
        // More than marshmallow
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permission = ContextCompat.checkSelfPermission(
                    MainActivity.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION);

            // Location off
            if (permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        PERMISSION_CODE);

            // Location on
            } else {
                wifiManager.startScan();
            }

        // Lower than marshmallow
        } else {
            wifiManager.startScan();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(wifiReceiver);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                wifiManager.startScan();
            }
        }
    }

    }
}