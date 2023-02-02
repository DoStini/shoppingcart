package com.xgen.interview.formatter;

import com.xgen.interview.Product;
import com.xgen.interview.currency.Currency;


public interface Formatter {
    void addProductLine(Product product, Integer amount, Currency total);

    void addTotalLine(Currency total);
}
