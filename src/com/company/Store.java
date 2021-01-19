package com.company;

public class Store {
    //Store class where the menus for buying animals/foods will be.

    private boolean menuChecker1 = true;
    private boolean menuChecker2 = true;
    private boolean menuChecker3 = true;
    GameHelper helper = new GameHelper();
    public Store() {
    }
    //Method for the menu for buying animals.
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
                case 3 -> {}
                case 0 -> menuChecker3 = false;
            }
        }
    }
    public void buyAnimals(Player player){
        GameHelper helper = new GameHelper();
        menuChecker1 = true;
        while(menuChecker1) {
            helper.menuClearScreen();
            System.out.println("""
                    --------------------------------
                    |         ANIMAL STORE         |
                    --------------------------------
                    #   |1| - Rat       10$.       #
                    #   |2| - Parrot    20$.       #
                    #   |3| - Cat       30$.       # 
                    #   |4| - Crocodile 40$.       #
                    #   |5| - Wolf      50$.       #   
                    #                              #
                    #   |0| - Exit animal store.   #""");
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
}
