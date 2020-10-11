package id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab.model;

import lombok.Data;

@Data
public class Motorcycle {
    private String name;
    private String brand;
    private String type;
    private String price;

    public Motorcycle(String name, String brand, String type, String price) {
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}