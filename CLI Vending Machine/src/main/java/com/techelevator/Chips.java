package com.techelevator;

public class Chips extends Products {

    //constructors
    public Chips (String name, double price) {
        super(name, price);
    }

    @Override
    public String getMessage() {
        return "Crunch Crunch, Yum!";
    }
}
