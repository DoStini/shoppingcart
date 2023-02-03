package com.xgen.interview.formatter.total;

import com.xgen.interview.currency.Currency;

public class SimpleTotalFormatter implements TotalFormatter {

    @Override
    public String format(Currency total) {
        return String.format("\nTotal: %s", total);
    }
}
