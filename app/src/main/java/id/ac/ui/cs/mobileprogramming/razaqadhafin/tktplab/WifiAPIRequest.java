package id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab;

public class WifiAPIRequest {
    private String SSID;
    private String BSSID;
    private String capabilities;
    private int frequency;

    public WifiAPIRequest(String SSID, String BSSID, String capabilities, int frequency) {
        this.SSID = SSID;
        this.BSSID = BSSID;
        this.capabilities = capabilities;
        this.frequency = frequency;
    }

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public String getBSSID() {
        return BSSID;
    }

    public void setBSSID(String BSSID) {
        this.BSSID = BSSID;
    }

    public String getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
