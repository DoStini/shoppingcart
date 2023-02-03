package com.xgen.interview.formatter;

import com.xgen.interview.formatter.body.BodyFormatter;
import com.xgen.interview.formatter.total.TotalFormatter;

public class FormatterBuilder {
    Formatter formatter = new Formatter();

    void addBodyFormatter(BodyFormatter bodyFormatter) {
        this.formatter.bodyFormatter = bodyFormatter;
    }

    void addTotalFormatter(TotalFormatter totalFormatter) {
        this.formatter.totalFormatter = totalFormatter;
    }

    Formatter buildFormatter() {
        Formatter formatter = this.formatter;
        this.reset();
        return formatter;
    }

    void reset() {
        this.formatter = new Formatter();
    }
}
