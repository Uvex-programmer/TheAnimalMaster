package com.company;

import java.io.Serializable;
import java.util.Scanner;

public class GameHelper implements Serializable {

    public static Scanner input = new Scanner(System.in);

    public GameHelper() {}


    public static void gameInfo(){
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

    public static void menuClearScreen() {
        System.out.println("\n".repeat(60));
    }
    //Method for stopping the program for moving so the user can read a message and the press enter to move forward.
    public static void menuHelper(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n");
        System.out.println("Press ENTER to continue:");
        scanner.nextLine();
    }

    public static int tryCatch(int num1, int num2){
        Scanner input = new Scanner(System.in);
        int choice = -1;
        while (choice < num1 || choice > num2) {
            try {
                System.out.println("Enter an option: ");
                choice = input.nextInt();
            } catch (Exception e) {
                System.out.println("You must enter a number in the menu.");
                input.next();
            }
        }return choice;
    }

}

