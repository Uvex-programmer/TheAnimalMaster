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
                    } else {
                        if(player.checkIfTrue(player.canSellAnimal)) {
                            player.sellAnimal(player, player.animals.get(index - 1));
                            player.setAllBooleanFalse();
                            player.setCanSellAnimal(true);
                        }else{
                            choiceIsMade();
                        }
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
                player.setAllBooleanFalse();
                player.setCanBuyAnimal(true);

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
                            player.removeMoney(food.getPrice());
                            foodCounter++;
                        }
                    }
                }
                if(foodCounter == 0){
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
    public void choiceIsMade() {
        helper.menuClearScreen();
        System.out.println("You already made your move this round!");
        System.out.println("Must wait for next round.");
        helper.menuHelper();
    }

}
