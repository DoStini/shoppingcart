package com.xgen.interview.formatter;

import com.xgen.interview.Product;
import com.xgen.interview.currency.Currency;

public class FormatterAmountInitial implements Formatter {
    @Override
    public void addProductLine(Product product, Integer amount, Currency total) {
        String output = String.format("%dx - %s - %s - %s", amount, product.getReference(), product.getName(), total);

        System.out.println(output);
    }

    @Override
    public void addTotalLine(Currency total) {
        String output = String.format("\nTotal: %s", total);
        System.out.println(output);
    }
}
