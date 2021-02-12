package com.company.foods;

public class Vegetables extends Food {

    private String name;
    private final int price;

    public Vegetables() {
        this.name = "Vegetables";
        this.price = 5;
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
