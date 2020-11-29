package id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab;

public class WifiAPIResponse {
    private boolean success;

    public WifiAPIResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
