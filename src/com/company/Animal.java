package com.company;

public class Animal {

    private String name;
    private Gender gender;
    private int health = 100;
    private int price;
    private String animalType;

    public enum Gender{
        FEMALE, MALE;
        public static Gender getRandom(){
            return values()[(int) (Math.random() * values().length)];
        }
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }
}
