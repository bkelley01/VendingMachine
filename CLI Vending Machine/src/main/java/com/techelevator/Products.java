package com.techelevator;

public abstract class Products {

    //variables
    private String name;
    private double price;
    //vending machine will restock to 5 everytime it restarts
    private int numberInStock = 5;
    private String getMessage;


    //constructors
    public Products(String name, double price) {
        this.name = name;
        this.price = price;
    }

    //getters
    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getNumberInStock() {
        return numberInStock;
    }

    //abstract so it can be overriden in each product child class
    public abstract String getMessage();


    //setters
    private void setName(String name) {
        this.name = name;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    //calls to see if numberInStock has made it below the buyable amount
    public boolean inStock() {
        return numberInStock >= 1;
    }

    public void purchaseItem() {
        numberInStock = numberInStock - 1;
    }

}
