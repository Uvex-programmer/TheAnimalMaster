package com.company;

public class Animal {

    private String name; // What user names them.

    private Gender gender; // Animal gender.
    private int health = 100; // Animals health.
    private int startPrice; // Starting price for each animal when you buy in shop.
    private int currentPrice; // Current price is when animal loose health, value drops.
    private String animalType; // What kind of animal it is.
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

    public int getCurrentPrice() {
        this.currentPrice = (this.health / 100) * this.startPrice;
        return this.currentPrice;
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

    public void setStartPrice(int startPrice) {
        this.startPrice = startPrice;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }
}
