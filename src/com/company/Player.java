package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player implements Serializable {
    GameHelper helper = new GameHelper();
    boolean canBuyFood = true;
    boolean canBuyAnimal = true;
    boolean canSellAnimal = true;
    boolean canBreed = true;
    boolean canFeed = true;

    private final String name;
    private int money = 200;
    ArrayList<Animal> animals = new ArrayList<>();
    ArrayList<Food> foods = new ArrayList<>();

    public Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void sellAnimal(Player player, Animal animal){
            helper.menuClearScreen();
            System.out.println("You want to sell animal: " + animal.getName() + " for " + animal.getCurrentPrice() + "$?");
            System.out.println("""
                    # |1| - Yes.
                    # |2| - No.""");
            switch (helper.tryCatch(1, 2)) {
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
    public void breedAnimal(Player player, ArrayList<Animal> animals){
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int animal1 = 0;
        int animal2 = 0;
        int chanceOfBreed = random.nextInt(101);
        ArrayList<Animal> tempList = new ArrayList<>();
        if(checkIfTrue(canBreed)) {
            if (animals.size() == 0) {
                helper.menuClearScreen();
                System.out.println("You don't have any animals to breed!");
                helper.menuHelper();
                return;
            } else {
                while (animal1 < 1 || animal1 > animals.size()) {
                    helper.menuClearScreen();
                    getPlayerAnimal();
                    System.out.println("\nChoose your first animal for breeding! Enter number: ");
                    try {
                        animal1 = Integer.parseInt(input.nextLine());
                    } catch (Exception e) {
                        helper.menuClearScreen();
                        System.out.println("You must enter a number for an Animal.");
                        helper.menuHelper();
                    }
                }
                if (listContains(animals.get(animal1 - 1))) {
                    for (Animal animal : animals) {
                        if (!(checkLeftForBreed(animals.get(animal1 - 1), animal))) {
                            tempList.add(animal);
                        }
                    }
                } else {
                    System.out.println("\n".repeat(20) + "There is no mate for " + animals.get(animal1 - 1).getName() + "!");
                    helper.menuHelper();
                    return;
                }
                while (animal2 < 1 || animal2 > tempList.size()) {
                    System.out.println("\n".repeat(20) + "You can pair " + animals.get(animal1 - 1).getName() + " with: \n");
                    int count = 1;
                    for (Animal animal : tempList) {
                        System.out.println("[" + count + "] " + animal.getName() + " --> " + animal.getAnimalType() + " | " + animal.getGender() + " | " + animal.getHealth() + "% health left.");
                        count++;
                    }
                    System.out.println("\nChoose your second animal for breeding! Enter number: ");
                    try {
                        animal2 = Integer.parseInt(input.nextLine());
                    } catch (Exception e) {
                        System.out.println("\n".repeat(20) + "You must enter a number for an Animal.");
                        helper.menuHelper();
                    }
                }
            }
            if (checkForBreed(animals.get(animal1 - 1), tempList.get(animal2 - 1))) {
                if (chanceOfBreed > 50) {
                    System.out.println("\n".repeat(20) + "Your breeding succeeded!");
                    //String gender = Animal.Gender.getRandom().toString();
                    String gender = Animal.Gender.MALE.toString();
                    System.out.println("You got a  " + animals.get(animal1 - 1).getAnimalType() + " that is " + gender + "!");
                    System.out.print("Enter name: ");
                    String name = input.nextLine();

                    if (animals.get(animal1 - 1).getAnimalType().equals("rat")) {
                        newAnimal(player, new Rat(name, gender));
                    }
                    if (animals.get(animal1 - 1).getAnimalType().equals("parrot")) {
                        newAnimal(player, new Parrot(name, gender));
                    }
                    if (animals.get(animal1 - 1).getAnimalType().equals("cat")) {
                        newAnimal(player, new Cat(name, gender));
                    }
                    if (animals.get(animal1 - 1).getAnimalType().equals("crocodile")) {
                        newAnimal(player, new Crocodile(name, gender));
                    }
                    if (animals.get(animal1 - 1).getAnimalType().equals("wolf")) {
                        newAnimal(player, new Wolf(name, gender));
                    }
                } else {
                    helper.menuClearScreen();
                    System.out.println("Breeding failed!! ");
                    player.setAllBooleanFalse();
                    helper.menuHelper();
                }
            } else {
                helper.menuClearScreen();
                System.out.println("Can't breed these two animals!");
                helper.menuHelper();
            }
        }else{
            choiceIsMade();
        }
    }
    public boolean listContains(Animal animal1) {
        for (Animal animal : animals) {
            if (animal.getAnimalType().equals(animal1.getAnimalType()) && !animal.getGender().equals(animal1.getGender())) {
                return true;
            }
        }
        return false;
    }
    public void newAnimal(Player player, Animal animal){
        player.animals.add(animal);
        helper.menuClearScreen();
        System.out.println("You now got a new " + animal.getAnimalType() + " named: " + animal.getName() + " | " + animal.getGender());
        player.setAllBooleanFalse();
        helper.menuHelper();
    }
    public boolean checkLeftForBreed(Animal animal1, Animal animal2){
        return animal1.getGender().equals(animal2.getGender()) || animal1.getName().equals(animal2.getName()) || !animal1.getAnimalType().equals(animal2.getAnimalType());
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
                System.out.println("[" + count + "] " + animal.getName() + " --> " + animal.getAnimalType() + " | " + animal.getGender() + " | " + animal.getHealth() + "% health left.");
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
                        helper.menuClearScreen();
                        getPlayerAnimal();
                        System.out.println("Which animal do you want to feed? Enter name of animal: ");
                        choice1 = Integer.parseInt(input.nextLine());
                    } catch (Exception e) {
                        helper.menuClearScreen();
                        System.out.println("You must enter a number.");
                        helper.menuHelper();
                    }
                }
                while (choice2 < 1 || choice2 > foods.size()) {
                    try {
                        helper.menuClearScreen();
                        getFood();
                        System.out.println("\nWhich food do you wanna use ?");
                        choice2 = Integer.parseInt(input.nextLine());
                    } catch (Exception e) {
                        helper.menuClearScreen();
                        System.out.println("You must enter a number.");
                        helper.menuHelper();
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
                    helper.menuClearScreen();
                    System.out.println("This animal can't eat this food!");
                    helper.menuHelper();
                }
            } else {
                helper.menuClearScreen();
                System.out.println("There is no food or no animals to feed.");
                helper.menuHelper();
            }
        }else{
            choiceIsMade();
        }
    }
    public void getWallet() {
        System.out.println("Wallet: " + this.money + "$");
    }
    public boolean checkForBreed(Animal animal1, Animal animal2){
        return animal1.getAnimalType().equals(animal2.getAnimalType()) && animal1.getGender() != animal2.getGender();
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
        helper.menuClearScreen();
        System.out.println("You already made your move this round!");
        System.out.println("Must wait for next round.");
        helper.menuHelper();
    }
    public boolean checkIfTrue(boolean bool){
        return bool;
    }
    public boolean lostGame(Player player){
        return animals.size() <= 0 && getMoney() <= 0;
    }
}
