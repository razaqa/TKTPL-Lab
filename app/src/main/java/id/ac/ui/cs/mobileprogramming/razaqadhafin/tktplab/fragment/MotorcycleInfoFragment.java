package id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab.R;
import id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab.model.Motorcycle;
import id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab.viewmodel.MotorcycleViewModel;

public class MotorcycleInfoFragment extends Fragment {

    private MotorcycleViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_motorcycle_info, container, false);

        TextView bikeNameTextView = view.findViewById(R.id.bike_name);
        TextView bikeBrandTextView = view.findViewById(R.id.bike_brand);
        TextView bikeTypeTextView = view.findViewById(R.id.bike_type);
        TextView bikePriceTextView = view.findViewById(R.id.bike_price);

        this.viewModel = new ViewModelProvider(requireActivity()).get(MotorcycleViewModel.class);
        this.viewModel.getSelectedMotorcycle().observe(getViewLifecycleOwner(), item -> {
            Motorcycle motorcycle = viewModel.getMotorcycleInfo(item);

            bikeNameTextView.setText("Nama : " + motorcycle.getName());
            bikeBrandTextView.setText("Brand : " + motorcycle.getBrand());
            bikeTypeTextView.setText("Tipe : " + motorcycle.getType());
            bikePriceTextView.setText("Harga : " + motorcycle.getPrice());
        });

        return view;
    }

}