package com.company.animals;

import com.company.foods.Food;
import com.company.GameHelper;

import java.io.Serializable;

public abstract class Animal implements Serializable {

    private String name;
    private Gender gender;
    private int health = 100;
    private int startPrice;
    private String animalType;
    private int age = 0;
    private int maxAge;
    private boolean sick = false;
    private int vetCost;

    public enum Gender{
        FEMALE, MALE;

        public static Gender getRandom(){
            return values()[(int) (Math.random() * values().length)];
        }
    }

    public Animal() {
    }

    public boolean isSick() {
        return sick;
    }

    public void setSick(boolean sick) {
        this.sick = sick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = Gender.valueOf(gender.toUpperCase());
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStartPrice() {
        return startPrice;
    }

    public int getCurrentPrice() {
        double tempNumber = ((this.health / 100.0) * this.startPrice);
        tempNumber = tempNumber - (this.age * 2);
        return (int) tempNumber;
    }

    public String getAnimalType() {
        return animalType;
    }

    public boolean canEat(Food food){
        return true;
    }

    public void eat(Food food){
        if(canEat(food)) {
            if (this.health >= 100) {
                this.health = 100;
                System.out.println("This animal is already at full health! ");
                GameHelper.menuHelper();
            }
            if (this.health < 100) {
                this.health = this.health + (int) (this.health * 0.10);
                if (this.health > 100) {
                    this.health = 100;
                    System.out.println(getName() + " -> is now at full health!" + getHealth() + "%");
                    GameHelper.menuHelper();

                }
            }
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = this.age + age;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public int getVetCost() {
        return vetCost;
    }
}
