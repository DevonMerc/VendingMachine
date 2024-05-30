package com.techelevator;

import java.math.BigDecimal;

public class Candy extends VendingMachineItem{
    public Candy(String slot, String name, BigDecimal price, int amount) {
        super(slot, name, price);
        this.message = "Munch Munch, Yum!";
        this.amount = amount;
    }
}
