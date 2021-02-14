package com.company;

import java.io.Serializable;
import java.util.Scanner;

public class GameHelper implements Serializable {

    public static Scanner input = new Scanner(System.in);

    public GameHelper() {}


    public static void gameInfo(){
        clearScreen();
        System.out.println("## GAME RULES ##\n");
        System.out.println("""
                * This is a game where the player at the end with\s
                  most money will win.
                * You need to buy/breed/sell animals to earn money. 
                * Your animals will loose 10% of its health each round. As the health drops,
                  the animals will be worth less money to sell.
                * Each round every animal have 20% to get sick. You then have a choice to pay the vet to heal. 
                  50% chance of survival.
                * You can buy food at the store for each animal to raise their health.
                * For the breeding you need 2 animals of the same type and different genders.
                * IMPORTANT! For each round you can only make ONE main option. Like buy animal/food or breed or sell.
                *  
                * Which food animals can eat:
                    Rat -> Dry food.
                    Parrot -> Vegetables
                    Wolf,Cat,Crocodile -> Meat.
                *
                * How many babies each animal can get each breeding:
                    Rat -> max babies = 4.
                    Parrot -> max babies = 3.
                    Cat -> max babies = 3.
                    Crocodile -> max babies = 2.
                    Wolf -> max babies = 2.
                """);
        menuHelper();
    }

    public static void clearScreen() {
        System.out.println("\n".repeat(60));
    }
    //Method for stopping the program for moving so the user can read a message and the press enter to move forward.
    public static void menuHelper(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n");
        System.out.println("Press ENTER to continue:");
        scanner.nextLine();
    }

    public static int tryCatch(int min, int max){
        Scanner input = new Scanner(System.in);
        int choice = -1;
        while (choice < min || choice > max) {
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

