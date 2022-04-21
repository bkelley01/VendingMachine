package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {

    private VendingMachine test;


    @Before
    public void SetUp() {
        this.test = new VendingMachine();
    }

    @Test
    public void feeding_money_feeds_balance_1_dollar() {
        test.feedMoney(1);
        double actual = test.getCurrentBalance();
        Assert.assertEquals(1, actual, 0.001);
    }

    @Test
    public void feeding_money_feeds_balance_5_dollar() {
        test.feedMoney(5);
        double actual = test.getCurrentBalance();
        Assert.assertEquals(5, actual, 0.001);
    }

    @Test
    public void feeding_money_feeds_balance_10_dollar() {
        test.feedMoney(10);
        double actual = test.getCurrentBalance();
        Assert.assertEquals(10, actual, 0.001);
    }

    @Test
    public void returns_correct_change() {

        test.currentBalance = 1.85;
        test.returnChange();
        double actual = test.getCurrentBalance();

        Assert.assertEquals("Did not give correct change", 0.00, actual, .001);

    }

    @Test
    public void reduces_inventory_when_item_is_purchased() {

        test.getInventory().get("A1").purchaseItem();

        int actual = test.getInventory().get("A1").getNumberInStock();

        Assert.assertEquals("Did not reduce inventory when item was purchased", 4, actual);

    }
    @Test
    public void reduces_current_balance_when_item_is_purchased() {

        test.currentBalance = 5.00;

        test.currentBalance = test.currentBalance - test.getInventory().get("A1").getPrice();

        double actual = test.getCurrentBalance();

        Assert.assertEquals("Did not reduce current balance when item was purchased", 1.95, actual, .001);

    }
}