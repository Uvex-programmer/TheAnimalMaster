package com.company;

public class Store {
    //Store class where the menus for buying animals/foods will be.
    // Booleans for looping each menu so the user can stay in menus until user choose to leave.
    private boolean menuChecker1 = true;
    private boolean menuChecker2 = true;
    private boolean menuChecker3 = true;
    private boolean menuChecker4 = true;
    GameHelper helper = new GameHelper(); // Calling for game helper methods
    public Store() {
    }
    //Method for the main menu in store.
    public void mainMenu(Player player){
        menuChecker3 = true;
        while(menuChecker3) {
            helper.menuClearScreen();
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
        menuChecker1 = true;
        while(menuChecker1) {
            helper.menuClearScreen();
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
                case 1 -> player.addAnimal(player, new Rat());
                case 2 -> player.addAnimal(player, new Parrot());
                case 3 -> player.addAnimal(player, new Cat());
                case 4 -> player.addAnimal(player, new Crocodile());
                case 5 -> player.addAnimal(player, new Wolf());
                case 0 -> menuChecker1 = false;
            }
        }
    }
    //Method for the food shop.
    public void buyFoods(Player player){
        GameHelper helper = new GameHelper();
        menuChecker2 = true;
        while(menuChecker2) {
            helper.menuClearScreen();
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
                case 1 -> player.addFood(player, new DryFood());
                case 2 -> player.addFood(player, new Vegetables());
                case 3 -> player.addFood(player, new Meat());
                case 4 -> player.addFood(player, new SuperFood());
                case 0 -> menuChecker2 = false;
            }
        }
    }
    //Method for selling animals.
    public void sellAnimals(Player player){
        int counter = 1;
        menuChecker4 = true;
        while(menuChecker4) {
            System.out.println("""
                    -----------------------------------
                    |          ANIMAL STORE           |
                    -----------------------------------
                    # WHICH ANIMAL DO YOU WANNA SELL? #""");
            for (Animal animal : player.animals) {
                System.out.println("# |" + counter + "| - " + animal.getName() + " ->" + animal.getGender() +
                        " | " + animal.getAnimalType() + " | " + animal.getHealth() + "% health" + " value: " + animal.getCurrentPrice());
                counter++;
            }
            System.out.println("# |0| - Exit store.");
            if(helper.tryCatch(0, player.animals.size()) == 0){
                menuChecker4 = false;
            }else{
                int index = helper.tryCatch(0, player.animals.size());
                player.sellAnimal(player, player.animals.get(index - 1));
            }
        }
    }
}
