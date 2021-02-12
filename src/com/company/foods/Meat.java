package com.company.foods;

public class Meat extends Food {

    private String name;
    private final int price;

    public Meat() {
        this.name = "Meat";
        this.price = 10;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getPrice() {
        return price;
    }

}
