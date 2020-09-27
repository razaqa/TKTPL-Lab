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

    public void checkPalindromeButtonClicked(View view) {
        EditText textInputPalindrome = findViewById(R.id.textInputPalindrome);
        TextView labelResult = findViewById(R.id.labelResult);

        String result = checkPalindrome(textInputPalindrome.getText().toString());
        labelResult.setText(result);
    }

    public String checkPalindrome(String str) {
        str = str.toLowerCase();
        String resultPrefix = "Result : ";
        int i = 0, j = str.length() - 1;

        while (i < j) {
            if (str.charAt(i) != str.charAt(j))
                return resultPrefix + "NOT A PALINDROME!";
            i++;
            j--;
        }

        return resultPrefix + "PALINDROME CONFIRMED!";

    }
}