package com.company;

import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

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
                Serializer.serialize("gameName/" + gameName, game);
            }
        }
    }
    public void loadGame(){
        String[] saveFiles;
        File f = new File("SavedGames/");
        FilenameFilter filter = (f1, name) -> name.endsWith(".ser");
        saveFiles = f.list(filter);
        if (saveFiles == null || saveFiles.length == 0) {
            GameHelper.menuClearScreen();
            System.out.println("You have no saved games!");
            GameHelper.menuHelper();
        } else {
            GameHelper.menuClearScreen();
            System.out.println("\nSAVED GAMES\n");
            int n = 1;
            for (String save : saveFiles) {
                System.out.println("[" + n + "] " + save.replaceAll(".ser", ""));
                n++;
            }
            int choice = GameHelper.tryCatch(1, saveFiles.length);
            String saveString = "SavedGames/" + saveFiles[choice - 1];
            try {
                this.game = (Game) Serializer.deserialize(saveString);
                game.theGame();
            } catch (Exception error) {
                System.out.println(error);
            }
        }
    }
}
