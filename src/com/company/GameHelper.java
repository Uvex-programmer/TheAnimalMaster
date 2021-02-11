package com.company;

import java.io.Serializable;
import java.util.Scanner;

public class GameHelper implements Serializable {

    public static Scanner input = new Scanner(System.in);

    public GameHelper() {}


    public static void gameInfo(){
        clearScreen();
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

    static public int menu(String menuName,int min, String ...options){
        // print the menu
        GameHelper.clearScreen();
        System.out.println("-".repeat(50));
        System.out.println(menuName);
        System.out.println("-".repeat(50));
        var counter = 1;
        for(var option : options){
            System.out.println(counter + ". " + option);
            counter++;
        }
        System.out.println("-".repeat(50));
        // wait for the user to make a choice
        var choice = 0;
        try {
            choice = Integer.parseInt(input.nextLine());
        }
        catch(Exception ignore){}
        // if illegal choice show the menu again (recursion)
        // otherwise return the choice
        return choice < min || choice > options.length ?
                menu(menuName,min, options) : choice;
    }

}

