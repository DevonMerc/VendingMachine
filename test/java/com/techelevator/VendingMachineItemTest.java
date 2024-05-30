package com.techelevator;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class VendingMachineItemTest {
Chip chip = new Chip("A1", "Chippy", BigDecimal.valueOf(5.50), 5);
Gum gum = new Gum("B9", "Chewey", BigDecimal.valueOf(0.95), 3);
Drink drink = new Drink("D4", "DEW", BigDecimal.valueOf(10.00), 1);
Candy candy = new Candy("C5", "MnM", BigDecimal.valueOf(1.00), 5);
VendingMachineItem[] actuals =new VendingMachineItem[]{chip, gum, drink, candy};


    @Test
    public void getName() {
        String[] namesActual = new String[4];
        int i=0;
        for(VendingMachineItem actual : actuals){
            namesActual[i]=(actual.getName());
            i++;
        }
        String[] names = new String[]{"Chippy", "Chewey", "DEW", "MnM"};
        assertArrayEquals(names, namesActual);
    }

    @Test
    public void getPrice() {
        BigDecimal[] priceActual = new BigDecimal[4];
        for(int i = 0; i< priceActual.length; i++ ){
            priceActual[i]= actuals[i].getPrice();
        }
        BigDecimal[] prices = new BigDecimal[]{BigDecimal.valueOf(5.50), BigDecimal.valueOf(0.95), BigDecimal.valueOf(10.00), BigDecimal.valueOf(1.00)};
        assertArrayEquals(prices, priceActual);
    }

    @Test
    public void getMessage() {
        String[] messActual = new String[4];
        int i=0;
        for(VendingMachineItem actual : actuals){
            messActual[i]=(actual.getMessage());
            i++;
        }
        String[] message = new String[]{"Crunch Crunch, Yum!", "Chew Chew, Yum!", "Glug Glug, Yum!", "Munch Munch, Yum!"};
        assertArrayEquals(message, messActual);
    }

    @Test
    public void getSlot() {
        String[] slotActual = new String[4];
        for(int i=0; i< slotActual.length; i++){
            slotActual[i]= actuals[i].getSlot();
        }
        String[] slot= new String[]{"A1", "B9", "D4", "C5"};
        assertArrayEquals(slot, slotActual);
    }

    @Test
    public void getAmount() {
        int[] amountActual = new int[4];
        for(int i=0; i< amountActual.length; i++){
            amountActual[i]= actuals[i].getAmount();
        }
        int[] amount= new int[]{5,3,1,5};
        assertArrayEquals(amount, amountActual);
    }

    @Test
    public void setAmount() {
        for(VendingMachineItem item : actuals){
            item.setAmount(4);
            assertEquals(4, item.getAmount());
        }
    }


}