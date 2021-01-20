package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player {
    GameHelper helper = new GameHelper();

    private String name;
    private int money = 200;
    ArrayList<Animal> animals = new ArrayList<>();
    ArrayList<Food> foods = new ArrayList<>();

    public Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }
    public void addAnimal(Player player, Animal animal){
        Scanner input = new Scanner(System.in);
        helper.menuClearScreen();
        System.out.println("You want to buy a " + animal.getAnimalType() + " for " + animal.getStartPrice() + "$?");
        System.out.println("""
                            # |1| - Yes.
                            # |2| - No.""");
        switch(helper.tryCatch(1, 2)){
            case 1 -> {
                helper.menuClearScreen();
                System.out.print("Enter a name for your " + animal.getAnimalType() + ": ");
                animal.setName(input.nextLine());
                System.out.println("Choose gender for your " + animal.getAnimalType() + "!");
                System.out.println("""
                            # |1| - MALE.
                            # |2| - FEMALE.""");
                switch (helper.tryCatch(1, 2)){
                    case 1 -> animal.setGender("MALE");
                    case 2 -> animal.setGender("FEMALE");
                }
                animals.add(animal);
                player.removeMoney(animal.getStartPrice());
            }
            case 2 -> {}
        }
    }
    public void addFood(Player player, Food food){
        Scanner input = new Scanner(System.in);
        int foodCounter = 0;
        helper.menuClearScreen();
        System.out.println("You want to buy 1kg of " + food.getName() + " for " + food.getPrice() + "$?");
        System.out.println("""
                            # |1| - Yes.
                            # |2| - No.""");
        switch(helper.tryCatch(1, 2)){
            case 1 -> {
                if(foods.size() > 0) {
                    for (Food food1 : foods) {
                        if (food1.getName().equals(food.getName())){
                            food1.setKiloGrams(1);
                            foodCounter++;
                        }
                    }
                }
                if(foodCounter == 0){
                    foods.add(food);
                    player.removeMoney(food.getPrice());
                }

            }
            case 2 -> {
            }
        }
    }
    public void sellAnimal(Player player, Animal animal){
        helper.menuClearScreen();
        System.out.println("You want to sell animal: " + animal.getName() + " for " + animal.getCurrentPrice() + "$?");
        System.out.println("""
                            # |1| - Yes.
                            # |2| - No.""");
        switch(helper.tryCatch(1, 2)){
            case 1 -> {
                player.addMoney(animal.getCurrentPrice());
                animals.remove(animal);
            }
            case 2 -> {}
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

        if(animals.size() == 0){
            System.out.println("\n".repeat(20) + "You don't have any animals to breed!");
            helper.menuHelper();
            return;
        }else {
            while (animal1 < 1 || animal1 > animals.size()) {
                System.out.println("\n".repeat(20));
                getPlayerAnimal();
                System.out.println("\nChoose your first animal for breeding! Enter number: ");
                try {
                    animal1 = Integer.parseInt(input.nextLine());
                } catch (Exception e) {
                    System.out.println("\n".repeat(20));
                    System.out.println("You must enter a number for an Animal.");
                    helper.menuHelper();
                }
            }

            if (listContains(animals.get(animal1-1))) {
                for (Animal animal : animals) {
                    if (!(checkLeftForBreed(animals.get(animal1 - 1), animal))) {
                        tempList.add(animal);
                    }
                }
            }else{
                System.out.println("\n".repeat(20) + "There is no mate for " + animals.get(animal1-1).getName() + "!");
                helper.menuHelper();
                return;
            }

            while (animal2 < 1 || animal2 > tempList.size()) {
                System.out.println("\n".repeat(20) + "You can pair " + animals.get(animal1 - 1).getName() + " with: \n");
                int count = 1;
                for(Animal animal: tempList){
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

        if(checkForBreed(animals.get(animal1 - 1), tempList.get(animal2 - 1))){
            if(chanceOfBreed > 50){
                System.out.println("\n".repeat(20) + "Your breeding succeeded!");

                System.out.println("Enter new name for your new" + animals.get(animal1 - 1).getAnimalType() + "!");
                String name = input.nextLine();

                if(animals.get(animal1 - 1).getAnimalType().equals("Rat")){
                    newAnimal(player, new Rat(name, Animal.Gender.getRandom().toString()));
                }
                if(animals.get(animal1 - 1).getAnimalType().equals("Parrot")){
                    newAnimal(player, new Parrot(name, Animal.Gender.getRandom().toString()));
                }
                if(animals.get(animal1 - 1).getAnimalType().equals("Cat")){
                    newAnimal(player, new Cat(name, Animal.Gender.getRandom().toString()));
                }
                if(animals.get(animal1 - 1).getAnimalType().equals("Crocodile")){
                    newAnimal(player, new Crocodile(name, Animal.Gender.getRandom().toString()));
                }
                if(animals.get(animal1 - 1).getAnimalType().equals("Wolf")){
                    newAnimal(player, new Wolf(name, Animal.Gender.getRandom().toString()));
                }

            }else{
                System.out.println("\n".repeat(20) + "Breeding failed!! ");
                helper.menuHelper();
            }

        }else{
            System.out.println("\n".repeat(20) + "Can't breed these two animals!");
            helper.menuHelper();
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
        System.out.println("\n".repeat(20));
        System.out.println("You now got a new " + animal.getAnimalType() + " named: " + animal.getName() + " | " + animal.getGender());
        helper.menuHelper();
    }
    public boolean checkForBreed(Animal animal1, Animal animal2){
        return animal1.getAnimalType().equals(animal2.getAnimalType()) && animal1.getGender() != animal2.getGender();
    }
    public boolean checkLeftForBreed(Animal animal1, Animal animal2){
        return animal1.getGender().equals(animal2.getGender()) || animal1.getName().equals(animal2.getName()) || !animal1.getAnimalType().equals(animal2.getAnimalType());
    }
    public void getFood() {
        if(foods.size() > 0){
            System.out.println("Here is your food:\n");
            int count = 1;
            for(Food food: foods){
                System.out.println("[" + count + "] " + food.getName() + " " + food.getKiloGrams() + "kg.");
                count++;
            }
        }
    }
    public void getPlayerAnimal() {
        if(animals.size() == 0){
            System.out.println("\nYou don't have any animals right now!");
        }else{
            System.out.println("\nHere is your animals: ");
            int count = 1;
            for(Animal animal: animals){
                System.out.println("[" + count + "] " + animal.getName() + " --> " + animal.getAnimalType() + " | " + animal.getGender() + " | " + animal.getHealth() + "% health left.");
                count++;
            }
        }
    }
    public void feedAnimal(Player player){
        Scanner input = new Scanner(System.in);
        int choice1 = 0;
        int choice2 = 0;

        if(player.animals.size() > 0 && player.foods.size() > 0) {
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
            if(animals.get(choice1 - 1).canEat(foods.get(choice2 - 1))){
                animals.get(choice1 -1).eat(foods.get(choice2 - 1));
                foods.get(choice2 - 1).setKiloGrams(-1);
                if(foods.get(choice2 - 1).getKiloGrams() <= 0){
                    foods.remove(foods.get(choice2 - 1));
                }
            }else{
                helper.menuClearScreen();
                System.out.println("This animal can't eat this food!");
                helper.menuHelper();
            }
        }else{
            helper.menuClearScreen();
            System.out.println("There is no food or no animals to feed.");
            helper.menuHelper();
        }
    }
}
