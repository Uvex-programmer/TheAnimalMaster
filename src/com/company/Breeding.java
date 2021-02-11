package com.company;

import com.company.animals.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Breeding implements Serializable{


    public Breeding() {
    }

    public void animalBreeding(Player player){
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int animal1 = 0;
        int animal2 = 0;
        int chanceOfBreed = random.nextInt(101);
        ArrayList<Animal> tempList = new ArrayList<>();
        if(player.checkIfTrue(player.canBreed)) {
            if (player.animals.size() == 0) {
                GameHelper.clearScreen();
                System.out.println("You don't have any animals to breed!");
                GameHelper.menuHelper();
                return;
            } else {
                while (animal1 < 1 || animal1 > player.animals.size()) {
                    GameHelper.clearScreen();
                    player.getPlayerAnimal();
                    System.out.println("\nChoose your first animal for breeding! Enter number: ");
                    animal1 = GameHelper.tryCatch(1, player.animals.size());
                }
                if (animalsThatCanMate(player, player.animals.get(animal1 - 1))) {
                    for (Animal animal : player.animals) {
                        if (!(checkLeftForBreed(player.animals.get(animal1 - 1), animal))) {
                            tempList.add(animal);
                        }
                    }
                } else {
                    System.out.println("\n".repeat(60) + "There is no mate for " + player.animals.get(animal1 - 1).getName() + "!");
                    GameHelper.menuHelper();
                    return;
                }
                while (animal2 < 1 || animal2 > tempList.size()) {
                    System.out.println("\n".repeat(60) + "You can pair " + player.animals.get(animal1 - 1).getName() + " with: \n");
                    int count = 1;
                    for (Animal animal : tempList) {
                        System.out.println("[" + count + "] " + animal.getName() + " --> " + animal.getAnimalType() + " | " + animal.getGender()
                                + " | " + animal.getHealth() + "% health left.");
                        count++;
                    }
                    System.out.println("\nChoose your second animal for breeding! Enter number: ");
                    try {
                        animal2 = Integer.parseInt(input.nextLine());
                    } catch (Exception e) {
                        System.out.println("\n".repeat(60) + "You must enter a number for an Animal.");
                        GameHelper.menuHelper();
                    }
                }
            }
            if (checkForBreed(player.animals.get(animal1 - 1), tempList.get(animal2 - 1))) {
                if (chanceOfBreed > 50) {
                    int counter;

                    if (player.animals.get(animal1 - 1).getAnimalType().equals("rat")) {
                        counter = animalBirth(4);
                        for(int i = 0; i < counter; i++) newAnimal(player, new Rat());
                    }
                    if (player.animals.get(animal1 - 1).getAnimalType().equals("parrot")) {
                        counter = animalBirth(3);
                        for(int i = 0; i < counter; i++) newAnimal(player, new Parrot());
                    }
                    if (player.animals.get(animal1 - 1).getAnimalType().equals("cat")) {
                        counter = animalBirth(3);
                        for(int i = 0; i < counter; i++) newAnimal(player, new Cat());
                    }
                    if (player.animals.get(animal1 - 1).getAnimalType().equals("crocodile")) {
                        counter = animalBirth(2);
                        for(int i = 0; i < counter; i++) newAnimal(player, new Crocodile());
                    }
                    if (player.animals.get(animal1 - 1).getAnimalType().equals("wolf")) {
                        counter = animalBirth(2);
                        for(int i = 0; i < counter; i++) newAnimal(player, new Wolf());
                    }
                } else {
                    GameHelper.clearScreen();
                    System.out.println("Breeding failed!! ");
                    player.close_Options();
                    GameHelper.menuHelper();
                }
            } else {
                GameHelper.clearScreen();
                System.out.println("Can't breed these two animals!");
                GameHelper.menuHelper();
            }
        }
    }

    public void newAnimal(Player player, Animal animal){
        Scanner input = new Scanner(System.in);
        String gender = Animal.Gender.getRandom().toString();

        GameHelper.clearScreen();
        System.out.println("You got a " + animal.getAnimalType() + " that is " + gender + "!");
        System.out.print("Enter name: ");

        animal.setName(input.nextLine());
        animal.setGender(gender);

        player.animals.add(animal);
        player.close_Options();
    }

    public boolean checkForBreed(Animal animal1, Animal animal2){
        return animal1.getAnimalType().equals(animal2.getAnimalType()) && animal1.getGender() != animal2.getGender();
    }

    public boolean checkLeftForBreed(Animal animal1, Animal animal2){
        return animal1.getGender().equals(animal2.getGender()) || animal1.getName().equals(animal2.getName()) || !animal1.getAnimalType().equals(animal2.getAnimalType());
    }

    public boolean animalsThatCanMate(Player player, Animal animal1) {
        for (Animal animal : player.animals) {
            if (animal.getAnimalType().equals(animal1.getAnimalType()) && !animal.getGender().equals(animal1.getGender())) {
                return true;
            }
        }
        return false;
    }

    public int animalBirth(int maxBabies){
        Random random = new Random();
        int counter = 1;
        System.out.println("\n".repeat(60) + "Your breeding succeeded!");
        for(int i = 0; i < maxBabies; i++) {
            int numberOfChildren = random.nextInt(101);
            if(numberOfChildren < 20) { counter++; }
        }
        System.out.println("You got " + counter + " babies!");
        GameHelper.menuHelper();
        return counter;
    }



}
