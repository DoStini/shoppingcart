package com.xgen.interview.formatter;

import com.xgen.interview.Product;
import com.xgen.interview.currency.Currency;
import com.xgen.interview.formatter.body.ReferenceBeginningBodyFormatter;
import com.xgen.interview.formatter.total.CustomTotalFormatter;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class FormatterBuilderTest {

    FormatterBuilder builder = new FormatterBuilder();
    Product appleProduct = new Product("apple", "Apple", new Currency(100));

    @Test
    public void referenceBeginFormatter() {
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        builder.addBodyFormatter(new ReferenceBeginningBodyFormatter());
        Formatter formatter = builder.buildFormatter();

        formatter.addProductLine(appleProduct, 2, appleProduct.getPrice().times(2));

        assertEquals("apple - Apple - 2x €1.00 = €2.00\n", myOut.toString());
    }

    @Test
    public void customTotalFormatter() {
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        builder.addTotalFormatter(
                new CustomTotalFormatter("------------------", "Total:", "Thanks for purchasing at MongoDB")
        );
        Formatter formatter = builder.buildFormatter();

        formatter.addTotalLine(new Currency(100));

        assertEquals("------------------\nTotal: €1.00\nThanks for purchasing at MongoDB\n", myOut.toString());
    }

    @Test
    public void mixedFormatter() {
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        builder.addBodyFormatter(new ReferenceBeginningBodyFormatter());
        builder.addTotalFormatter(
                new CustomTotalFormatter("\n\n------------------", "Final Price:", "Thanks for purchasing at MongoDB")
        );
        Formatter formatter = builder.buildFormatter();


        formatter.addProductLine(appleProduct, 2, appleProduct.getPrice().times(2));
        formatter.addTotalLine(new Currency(100));

        assertEquals("apple - Apple - 2x €1.00 = €2.00\n\n\n------------------\nFinal Price: €1.00\nThanks for purchasing at MongoDB\n", myOut.toString());
    }
}