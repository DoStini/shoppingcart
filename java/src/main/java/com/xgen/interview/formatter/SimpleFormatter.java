package com.xgen.interview.formatter;

import com.xgen.interview.formatter.body.AmountBeginningBodyFormatter;
import com.xgen.interview.formatter.total.SimpleTotalFormatter;

public class SimpleFormatter extends Formatter {
    public SimpleFormatter() {
        this.bodyFormatter = new AmountBeginningBodyFormatter();
        this.totalFormatter = new SimpleTotalFormatter();
    }
}
