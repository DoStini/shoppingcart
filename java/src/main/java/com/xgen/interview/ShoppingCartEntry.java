package com.xgen.interview;

import com.xgen.interview.currency.Currency;

public class ShoppingCartEntry {
    private Product product;
    private Integer amount;

    public ShoppingCartEntry(Product product, Integer amount) {
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void addAmount(Integer amount) {
        this.amount += amount;
    }

    public Currency total() {
        return product.getPrice().times(amount);
    }
}
