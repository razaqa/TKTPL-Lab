package id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab.R;
import id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab.fragment.MotorcycleInfoFragment;
import id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab.fragment.MotorcycleListFragment;

public class MainActivity extends AppCompatActivity {

    public static final String INFO_CLASS_NAME = "MotorcycleInfoFragment";
    public static final String LIST_CLASS_NAME = "MotorcycleListFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            MotorcycleListFragment fragment = new MotorcycleListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, fragment, LIST_CLASS_NAME)
                    .commit();
        }
    }

    public void showMotorcycleInfoFragment() {
        MotorcycleInfoFragment fragment = new MotorcycleInfoFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment, INFO_CLASS_NAME)
                .addToBackStack(INFO_CLASS_NAME)
                .commit();
    }
}