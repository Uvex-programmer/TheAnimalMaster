package com.company;

import java.io.*; // serialization/deserialization

// A helper class to serialize and deserialize data structure
// (objects, array list of objects etc)
public class Serializer implements Serializable{




    static public boolean serialize(String filePath, Object data) {
        try {
            var file = new FileOutputStream(filePath);
            var out = new ObjectOutputStream(file);
            out.writeObject(data);
            out.close();
            file.close();
            GameHelper.menuClearScreen();
            System.out.println("Game is saved!");
            GameHelper.menuHelper();
            return true; // everything went fine
        }
        catch(Exception error){
            System.out.println("Game did not get saved");
            System.out.println(error);
            return false; // we couldn't complete the serialization
        }
    }

    static public Object deserialize(String filePath){
        try {
            var file = new FileInputStream(filePath);
            var in = new ObjectInputStream(file);
            var data = in.readObject();
            in.close();
            file.close();
            return data;
        }
        catch(Exception error){
            System.out.println(error);
            return false; // we couldn't complete deserialization
        }
    }

}