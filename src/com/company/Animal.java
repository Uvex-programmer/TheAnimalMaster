package com.company;

public class Animal {

    private String name; // What user names them.
    GameHelper helper = new GameHelper();

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

    public String getAnimalType() {
        return animalType;
    }

    public boolean canEat(Food food){
        return false;
    }
    public void eat(Food food){
        if(this.health >= 100){
            this.health = 100;
            System.out.println("This animal is already at full health! ");
            helper.menuHelper();
        }
        if (this.health < 100){
            this.health = this.health + (int)(this.health * 0.10);
            if(this.health > 100){
                this.health = 100;
                System.out.println(getName() + " -> is now at full health!" + getHealth() + "%");
                helper.menuHelper();

            }
        }
    }
}
