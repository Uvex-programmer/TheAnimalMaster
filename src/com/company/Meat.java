package com.company;

public class Meat extends Food{

    private String name = "Meat";
    private int kiloGrams = 1;
    private int price = 10;

    public Meat() {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getKiloGrams() {
        return kiloGrams;
    }

    @Override
    public void setKiloGrams(int kiloGrams) {
        this.kiloGrams = this.kiloGrams + kiloGrams;
    }
}
