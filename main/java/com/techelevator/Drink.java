package com.techelevator;

import java.math.BigDecimal;

public class Drink extends VendingMachineItem{
    public Drink(String slot, String name, BigDecimal price, int amount) {
        super(slot, name, price);
        this.message = "Glug Glug, Yum!";
        this.amount = amount;
    }
}
