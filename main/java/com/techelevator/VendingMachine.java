package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    // importing stock csv file
    Scanner fileScanner;
    Scanner userInput = new Scanner(System.in);
    List<VendingMachineItem> items = new ArrayList<>();
    private BigDecimal money;
    private final double QUARTER = 0.25;
    private final double DIME = 0.10;
    private final double NICKLE = 0.05;
    Logger logLogger = new Logger("Log.txt");
    private File salesReport;

    public void importGoods() {
        try {
            fileScanner = new Scanner(new File("vendingmachine.csv"));
            while (fileScanner.hasNextLine()) {
                String lineOfInput = fileScanner.nextLine();
                String[] strArr = lineOfInput.split("\\|");
                String location = strArr[0];
                String name = strArr[1];
                BigDecimal price = new BigDecimal(strArr[2]);
                String type = strArr[3];

                if (type.equals("Candy")) {
                    VendingMachineItem blah = new Candy(location, name, price, 5);
                    items.add(blah);
                } else if (type.equals("Chip")) {
                    VendingMachineItem blah = new Chip(location, name, price, 5);
                    items.add(blah);
                } else if (type.equals("Drink")) {
                    VendingMachineItem blah = new Drink(location, name, price, 5);
                    items.add(blah);
                } else if (type.equals("Gum")) {
                    VendingMachineItem blah = new Gum(location, name, price, 5);
                    items.add(blah);
                } else {
                    System.out.println("Error");
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void mainMenu() {
        boolean stay = true;
        while (stay) {
            System.out.println("\n(1) Display Vending Machine Items\n(2) Purchase\n(3) Exit");
            String input = userInput.nextLine();
            if (input.equals("1")) {
                for (VendingMachineItem item : items) {
                    if (item.getAmount() < 1) {
                        System.out.println(item.getName() + " " + "SOLD OUT");
                    } else {
                        System.out.println(item.getName() + " " + item.getAmount());
                    }
                }
            } else if (input.equals("2")) {
                purchase();
            } else if (input.equals("3")) {
                stay = false;
            } else if (input.equals("4")) {
                //salesreport
                LocalDateTime date = LocalDateTime.now();
                String dateFormatted = DateTimeFormatter.ofPattern("yyyy_MM_dd hh_mm_ss").format(date);
                salesReport = new File(dateFormatted + ".txt");

                try (PrintWriter pw = new PrintWriter(salesReport)){

                    BigDecimal totalSales = new BigDecimal("0.00");

                    for (VendingMachineItem item : items) {
                        int amountSold = 5 - item.getAmount();
                        totalSales= totalSales.add(item.getPrice().multiply(BigDecimal.valueOf(amountSold)));
                        pw.println(item.getName() + "|" + amountSold);
                    }
                    pw.println("\n**TOTAL SALES** $" + totalSales);

                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }

            } else {
                System.out.println("Enter 1, 2, or 3");
            }
        }
    }

    public void purchase() {
        boolean stay = true;
        money = new BigDecimal("0.00");
        while (stay) {
            System.out.println("Current Money Provided: $" + money + "\n" +
                    "\n" +
                    "(1) Feed Money\n" +
                    "(2) Select Product\n" +
                    "(3) Finish Transaction\n");
            String input = userInput.nextLine();
            if (input.equals("1")) {
                System.out.println("Enter cash: ");
                BigDecimal amount;
                try {
                    amount = userInput.nextBigDecimal();
                    money = money.add(amount);
                    logLogger.writeMessage("FEED MONEY: $" + amount + " $" + money);
                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (input.equals("2")) {
                for (VendingMachineItem item : items) {
                    System.out.println(item.slot + " " + item.name);
                }
                System.out.println("Whatcha want??");
                String inputSlot = userInput.nextLine();
                boolean found = false;
                for (VendingMachineItem item : items) {
                    if (inputSlot.equals(item.slot) && item.getAmount() < 1) {
                        System.out.println("SOLD OUT");
                        found = true;
                    } else if (inputSlot.equals(item.slot) && item.getAmount() > 0 && money.compareTo(item.getPrice())>=0) {
                        money= money.subtract(item.getPrice());
                        item.amount--;
                        System.out.println(item.name + " " + item.price + " " + money + " " + item.message);
                        logLogger.writeMessage(item.getName() + " " + item.getSlot() + " " + item.getPrice() + " " + money);
                        found = true;
                    } else if(inputSlot.equals(item.slot)&& money.compareTo(item.getPrice())<0){
                        System.out.println("U BROKE");
                        found=true;
                    }
                }
                if (found == false) {
                    System.out.println("Item does not exist!");
                }
            }
            if (input.equals("3")) {
                int quarters = 0;
                int dimes = 0;
                int nickles = 0;
                logLogger.writeMessage("GIVE CHANGE: $" + money + " $0.00");
                while (money.compareTo(BigDecimal.ZERO) > 0) {
                    quarters = money.divide(BigDecimal.valueOf(QUARTER)).intValue();
//                    BigDecimal quartersValue = BigDecimal.valueOf(quarter*quarters);
//                    money= money.subtract(quartersValue);
                    money = money.remainder(BigDecimal.valueOf(QUARTER));
                    dimes = money.divide(BigDecimal.valueOf(DIME)).intValue();
                    money = money.remainder(BigDecimal.valueOf(DIME));
                    nickles = money.divide(BigDecimal.valueOf(NICKLE)).intValue();
                    money = money.remainder(BigDecimal.valueOf(NICKLE));
                }
                System.out.println("Your change is:\n" + quarters + " quarters\n" + dimes + " dimes\n" + nickles + " nickles\n");
                stay = false;
            }
        }
    }
}
