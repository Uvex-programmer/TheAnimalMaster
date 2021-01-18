package com.company;

public class Rat extends Animal{

    private int price = 10;
    private String name;
    private String animalType = "rat";

    public Rat() {
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
