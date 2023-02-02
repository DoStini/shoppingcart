package com.xgen.interview.currency;

// TODO: DIFF CURRENCY CLASSES
public class Currency {
    int value;

    public Currency(int value) {
        this.value = value;
    }

    public Currency times(int value) {
        return new Currency(value * this.value);
    }

    @Override
    public String toString() {
        return String.format("€%.2f", (float) this.value / 100);
    }
}