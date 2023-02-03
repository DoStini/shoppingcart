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

    public Currency add(int value) {
        return new Currency(value + this.value);
    }

    public Currency add(Currency currency) {
        return add(currency.value);
    }

    @Override
    public String toString() {
        return String.format("€%.2f", (float) this.value / 100);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;

        return value == currency.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
