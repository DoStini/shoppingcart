package com.xgen.interview.formatter;

import com.xgen.interview.Product;
import com.xgen.interview.currency.Currency;
import com.xgen.interview.formatter.body.BodyFormatter;
import com.xgen.interview.formatter.total.TotalFormatter;


public class Formatter {
    BodyFormatter bodyFormatter;
    TotalFormatter totalFormatter;

    public void addProductLine(Product product, Integer amount, Currency total) {
        if (bodyFormatter != null) {
            System.out.println(bodyFormatter.format(product, amount, total));
        }
    }

    public void addTotalLine(Currency total) {
        if (totalFormatter != null) {
            System.out.println(totalFormatter.format(total));
        }
    }
}
