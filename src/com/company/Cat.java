package com.company;

public class Cat extends Animal {

    private final int startPrice = 30;
    private String name;
    private final String animalType = "cat";
    private int currentPrice;
    private int health = 100;

    public Cat() {
    }
    public Cat(String name, String gender) {
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
        double tempNumber = ((this.health / 100.0) * this.startPrice);
        this.currentPrice = (int) tempNumber;
        return this.currentPrice;
    }
    @Override
    public boolean canEat(Food food) {
        if(food instanceof Meat){
            return true;
        }
        return food instanceof SuperFood;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }
}
