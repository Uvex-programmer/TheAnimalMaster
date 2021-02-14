package com.company.foods;

import java.io.Serializable;

public abstract class Food implements Serializable {

    protected String name;
    protected int kiloGrams = 1;
    protected int price;

    public Food() {
    }

    public String getName() {
        return name;
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
