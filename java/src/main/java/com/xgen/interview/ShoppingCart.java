package com.xgen.interview;

import com.xgen.interview.currency.Currency;
import com.xgen.interview.formatter.Formatter;
import com.xgen.interview.formatter.FormatterAmountInitial;
import com.xgen.interview.pricer.IPricer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * This is the current implementation of ShoppingCart.
 * Please write a replacement
 */
public class ShoppingCart implements IShoppingCart {
    protected LinkedHashMap<String, ShoppingCartEntry> cartItems = new LinkedHashMap<>();
    IPricer pricer;
    private final Formatter formatter;

    public ShoppingCart(IPricer pricer) {
        this(pricer, new FormatterAmountInitial());
    }

    public ShoppingCart(IPricer pricer, Formatter formatter) {
        this.pricer = pricer;
        this.formatter = formatter;
    }

    public void addItem(String reference, int amount) {
        if (!this.cartItems.containsKey(reference)) {
            Product product = this.pricer.getProduct(reference);

            ShoppingCartEntry cartEntry = new ShoppingCartEntry(product, amount);
            this.cartItems.put(reference, cartEntry);
        } else {
            ShoppingCartEntry cartEntry = this.cartItems.get(reference);
            cartEntry.addAmount(amount);
        }
    }

    public void printReceipt() {
        String[] references = this.cartItems.keySet().toArray(new String[0]);

        Currency total = new Currency(0);

        for (String reference: references) {
            ShoppingCartEntry cartEntry = this.cartItems.get(reference);
            Product product = cartEntry.getProduct();
            Integer amount = cartEntry.getAmount();

            total = total.add(product.getPrice().times(amount));

            this.formatter.addProductLine(product, amount, cartEntry.total());
        }

        this.formatter.addTotalLine(total);
    }
}
