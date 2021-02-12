package com.company.foods;

public class SuperFood extends Food {

    private String name;
    private final int price;

    public SuperFood() {
        this.name = "Super food";
        this.price = 25;
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
