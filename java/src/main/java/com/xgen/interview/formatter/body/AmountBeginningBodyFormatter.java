package com.xgen.interview.formatter.body;

import com.xgen.interview.Product;
import com.xgen.interview.currency.Currency;

public class AmountBeginningBodyFormatter implements BodyFormatter {
    @Override
    public String format(Product product, Integer amount, Currency total) {
        return String.format("%dx - %s - %s - %s", amount, product.getReference(), product.getName(), total);
    }
}
