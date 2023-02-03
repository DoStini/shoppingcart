package com.xgen.interview;

import com.xgen.interview.currency.Currency;
import com.xgen.interview.formatter.Formatter;
import com.xgen.interview.formatter.FormatterAmountInitial;
import com.xgen.interview.pricer.IPricer;

import java.util.HashMap;
import java.util.Map;


/**
 * This is the current implementation of ShoppingCart.
 * Please write a replacement
 */
public class ShoppingCart implements IShoppingCart {
    protected Map<String, Integer> cartItems = new HashMap<>();
    IPricer pricer;
    private Formatter formatter;

    public ShoppingCart(IPricer pricer) {
        this(pricer, new FormatterAmountInitial());
    }

    public ShoppingCart(IPricer pricer, Formatter formatter) {
        this.pricer = pricer;
        this.formatter = formatter;
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

        Currency total = new Currency(0);

        for (String reference: references) {
            Product product = this.pricer.getProduct(reference);
            Integer amount = this.cartItems.get(reference);

            if (product == null) {
                product = new Product(reference, "DISCONTINUED", new Currency(0));
            }

            total = total.add(product.getPrice().times(amount));

            Currency productTotal = product.getPrice().times(amount);
            this.formatter.addProductLine(product, amount, productTotal);
        }

        this.formatter.addTotalLine(total);
    }
}
