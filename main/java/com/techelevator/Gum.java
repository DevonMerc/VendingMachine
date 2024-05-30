package com.techelevator;

import java.math.BigDecimal;

public class Gum extends VendingMachineItem{
    public Gum(String slot, String name, BigDecimal price, int amount) {
        super(slot, name, price);
        this.message = "Chew Chew, Yum!";
        this.amount = amount;
    }
}
