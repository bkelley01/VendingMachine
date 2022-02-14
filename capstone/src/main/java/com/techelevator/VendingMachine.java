package com.techelevator;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;


public class VendingMachine {


    //variables
    private double startingBalance = 0;
    public double currentMoneyProvided = 0;
    private Map<String, Products> inventory;
    public List<String> list;


    public double getStartingBalance() {
        return startingBalance + currentMoneyProvided;
    }

    public double getCurrentMoneyProvided() {
        return currentMoneyProvided;
    }

    public void feedMoney(double moneyFed) {
        currentMoneyProvided = currentMoneyProvided + moneyFed;
    }


//    String path = "C:\\Users\\Student\\workspace\\capstone-1-team-0\\capstone\\vendingmachine.csv";
//    File inputFile = new File(path);

    //constructor
    public VendingMachine() {
        this.inventory = loadInventory();
    }

    public Map<String, Products> getInventory() {
        return inventory;
    }


    private Map<String, Products> loadInventory() {
        String path = "C:\\Users\\Student\\workspace\\capstone-1-team-0\\capstone\\vendingmachine.csv";
        File inputFile = new File(path);
        Map<String, Products> inventory = new HashMap<>();
        try (Scanner fileScanner = new Scanner(inputFile)) {


            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] value_split = line.split(Pattern.quote("|"));
                if (value_split[3].equals("Chip")) {
                    Products product = new Chips(value_split[1], Double.parseDouble(value_split[2]));
                    inventory.put(value_split[0], product);

                } else if (value_split[3].equals("Candy")) {
                    Products product = new Candy(value_split[1], Double.parseDouble(value_split[2]));
                    inventory.put(value_split[0], product);

                } else if (value_split[3].equals("Drink")) {
                    Products product = new Bev(value_split[1], Double.parseDouble(value_split[2]));
                    inventory.put(value_split[0], product);

                } else if (value_split[3].equals("Gum")) {
                    Products product = new Gum(value_split[1], Double.parseDouble(value_split[2]));
                    inventory.put(value_split[0], product);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading...");
        }
        return inventory;

    }

    public void displayItems() {// CLI is using this method for displaying items...
        Map<String, Products> sortedInventory = new TreeMap<>(getInventory());

        String[] productArray = new String[getInventory().size()];

        Set<Map.Entry<String, Products>> entrySet = sortedInventory.entrySet();
        int counter = 0;
        for (Map.Entry<String, Products> entry : entrySet) {
            String key = entry.getKey();
            Products value = entry.getValue();
            productArray[counter] = "Slot: " + key + " | " + "Item: " + value.getName() + " | " + "Price: " + "$" + value.getPrice() + " | " + "Quantity: " + value.getNumberInStock();
            counter++;
        }
        for (int i = 0; i < counter; i++) {
            System.out.println(productArray[i]);
        }
    }

    //*****this is SUPPOSED to write a new file log.txt when run... cant test functionality until we figure out vending classes
    public void logFile() /*throws IOException*/ {
        File outputFile = new File("C:\\Users\\Student\\workspace\\capstone-1-team-0\\capstone\\log.txt");
        //List<String> list = getList();
        try (FileWriter logWriter = new FileWriter(outputFile, true)) {
            for (String str : list) {
                logWriter.write(str);
                logWriter.write("\n");
            }
        }catch (IOException e) {
            System.out.println("File location not valid");}
    }

    public void log(String name, double startingBalance, double endAmount) {

        LocalDateTime time = LocalDateTime.now();
        DecimalFormat format = new DecimalFormat("#.00");
        String str = time + " " + name + " " + getStartingBalance() + " " + format.format(endAmount);
        //List<String> list = getList();
        list.add(str);
    }

    public void getList() {
        //return this.list;
        System.out.println(list);
    }

    //working on this method to vend product...
    public void selectItemToVend() {
        try {
            Scanner scanner = new Scanner(System.in);
            String slotChoice = null;
            System.out.print(System.lineSeparator() + "Please input slot location for desired item >>> ");
            slotChoice = scanner.nextLine().toUpperCase();

            if (!inventory.containsKey(slotChoice) || slotChoice.equals(" ")) {
                System.out.println(System.lineSeparator() + "Sorry, " + slotChoice + " is not a valid selection");

            }
            if (!inventory.get(slotChoice.toUpperCase()).inStock()) {
                System.out.println(System.lineSeparator() + "Sorry, " + inventory.get(slotChoice).getName() + " is not available\n Please make another selection");

            } else if (inventory.get(slotChoice).getPrice() > currentMoneyProvided) {
                System.out.println(System.lineSeparator() + "Please deposit more funds");

            } else {
                System.out.println(System.lineSeparator() + "Dispensing " + inventory.get(slotChoice).getName() + " for $" + inventory.get(slotChoice).getPrice());
                System.out.println(System.lineSeparator() + inventory.get(slotChoice).getMessage());
                currentMoneyProvided = currentMoneyProvided - inventory.get(slotChoice).getPrice();
                inventory.get(slotChoice).purchaseItem();

                log(inventory.get(slotChoice).getName(), startingBalance, currentMoneyProvided);
            }
        } catch (Exception e) {
            System.out.println("Return to Purchase menu...");
        }
    }

    public void returnChange() {
        if (currentMoneyProvided > 0) {
            ChangeCalculator changeCalculator = new ChangeCalculator();
            changeCalculator.change(currentMoneyProvided);
        }
        currentMoneyProvided = 0;
    }
}






