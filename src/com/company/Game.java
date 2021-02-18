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

    boolean exitMenu = false;

    public Game() {
        SaveGame.setgame(this);

        boolean startMenu = true;
        while(startMenu) {

            players.clear();
            GameHelper.clearScreen();
            int choice = GameHelper.printMenu("ANIMAL MASTER 2000", " Start Game",
                    " Game Info", " Load Game\n", " Exit");

            switch (choice){
                case 1 -> startMenu();
                case 2 -> GameHelper.gameInfo();
                case 3 -> SaveGame.loadGame();
                case 4 -> {
                    boolean exitMenu = true;
                    while (exitMenu) {
                        GameHelper.clearScreen();
                        choice = GameHelper.printMenu("EXIT GAME", " Yes", " No");
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
        exitMenu = false;

        numberOfTurns = gameSetup("Enter how many rounds you want, between 5-30 rounds!",5, 30);
        numberOfPlayers = gameSetup("Enter number of players, between 1-4!",1, 4);
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
            int choice = 0;

            while(choice < 1 || choice > 7) {
            GameHelper.clearScreen();
            currentPlayer.getPlayerInventory();
            System.out.println("\nIt's now round: " + currentTurn);
            System.out.println(currentPlayer.getName() + "'s turn!\n");
                choice = GameHelper.printMenu("THE GAME", " The Store", " Breed", " Feed animal",
                        " Next player", " Game info\n", " Save Game", " Exit to main menu");
            }

            switch (choice) {
                case 1 -> store.storeMenu(currentPlayer);
                case 2 -> breed.animalBreeding(currentPlayer);
                case 3 -> currentPlayer.feedAnimal(currentPlayer);
                case 4 -> realGameMenu = false;
                case 5 -> GameHelper.gameInfo();
                case 6 -> SaveGame.saveGame(this);
                case 7 -> {
                    realGameMenu = false;
                    exitMenu = true; // So we can break the main loop and get to the main menu at start.
                }
            }
        }
    }

    //main game loop
    public void theGame(){
        
        for (int i = currentTurn; i < numberOfTurns + 1; i++) {
            
            for(int j = currentPlayerIndex; j < players.size(); j++){
                currentPlayer = players.get(j);
                currentPlayer.openOptions();
                
                event.beginningOfRound(currentPlayer);
                playerMenu();
                event.endOfRound(currentPlayer, this);
                
                if (exitMenu)
                    break;
                currentPlayerIndex++;
            }if(exitMenu)
                break;

            currentPlayerIndex = 0;
            currentTurn++;
        }
        event.checkWinner(this);
    }

    public int gameSetup(String line, int min, int max){
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
                gameSetup(line, min, max) : choice;

    }


}
