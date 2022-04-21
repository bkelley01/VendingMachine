package com.techelevator;

public class Gum extends Products {

    //constructors
    public Gum (String name, double price) {
        super(name, price);
    }

    @Override
    public String getMessage() {
        return "Chew Chew, Yum!";
    }
}
