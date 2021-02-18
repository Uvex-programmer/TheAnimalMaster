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
                * The player with the most money at the end wins.
                * If you have no animals and you do not have the money to buy the cheapest animal, you lose!
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

    static public int printMenu(String menuName, String ...options){
        System.out.println("-".repeat(30));
        System.out.println("\t" + menuName);
        System.out.println("-".repeat(30));

        var counter = 1;
        for(var option: options){
            System.out.println(" |" + counter + "| -" + option );
            counter++;
        }
        var choice = 0;
        try{
            System.out.print("\nEnter option: ");
            choice = Integer.parseInt(input.nextLine());

        }catch(Exception ignore){}
        return choice;
    }

    static public int printQuestion(String question, String ...options){
        clearScreen();
        System.out.println(question);

        var counter = 1;
        for(var option: options){
            System.out.println(" |" + counter + "| -" + option );
            counter++;
        }
        var choice = 0;
        try{
            System.out.print("\nEnter option: ");
            choice = Integer.parseInt(input.nextLine());

        }catch(Exception ignore){}
        return choice < 1 || choice > options.length ? printQuestion(question, options) : choice;
    }

    public static void printText(String text) {
        GameHelper.clearScreen();
        System.out.println(text);
        GameHelper.menuHelper();
    }



}

