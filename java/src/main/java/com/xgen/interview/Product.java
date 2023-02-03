package com.xgen.interview;

import com.xgen.interview.currency.Currency;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!Objects.equals(reference, product.reference)) return false;
        if (!Objects.equals(name, product.name)) return false;
        return Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        int result = reference != null ? reference.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    public void setPrice(Currency price) {
        this.price = price;
    }
}
