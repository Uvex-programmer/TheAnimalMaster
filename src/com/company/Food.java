package com.company;

public abstract class Food {

    private String name;
    private int kiloGrams = 1;
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
