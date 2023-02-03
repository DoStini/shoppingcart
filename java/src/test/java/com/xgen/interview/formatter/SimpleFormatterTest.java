package com.xgen.interview.formatter;

import com.xgen.interview.Product;
import com.xgen.interview.currency.Currency;
import com.xgen.interview.formatter.body.AmountBeginningBodyFormatter;
import com.xgen.interview.formatter.total.SimpleTotalFormatter;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class SimpleFormatterTest {
    Product appleProduct = new Product("apple", "Apple", new Currency(100));
    Product bananaProduct = new Product("banana", "Banana", new Currency(200));

    private Formatter buildSimpleFormatter() {
        FormatterBuilder builder = new FormatterBuilder();

        builder.addBodyFormatter(new AmountBeginningBodyFormatter());
        builder.addTotalFormatter(new SimpleTotalFormatter());

        return builder.buildFormatter();
    }

    @Test
    public void canAddAnItem() {
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        Formatter formatter = buildSimpleFormatter();
        formatter.addProductLine(appleProduct, 1, appleProduct.getPrice());

        assertEquals("1x - apple - Apple - €1.00\n", myOut.toString());
    }

    @Test
    public void canAddMoreThanOneItem() {
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        Formatter formatter = buildSimpleFormatter();
        formatter.addProductLine(appleProduct, 2, appleProduct.getPrice().times(2));

        assertEquals("2x - apple - Apple - €2.00\n", myOut.toString());
    }

    @Test
    public void total() {
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        Formatter formatter = buildSimpleFormatter();

        formatter.addTotalLine(new Currency(400));

        String result = myOut.toString();

        assertEquals("\nTotal: €4.00\n", result);
    }

    @Test
    public void canAddDifferentItems() {
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        Formatter formatter = buildSimpleFormatter();

        formatter.addProductLine(appleProduct, 2, appleProduct.getPrice().times(2));
        formatter.addProductLine(bananaProduct, 1, bananaProduct.getPrice());
        formatter.addTotalLine(new Currency(400));

        String result = myOut.toString();

        assertEquals("2x - apple - Apple - €2.00\n1x - banana - Banana - €2.00\n\nTotal: €4.00\n", result);
    }
}