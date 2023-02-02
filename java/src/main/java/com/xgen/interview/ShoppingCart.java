package com.xgen.interview;

import com.xgen.interview.currency.Currency;
import com.xgen.interview.pricer.IPricer;

import java.util.*;


/**
 * This is the current implementation of ShoppingCart.
 * Please write a replacement
 */
public class ShoppingCart implements IShoppingCart {
    Map<String, Integer> cartItems = new HashMap<>();
    IPricer pricer;

    public ShoppingCart(IPricer pricer) {
        this.pricer = pricer;
    }

    public void addItem(String reference, int number) {
        if (!this.cartItems.containsKey(reference)) {
            this.cartItems.put(reference, number);
        } else {
            int existing = this.cartItems.get(reference);
            this.cartItems.put(reference, existing + number);
            // Need to change this to allow order of insertion
        }
    }

    public void printReceipt() {
        String[] references = this.cartItems.keySet().toArray(new String[0]);

        for (String reference: references) {
            Product product = this.pricer.getProduct(reference);
            Integer amount = this.cartItems.get(reference);

            if (product == null) {
                product = new Product(reference, "DISCONTINUED", new Currency(0));
            }

            String output = amount + "x - " + reference + " - " + product.getName() + " - " + product.getPrice().times(amount);

            System.out.println(output);
        }
    }
}
