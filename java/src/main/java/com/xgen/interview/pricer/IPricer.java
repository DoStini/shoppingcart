package com.xgen.interview.pricer;

import com.xgen.interview.Product;

public interface IPricer {
    void addProduct(Product product);

    Product getProduct(String reference);
}
