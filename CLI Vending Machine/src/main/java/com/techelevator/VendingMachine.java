package com.techelevator;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;


public class VendingMachine {

    //variables
    private double startingBalance = 0.00; //define in method
    public double currentBalance = 0.00;
    public double endingBalance = 0.00;    //define in method
    private Map<String, Products> inventory;
    public List<String> list = new ArrayList<>(); //change name


    public double getStartingBalance() {
        return startingBalance;
    }

    public double getEndingBalance() {
        return endingBalance;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void feedMoney(double moneyFed) {
        startingBalance = startingBalance + currentBalance;
        endingBalance = currentBalance + moneyFed;
        currentBalance = endingBalance;
        log("FEED MONEY", startingBalance, endingBalance);

    }

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

    public void logFile() {
        File outputFile = new File("C:\\Users\\Student\\workspace\\capstone-1-team-0\\capstone\\log.txt");

        try (FileWriter logWriter = new FileWriter(outputFile, true)) {
            for (String str2 : list) {
                logWriter.write(str2);
                logWriter.write("\n");
            }
        } catch (IOException e) {
            System.out.println("File location not valid");
        }
    }

    public void log(String name, double startingBalance, double endingBalance) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time1 = now.format(time);
        DecimalFormat format = new DecimalFormat("#.00");
        String str = time1 + " " + name + " $" + format.format(startingBalance) + " $" + format.format(endingBalance);

        list.add(str);
    }

    //This method to vend product is now working correctly
    public void selectItemToVend() {
        try {
            Scanner scanner = new Scanner(System.in);
            String slotChoice = null;
            System.out.print(System.lineSeparator() + "Please input slot location for desired item >>> ");
            slotChoice = scanner.nextLine().toUpperCase();

            if (!inventory.containsKey(slotChoice)) {
                System.out.println(System.lineSeparator() + "Sorry, " + slotChoice + " is not a valid selection");

            }
            if (!inventory.get(slotChoice.toUpperCase()).inStock()) {
                System.out.println(System.lineSeparator() + "Sorry, " + inventory.get(slotChoice).getName() + " is not available\n Please make another selection");

            } else if (inventory.get(slotChoice).getPrice() > currentBalance) {
                System.out.println(System.lineSeparator() + "Please deposit more funds");

            } else {
                System.out.println(System.lineSeparator() + "Dispensing " + inventory.get(slotChoice).getName() + " for $" + inventory.get(slotChoice).getPrice());
                System.out.println(System.lineSeparator() + inventory.get(slotChoice).getMessage());
                startingBalance = currentBalance;
                endingBalance = (currentBalance - inventory.get(slotChoice).getPrice());
                currentBalance = endingBalance;
                inventory.get(slotChoice).purchaseItem();


                log(inventory.get(slotChoice).getName(), startingBalance, endingBalance);
            }
        } catch (Exception e) {
            System.out.println("Return to Purchase menu...");
        }
    }

    public void returnChange() {
        if (currentBalance > 0) {
            startingBalance = currentBalance;
            ChangeCalculator changeCalculator = new ChangeCalculator();
            changeCalculator.change(currentBalance);
        }
        currentBalance = 0;
        endingBalance = 0;
        log("GIVE CHANGE", startingBalance, endingBalance);
    }
}







