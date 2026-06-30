package com.vitalik.multiplicationtable.strategy;

public interface NumberStrategy<T> {
    T parse(String value);

    T add(T first, T second);

    T multiply(T first, T second);

    int compare(T first, T second);

    String format(T value);
}
