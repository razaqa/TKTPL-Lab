package id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab.repository;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab.model.Motorcycle;

public class MotorcycleRepository {
    private HashMap<String, Motorcycle> motorcycles = new HashMap<>();

    private static MotorcycleRepository instance;

    private MotorcycleRepository() {
        Motorcycle[] motorcycleArray = new Motorcycle[]{
                new Motorcycle(
                        "H2",
                        "Kawasaki",
                        "Sport Fairing",
                        "660 juta"
                ),
                new Motorcycle(
                        "S 1000 RR",
                        "BMW",
                        "Sport Fairing",
                        "477 juta"
                ),
                new Motorcycle(
                        "1299 Panigale S",
                        "Ducati",
                        "Sport Fairing",
                        "403 juta"
                ),
                new Motorcycle(
                        "R Nine T Racer",
                        "BMW",
                        "Retro Cafe Racer",
                        "595 juta"
                ),
                new Motorcycle(
                        "ZX10-R",
                        "Kawasaki",
                        "Sport Fairing",
                        "460 juta"
                ),
                new Motorcycle(
                        "Z1000",
                        "Kawasaki",
                        "Sport Naked",
                        "329 juta"
                ),
                new Motorcycle(
                        "XDiavel",
                        "Ducati",
                        "Cruiser",
                        "988 juta"
                ),
                new Motorcycle(
                        "MT-10",
                        "Yamaha",
                        "Sport Naked",
                        "386 juta"
                ),
                new Motorcycle(
                        "CRF1000L Africa Twin",
                        "Honda",
                        "Dual-sport Adventure",
                        "464 juta"
                ),
                new Motorcycle(
                        "Brutale 1090 RR",
                        "MV Agusta",
                        "Sport Naked",
                        "989 juta"
                )
        };
        for (Motorcycle motorcycle : motorcycleArray) {
            this.motorcycles.put(motorcycle.getName(), motorcycle);
        }
    }

    public static MotorcycleRepository getInstance() {
        if (instance == null) {
            instance = new MotorcycleRepository();
        }
        return instance;
    }

    public List<String> getMotorcycleList() {
        return this.motorcycles.values()
                .stream()
                .map(Motorcycle::getName)
                .collect(Collectors.toList());
    }

    public Motorcycle getMotorcycleInfo(String name) {
        return this.motorcycles.get(name);
    }
}
