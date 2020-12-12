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
    }

    public void onButtonClick(View view) {
        EditText textInput= findViewById(R.id.editTextTextPersonName);
        TextView labelResult = findViewById(R.id.label);

        String name = textInput.getText().toString();
        labelResult.setText("Hello, " + name + "!");
    }
}