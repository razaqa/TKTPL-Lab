package id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.loadLibrary("uppercase-word-jni");
    }

    public native String uppercaseStringFromJNI(String input);

    public void onClickUppercaseWord(View view) {
        EditText editTextWord = findViewById(R.id.editTextWord);
        TextView textViewOutput = findViewById(R.id.textViewOutput);

        String uppercaseText = uppercaseStringFromJNI(editTextWord.getText().toString());
        textViewOutput.setText(uppercaseText);
    }
}