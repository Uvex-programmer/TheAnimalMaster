package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class GameMenus {
    //Here is where I will put the menus for the start of the game.
    //Will take in information as how the player wants to setup the game.
    public GameMenus() {

    }

    public void chooseRounds(){
        Scanner input = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Enter how many rounds you wanna play. Must be 5-30 rounds.");
            System.out.print("Enter rounds: ");
            while (!input.hasNextInt()) { // Checks so the user inputs an integer.
                System.out.println("Must enter a number in the menu!");
                input.next();
            }choice = input.nextInt();
        }while (choice < 1 || choice > 3); // If the user choose a number that's not 5-30, loop again.
    }

    //Method that only contains a text of how the game works.
    public void gameInfo(){
        menuClearScreen();
        System.out.println("\t# GAME RULES #\n");
        System.out.println("""
                * This is a game where the player at the end with\s
                  most money will win.
                * You need to buy/breed/sell animals to earn money. 
                * Your animals will loose 10% of its health each round. As the health drops,
                  the animals will be worth less money to sell.
                * You can buy food at the store for each animal to raise their health.
                * For the breeding you need 2 animals of the same type and different genders.
                * IMPORTANT! For each round you can only make ONE main option. Like buy animal/food or breed or sell.""");
        menuHelper();
    }

    //Method to clear text in terminal.
    public void menuClearScreen() {
        System.out.println("\n".repeat(30));
    }
    //Method for stopping the program for moving so the user can read a message and the press enter to move forward.
    public void menuHelper(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n");
        System.out.println("Press ENTER to continue:");
        scanner.nextLine();
    }

}
