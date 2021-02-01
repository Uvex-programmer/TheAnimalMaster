package com.company;

import java.io.Serializable;
import java.util.*;

public class Game implements Serializable {
    ArrayList<Player> players = new ArrayList<>();// List of players
    ArrayList<Player> playersWhoLost = new ArrayList<>(); // List of the players who lost
    SaveGame SaveGame = new SaveGame();
    Player currentPlayer; // Saves current player
    int numberOfTurns; // Saves the number of rounds to play
    int currentTurn; // Checks which round in the game it is
    int currentPlayerIndex; // Checks index of current player

    boolean forEach = true; // A boolean I need for breaking a for-loop if player wants to exit for main menu in game.
    //Menu for the very start of the game
    public Game() {
        SaveGame.setgame(this);
        // Start with main menu when program is running.
        boolean startMenu = true;
        while(startMenu) {
            players.clear();
            Scanner input = new Scanner(System.in); // So we can get user input.
            GameHelper.menuClearScreen();// Clear the screen for to much text in terminal.
            System.out.println("""
                                ------------------------
                                |  ANIMAl MASTER 2000  |
                                ------------------------
                                #   |1| - Start game   #
                                #   |2| - Game info    #
                                #   |3| - Load game    #
                                #                      #
                                #   |4| - Exit         #
                                ------------------------""");
            //Checks so user enters right input and stops from getting exceptions.
            int choice = 0;
            while (choice < 1 || choice > 4) {
                try {
                    System.out.print("Enter an option: ");
                    choice = input.nextInt();
                } catch (Exception e) {
                    System.out.println("You must enter a number in the menu.");
                    input.next();
                }
            }
            //Switch-cases for the user choice.
            switch (choice){
                case 1 -> startGame(); // Continue the game
                case 2 -> GameHelper.gameInfo(); // Game info in text
                case 3 -> SaveGame.loadGame();
                case 4 -> {  //Option to exit the game.
                    boolean exitMenu = true;
                    choice = 0;
                    while (exitMenu) {
                        while (choice < 1 || choice > 2) {  // Looping the exit menu until the user makes a
                            GameHelper.menuClearScreen();     // correct choice. Must press 1 or 2.
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
                                input.next(); // Must have or it will be looping all over the place.
                            }                 // Don't want any error message just return to the choices.
                        }
                        if(choice == 1){ // If user press 1 , game will close.
                            GameHelper.menuClearScreen();
                            System.out.println("Game is closing...");
                            exitMenu = false;
                            startMenu = false;
                        }
                        if(choice == 2){ // If user press 2, return to start menu.
                            exitMenu = false;
                        }

                    }
                }

            }
        }
    }

    //Method to start the real game.
    public void startGame(){
        currentTurn = 1;
        setForEach(true);
        Scanner myScanner = new Scanner(System.in);
        numberOfTurns = 0; // User decide how many turns the game will do before ends.
        while(numberOfTurns < 5 || numberOfTurns > 30) { // Must be 5-30 turns or user wont continue.
            GameHelper.menuClearScreen(); // Clear text
            System.out.println("Enter how many rounds you want the game to be, between 5-30 rounds!");
            System.out.print("Number of rounds: ");
            try { // Handle exceptions.
                numberOfTurns = myScanner.nextInt();

            } catch (Exception e) {
                myScanner.next();
            }
        }
        // Ask the user how many players will join the game.
        int numberOfPlayers = 0;
        while(numberOfPlayers < 1 || numberOfPlayers > 4) { // Can only be 1-4 players.
            GameHelper.menuClearScreen(); // Clear text
            System.out.println("You have chosen " + numberOfTurns + " rounds for the game!\n");
            System.out.println("Now enter how many players between 1-4! ");
            System.out.print("Number of players: ");
            try { // Handle exceptions.
                numberOfPlayers = myScanner.nextInt();

            } catch (Exception e) {
                myScanner.next();
            }

        }
        GameHelper.menuClearScreen(); // Lets the users enter their player names.
        System.out.println("\nYou have chosen " + numberOfPlayers + " players for the game!");
        for(int i = 1; i < numberOfPlayers + 1; i++){
            System.out.print("Player " + i + " -> choose your name: ");
            String name = myScanner.next();
            players.add(new Player(name));
        }
        // Putting out some game info from what the user has entered before.
        GameHelper.menuClearScreen();
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

        theGame();

    }
    //Main menu in game
    public void playerMenu(){
        Store store = new Store();
        Breeding breed = new Breeding();
        boolean realGameMenu = true;

        while(realGameMenu) {

            System.out.println("\n".repeat(20));
            currentPlayer.getPlayerAnimal();
            currentPlayer.getFood();
            currentPlayer.getWallet();
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
                                #                              #
                                #   |5| - Save game            #
                                #   |0| - Exit to main menu.   #""");
            switch (GameHelper.tryCatch(0,5)) {
                case 1 -> store.mainMenu(currentPlayer);
                case 2 -> breed.breedAnimal(currentPlayer, currentPlayer.animals);
                case 3 -> currentPlayer.feedAnimal(currentPlayer);
                case 4 -> realGameMenu = false;
                case 5 -> SaveGame.saveGame(this);
                case 0 -> {
                    realGameMenu = false;
                    setForEach(false);
                }
            }
        }
    }
    //Method for checking the boolean that breaks the for-loop if user needs to exit for main menu.
    public boolean checkIfFalse(){
        return !this.forEach;
    }
    public void setForEach(boolean forEach) {
        this.forEach = forEach;
    }
    // Method for animals to lose health
    public void animalLooseHealth(){
        Random random = new Random();
        for(Animal animal: currentPlayer.animals){
            int damage = 10 + random.nextInt(21);
            animal.setHealth(animal.getHealth() - damage);
        }
    }
    //Method for checking if any animals have died
    public void animalDead(){
        ArrayList<Animal> deadAnimals = new ArrayList<>();
        for (int i = 0; i < currentPlayer.animals.size(); i++){
            if(currentPlayer.animals.get(i).getHealth() < 1){
                deadAnimals.add(currentPlayer.animals.get(i));
                currentPlayer.animals.remove(currentPlayer.animals.get(i));
                i--;
            }
        }
        if(deadAnimals.size() > 0){
            System.out.println("\n".repeat(20));
            for(Animal animal: deadAnimals){
                System.out.println(animal.getName() + " is dead.");
            }
            GameHelper.menuHelper();
        }
    }
    //Method check for winner
    public void checkWinner(){
        int bestScore = 0;
        int bestIndex = 0;
        if(players.size() == 0){
            System.out.println("\nThere is no winner, only losers!!");
            GameHelper.menuHelper();
        }else {
            sellAllAnimals();
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).getMoney() > bestScore) {
                    bestScore = players.get(i).getMoney();
                    bestIndex = i;
                }
            }
            System.out.println("\nThe winner of this game is: " + players.get(bestIndex).getName() + " with: " + players.get(bestIndex).getMoney() + "$!\n");
            for (Player player : players) {
                System.out.println(player.getName() + ": " + player.getMoney() + "$.");
            }
            for(Player player: playersWhoLost){
                System.out.println(player.getName() + ": " + player.getMoney() + "$.");
            }
            GameHelper.menuHelper();
        }
    }
    //Method for selling all animals when the game ends
    public void sellAllAnimals(){
        GameHelper.menuClearScreen();
        System.out.println("Game is now ending... All animals every player have left, have been sold for money..");
        for(Player player: players){

            for(int i = 0; i < player.animals.size(); i++){
                player.addMoney(player.animals.get(i).getCurrentPrice());
                player.animals.remove(player.animals.get(i));
                i--;
            }
        }
    }
    //Method that makes a player lose the game if they have no food/money left
    public void playerLost(){
        if(currentPlayer.lostGame()){
            GameHelper.menuClearScreen();
            System.out.println(currentPlayer.getName() + " -> have no money/food left. You are OUT!");
            GameHelper.menuHelper();
            players.remove(currentPlayer);
            playersWhoLost.add(currentPlayer);
        }
    }
    //main game loop
    public void theGame(){
        for (int i = currentTurn; i < numberOfTurns + 1; i++) {
            for(int j = currentPlayerIndex; j < players.size(); j++){
                currentPlayer = players.get(j);
                currentPlayer.setAllBooleanTrue();
                animalDead();
                playerMenu();
                playerLost();
                animalLooseHealth();
                if (checkIfFalse()) { break; }
                currentPlayerIndex++;
            }if(checkIfFalse()){ break; }
            currentPlayerIndex = 0;
            currentTurn++;
        }
        checkWinner();
    }


}
