package com.company;

public class Parrot extends Animal {

    private int startPrice = 20;
    private String name;
    private String animalType = "parrot";
    private int currentPrice;
    private int health = 100;


    public Parrot() {
    }
    public Parrot(String name, String gender) {
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
        if(food instanceof Vegetables){
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
                helper.menuHelper();
            }
            if (this.health < 100) {
                if(food instanceof SuperFood){
                    this.health = this.health + (int) (this.health * 0.20);
                    if (this.health > 100) {
                        this.health = 100;
                        System.out.println(getName() + " -> is now at full health!" + getHealth() + "%");
                        helper.menuHelper();

                    }
                }else{
                    this.health = this.health + (int) (this.health * 0.10);
                    if (this.health > 100) {
                        this.health = 100;
                        System.out.println(getName() + " -> is now at full health!" + getHealth() + "%");
                        helper.menuHelper();
                    }
                }
            }
        }
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
