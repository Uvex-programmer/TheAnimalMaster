package com.company;

public class Parrot extends Animal {

    private int price = 20;
    private String name;
    private String animalType = "parrot";

    public Parrot() {
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
