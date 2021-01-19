package com.company;

public class Food {

    private String name;
    private int kiloGrams;
    private int price;

    public Food() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKiloGrams() {
        return kiloGrams;
    }

    public void setKiloGrams(int kiloGrams) {
        this.kiloGrams = this.kiloGrams + kiloGrams;
    }

    public int getPrice() {
        return price;
    }
}
