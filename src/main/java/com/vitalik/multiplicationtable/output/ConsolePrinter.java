package com.vitalik.multiplicationtable.output;

import com.vitalik.multiplicationtable.model.MultiplicationExpression;

import java.util.List;

public final class ConsolePrinter {
    private ConsolePrinter() {
    }

    public static void print(List<MultiplicationExpression> expressions) {
        expressions.forEach(System.out::println);
    }
}