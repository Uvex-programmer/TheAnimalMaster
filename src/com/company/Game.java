package com.company;

import java.io.Serializable;
import java.util.*;

public class Game implements Serializable {

    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Player> playersWhoLost = new ArrayList<>();

    SaveGame SaveGame = new SaveGame();
    private Player currentPlayer;
    private final Events event = new Events();

    private int numberOfTurns;
    private int currentTurn;
    private int currentPlayerIndex;
    private int numberOfPlayers = 0;

    boolean forEach = true; // A boolean I need for breaking a for-loop if player wants to exit for main menu in game.

    public Game() {
        SaveGame.setgame(this);
        // Start with main menu when program is running.
        boolean startMenu = true;
        while(startMenu) {
            players.clear();
            Scanner input = new Scanner(System.in);
            GameHelper.clearScreen();
            System.out.println("""
                                ------------------------
                                |  ANIMAl MASTER 2000  |
                                ------------------------
                                #   |1| - Start game   #
                                #   |2| - Game info    #
                                #   |3| - Load game    #
                                #                      #
                                #   |0| - Exit         #
                                ------------------------""");

            int choice = GameHelper.tryCatch(0,3);

            switch (choice){
                case 1 -> startMenu();
                case 2 -> GameHelper.gameInfo();
                case 3 -> SaveGame.loadGame();
                case 0 -> {
                    boolean exitMenu = true;
                    choice = 0;
                    while (exitMenu) {
                        while (choice < 1 || choice > 2) {
                            GameHelper.clearScreen();
                            System.out.println("""
                                                ------------------------
                                                |      EXIT GAME       |
                                                ------------------------
                                                # |1| - Yes.
                                                # |2| - No.""");
                            try {
                                System.out.println("Enter an option: ");
                                choice = input.nextInt();
                            } catch (Exception e) {
                                input.next();
                            }
                        }
                        if(choice == 1){
                            GameHelper.clearScreen();
                            System.out.println("Game is closing...");
                            exitMenu = false;
                            startMenu = false;
                        }
                        if(choice == 2){
                            exitMenu = false;
                        }

                    }
                }

            }
        }
    }

    public void startMenu(){
        currentTurn = 1;
        exitPlayerMenu(true);

        numberOfTurns = game_Setup("Enter how many rounds you want, between 5-30 rounds!",5, 30);
        numberOfPlayers = game_Setup("Enter number of players, between 1-4!",1, 4);
        choosePlayerNames();
        gameInfo();
        theGame();

    }

    public void choosePlayerNames(){
        Scanner myScanner = new Scanner(System.in);
        GameHelper.clearScreen();
        System.out.println("\nYou have chosen " + numberOfPlayers + " players for the game!");
        for(int i = 1; i < numberOfPlayers + 1; i++){
            System.out.print("Player " + i + " -> choose your name: ");
            String name = myScanner.next();
            players.add(new Player(name));
        }
    }

    public void gameInfo(){
        GameHelper.clearScreen();
        System.out.println("""
                                ------------------------
                                |      GAME INFO       |
                                ------------------------""");
        System.out.println("# Number of rounds = " + numberOfTurns);
        int playerNumber = 1;
        for(Player player: players){
            System.out.println("# Player " + playerNumber + ": " + player.getName());
            playerNumber++;
        }
        System.out.println("#");
        System.out.println("# " + players.get(0).getName() + " will start the first round!");
        System.out.println("# May the best player win!");
        GameHelper.menuHelper();
    }

    public void playerMenu(){
        Store store = new Store();
        Breeding breed = new Breeding();
        boolean realGameMenu = true;

        while(realGameMenu) {

            GameHelper.clearScreen();
            currentPlayer.getPlayerInventory();
            System.out.println("\nIt's now round: " + currentTurn);
            System.out.println(currentPlayer.getName() + "'s turn!\n");
            System.out.println("""
                                --------------------------------
                                |          THE GAME            |
                                --------------------------------
                                #   |1| - The Store.           #
                                #   |2| - Breed.               # 
                                #   |3| - Feed animal.         #
                                #   |4| - Done, next player.   #
                                #   |5| - Game Info            #
                                #                              #
                                #   |6| - Save game            #
                                #   |0| - Exit to main menu.   #""");

            switch (GameHelper.tryCatch(0,5)) {
                case 1 -> store.storeMenu(currentPlayer);
                case 2 -> breed.animalBreeding(currentPlayer);
                case 3 -> currentPlayer.feedAnimal(currentPlayer);
                case 4 -> realGameMenu = false;
                case 5 -> GameHelper.gameInfo();
                case 6 -> SaveGame.saveGame(this);
                case 0 -> {
                    realGameMenu = false;
                    exitPlayerMenu(false); // So we can break the main loop and get to the main menu at start.

                }
            }
        }
    }

    //main game loop
    public void theGame(){
        
        for (int i = currentTurn; i < numberOfTurns + 1; i++) {
            
            for(int j = currentPlayerIndex; j < players.size(); j++){
                currentPlayer = players.get(j);
                currentPlayer.open_Options();
                
                event.beginning_Of_Round(currentPlayer);
                playerMenu();
                event.end_Of_Round(currentPlayer, this);
                
                if (checkIfFalse()) 
                    break;
                currentPlayerIndex++;
            }if(checkIfFalse())
                break;

            currentPlayerIndex = 0;
            currentTurn++;
        }
        event.check_Winner(this);
    }

    public boolean checkIfFalse(){
        return !this.forEach;
    }
    //So we can exit main game loop to main menu.
    public void exitPlayerMenu(boolean forEach) {
        this.forEach = forEach;
    }

    public int game_Setup(String line, int min, int max){
        Scanner myScanner = new Scanner(System.in);
        GameHelper.clearScreen();
        System.out.println(line);

        int choice = -1;
        try { // Handle exceptions.
            choice = myScanner.nextInt();
        } catch (Exception e) {
            myScanner.next();
        }
        return choice < min || choice > max ?
                game_Setup(line, min, max) : choice;

    }


}
