package com.company;

public class Player {

    private String name;
    private int money = 200;

    public Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }
}
