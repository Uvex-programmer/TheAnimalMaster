package com.company;

import com.company.animals.Animal;
import com.company.foods.Food;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.Scanner;

public class Player implements Serializable {
    boolean canBuyFood = true;
    boolean canBuyAnimal = true;
    boolean canSellAnimal = true;
    boolean canBreed = true;
    boolean canFeed = true;

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
    public void sellAnimal(Player player, Animal animal){
            GameHelper.menuClearScreen();
            System.out.println("You want to sell animal: " + animal.getName() + " for " + animal.getCurrentPrice() + "$?");
            System.out.println("""
                    # |1| - Yes.
                    # |2| - No.""");
            switch (GameHelper.tryCatch(1, 2)) {
                case 1 -> {
                    player.addMoney(animal.getCurrentPrice());
                    animals.remove(animal);
                }
                case 2 -> {
                }
            }
    }
    public void removeMoney(int money) {
        this.money = this.money - money;
    }
    public void addMoney(int money){
        this.money = this.money + money;
    }
    public boolean listContains(Animal animal1) {
        for (Animal animal : animals) {
            if (animal.getAnimalType().equals(animal1.getAnimalType()) && !animal.getGender().equals(animal1.getGender())) {
                return true;
            }
        }
        return false;
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
                System.out.println("[" + count + "] " + animal.getName() + " --> " + animal.getAnimalType() + " | " + animal.getGender() + " | " + animal.getHealth() + "% health." + " Age: " + animal.getAge());
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
                        GameHelper.menuClearScreen();
                        getPlayerAnimal();
                        System.out.println("Which animal do you want to feed? Enter name of animal: ");
                        choice1 = Integer.parseInt(input.nextLine());
                    } catch (Exception e) {
                        GameHelper.menuClearScreen();
                        System.out.println("You must enter a number.");
                        GameHelper.menuHelper();
                    }
                }
                while (choice2 < 1 || choice2 > foods.size()) {
                    try {
                        GameHelper.menuClearScreen();
                        getFood();
                        System.out.println("\nWhich food do you wanna use ?");
                        choice2 = Integer.parseInt(input.nextLine());
                    } catch (Exception e) {
                        GameHelper.menuClearScreen();
                        System.out.println("You must enter a number.");
                        GameHelper.menuHelper();
                    }
                }

                if (animals.get(choice1 - 1).canEat(foods.get(choice2 - 1))) {
                    animals.get(choice1 - 1).eat(foods.get(choice2 - 1));
                    foods.get(choice2 - 1).setKiloGrams(-1);
                    player.setAllBooleanFalse();
                    player.setCanFeed(true);
                    if (foods.get(choice2 - 1).getKiloGrams() <= 0) {
                        foods.remove(foods.get(choice2 - 1));
                    }
                } else {
                    GameHelper.menuClearScreen();
                    System.out.println("This animal can't eat this food!");
                    GameHelper.menuHelper();
                }
            } else {
                GameHelper.menuClearScreen();
                System.out.println("There is no food or no animals to feed.");
                GameHelper.menuHelper();
            }
        }else{
            choiceIsMade();
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
    //Calls this method in beginning of each round so player can choose which action to take.
    public void setAllBooleanTrue(){
        canBreed = true;
        canBuyAnimal = true;
        canBuyFood = true;
        canSellAnimal = true;
        canFeed = true;
    }
    //When player made a choice they cant do anything else
    public void setAllBooleanFalse(){
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
    public void choiceIsMade() {
        GameHelper.menuClearScreen();
        System.out.println("You already made your move this round!");
        System.out.println("Must wait for next round.");
        GameHelper.menuHelper();
    }
    public boolean checkIfTrue(boolean bool){
        return bool;
    }
}
