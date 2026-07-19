package com.vitalik.multiplicationtable.strategy;

public class LongNumberStrategy implements NumberStrategy<Long> {
    @Override
    public Long parse(String value) {
        return Long.parseLong(value);
    }

    @Override
    public Long add(Long first, Long second) {
        return first + second;
    }

    @Override
    public Long multiply(Long first, Long second) {
        return first * second;
    }

    @Override
    public int compare(Long first, Long second) {
        return Long.compare(first, second);
    }

    @Override
    public Long zero() {
        return 0L;
    }

    @Override
    public String format(Long value) {
        return String.valueOf(value);
    }
}