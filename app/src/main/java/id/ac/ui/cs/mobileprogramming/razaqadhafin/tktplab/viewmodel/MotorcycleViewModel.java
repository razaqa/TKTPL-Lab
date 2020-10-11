package id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab.model.Motorcycle;
import id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab.repository.MotorcycleRepository;

public class MotorcycleViewModel extends ViewModel {

    private final MutableLiveData<String> selectedMotorcycle = new MutableLiveData<>();
    private final MotorcycleRepository repository = MotorcycleRepository.getInstance();

    public List<String> getMotorcycleList() {
        return this.repository.getMotorcycleList();
    }

    public Motorcycle getMotorcycleInfo(String name) {
        return this.repository.getMotorcycleInfo(name);
    }

    public void selectMotorcycle(String MotorcycleName) {
        this.selectedMotorcycle.setValue(MotorcycleName);
    }

    public LiveData<String> getSelectedMotorcycle() {
        return this.selectedMotorcycle;
    }
}