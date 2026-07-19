package com.vitalik.multiplicationtable.strategy;

public class IntNumberStrategy implements NumberStrategy<Integer> {
    @Override
    public Integer parse(String value) {
        return Integer.parseInt(value);
    }

    @Override
    public Integer add(Integer first, Integer second) {
        return first + second;
    }

    @Override
    public Integer multiply(Integer first, Integer second) {
        return first * second;
    }

    @Override
    public int compare(Integer first, Integer second) {
        return Integer.compare(first, second);
    }

    @Override
    public Integer zero() {
        return 0;
    }

    @Override
    public String format(Integer value) {
        return String.valueOf(value);
    }
}
