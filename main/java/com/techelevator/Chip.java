package com.techelevator;

import java.math.BigDecimal;

public class Chip extends VendingMachineItem{
    public Chip(String slot, String name, BigDecimal price, int amount) {
        super(slot, name, price);
        this.message = "Crunch Crunch, Yum!";
        this.amount = amount;
    }
}
