package com.company.animals;

import com.company.foods.Food;
import com.company.GameHelper;
import com.company.foods.Vegetables;

public class Parrot extends Animal {

    public Parrot() {
        startPrice = 20;
        maxAge = 4;
        animalType = "parrot";
        vetCost = 5;
    }

    @Override
    public boolean canEat(Food food) {
        return food instanceof Vegetables;
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
                if(food instanceof Vegetables){
                    if(this.health >= 50)
                        this.health = this.health + (int) (this.health * 0.10);
                    if(this.health < 50)
                        this.health = this.health + 10;
                }
                if (this.health > 100) {
                    this.health = 100;
                    System.out.println(getName() + " -> is now at full health!" + getHealth() + "%");
                    GameHelper.menuHelper();
                }
            }
        }
    }

}
