package com.techelevator;

import com.techelevator.view.Menu;

import javax.imageio.IIOException;
import java.sql.SQLOutput;
import java.util.*;

public class VendingMachineCLI {

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
    private static final String FEED_MONEY = "Feed Money";
    private static final String SELECT_PRODUCT = "Select Product";
    private static final String FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] PURCHASE_MENU_OPTIONS = {FEED_MONEY, SELECT_PRODUCT, FINISH_TRANSACTION};
    private static final String FEED_ONE_DOLLAR = "Feed One Dollar";
    private static final String FEED_FIVE_DOLLARS = "Feed Five Dollars";
    private static final String FEED_TEN_DOLLARS = "Feed Ten Dollars";
    private static final String[] FEED_MONEY_OPTIONS = {FEED_ONE_DOLLAR, FEED_FIVE_DOLLARS, FEED_TEN_DOLLARS};

    private Menu inventory;
    private VendingMachine vendingMachine;

    public VendingMachineCLI(Menu menu) {
        this.inventory = menu;
        this.vendingMachine = new VendingMachine();
    }

    public void run() {
        System.out.println("********************************\n" +
                "***Welcome to Vendo-Matic 800***\n" +
                "********************************");
        while (true) {
            String choice = (String) this.inventory.getChoiceFromOptions(MAIN_MENU_OPTIONS);



            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                //display items using vendingMachine method
                vendingMachine.displayItems();
            }


            if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

                while (true) {
                    //print currentMoneyProvided
                    System.out.println(System.lineSeparator() + "Current Money Provided: $" + vendingMachine.getCurrentBalance());

                    choice = (String) this.inventory.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

                    if (choice.equals(FEED_MONEY)) {
                        choice = (String) this.inventory.getChoiceFromOptions(FEED_MONEY_OPTIONS);
                        if (choice.equals(FEED_ONE_DOLLAR)) {
                            vendingMachine.feedMoney(1.00);
                        }
                        if (choice.equals(FEED_FIVE_DOLLARS)) {
                            vendingMachine.feedMoney(5.00);
                        }
                        if (choice.equals(FEED_TEN_DOLLARS)) {
                            vendingMachine.feedMoney(10.00);
                        }
                    }
                    if (choice.equals(SELECT_PRODUCT)) {
                        vendingMachine.displayItems();
                        vendingMachine.selectItemToVend();
                    }
                    if (choice.equals(FINISH_TRANSACTION)) {
                        //Receive change, return to main menu
                        vendingMachine.returnChange();
                        break;
                    }
                }

            }
            if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
                System.out.println("***Thank you for using Vendo-Matic 800!***");
                vendingMachine.logFile();
                System.exit(0);
            }

        }


    }
}

