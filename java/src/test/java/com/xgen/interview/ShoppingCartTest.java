package com.xgen.interview;

import com.xgen.interview.currency.Currency;
import com.xgen.interview.formatter.Formatter;
import com.xgen.interview.pricer.IPricer;
import com.xgen.interview.pricer.Pricer;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.inOrder;


public class ShoppingCartTest {
    Product p1 = new Product("apple", "Apple", new Currency(100));
    Product p2 = new Product("banana", "Banana", new Currency(200));


    @Test
    public void canAddOneItem() {
        IPricer pricer = new Pricer();
        pricer.addProduct(p1);
        pricer.addProduct(p2);

        ShoppingCart sc = new ShoppingCart(pricer);

        sc.addItem("apple", 1);
        assertNotNull(sc.cartItems.get("apple"));
        assertEquals(1, sc.cartItems.get("apple").getAmount().intValue());
    }

    @Test
    public void canAddOneItemAndPrint() {
        IPricer pricer = new Pricer();
        pricer.addProduct(p1);
        pricer.addProduct(p2);

        Formatter formatter = Mockito.mock(Formatter.class);

        ShoppingCart sc = new ShoppingCart(pricer, formatter);

        sc.addItem("apple", 1);

        sc.printReceipt();

        Mockito.verify(formatter, Mockito.times(1))
                .addProductLine(p1, 1, p1.getPrice().times(1));
        Mockito.verify(formatter, Mockito.times(1))
                .addTotalLine(p1.getPrice());
    }

    @Test
    public void canAddMoreThanOneItem() {
        IPricer pricer = new Pricer();
        pricer.addProduct(p1);
        pricer.addProduct(p2);

        ShoppingCart sc = new ShoppingCart(pricer);

        sc.addItem("apple", 2);
        assertNotNull(sc.cartItems.get("apple"));
        assertEquals(2, sc.cartItems.get("apple").getAmount().intValue());
        assertEquals(p1, sc.cartItems.get("apple").getProduct());
        assertEquals(p1.getPrice().times(2), sc.cartItems.get("apple").total());
    }

    @Test
    public void canAddMoreThanOneItemAndPrint() {
        IPricer pricer = new Pricer();
        pricer.addProduct(p1);
        pricer.addProduct(p2);

        Formatter formatter = Mockito.mock(Formatter.class);

        ShoppingCart sc = new ShoppingCart(pricer, formatter);

        sc.addItem("apple", 2);

        sc.printReceipt();

        Mockito.verify(formatter, Mockito.times(1))
                .addProductLine(p1, 2, p1.getPrice().times(2));
        Mockito.verify(formatter, Mockito.times(1))
                .addTotalLine(p1.getPrice().times(2));
    }

    @Test
    public void canAddDifferentItems() {
        IPricer pricer = new Pricer();
        pricer.addProduct(p1);
        pricer.addProduct(p2);

        ShoppingCart sc = new ShoppingCart(pricer);

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        assertNotNull(sc.cartItems.get("apple"));
        assertEquals(sc.cartItems.get("apple").getAmount().intValue(), 2);
        assertEquals(p1, sc.cartItems.get("apple").getProduct());
        assertEquals(p1.getPrice().times(2), sc.cartItems.get("apple").total());

        assertNotNull(sc.cartItems.get("banana"));
        assertEquals(1, sc.cartItems.get("banana").getAmount().intValue());
        assertEquals(p2, sc.cartItems.get("banana").getProduct());
        assertEquals(p2.getPrice().times(1), sc.cartItems.get("banana").total());
    }

    @Test
    public void canAddDifferentItemsAndPrint() {
        IPricer pricer = new Pricer();
        pricer.addProduct(p1);
        pricer.addProduct(p2);

        Formatter formatter = Mockito.mock(Formatter.class);

        ShoppingCart sc = new ShoppingCart(pricer, formatter);

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        sc.printReceipt();

        Mockito.verify(formatter, Mockito.times(1))
                .addProductLine(p1, 2, p1.getPrice().times(2));
        Mockito.verify(formatter, Mockito.times(1))
                .addProductLine(p2, 1, p2.getPrice());
        Mockito.verify(formatter, Mockito.times(1))
                .addTotalLine(p1.getPrice().times(2).add(p2.getPrice()));
    }

    @Test
    public void canAddMorePreviousItems() {
        IPricer pricer = new Pricer();
        pricer.addProduct(p1);
        pricer.addProduct(p2);

        ShoppingCart sc = new ShoppingCart(pricer);

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);
        sc.addItem("apple", 4);

        assertNotNull(sc.cartItems.get("apple"));
        assertEquals(6, sc.cartItems.get("apple").getAmount().intValue());
        assertEquals(p1, sc.cartItems.get("apple").getProduct());
        assertEquals(p1.getPrice().times(6), sc.cartItems.get("apple").total());

        assertNotNull(sc.cartItems.get("banana"));
        assertEquals(1, sc.cartItems.get("banana").getAmount().intValue());
        assertEquals(p2, sc.cartItems.get("banana").getProduct());
        assertEquals(p2.getPrice().times(1), sc.cartItems.get("banana").total());
    }

    @Test
    public void canAddMorePreviousItemsAndPrintScanOrder() {
        IPricer pricer = new Pricer();
        pricer.addProduct(p1);
        pricer.addProduct(p2);

        Formatter formatter = Mockito.mock(Formatter.class);
        InOrder verifier = Mockito.inOrder(formatter);

        ShoppingCart sc = new ShoppingCart(pricer, formatter);

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);
        sc.addItem("apple", 4);

        sc.printReceipt();

        verifier.verify(formatter, Mockito.times(1))
                .addProductLine(p1, 6, p1.getPrice().times(6));
        verifier.verify(formatter, Mockito.times(1))
                .addProductLine(p2, 1, p2.getPrice());
        verifier.verify(formatter, Mockito.times(1))
                .addTotalLine(p1.getPrice().times(6).add(p2.getPrice()));
    }

    @Test
    public void doesntExplodeOnMysteryItem() {
        IPricer pricer = new Pricer();

        Formatter formatter = Mockito.mock(Formatter.class);

        ShoppingCart sc = new ShoppingCart(pricer, formatter);

        sc.addItem("crisps", 2);

        sc.printReceipt();

        Mockito.verify(formatter, Mockito.times(1))
                .addProductLine(
                        new Product(
                        "crisps",
                                "DISCONTINUED",
                                new Currency(0)),
                        2, new Currency(0));

        Mockito.verify(formatter, Mockito.times(1))
                .addTotalLine(new Currency(0));
    }
}


