package com.company;

public class Wolf extends Animal{

    private int price = 50;
    private String name;
    private String animalType = "wolf";

    public Wolf() {
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAnimalType() {
        return animalType;
    }
}
