package com.company;

public class Wolf extends Animal{

    private int startPrice = 50;
    private String name;
    private String animalType = "wolf";
    private int currentPrice;
    private int health = 100;

    public Wolf() {
    }

    public Wolf(String name, String gender){
        super(name, gender);
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
    @Override
    public boolean canEat(Food food) {
        if(food instanceof Meat){
            return true;
        }
        if(food instanceof DryFood){
            return true;
        }
        return food instanceof SuperFood;
    }
}
