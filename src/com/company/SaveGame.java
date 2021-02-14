package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveGame implements Serializable {


    Game game;

    public void setgame(Game game){
        this.game = game;
    }
    public void saveGame(Game game){

        File gameFile = new File("SavedGames/");
        GameHelper.clearScreen();
        boolean running = true;
        while(running) {
            System.out.print("Name your saved game file: ");
            String gameName = (GameHelper.input.nextLine() + ".ser");

            if (!Files.exists(Paths.get("SavedGames/" + gameName))) {
                if (!gameFile.exists()) {
                    gameFile.mkdir();
                }
                Serializer.serialize("SavedGames/" + gameName, game);
                running = false;
            } else {
                System.out.println("Filename already exist.");
                System.out.println("[1] Overwrite existing file\n"
                                 + "[2] Enter new name");
                if (GameHelper.tryCatch(1, 2) == 1) {
                    Serializer.serialize("SavedGames/" + gameName, game);
                    running = false;
                }
            }
        }
    }
    public void loadGame(){
        File[] savedGames;
        File f = new File("SavedGames/");
        FilenameFilter filter = (dir, name) -> name.endsWith(".ser");

        savedGames = f.listFiles(filter);

        GameHelper.clearScreen();
        if(savedGames == null){
            System.out.println("You have no saved games");
                GameHelper.menuHelper();

        }else {
            System.out.println("Choose a game to load!\n");
            int counter = 1;
            for (File file : savedGames) {
                System.out.println("|" + counter + "| - " + file.getName());
                counter++;
            }
            System.out.println("\n|0| - Back");

            System.out.println("\n");
            int choice = GameHelper.tryCatch(0,savedGames.length);
            if(choice == 0){
                return;
            }
            String gameFile = savedGames[choice - 1].toString();
            try{
                this.game = (Game) Serializer.deserialize(gameFile);
                game.theGame();
            }catch (Exception error){
                System.out.println(error);
            }
        }
    }
}
