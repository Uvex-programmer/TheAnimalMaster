package com.company;

import java.util.Scanner;

public class Store {
    GameHelper helper = new GameHelper(); // Calling for game helper methods
    public Store() {
    }
    //Method for the main menu in store.
    public void mainMenu(Player player){
        boolean menuChecker3 = true;
        while(menuChecker3) {
            helper.menuClearScreen();
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
            switch (helper.tryCatch(0, 3)) {
                case 1 -> buyAnimals(player);
                case 2 -> buyFoods(player);
                case 3 -> sellAnimals(player);
                case 0 -> menuChecker3 = false;
            }
        }
    }
    //Method for the animal shop.
    public void buyAnimals(Player player){
        GameHelper helper = new GameHelper();
        //Store class where the menus for buying animals/foods will be.
        // Booleans for looping each menu so the user can stay in menus until user choose to leave.
        boolean menuChecker1 = true;
        while(menuChecker1) {
            helper.menuClearScreen();
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
            switch (helper.tryCatch(0, 5)) {
                case 1 -> addAnimal(player, new Rat());
                case 2 -> addAnimal(player, new Parrot());
                case 3 -> addAnimal(player, new Cat());
                case 4 -> addAnimal(player, new Crocodile());
                case 5 -> addAnimal(player, new Wolf());
                case 0 -> menuChecker1 = false;
            }
        }
    }
    //Method for the food shop.
    public void buyFoods(Player player){
        GameHelper helper = new GameHelper();
        boolean menuChecker2 = true;
        while(menuChecker2) {
            helper.menuClearScreen();
            player.getPlayerInventory();
            System.out.println("""
                    ----------------------------------
                    |           FOOD STORE           |
                    ----------------------------------
                    #   |1| - Dry food    1kg    5$. #
                    #   |2| - Vegetables  1kg   10$. #
                    #   |3| - Meat        1kg   15$. # 
                    #   |4| - Super food  1kg   20$. #
                    #                                #
                    #   |0| - Exit food store.       #""");
            switch (helper.tryCatch(0, 4)) {
                case 1 -> addFood(player, new DryFood());
                case 2 -> addFood(player, new Vegetables());
                case 3 -> addFood(player, new Meat());
                case 4 -> addFood(player, new SuperFood());
                case 0 -> menuChecker2 = false;
            }
        }
    }
    //Method for selling animals.
    public void sellAnimals(Player player){
        boolean menuChecker4 = true;
        if (player.animals.size() > 0) {
        while(menuChecker4) {
                helper.menuClearScreen();
                player.getWallet();
                System.out.println("""
                        -----------------------------------
                        |          ANIMAL STORE           |
                        -----------------------------------
                        # WHICH ANIMAL DO YOU WANNA SELL? #""");
                int counter = 1;
                for (Animal animal : player.animals) {
                    System.out.println("# |" + counter + "| - " + animal.getName() + " ->" + animal.getGender() +
                            " | " + animal.getAnimalType() + " | " + animal.getHealth() + "% health" + " value: " + animal.getCurrentPrice());
                    counter++;
                }
                System.out.println("\n# |0| - Exit store.");
                int index = helper.tryCatch(0, player.animals.size());
                if (index == 0) {
                    menuChecker4 = false;
                }else {
                    player.sellAnimal(player, player.animals.get(index - 1));
                }
            }
        }else{
            helper.menuClearScreen();
            System.out.println("You don't have any animals to sell!");
            helper.menuHelper();
        }
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
                helper.menuClearScreen();
                System.out.println("Choose gender for your " + animal.getAnimalType() + "!");
                System.out.println("""
                            # |1| - MALE.
                            # |2| - FEMALE.""");
                switch (helper.tryCatch(1, 2)){
                    case 1 -> animal.setGender("MALE");
                    case 2 -> animal.setGender("FEMALE");
                }
                player.animals.add(animal);
                player.removeMoney(animal.getStartPrice());
            }
            case 2 -> {}
        }
    }
    public void addFood(Player player, Food food){
        int foodCounter = 0;
        helper.menuClearScreen();
        System.out.println("You want to buy 1kg of " + food.getName() + " for " + food.getPrice() + "$?");
        System.out.println("""
                            # |1| - Yes.
                            # |2| - No.""");
        switch(helper.tryCatch(1, 2)){
            case 1 -> {
                if(player.foods.size() > 0) {
                    for (Food food1 : player.foods) {
                        if (food1.getName().equals(food.getName())){
                            food1.setKiloGrams(1);
                            foodCounter++;
                        }
                    }
                }
                if(foodCounter == 0){
                    player.foods.add(food);
                    player.removeMoney(food.getPrice());
                }

            }
            case 2 -> {
            }
        }
    }
}
