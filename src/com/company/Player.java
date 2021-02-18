package com.company;

import com.company.animals.Animal;
import com.company.foods.Food;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.Scanner;

public class Player implements Serializable {

    public boolean canFeed, canBreed, canSellAnimal, canBuyAnimal, canBuyFood;

    private final String name;
    private int money = 400;

    ArrayList<Animal> animals = new ArrayList<>();
    ArrayList<Food> foods = new ArrayList<>();

    public Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void removeMoney(int money) {
        this.money = this.money - money;
    }

    public void addMoney(int money){
        this.money = this.money + money;
    }

    public void getFood() {
        if(foods.size() > 0){
            System.out.println("\n#### Here is your food #### ");
            int count = 1;
            for(Food food: foods){
                System.out.println("[" + count + "] " + food.getName() + " " + food.getKiloGrams() + "kg.");
                count++;
            }
            System.out.println("###########################\n ");
        }
    }

    public void getPlayerAnimal() {
        if(animals.size() > 0){
            System.out.println("\n#### Here is your animals ##### ");
            int count = 1;
            for(Animal animal: animals) {
                System.out.println("[" + count + "] " + animal.getName() + " --> " + animal.getAnimalType() + " | " +
                        animal.getGender() + " | " + animal.getHealth() + "% health." + " Age: " + animal.getAge());
                count++;
            }
            System.out.println("############################### ");
        }
    }

    public void feedAnimal(Player player) {
        Scanner input = new Scanner(System.in);
        int choice1 = 0;
        int choice2 = 0;
        if (checkIfTrue(canFeed)) {

            if (player.animals.size() > 0 && player.foods.size() > 0) {
                while (choice1 < 1 || choice1 > animals.size()) {
                    try {
                        GameHelper.clearScreen();
                        getPlayerAnimal();
                        System.out.println("Which animal do you want to feed? Enter name of animal: ");
                        choice1 = Integer.parseInt(input.nextLine());
                    } catch (Exception e) {
                        GameHelper.printText("You must enter a number.");
                    }
                }

                    while (choice2 < 1 || choice2 > foods.size()) {
                        try {
                            GameHelper.clearScreen();
                            getFood();
                            System.out.println("\nWhich food do you wanna use ?");
                            choice2 = Integer.parseInt(input.nextLine());
                        } catch (Exception e) {
                            GameHelper.printText("You must enter a number.");
                        }
                    }
                    Food food = foods.get(choice2 - 1);
                    Animal animal = animals.get(choice1 - 1);

                    if (animal.canEat(food)) {
                        animal.eat(food);
                        food.setKiloGrams(-1);
                        player.closeOptions();
                        player.setCanFeed(true);
                        if (food.getKiloGrams() <= 0) {
                            foods.remove(food);
                        }
                    } else {
                        GameHelper.printText("This animal can't eat this food!");
                    }
            } else {
                GameHelper.printText("There is no food or no animals to feed.");
            }
        }
    }

    public void getWallet() {
        System.out.println("Wallet: " + this.money + "$");
    }

    public void getPlayerInventory(){
        getPlayerAnimal();
        getFood();
        getWallet();
    }

    public int getMoney(){
        return this.money;
    }

    public void openOptions(){
        canBreed = true;
        canBuyAnimal = true;
        canBuyFood = true;
        canSellAnimal = true;
        canFeed = true;
    }

    public void closeOptions(){
        canBuyAnimal = false;
        canBuyFood = false;
        canSellAnimal = false;
        canBreed = false;
        canFeed = false;
    }

    public void setCanBuyFood(boolean canBuyFood) {
        this.canBuyFood = canBuyFood;
    }

    public void setCanBuyAnimal(boolean canBuyAnimal) {
        this.canBuyAnimal = canBuyAnimal;
    }

    public void setCanSellAnimal(boolean canSellAnimal) {
        this.canSellAnimal = canSellAnimal;
    }

    public void setCanFeed(boolean canFeed) {
        this.canFeed = canFeed;
    }

    public boolean checkIfTrue(boolean bool){
        if(!bool) {
            GameHelper.printText("You have already made your choice this turn!");
            return false;
        }
        return true;
    }
}
