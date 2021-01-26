package com.company;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class save implements Serializable {

    Game game;


    public void setgame(Game game){
        this.game = game;
    }
    public void saveGame(Game game){
        if(!Files.exists(Paths.get("Robin.ser"))){
            Serializer.serialize("Robin.ser", game);
            System.out.println("Created the game file and serialized game to disk!");
        }
    }
    public void loadGame(){
        try {
            this.game = (Game) Serializer.deserialize("Robin.ser");
            game.theGame();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}
