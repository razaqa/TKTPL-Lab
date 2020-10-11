package id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab.R;
import id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab.activity.MainActivity;
import id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab.viewmodel.MotorcycleViewModel;

public class MotorcycleListFragment extends Fragment {

    private MotorcycleViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_motorcycle_list, container, false);

        this.viewModel = new ViewModelProvider(requireActivity()).get(MotorcycleViewModel.class);

        ListView bikesListView = view.findViewById(R.id.bikes_list);
        bikesListView.setAdapter(
            new ArrayAdapter<>(
                    this.getActivity(),
                    android.R.layout.simple_list_item_1,
                    this.viewModel.getMotorcycleList()
            )
        );

        bikesListView.setOnItemClickListener((parent, itemView, position, id) -> {
            TextView textView = (TextView) itemView;
            this.viewModel.selectMotorcycle(textView.getText().toString());
            ((MainActivity) requireActivity()).showMotorcycleInfoFragment();
        });

        return view;
    }
}