package com.company;

public class Rat extends Animal{

    private int startPrice = 10;
    private String name;
    private String animalType = "rat";
    private int currentPrice;
    private int health = 100;
    private int age = 0;
    private int maxAge = 4;


    public Rat() {
    }

    public Rat(String name, String gender){
        super(name, gender);
    }

    @Override
    public int getCurrentPrice() {
        double tempNumber = ((this.health / 100.0) * this.startPrice);
        tempNumber = tempNumber - (this.age * 2);
        this.currentPrice = (int) tempNumber;
        return this.currentPrice;
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
                if(food instanceof SuperFood){
                    if(this.health >= 50)
                        this.health = this.health + (int) (this.health * 0.20);
                    if(this.health < 50)
                        this.health = this.health + 15;
                }else{
                    if(this.health >= 50)
                        this.health = this.health + (int) (this.health * 0.20);
                    if(this.health < 50)
                        this.health = this.health + 15;
                }
                if (this.health > 100) {
                    this.health = 100;
                    System.out.println(getName() + " -> is now at full health!" + getHealth() + "%");
                    GameHelper.menuHelper();

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

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = this.age + age;
    }

    @Override
    public int getMaxAge() {
        return maxAge;
    }

    @Override
    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

}
