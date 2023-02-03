package com.xgen.interview.formatter.body;

import com.xgen.interview.Product;
import com.xgen.interview.currency.Currency;

public class ReferenceBeginningBodyFormatter implements BodyFormatter {
    @Override
    public String format(Product product, Integer amount, Currency total) {
        return String.format("%s - %s - %dx %s = %s", product.getReference(), product.getName(), amount, product.getPrice(), total);
    }
}
