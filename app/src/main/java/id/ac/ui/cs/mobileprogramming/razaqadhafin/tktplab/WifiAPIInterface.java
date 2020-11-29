package id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WifiAPIInterface {

    @POST(".")
    Call<WifiAPIResponse> sendWifiList(@Body List<WifiAPIRequest> wifiData);
}
