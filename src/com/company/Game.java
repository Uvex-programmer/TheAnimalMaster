package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    GameMenus gameMenu = new GameMenus();

    public Game() {
        // Start with main menu when program is running.
        boolean startMenu = true;
        while(startMenu) {
            Scanner input = new Scanner(System.in); // So we can get user input.
            gameMenu.menuClearScreen();// Clear the screen for to much text in terminal.
            System.out.println("\n\n|| ANIMAl MASTER 2000 ||");
            System.out.println("\n[1] Start game.");
            System.out.println("[2] Game info.");
            System.out.println("[3] Exit.");
            System.out.println("\nPlease enter a menu choice!");
            //Checks so user enters right input and stops from getting exceptions.
            int choice = 0;
            while (choice < 1 || choice > 3) {
                try {
                    System.out.println("Enter an option: ");
                    choice = input.nextInt();
                } catch (Exception e) {
                    System.out.println("You must enter a number between 1-3.");
                }
            }
            //Switch-cases for the user choice.
            switch (choice){
                case 1 -> startGame(); // Continue the game
                case 2 -> gameMenu.gameInfo(); // Game info in text
                case 3 -> {  //Option to exit the game.
                    boolean exitMenu = true;
                    choice = 0;
                    while (exitMenu) {
                        while (choice < 1 || choice > 2) {  // Looping the exit menu until the user makes a
                            gameMenu.menuClearScreen();     // correct choice. Must press 1 or 2.
                            System.out.println("You want to exit the game?");
                            System.out.println("1 - Yes");
                            System.out.println("2 - No");
                            try {
                                System.out.println("You must enter a number between 1-2.");
                                System.out.println("Enter an option: ");
                                choice = input.nextInt();
                            } catch (Exception e) {
                                input.next(); // Must have or it will be looping all over the place.
                            }                 // Don't want any error message just return to the choices.
                        }
                        if(choice == 1){ // If user press 1 , game will close.
                            gameMenu.menuClearScreen();
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

        Scanner myScanner = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<>(); // Must have a list to save the players in.
        int numberOfTurns = 0; // User decide how many turns the game will do before ends.
        while(numberOfTurns < 5 || numberOfTurns > 30) { // Must be 5-30 turns or user wont continue.
            gameMenu.menuClearScreen(); // Clear text
            System.out.println("Enter how many rounds you want the game to be, between 5-30 rounds!");
            System.out.print("Number of rounds: ");
            try { // Handle exceptions.
                numberOfTurns = myScanner.nextInt();

            } catch (Exception e) {
                myScanner.next();
            }
        }

        int numberOfPlayers = 0;
        while(numberOfPlayers < 1 || numberOfPlayers > 4) {
            gameMenu.menuClearScreen(); // Clear text
            System.out.println("You have chosen " + numberOfTurns + " rounds for the game!\n");
            System.out.println("Now enter how many players between 1-4! ");
            System.out.print("Number of players: ");
            try {
                numberOfPlayers = myScanner.nextInt();

            } catch (Exception e) {
                myScanner.next();
            }

        }
        gameMenu.menuClearScreen();
        System.out.println("\nYou have chosen " + numberOfPlayers + " players for the game!");
        for(int i = 1; i < numberOfPlayers + 1; i++){
            System.out.print("Player " + i + " -> choose your name: ");
            String name = myScanner.next();
            players.add(new Player(name));
        }


        System.out.println("\n".repeat(15) + "<--- Here is the players --->");
        int playerNumber = 1;
        for(Player player: players){
            System.out.println("Player " + playerNumber + ": " + player.getName());
            playerNumber++;
        }
        
    }
}
