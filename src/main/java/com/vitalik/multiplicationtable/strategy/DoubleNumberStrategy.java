package com.vitalik.multiplicationtable.strategy;

public class DoubleNumberStrategy implements NumberStrategy<Double> {
    @Override
    public Double parse(String value) {
        return Double.parseDouble(value);
    }

    @Override
    public Double add(Double first, Double second) {
        return first + second;
    }

    @Override
    public Double multiply(Double first, Double second) {
        return first * second;
    }

    @Override
    public int compare(Double first, Double second) {
        return Double.compare(first, second);
    }

    @Override
    public Double zero() {
        return 0.0;
    }

    @Override
    public String format(Double value) {
        return String.valueOf(value);
    }
}
