package com.company.animals;

import com.company.foods.Food;
import com.company.GameHelper;
import com.company.foods.Meat;
import com.company.foods.SuperFood;

public class Wolf extends Animal {

    public Wolf() {
        animalType = "wolf";
        maxAge = 7;
        startPrice = 50;
        vetCost = 20;
    }

    @Override
    public boolean canEat(Food food) {
        if(food instanceof Meat){
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
                if(food instanceof SuperFood){
                    if(this.health >= 50)
                        this.health = this.health + (int) (this.health * 0.20);
                    if(this.health < 50)
                        this.health = this.health + 15;
                }else{
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
