package com.xgen.interview.pricer;

import com.xgen.interview.Product;
import com.xgen.interview.currency.Currency;

import java.util.HashMap;
import java.util.Map;


/**
 * A stub implementation - for this exercise, you may disregard that this is incomplete.
 */
public class Pricer implements IPricer {
    Map<String, Product> pricingDatabase = new HashMap<>(); // stub

    public Pricer() {
        Product p1 = new Product("apple", "Apple", new Currency(100));
        Product p2 = new Product("banana", "Banana", new Currency(200));

        this.addProduct(p1);
        this.addProduct(p2);
    }

    @Override
    public void addProduct(Product product) {
        if (pricingDatabase.containsKey(product.getReference())) {
            // TODO THROW
            return;
        }
        this.pricingDatabase.put(product.getReference(), product);
    }

    @Override
    public Product getProduct(String reference) {
        if (!pricingDatabase.containsKey(reference)) {
            return null;
        }
        return pricingDatabase.get(reference);
    }
}
