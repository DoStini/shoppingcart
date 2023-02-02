package com.xgen.interview;

import com.xgen.interview.currency.Currency;

public class Product {
    String reference;
    private String name;
    private Currency price;

    public Product(String reference, String name, Currency price) {
        this.reference = reference;
        this.name = name;
        this.price = price;
    }

    public String getReference() {
        return reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getPrice() {
        return price;
    }

    public void setPrice(Currency price) {
        this.price = price;
    }
}
