package com.xgen.interview.formatter.total;

import com.xgen.interview.currency.Currency;

public class CustomTotalFormatter implements TotalFormatter {
    String initialMessage;
    String totalMessage;
    String finalMessage;

    public CustomTotalFormatter(String initialMessage, String totalMessage, String finalMessage) {
        this.initialMessage = initialMessage;
        this.totalMessage = totalMessage;
        this.finalMessage = finalMessage;
    }

    @Override
    public String format(Currency total) {
        return String.format("%s\n%s %s\n%s",initialMessage, totalMessage, total, finalMessage);
    }
}
