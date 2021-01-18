package com.company;

public class Store {

    public Store() {
    }

    public void buyAnimals(Player player){
        GameHelper helper = new GameHelper();
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
        switch (helper.tryCatch(0, 5)){
            case 1 -> player.addAnimal(player, new Rat());
        }
    }


}
