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

        File f = new File("SavedGames/");
        GameHelper.menuClearScreen();
        System.out.print("Name your saved game file: ");
        String gameName = (GameHelper.input.nextLine() + ".ser");

        if (!Files.exists(Paths.get("SavedGames/" + gameName))) {
            if (!f.exists()) {
                f.mkdir();
            }
            Serializer.serialize("SavedGames/" + gameName, game);
        } else {
            System.out.println("Filename already exist.");
            System.out.println("[1] Overwrite existing game\n" + "[2] Create a new one");
            if (GameHelper.tryCatch(1, 2) == 1) {
                Serializer.serialize("SavedGames/" + gameName, game);
            }
        }
    }
    public void loadGame(){
            File[] savedGames;
            File f = new File("SavedGames/");
            FilenameFilter filter = (dir, name) -> {
                // We only want the files that ends with .ser
                return name.endsWith(".ser");
            };

            savedGames = f.listFiles(filter);

        GameHelper.menuClearScreen();
        if(savedGames == null){
            System.out.println("You have no saved files");
                GameHelper.menuHelper();

        }else {
            //Print out the saved game files.
            System.out.println("Choose a game to load!\n");
            int counter = 1;
            for (File file : savedGames) {
                System.out.println("|" + counter + "| - " + file.getName());
                counter++;
            }

            System.out.println("\n");
            int choice = GameHelper.tryCatch(1,savedGames.length);
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
