package com.xgen.interview;

import java.lang.reflect.Array;
import java.util.*;


/**
 * This is the current implementation of ShoppingCart.
 * Please write a replacement
 */
public class ShoppingCart implements IShoppingCart {
    Map<String, Integer> contents = new HashMap<>();
    Pricer pricer;

    public ShoppingCart(Pricer pricer) {
        this.pricer = pricer;
    }

    public void addItem(String itemType, int number) {
        if (!contents.containsKey(itemType)) {
            contents.put(itemType, number);
        } else {
            int existing = contents.get(itemType);
            contents.put(itemType, existing + number);
        }
    }

    public void printReceipt() {
        Object[] keys = contents.keySet().toArray();

        for (Object key : keys) {
            int price = pricer.getPrice((String)key) * contents.get(key);
            Float priceFloat = (float) price / 100;
            String priceString = String.format("€%.2f", priceFloat);

            System.out.println(key + " - " + contents.get(key) + " - " + priceString);
        }
    }
}
