package id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
    private boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("onCreate - seconds", Integer.toString(seconds));
        Log.d("onCreate - running", Boolean.toString(running));

        if (savedInstanceState != null) {
            this.seconds = savedInstanceState.getInt("seconds");
            this.running = savedInstanceState.getBoolean("running");
        }
        runTimer();
    }

    @Override
    public void onBackPressed() {
        Log.d("onBackPressed - seconds", Integer.toString(seconds));
        Log.d("onBackPressed - running", Boolean.toString(running));

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("TKTPLab - Lab 3")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
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

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("onStart - seconds", Integer.toString(seconds));
        Log.d("onStart - running", Boolean.toString(running));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume - seconds", Integer.toString(seconds));
        Log.d("onResume - running", Boolean.toString(running));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onPause - seconds", Integer.toString(seconds));
        Log.d("onPause - running", Boolean.toString(running));
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop - seconds", Integer.toString(seconds));
        Log.d("onStop - running", Boolean.toString(running));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("onRestart - seconds", Integer.toString(seconds));
        Log.d("onRestart - running", Boolean.toString(running));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy - seconds", Integer.toString(seconds));
        Log.d("onDestroy - running", Boolean.toString(running));
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("seconds", this.seconds);
        outState.putBoolean("running", this.running);

        Log.d("onSaveInstanceState - seconds", Integer.toString(seconds));
        Log.d("onSaveInstanceState - running", Boolean.toString(running));
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