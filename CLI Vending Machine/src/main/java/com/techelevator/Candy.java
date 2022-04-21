package com.techelevator;

public class Candy extends Products {

    //constructors
    public Candy (String name, double price) {
        super(name, price);
    }

    @Override
    public String getMessage() {
        return "Munch Munch, Yum!";
    }
}
