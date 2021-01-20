package com.company;

public class Crocodile extends Animal {

    private int startPrice = 40;
    private String name;
    private String animalType = "crocodile";
    private int currentPrice;
    private int health = 100;

    public Crocodile() {
    }
    public Crocodile(String name, String gender) {
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
        return food instanceof SuperFood;
    }
}
