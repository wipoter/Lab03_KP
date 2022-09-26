package com.lab03.Product;

public class Product {
    private String name;
    private String model;
    private String brand;
    private int cost;
    private int maxPower;

    public Product(String name, String model, String brand, int cost, int maxPower){
        this.name = name;
        this.model = model;
        this.brand = brand;
        this.cost = cost;
        this.maxPower = maxPower;
    }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getModel() { return this.model; }
    public void setModel(String model) { this.model = model; }

    public String getBrand() { return this.brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public int getCost() { return this.cost; }
    public void setCost(int cost) { this.cost = cost; }

    public int getMaxPower() { return this.maxPower; }
    public void setaxPower(int maxPower) { this.maxPower = maxPower; }

    @Override
    public String toString(){
        return "name: " + name + "\n" + "brand: " + brand + "\n" + "model: " + model + "\n" + "cost: " + cost + "\n" + "Max power: " + maxPower + "\n";
    }
}
