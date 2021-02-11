package com.company;

import com.company.animals.*;
import com.company.foods.*;

import java.io.Serializable;
import java.util.Scanner;

public class Store implements Serializable {

    public Store() {
    }

    public void mainMenu(Player player){
        boolean menuChecker3 = true;
        while(menuChecker3) {
            GameHelper.menuClearScreen();
            player.getPlayerInventory();
            System.out.println("""
                    --------------------------------
                    |          THE STORE           |
                    --------------------------------
                    #   |1| - Buy animals          #
                    #   |2| - Buy foods            #
                    #   |3| - Sell animals         # 
                    #                              # 
                    #   |0| - Exit store.          #""");
            switch (GameHelper.tryCatch(0, 3)) {
                case 1 -> buyAnimals(player);
                case 2 -> buyFoods(player);
                case 3 -> sellAnimals_Shop(player);
                case 0 -> menuChecker3 = false;
            }
        }
    }

    public void buyAnimals(Player player){

        //Store class where the menus for buying animals/foods will be.
        // Booleans for looping each menu so the user can stay in menus until user choose to leave.
        boolean menuChecker1 = true;
        while(menuChecker1) {
            GameHelper.menuClearScreen();
            player.getPlayerInventory();
            System.out.println("""
                    ----------------------------------
                    |          ANIMAL STORE          |
                    ----------------------------------
                    # WHICH ANIMAL DO YOU WANNA BUY? #
                    #                                #
                    #   |1| - Rat         10$.       #
                    #   |2| - Parrot      20$.       #
                    #   |3| - Cat         30$.       # 
                    #   |4| - Crocodile   40$.       #
                    #   |5| - Wolf        50$.       #   
                    #                                #
                    #   |0| - Exit animal store.     #""");
                switch (GameHelper.tryCatch(0, 5)) {
                    case 1 -> {
                        if(player.checkIfTrue(player.canBuyAnimal)){
                            addAnimal(player, new Rat());
                        }else{
                            choiceIsMade();
                        }
                    }
                    case 2 -> {
                        if(player.checkIfTrue(player.canBuyAnimal)){
                            addAnimal(player, new Parrot());
                        }else{
                            choiceIsMade();
                        }
                    }
                    case 3 -> {
                        if(player.checkIfTrue(player.canBuyAnimal)){
                            addAnimal(player, new Cat());
                        }else{
                            choiceIsMade();
                        }
                    }
                    case 4 -> {
                        if(player.checkIfTrue(player.canBuyAnimal)){
                            addAnimal(player, new Crocodile());
                        }else{
                            choiceIsMade();
                        }
                    }
                    case 5 -> {
                        if(player.checkIfTrue(player.canBuyAnimal)){
                            addAnimal(player, new Wolf());
                        }else{
                            choiceIsMade();
                        }
                    }
                    case 0 -> menuChecker1 = false;

                }
            }
        }

    public void buyFoods(Player player){
        boolean menuChecker2 = true;
        while(menuChecker2) {
            GameHelper.menuClearScreen();
            player.getPlayerInventory();
            System.out.println("""
                    ----------------------------------
                    |           FOOD STORE           |
                    ----------------------------------
                    #   |1| - Dry food    1kg    5$. # || Can eat: Rat -> gives + 10% Health.
                    #   |2| - Vegetables  1kg    5$. # || Can eat: Rat, Parrot -> gives + 10% Health.
                    #   |3| - Meat        1kg   10$. # || Can eat: Cat,Wolf,Crocodile,Rat -> gives + 10% Health.
                    #   |4| - Super food  1kg   25$. # || All animals can eat -> gives 20% Health.
                    #                                #
                    #   |0| - Exit food store.       #""");
            switch (GameHelper.tryCatch(0, 4)) {
                case 1 -> {
                    if(player.checkIfTrue(player.canBuyFood)){
                        addFood(player, new DryFood());
                    }else{
                        choiceIsMade();
                    }
                }
                case 2 -> {
                    if(player.checkIfTrue(player.canBuyFood)){
                        addFood(player, new Vegetables());
                    }else{
                        choiceIsMade();
                    }
                }
                case 3 -> {
                    if(player.checkIfTrue(player.canBuyFood)){
                        addFood(player, new Meat());
                    }else{
                        choiceIsMade();
                    }
                }
                case 4 -> {
                    if(player.checkIfTrue(player.canBuyFood)){
                        addFood(player, new SuperFood());
                    }else{
                        choiceIsMade();
                    }
                }
                case 0 -> menuChecker2 = false;
            }
        }
    }

    public void sellAnimals_Shop(Player player){
        boolean menuChecker4 = true;
        if (player.animals.size() > 0) {
        while(menuChecker4) {
                GameHelper.menuClearScreen();
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
                        menuChecker4 = false;
                    } else {
                        if(player.checkIfTrue(player.canSellAnimal)) {
                            sellAnimal(player, player.animals.get(index - 1));
                            player.setAllBooleanFalse();
                            player.setCanSellAnimal(true);
                        }else{
                            choiceIsMade();
                        }
                    }
                }
        }else{
            GameHelper.menuClearScreen();
            System.out.println("You don't have any animals to sell!");
            GameHelper.menuHelper();
        }
    }

    public void addAnimal(Player player, Animal animal){
        Scanner input = new Scanner(System.in);
        if(player.getMoney() < animal.getStartPrice()){
            GameHelper.menuClearScreen();
            System.out.println("You don't have enough money for this animal.");
            GameHelper.menuHelper();
        }else {
            GameHelper.menuClearScreen();
            System.out.println("You want to buy a " + animal.getAnimalType() + " for " + animal.getStartPrice() + "$?");
            System.out.println("""
                    # |1| - Yes.
                    # |2| - No.""");
            switch (GameHelper.tryCatch(1, 2)) {
                case 1 -> {
                    GameHelper.menuClearScreen();
                    System.out.print("Enter a name for your " + animal.getAnimalType() + ": ");
                    animal.setName(input.nextLine());
                    GameHelper.menuClearScreen();
                    System.out.println("Choose gender for your " + animal.getAnimalType() + "!");
                    System.out.println("""
                            # |1| - MALE.
                            # |2| - FEMALE.""");
                    switch (GameHelper.tryCatch(1, 2)) {
                        case 1 -> animal.setGender("MALE");
                        case 2 -> animal.setGender("FEMALE");
                    }
                    player.animals.add(animal);
                    player.removeMoney(animal.getStartPrice());
                    player.setAllBooleanFalse();
                    player.setCanBuyAnimal(true);

                }
                case 2 -> {
                }
            }
        }
    }

    public void addFood(Player player, Food food){
        if(player.getMoney() < food.getPrice()){
            GameHelper.menuClearScreen();
            System.out.println("You don't have enough money to buy this food.");
            GameHelper.menuHelper();
        }else {
            int foodCounter = 0;
            GameHelper.menuClearScreen();
            System.out.println("You want to buy 1kg of " + food.getName() + " for " + food.getPrice() + "$?");
            System.out.println("""
                    # |1| - Yes.
                    # |2| - No.""");
            switch (GameHelper.tryCatch(1, 2)) {
                case 1 -> {
                    if (player.foods.size() > 0) {
                        for (Food food1 : player.foods) {
                            if (food1.getName().equals(food.getName())) {
                                food1.setKiloGrams(1);
                                player.removeMoney(food.getPrice());
                                foodCounter++;
                            }
                        }
                    }
                    if (foodCounter == 0) {
                        player.foods.add(food);
                        player.removeMoney(food.getPrice());
                        player.setAllBooleanFalse();
                        player.setCanBuyFood(true);
                    }

                }
                case 2 -> {
                }
            }
        }
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
                player.animals.remove(animal);
            }
            case 2 -> {
            }
        }
    }

    public void choiceIsMade() {
        GameHelper.menuClearScreen();
        System.out.println("You already made your move this round!");
        System.out.println("Must wait for next round.");
        GameHelper.menuHelper();
    }

}
