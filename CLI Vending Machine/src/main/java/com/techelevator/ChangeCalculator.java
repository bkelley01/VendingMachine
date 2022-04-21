package com.techelevator;


public class ChangeCalculator {

    //calculates the required change in the least amount of coins possible

    public void change(double coins) {

        //vars

        int quarters = 0;
        int dimes = 0;
        int nickles = 0;
        int pennies = 0;

        while (coins >= 0.25) {
            quarters = quarters + 1;
            coins = coins - 0.25;
        }
        while (coins >= 0.10) {
            dimes = dimes + 1;
            coins = coins - 0.10;
        }
        while (coins >= 0.05) {
            nickles = nickles + 1;
            coins = coins - 0.05;
        }
        while (coins >= 0.01) {             //redundant for this project but added
            pennies = pennies + 1;
            coins = coins - 0.01;
        }

        if ((dimes == 0) && (nickles == 0)) {
            System.out.println(System.lineSeparator() + "Your change is " + quarters + " quarters.");
        }
        else if (nickles == 0) {
            System.out.println(System.lineSeparator() + "Your change is " + quarters + " quarters and " + dimes + " dimes.");
        } else if (dimes == 0) {
            System.out.println(System.lineSeparator() + "Your change is " + quarters + " quarters and " + nickles + " nickles.");
        } else {

            System.out.println(System.lineSeparator() + "Your change is " + quarters + " quarters, " + dimes + " dimes and " + nickles + " nickles.");
        }
    }


}

