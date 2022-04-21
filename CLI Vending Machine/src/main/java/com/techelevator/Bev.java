package com.techelevator;

public class Bev extends Products {

    //constructors
    public Bev (String name, double price) {
        super(name, price);
    }

    @Override
    public String getMessage() {
        return "Glug Glug, Yum!";
    }
}
