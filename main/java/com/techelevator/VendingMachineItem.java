package com.techelevator;

import java.math.BigDecimal;

public abstract class VendingMachineItem {
    protected String name;
    protected BigDecimal price;
    protected String message;
    protected String slot;
    protected int amount;
    public VendingMachineItem(String slot, String name, BigDecimal price) {
        this.slot = slot;
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public String getMessage() {
        return message;
    }
    public String getSlot() {
        return slot;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
