package com.company;

import com.company.animals.*;
import com.company.foods.*;

import java.io.Serializable;
import java.util.Scanner;

public class Store implements Serializable {

    public Store() {
    }

    public void storeMenu(Player player){
        boolean menuChecker = true;
        while(menuChecker) {
            GameHelper.clearScreen();
            player.getPlayerInventory();
            int choice = GameHelper.printMenu("THE STORE", " Buy animals", " Buy foods", " Sell animals\n",
                    " Exit store");

            switch (choice) {
                case 1 -> animalStore(player);
                case 2 -> foodStore(player);
                case 3 -> sellAnimalsShop(player);
                case 4 -> menuChecker = false;
            }
        }
    }

    public void animalStore(Player player){

        boolean menuChecker = true;
        while(menuChecker) {
            GameHelper.clearScreen();
            player.getPlayerInventory();
            int choice = GameHelper.printMenu("ANIMAL STORE", " Rat 10$", " Parrot 20$", " Cat 30$",
                    " Crocodile 40$", " Wolf 50$\n", " Exit animal store");

                switch (choice) {
                    case 1 -> addAnimal(player, new Rat());
                    case 2 -> addAnimal(player, new Parrot());
                    case 3 -> addAnimal(player, new Cat());
                    case 4 -> addAnimal(player, new Crocodile());
                    case 5 -> addAnimal(player, new Wolf());
                    case 6 -> menuChecker = false;
                }
            }
        }

    public void foodStore(Player player){
        boolean menuChecker = true;
        while(menuChecker) {
            GameHelper.clearScreen();
            player.getPlayerInventory();
            int choice = GameHelper.printMenu("FOOD STORE", " Dry food 1kg - 5$ (Rat)",
                    " Vegetables 1kg - 5 $ (Parrot)", " Meat 1kg - 10$ (Cat, Wolf, Crocodile)\n", " Exit food store");

            switch (choice) {
                case 1 -> addFood(player, new DryFood());
                case 2 -> addFood(player, new Vegetables());
                case 3 -> addFood(player, new Meat());
                case 4 -> menuChecker = false;
            }
        }
    }

    public void sellAnimalsShop(Player player){
        boolean menuChecker = true;
        if (player.animals.size() > 0) {
        while(menuChecker) {
                GameHelper.clearScreen();
                player.getWallet();
                System.out.println("""
                        -----------------------------------
                        |          ANIMAL STORE           |
                        -----------------------------------
                        # WHICH ANIMAL DO YOU WANNA SELL? #""");
                int counter = 1;
                for (Animal animal : player.animals) {
                    System.out.println("# |" + counter + "| - " + animal.getName() + " -> " + animal.getGender() +
                            " | " + animal.getAnimalType() + " | " + animal.getHealth() + "% health" + " value: " + animal.getCurrentPrice());
                    counter++;
                }
                System.out.println("\n# |0| - Exit store.");
                    int index = GameHelper.tryCatch(0, player.animals.size());
                    if (index == 0) {
                        menuChecker = false;
                    } else {
                        if(player.checkIfTrue(player.canSellAnimal)) {
                            sellAnimal(player, player.animals.get(index - 1));
                        }
                    }
                }
        }else{
            GameHelper.printText("You don't have any animals to sell!");
        }
    }

    public void addAnimal(Player player, Animal animal){
        Scanner input = new Scanner(System.in);

        if(player.checkIfTrue(player.canBuyAnimal)) {
            if (player.getMoney() < animal.getStartPrice()) {
                GameHelper.printText("You don't have enough money for this animal.");

            } else {

                int choice = GameHelper.printQuestion("You want to buy a " + animal.getAnimalType() + " for " +
                        animal.getStartPrice() + "$?", " Yes", " No");

                if (choice == 1) {
                    GameHelper.clearScreen();
                    System.out.print("Enter a name for your " + animal.getAnimalType() + ": ");
                    animal.setName(input.nextLine());

                    choice = GameHelper.printQuestion("Choose gender for your " + animal.getAnimalType() + "!",
                            " Male", " Female");

                    if (choice == 1) animal.setGender("MALE");
                    if (choice == 2) animal.setGender("FEMALE");

                    afterAnimalPurchase(player, animal);

                }
            }
        }
    }

    private void afterAnimalPurchase(Player player, Animal animal) {
        player.animals.add(animal);
        player.removeMoney(animal.getStartPrice());
        player.closeOptions();
        player.setCanBuyAnimal(true);
    }

    public void addFood(Player player, Food food){
        if(player.checkIfTrue(player.canBuyFood)) {
            if (player.getMoney() < food.getPrice()) {
                GameHelper.printText("You don't have enough money to buy this food.");
            } else {
                int foodCounter = 0;
                int choice = GameHelper.printQuestion("You want to buy 1kg of " + food.getName() + " for " +
                        food.getPrice() + "$?", " Yes", " No");
                if (choice == 1) {
                    if (player.foods.size() > 0) {
                        for (Food food1 : player.foods) {
                            if (food1.getName().equals(food.getName())) {
                                food1.setKiloGrams(1);
                                player.removeMoney(food.getPrice());
                                player.closeOptions();
                                player.setCanBuyFood(true);
                                foodCounter++;
                            }
                        }
                    }
                    if (foodCounter == 0) {
                        player.foods.add(food);
                        player.removeMoney(food.getPrice());
                        player.closeOptions();
                        player.setCanBuyFood(true);
                    }
                }
            }
        }
    }

    public void sellAnimal(Player player, Animal animal){
        int choice = GameHelper.printQuestion("You want to sell animal: " + animal.getName() + " for " +
                animal.getCurrentPrice() + "$?", " Yes", " No");
        if(choice == 1) {
            player.addMoney(animal.getCurrentPrice());
            player.animals.remove(animal);
            player.closeOptions();
            player.setCanSellAnimal(true);

        }
    }
}
