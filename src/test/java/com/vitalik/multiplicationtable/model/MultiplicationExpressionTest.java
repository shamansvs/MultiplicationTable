package com.vitalik.multiplicationtable.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiplicationExpressionTest {
    @Test
    void shouldFormatExpressionAsText() {
        MultiplicationExpression expression =
                new MultiplicationExpression("2", "4", "8");

        assertEquals("2 * 4 = 8", expression.toString());
    }
}
