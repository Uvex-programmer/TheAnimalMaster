package com.company;

import java.util.ArrayList;
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
        System.out.println("You want to buy a" + animal.getAnimalType() + " for " + animal.getPrice() + "$?");
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
                player.removeMoney(animal.getPrice());
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
    public void removeMoney(int money) {
        this.money = this.money - money;
    }
}
