package id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WifiReceiver extends BroadcastReceiver {
    private final WifiManager wifiManager;
    private final ListView listViewWifi;
    private List<WifiAPIRequest> listWifiAPIRequestData = new ArrayList<>();

    public WifiReceiver(WifiManager wifiManager, ListView listViewWifi) {
        this.wifiManager = wifiManager;
        this.listViewWifi = listViewWifi;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action)) {
            List<ScanResult> listWifi = wifiManager.getScanResults();

            listViewWifi.setAdapter(new ArrayAdapter<> (
                    context,
                    android.R.layout.simple_list_item_1,
                    listWifi.stream()
                            .map(wifi -> wifi.SSID)
                            .collect(Collectors.toList())));

            listWifiAPIRequestData = listWifi.stream()
                    .map(wifi -> new WifiAPIRequest(
                            wifi.SSID,
                            wifi.BSSID,
                            wifi.capabilities,
                            wifi.frequency))
                    .collect(Collectors.toList());

            String notificationText = "Getting wifi data...";
            Toast.makeText(context, notificationText, Toast.LENGTH_SHORT).show();
        }
    }

    public List<WifiAPIRequest> getListWifiAPIRequestData() {
        return listWifiAPIRequestData;
    }
}
