package com.company;

public class Rat extends Animal{

    private int startPrice = 10;
    private String name;
    private String animalType = "rat";
    private int currentPrice;
    private int health = 100;

    public Rat() {
    }

    public Rat(String name, String gender){
        super(name, gender);
    }

    @Override
    public int getCurrentPrice() {
        this.currentPrice = (this.health / 100) * this.startPrice;
        return this.currentPrice;
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
    public boolean canEat(Food food) {
        if(food instanceof Vegetables){
            return true;
        }
        if(food instanceof Meat){
            return true;
        }
        if(food instanceof DryFood){
            return true;
        }
        return food instanceof SuperFood;
    }
}
