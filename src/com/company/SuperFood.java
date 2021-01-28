package com.company;

public class SuperFood extends Food{

    private String name = "Super food";
    private int kiloGrams = 1;
    private int price = 25;

    public SuperFood() {
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
