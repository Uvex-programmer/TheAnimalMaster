package com.company;

public class Crocodile extends Animal {

    private int price = 40;
    private String name;
    private String animalType = "crocodile";

    public Crocodile() {
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
