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
            return new Product(reference, "DISCONTINUED", new Currency(0));
        }
        return pricingDatabase.get(reference);
    }
}
