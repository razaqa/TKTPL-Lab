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

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button buttonScan;
    private Button buttonSend;
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
        setOnButtonSendClicked();
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

    protected void setOnButtonSendClicked() {
        buttonSend.setOnClickListener(v -> {
            sendWifiListToServer();
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
                        Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED ||
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
                        Manifest.permission.INTERNET,
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

    public void sendWifiListToServer() {
        String PIPEDREAM_ENDPOINT = "https://1bab887db44e8baf390c5d336a2210aa.m.pipedream.net";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PIPEDREAM_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WifiAPIInterface wifiAPI = retrofit.create(WifiAPIInterface.class);

        List<WifiAPIRequest> listWifiAPIRequestData = wifiReceiver.getListWifiAPIRequestData();
        Call<WifiAPIResponse> request = wifiAPI.sendWifiList(listWifiAPIRequestData);

        request.enqueue(new Callback<WifiAPIResponse>() {
            @Override
            public void onResponse(Call<WifiAPIResponse> call, Response<WifiAPIResponse> response) {
                String result = "Sending wifi data success: " + Objects.requireNonNull(response.body()).isSuccess();
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<WifiAPIResponse> call, Throwable t) {
                String result = "Failed to send...";
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            }
        });
    }
}