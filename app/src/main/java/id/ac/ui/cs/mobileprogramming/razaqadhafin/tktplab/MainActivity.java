package id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.Locale;

/**
 * reference: https://www.engbookspdf.com/uploads/pdf-books/HeadFirstAndroidDevelopmentABrainFriendlyGuide2ndEdition-1.pdf
 */
public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running;
    private final String LOG_TAG = "Lab 3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runTimer();
    }

    public void onButtonStartClicked(View view) {
        running = true;
    }

    public void onButtonStopClicked(View view) {
        running = false;
    }

    public void onButtonResetClicked(View view) {
        running = false;
        seconds = 0;
    }

    private void runTimer() {
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}