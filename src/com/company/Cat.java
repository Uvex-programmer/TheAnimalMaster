package com.company;

public class Cat extends Animal {

    private int price = 30;
    private String name;
    private String animalType = "cat";

    public Cat() {
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
