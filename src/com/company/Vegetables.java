package com.company;

public class Vegetables extends Food {

    private String name = "Vegetables";
    private int kiloGrams = 1;
    private int price  = 5;

    public Vegetables() {
    }

    @Override
    public String getName() {
        return name;
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

    @Override
    public int getPrice() {
        return price;
    }
}
