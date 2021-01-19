package com.company;

public class Parrot extends Animal {

    private int startPrice = 20;
    private String name;
    private String animalType = "parrot";
    private int currentPrice;
    private int health = 100;


    public Parrot() {
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getStartPrice() {
        return startPrice;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAnimalType() {
        return animalType;
    }
    @Override
    public int getCurrentPrice() {
        this.currentPrice = (this.health / 100) * this.startPrice;
        return this.currentPrice;
    }
}
