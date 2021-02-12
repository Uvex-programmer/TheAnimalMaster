package com.company.animals;

import com.company.GameHelper;
import com.company.foods.*;

public class Rat extends Animal {

    private final int maxAge;
    private final String animalType;
    private final int startPrice;
    private final int vetCost;
    private int health;

    public Rat() {
        this.maxAge = 4;
        this.startPrice = 10;
        this.animalType = "rat";
        this.vetCost = 5;
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

    @Override
    public void eat(Food food) {
        if (canEat(food)) {
            if (this.health >= 100) {
                this.health = 100;
                System.out.println("This animal is already at full health! ");
                GameHelper.menuHelper();
            }
            if (this.health < 100) {
                if(this.health >= 50)
                    this.health = this.health + (int) (this.health * 0.20);
                if(this.health < 50)
                    this.health = this.health + 15;
                if (this.health > 100) {
                    this.health = 100;
                    System.out.println(getName() + " -> is now at full health!" + getHealth() + "%");
                    GameHelper.menuHelper();

                }
            }
        }
    }

    @Override
    public int getMaxAge() {
        return maxAge;
    }

    @Override
    public String getAnimalType() {
        return animalType;
    }

    @Override
    public int getStartPrice() {
        return startPrice;
    }

    @Override
    public int getVetCost() {
        return vetCost;
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
