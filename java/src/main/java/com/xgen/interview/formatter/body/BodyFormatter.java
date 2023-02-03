package com.xgen.interview.formatter.body;

import com.xgen.interview.Product;
import com.xgen.interview.currency.Currency;

public interface BodyFormatter {
    String format(Product product, Integer amount, Currency total);
}
