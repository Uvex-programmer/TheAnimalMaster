package com.company;

import com.company.animals.Animal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Events implements Serializable {

    public Events(){
    }

    public void animal_Lose_Health(Player player){
        Random random = new Random();
        for(Animal animal: player.animals){
            int damage = 10 + random.nextInt(21);
            animal.setHealth(animal.getHealth() - damage);
        }
    }

    public void player_Lost(Player player, Game game){
        if(player.animals.size() <= 0 && player.getMoney() <= 0){
            GameHelper.clearScreen();
            System.out.println(player.getName() + " -> have no money/food left. You are OUT!");
            GameHelper.menuHelper();
            game.players.remove(player);
            game.playersWhoLost.add(player);
        }
    }

    public void sell_All_Animals(Game game){
        GameHelper.clearScreen();
        System.out.println("Game is now ending... All animals every player have left, have been sold for money..");
        for(Player player: game.players){

            for(int i = 0; i < player.animals.size(); i++){
                player.addMoney(player.animals.get(i).getCurrentPrice());
                player.animals.remove(player.animals.get(i));
                i--;
            }
        }
    }

    public void check_Winner(Game game){
        int bestScore = 0;
        int bestIndex = 0;
        if(game.players.size() == 0){
            System.out.println("\nThere is no winner, only losers!!");
            GameHelper.menuHelper();
        }else {
            sell_All_Animals(game);
            for (int i = 0; i < game.players.size(); i++) {
                if (game.players.get(i).getMoney() > bestScore) {
                    bestScore = game.players.get(i).getMoney();
                    bestIndex = i;
                }
            }
            System.out.println("\nThe winner of this game is: " + game.players.get(bestIndex).getName() + " with: " + game.players.get(bestIndex).getMoney() + "$!\n");
            for (Player player : game.players) {
                System.out.println(player.getName() + ": " + player.getMoney() + "$.");
            }
            for(Player player: game.playersWhoLost){
                System.out.println(player.getName() + ": " + player.getMoney() + "$.");
            }
            GameHelper.menuHelper();
        }
    }

    public void animal_Age(Player player){
        for(Animal animal: player.animals)
            animal.setAge(1);
    }

    public void check_If_Animal_Dead(Player player){
        ArrayList<Animal> deadAnimalsOfHealth = new ArrayList<>();
        ArrayList<Animal> deadAnimalsOfAge = new ArrayList<>();

        for (int i = 0; i < player.animals.size(); i++) {
            if (player.animals.get(i).getHealth() < 1) {
                deadAnimalsOfHealth.add(player.animals.get(i));
                player.animals.remove(player.animals.get(i));
                i--;

            } else if (player.animals.get(i).getAge() > player.animals.get(i).getMaxAge()) {
                deadAnimalsOfAge.add(player.animals.get(i));
                player.animals.remove(player.animals.get(i));
                i--;
            }
        }
        if(deadAnimalsOfAge.size() > 0 || deadAnimalsOfHealth.size() > 0) {
            GameHelper.clearScreen();
            if (deadAnimalsOfHealth.size() > 0) {
                System.out.println("\n" + player.getName() + " you have animals that died from low health.");
                for (Animal animal : deadAnimalsOfHealth) {
                    System.out.println(animal.getName() + " died from low health.");
                }
            }
            if (deadAnimalsOfAge.size() > 0) {
                System.out.println("\n" + player.getName() + " you have animals that died from old age.");
                for (Animal animal : deadAnimalsOfAge) {
                    System.out.println(animal.getName() + " died from old age.");
                }
            }
            GameHelper.menuHelper();
        }

    }

    public void beginning_Of_Round(Player player){
        animal_Age(player);
        animalCureDisease(player);
        check_If_Animal_Dead(player);
    }

    public void end_Of_Round(Player player, Game game){
        player_Lost(player, game);
        animal_Lose_Health(player);
        animalDisease(player);
    }

    public void animalDisease(Player player){
        Random random = new Random();
        for(Animal animal : player.animals){
            int number = random.nextInt(101);
            if(number < 20)
                animal.setSick(true);
        }

    }

    public void animalCureDisease(Player player){
        Random random = new Random();
        int chanceOfCure = 0;
        for(int i = 0; i < player.animals.size(); i++){
            if(player.animals.get(i).isSick()){
                GameHelper.clearScreen();
                System.out.println(player.animals.get(i).getName() + " have suffered from a disease...\n"
                                                    + " Do you wanna pay the vet to try save " + player.animals.get(i).getName() + "? 50% chance to die..\n"
                                                    + " It will cost " + player.animals.get(i).getVetCost() + "$.\n\n"
                                                    + " |1| - Yes \n |2| - No");
                int choice = GameHelper.tryCatch(1,2);
                if(choice == 1) {
                    chanceOfCure = random.nextInt(101);
                    if (chanceOfCure < 50) {
                        player.animals.get(i).setSick(false);
                        GameHelper.clearScreen();
                        System.out.println(player.animals.get(i).getName() + " got cured from the disease!! Congratulations!");
                        player.removeMoney(player.animals.get(i).getVetCost());
                        GameHelper.menuHelper();
                    } else {
                        player.animals.get(i).setHealth(0);
                        GameHelper.clearScreen();
                        System.out.println(player.animals.get(i).getName() + " didn't survive the disease and have died...");
                        player.removeMoney(player.animals.get(i).getVetCost());
                        GameHelper.menuHelper();
                        player.animals.remove(player.animals.get(i));
                    }
                }

                if(choice == 2) {
                    player.animals.get(i).setHealth(0);
                    GameHelper.clearScreen();
                    System.out.println(player.animals.get(i).getName() + " didn't survive the disease and have died...");
                    GameHelper.menuHelper();
                    player.animals.remove(player.animals.get(i));
                    return;
                }

            }
        }
    }


}
