package com.company;

import java.io.Serializable;

public abstract class Animal implements Serializable {

    private String name; // What user names them.
    GameHelper helper = new GameHelper();

    private Gender gender; // Animal gender.
    private int health; // Animals health.
    private int startPrice; // Starting price for each animal
    private String animalType; // What kind of animal it is.
    private int age;
    private int maxAge;
    // Enum class with the genders for the animals.
    public enum Gender{
        FEMALE, MALE;
        //A method for getting random gender when breeding. Wont allow user to choose new animals gender.
        public static Gender getRandom(){
            return values()[(int) (Math.random() * values().length)];
        }
    }
    // Empty constructor if needed.
    public Animal() {
    }

    public Animal(String name, String gender) {
        this.name = name;
        this.gender = Gender.valueOf(gender.toUpperCase());
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
        // Current price is when animal loose health, value drops.
        double tempNumber = ((this.health / 100.0) * this.startPrice);
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

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }
}
