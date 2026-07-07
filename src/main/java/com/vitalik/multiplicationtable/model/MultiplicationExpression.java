package com.vitalik.multiplicationtable.model;

public record MultiplicationExpression(String left, String right, String result) {

    @Override
    public String toString() {
        return left + " * " + right + " = " + result;
    }
}
